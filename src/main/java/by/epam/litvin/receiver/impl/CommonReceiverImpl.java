package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.News;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.GameDAO;
import by.epam.litvin.dao.KindOfSportDAO;
import by.epam.litvin.dao.NewsDAO;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CommonReceiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class CommonReceiverImpl implements CommonReceiver{
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void openMainPage(RequestContent requestContent) throws ReceiverException {
        TransactionManager handler = null;
        try {
            handler = new TransactionManager();
            NewsDAO newsDAO = new NewsDAO();
            GameDAO gameDAO = new GameDAO();
            KindOfSportDAO kindOfSportDAO = new KindOfSportDAO();

            handler.beginTransaction(newsDAO, gameDAO, kindOfSportDAO);

            List<News> newsList = newsDAO.find(3);
            List<Map<String, Object>> liveGamesList = gameDAO.findLiveGames(10);
            List<Map<String, Object>> upcommingGamesList = gameDAO.findUpcommingGames(10);
            List<Map<String, Object>> pastGamesList = gameDAO.findPastGames(10);
            List<Map<String, Object>> kindOfSportList = kindOfSportDAO.findUsingKindsOfSport();

            handler.commit();
            handler.endTransaction();

            Map<String, Map<String, Integer>> kindOfSportListResult = new HashMap<>();

            for (Map<String,Object> kind :  kindOfSportList) {
                String kindOfSportName = (String) kind.get("kind_of_sport_name");
                boolean hasCoincidence = false;
                Iterator<String> kindOfSportIterator = kindOfSportListResult.keySet().iterator();

                while (kindOfSportIterator.hasNext()) {
                    if (kindOfSportIterator.next().equals(kindOfSportName)) {
                        String competitionType = (String) kind.get("competition_type_name");
                        Integer competition_type_id = (Integer) kind.get("competition_type_id");
                        kindOfSportListResult.get(kindOfSportName).put(competitionType, competition_type_id);
                        hasCoincidence = true;
                        break;
                    }
                }

                if (!hasCoincidence) {
                    String competitionTypeName = (String) kind.get("competition_type_name");
                    Integer competitionTypeId = (Integer) kind.get("competition_type_id");
                    HashMap<String, Integer> competitionTypeMap = new HashMap<>();
                    competitionTypeMap.put(competitionTypeName, competitionTypeId);
                    kindOfSportListResult.put(kindOfSportName, competitionTypeMap);
                }
            }

            for (News news: newsList) {
                if (news.getText().length() > 100) {
                    news.setText(news.getText().substring(0, 100).concat("..."));
                }
            }

            requestContent.getRequestAttributes().put("newsList", newsList);
            requestContent.getRequestAttributes().put("liveGames", liveGamesList);
            requestContent.getRequestAttributes().put("upcommingGames", upcommingGamesList);
            requestContent.getRequestAttributes().put("pastGames", pastGamesList);
            requestContent.getRequestAttributes().put("kindsOfSport", kindOfSportListResult);

        } catch (DAOException e) {
            if (handler != null) {
                try {
                    handler.rollback();
                } catch (DAOException e1) {
                    throw new ReceiverException("Rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void changeLocale(RequestContent requestContent) throws ReceiverException {
        String locale = requestContent.getRequestParameters().get("locale")[0];
        requestContent.getSessionAttributes().put("locale", locale.toLowerCase());
    }
}
