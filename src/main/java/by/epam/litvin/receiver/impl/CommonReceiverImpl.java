package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.NewsEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.impl.CompetitionDAOImpl;
import by.epam.litvin.dao.impl.KindOfSportDAOImpl;
import by.epam.litvin.dao.impl.NewsDAOImpl;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CommonReceiver;
import by.epam.litvin.util.NewsFormatter;
import by.epam.litvin.util.Packer;
import com.google.gson.JsonObject;

import java.io.File;
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
        TransactionManager handler = null;
        try {
            handler = new TransactionManager();
            NewsDAOImpl newsDAO = new NewsDAOImpl();
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            KindOfSportDAOImpl kindOfSportDAO = new KindOfSportDAOImpl();
            String newsImagePath = "/image/news";


            handler.beginTransaction(newsDAO, competitionDAO, kindOfSportDAO);

            List<NewsEntity> newsList = newsDAO.find(0, COUNT_NEWS_ON_MAIN_PAGE);
            List<Map<String, Object>> upcommingGameList =
                    competitionDAO.findUpcomingCompetitions(0, COUNT_COMPETITIONS_ON_MAIN_PAGE);
            List<Map<String, Object>> pastGameList =
                    competitionDAO.findPastCompetitions(0, COUNT_COMPETITIONS_ON_MAIN_PAGE);
            List<Map<String, Object>> kindOfSportList = kindOfSportDAO.findUsingKindsOfSport();

            handler.commit();
            handler.endTransaction();

            Packer packer = new Packer();
            NewsFormatter newsFormatter = new NewsFormatter();

            /*List<Map<String, Object>> orderedUpcomingGameList =
                    packer.orderLiveAndUpcomingGames(upcommingGameList);
            List<Map<String, Object>> orderedPastGameList =
                    packer.orderPastGames(pastGameList);*/
            Map<String, Map<String, Integer>> kindOfSportListResult =
                    packer.orderKindsOfSport(kindOfSportList);

            newsFormatter.formatNewsforPreview(newsList);

            requestContent.getRequestAttributes().put(NEWS_LIST, newsList);
            /*requestContent.getRequestAttributes().put(UPCOMING_GAMES, orderedUpcomingGameList);
            requestContent.getRequestAttributes().put(PAST_GAMES, orderedPastGameList);*/
            requestContent.getSessionAttributes().put("kindsOfSportLeftBar", kindOfSportListResult);
            requestContent.getRequestAttributes().put("newsImagePath", newsImagePath);

        } catch (DAOException e) {
            if (handler != null) {
                try {
                    handler.rollback();
                    handler.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Open main page tollback error", e);
                }
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void openMainAdminPage(RequestContent requestContent) throws ReceiverException {

    }
}
