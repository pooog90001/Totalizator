package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.NewsEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.*;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.PageReceiver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PageReceiverImpl implements PageReceiver {

    @Override
    public void openMainPage(RequestContent requestContent) throws ReceiverException {
        TransactionManager handler = null;
        try {
            handler = new TransactionManager();
            NewsDAO newsDAO = new NewsDAO();
            GameDAO gameDAO = new GameDAO();
            KindOfSportDAO kindOfSportDAO = new KindOfSportDAO();

            handler.beginTransaction(newsDAO, gameDAO, kindOfSportDAO);

            List<NewsEntity> newsList = newsDAO.find(0, 3);
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

            for (NewsEntity news: newsList) {
                if (news.getText().length() > 100) {
                    news.setText(news.getText().substring(0, 100).concat("..."));
                }
            }

            requestContent.getRequestAttributes().put("newsList", newsList);
            requestContent.getRequestAttributes().put("liveGames", liveGamesList);
            requestContent.getRequestAttributes().put("upcommingGames", upcommingGamesList);
            requestContent.getRequestAttributes().put("pastGames", pastGamesList);
            requestContent.getSessionAttributes().put("kindsOfSport", kindOfSportListResult);

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
    public void openAllNewsPage(RequestContent requestContent) throws ReceiverException {

        String[] page = requestContent.getRequestParameters().get("pageNumber");

        int startIndex = (page != null) ? Integer.valueOf(page[0]) : 1;
        startIndex = (startIndex - 1) * 2;

        TransactionManager handler = null;
        try {
            handler = new TransactionManager();
            NewsDAO newsDAO = new NewsDAO();
            handler.beginTransaction(newsDAO);
            List<NewsEntity> newsList = newsDAO.find(startIndex, 2);
            int newsCount = newsDAO.findNewsCount();
            handler.commit();
            handler.endTransaction();

            for (NewsEntity news: newsList) {
                if (news.getText().length() > 100) {
                    news.setText(news.getText().substring(0, 100).concat("..."));
                }
            }

            requestContent.getRequestAttributes().put("newsList", newsList);
            requestContent.getRequestAttributes().put("limit", 2);
            requestContent.getRequestAttributes().put("newsCount", newsCount);

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
    public void openConcreteNewsPage(RequestContent requestContent) throws ReceiverException {
        String[] newsIdString = requestContent.getRequestParameters().get("newsId");
        int newsId = Integer.valueOf(newsIdString[0]);

        TransactionManager handler = null;
        try {
            handler = new TransactionManager();
            NewsDAO newsDAO = new NewsDAO();
            CommentDAO commentDAO = new CommentDAO();
            handler.beginTransaction(newsDAO, commentDAO);
            NewsEntity news = newsDAO.findEntityById(newsId);

            if (news == null) {
                requestContent.getRequestAttributes().put("wrongNews", new Object());
                handler.commit();
                handler.endTransaction();
                return;
            }

            List<Map<String, Object>> newsCommentList = commentDAO.findNewsComments(newsId);
            handler.commit();
            handler.endTransaction();

            requestContent.getRequestAttributes().put("newsCommentList", newsCommentList);
            requestContent.getRequestAttributes().put("attrNews", news);

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
}
