package by.epam.totalizator.receiver.impl;

import by.epam.totalizator.bean.CompetitionTypeEntity;
import by.epam.totalizator.bean.KindOfSportEntity;
import by.epam.totalizator.bean.NewsEntity;
import by.epam.totalizator.bean.UserEntity;
import by.epam.totalizator.constant.SQLFieldConstant;
import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.dao.TransactionManager;
import by.epam.totalizator.dao.impl.*;
import by.epam.totalizator.exception.DAOException;
import by.epam.totalizator.exception.ReceiverException;
import by.epam.totalizator.receiver.CommonReceiver;
import by.epam.totalizator.type.MailType;
import by.epam.totalizator.type.UploadType;
import by.epam.totalizator.util.Formatter;
import by.epam.totalizator.util.MailSender;
import by.epam.totalizator.util.Packer;
import by.epam.totalizator.validator.impl.CommonValidatorImpl;
import by.epam.totalizator.validator.impl.UserValidatorImpl;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.totalizator.constant.GeneralConstant.*;
import static by.epam.totalizator.constant.RequestNameConstant.EMAIL;
import static javax.servlet.jsp.PageContext.SESSION;

public class CommonReceiverImpl implements CommonReceiver {

    @Override
    public void sendQuestionEmail(RequestContent content) throws ReceiverException {
        UserValidatorImpl userValidator = new UserValidatorImpl();
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        HashMap<String, Object> data = new HashMap<>();
        String[] emailArr = content.getRequestParameters().get(EMAIL);
        String[] textArr = content.getRequestParameters().get(TEXT);

        if (commonValidator.isVarExist(emailArr) &&
                commonValidator.isVarExist(textArr) &&
                userValidator.checkEmail(emailArr[0])) {

            MailSender sender = new MailSender(emailArr[0], textArr[0], MailType.EMAIL.getValue());
            sender.start();
            data.put(SUCCESS, true);
            content.getSessionAttributes().put(TEMPORARY, data);

        } else {
            data.put(WRONG_DATA, true);
            content.getSessionAttributes().put(TEMPORARY, data);
        }


    }


    @Override
    public void changeLocale(RequestContent requestContent) throws ReceiverException {
        String locale = requestContent.getRequestParameters().get(LOCALE)[0];
        requestContent.getSessionAttributes().put(LOCALE, locale.toLowerCase());

        JsonObject object = new JsonObject();
        requestContent.setAjaxResult(object);
    }


    @Override
    public void openMainPage(RequestContent content) throws ReceiverException {
        Packer packer = new Packer();
        Formatter newsFormatter = new Formatter();
        UserEntity user = (UserEntity)  content.getSessionAttributes().get(USER);

        TransactionManager handler = new TransactionManager();
        try {
            NewsDAOImpl newsDAO = new NewsDAOImpl();
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            KindOfSportDAOImpl kindOfSportDAO = new KindOfSportDAOImpl();
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            UserDAOImpl userDAO = new UserDAOImpl();
            handler.beginTransaction(newsDAO, competitionDAO,
                    kindOfSportDAO, competitorDAO, userDAO);

            List<Map<String, Object>> kindOfSportList = kindOfSportDAO.findUsingKindsOfSport();
            List<NewsEntity> newsList = newsDAO.findLimit(0, COUNT_NEWS_ON_MAIN_PAGE);

            List<Map<String, Object>> upcomingGames = competitionDAO.findLimitUpcomingGames(0,
                    COUNT_COMPETITIONS_ON_MAIN_PAGE, true);

            List<Map<String, Object>> pastGames = competitionDAO.findLimitPastGames(0,
                            COUNT_COMPETITIONS_ON_MAIN_PAGE, true, true);

            extractCompetitors(upcomingGames, competitorDAO);
            extractCompetitors(pastGames, competitorDAO);

            if (user != null) {
                user = userDAO.findEntityById(user.getId());
            }
            handler.commit();
            handler.endTransaction();

            Map<KindOfSportEntity, List<CompetitionTypeEntity>> kindsOfSportResult =
                    packer.orderKindsOfSport(kindOfSportList);

            newsFormatter.formatNewsForPreview(newsList);
            content.getSessionAttributes().put(SESSION, true);
            content.getRequestAttributes().put(NEWS_LIST, newsList);
            content.getRequestAttributes().put(UPCOMING_GAMES, upcomingGames);
            content.getRequestAttributes().put(PAST_GAMES, pastGames);
            content.getSessionAttributes().put(KINDS_OF_SPORT_LEFT_BAR, kindsOfSportResult);
            content.getSessionAttributes().put(NEWS_IMAGE_PATH, UploadType.NEWS.getUploadFolder());
            content.getSessionAttributes().put(USER_IMAGE_PATH, UploadType.AVATARS.getUploadFolder());
            content.getSessionAttributes().put(USER, user);

        } catch (DAOException e) {
            try {
                handler.rollback();
                handler.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Open main page rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    private void extractCompetitors(List<Map<String, Object>> competitions,
                                         CompetitorDAOImpl competitorDAO) throws DAOException {

        for (Map<String, Object> competition : competitions) {
            int compId = (int) competition.get(SQLFieldConstant.Competition.ID);
            List<Map<String, Object>> competitors = competitorDAO.findWithTeamByGameId(compId);
            competition.put(COMPETITORS, competitors);
        }
    }

    @Override
    public void openNotFoundPage(RequestContent requestContent) throws ReceiverException {
    }

    @Override
    public void openAdminStatistic(RequestContent requestContent) throws ReceiverException {

        TransactionManager manager = new TransactionManager();
        try {
            CommonDAOImpl commonDAO = new CommonDAOImpl();
            manager.beginTransaction(commonDAO);

            Map<String, Object> statisticMap = commonDAO.findAdminStatistic();

            manager.commit();
            manager.endTransaction();

            requestContent.getRequestAttributes().put(STATISTIC_MAP, statisticMap);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Open admin main page rollback error", e);
            }
            throw new ReceiverException(e);
        }


    }
}
