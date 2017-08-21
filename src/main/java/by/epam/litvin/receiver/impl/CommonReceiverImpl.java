package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.NewsEntity;
import by.epam.litvin.constant.PageConstant;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.CompetitorDAO;
import by.epam.litvin.dao.impl.*;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CommonReceiver;
import by.epam.litvin.util.NewsFormatter;
import by.epam.litvin.util.Packer;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;

public class CommonReceiverImpl implements CommonReceiver{

    @Override
    public void changeLocale(RequestContent requestContent) throws ReceiverException {
        String locale = requestContent.getRequestParameters().get(LOCALE)[0];
        requestContent.getSessionAttributes().put(LOCALE, locale.toLowerCase());

        JsonObject object = new JsonObject();
        requestContent.setAjaxResult(object);
    }


    @Override
    public void openMainPage(RequestContent requestContent) throws ReceiverException {
        Packer packer = new Packer();
        NewsFormatter newsFormatter = new NewsFormatter();

        TransactionManager handler = new TransactionManager();
        try {
            NewsDAOImpl newsDAO = new NewsDAOImpl();
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            KindOfSportDAOImpl kindOfSportDAO = new KindOfSportDAOImpl();
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            handler.beginTransaction(newsDAO, competitionDAO, kindOfSportDAO, competitorDAO);

            List<Map<String, Object>> kindOfSportList = kindOfSportDAO.findUsingKindsOfSport();
            List<NewsEntity> newsList = newsDAO.find(0, COUNT_NEWS_ON_MAIN_PAGE);

            List<Map<String, Object>> upcomingGames = competitionDAO.findLimitUpcomingGames(0,
                    COUNT_COMPETITIONS_ON_MAIN_PAGE, true);

            List<Map<String, Object>> pastGames = competitionDAO.findLimitPastGames(0,
                            COUNT_COMPETITIONS_ON_MAIN_PAGE, true, true);

            extractCompetitors(upcomingGames, competitorDAO);
            extractCompetitors(pastGames, competitorDAO);

            handler.commit();
            handler.endTransaction();

            Map<String, Map<String, Integer>> kindOfSportListResult =
                    packer.orderKindsOfSport(kindOfSportList);

            newsFormatter.formatNewsforPreview(newsList);

            requestContent.getRequestAttributes().put(NEWS_LIST, newsList);
            requestContent.getRequestAttributes().put(UPCOMING_GAMES, upcomingGames);
            requestContent.getRequestAttributes().put(PAST_GAMES, pastGames);
            requestContent.getSessionAttributes().put("kindsOfSportLeftBar", kindOfSportListResult);
            requestContent.getSessionAttributes().put("newsImagePath", PageConstant.PATH_TO_UPLOAD_NEWS);
            requestContent.getSessionAttributes().put("userImagePath", PageConstant.PATH_TO_UPLOAD_AVATARS);

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
            List<Map<String, Object>> competitors = competitorDAO.findWithCommandByCompetitionId(compId);
            competition.put("competitors", competitors);
        }
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

            requestContent.getRequestAttributes().put("statisticMap", statisticMap);

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
