package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.NewsEntity;
import by.epam.litvin.bean.UserEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.CompetitionDAO;
import by.epam.litvin.dao.KindOfSportDAO;
import by.epam.litvin.dao.NewsDAO;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CommonReceiver;
import by.epam.litvin.type.UserType;
import by.epam.litvin.util.NewsFormatter;
import by.epam.litvin.util.Packer;
import by.epam.litvin.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;

public class CommonReceiverImpl implements CommonReceiver{
    private static final Logger LOGGER = LogManager.getLogger();



    @Override
    public void changeLocale(RequestContent requestContent) throws ReceiverException {
        String locale = requestContent.getRequestParameters().get(LOCALE)[0];
        requestContent.getSessionAttributes().put(LOCALE, locale.toLowerCase());
    }


    @Override
    public void openMainPage(RequestContent requestContent) throws ReceiverException {
        TransactionManager handler = null;
        try {
            handler = new TransactionManager();
            NewsDAO newsDAO = new NewsDAO();
            CompetitionDAO competitionDAO = new CompetitionDAO();
            KindOfSportDAO kindOfSportDAO = new KindOfSportDAO();

            handler.beginTransaction(newsDAO, competitionDAO, kindOfSportDAO);

            List<NewsEntity> newsList = newsDAO.find(0, COUNT_NEWS_ON_MAIN_PAGE);
            List<Map<String, Object>> liveGameList =
                    competitionDAO.findLiveCompetitions(0, COUNT_COMPETITIONS_ON_MAIN_PAGE);
            List<Map<String, Object>> upcommingGameList =
                    competitionDAO.findUpcomingCompetitions(0, COUNT_COMPETITIONS_ON_MAIN_PAGE);
            List<Map<String, Object>> pastGameList =
                    competitionDAO.findPastCompetitions(0, COUNT_COMPETITIONS_ON_MAIN_PAGE);
            List<Map<String, Object>> kindOfSportList = kindOfSportDAO.findUsingKindsOfSport();

            handler.commit();
            handler.endTransaction();

            Packer packer = new Packer();
            NewsFormatter newsFormatter = new NewsFormatter();

            List<Map<String, Object>> orderedLiveGameList =
                    packer.orderLiveAndUpcomingGames(liveGameList);
            List<Map<String, Object>> orderedUpcomingGameList =
                    packer.orderLiveAndUpcomingGames(upcommingGameList);
            List<Map<String, Object>> orderedPastGameList =
                    packer.orderPastGames(pastGameList);
            Map<String, Map<String, Integer>> kindOfSportListResult =
                    packer.orderKindsOfSport(kindOfSportList);

            newsFormatter.formatNewsforPreview(newsList);

            requestContent.getRequestAttributes().put(NEWS_LIST, newsList);
            requestContent.getRequestAttributes().put(LIVE_GAMES, orderedLiveGameList);
            requestContent.getRequestAttributes().put(UPCOMING_GAMES, orderedUpcomingGameList);
            requestContent.getRequestAttributes().put(PAST_GAMES, orderedPastGameList);
            requestContent.getSessionAttributes().put("kindsOfSportLeftBar", kindOfSportListResult);

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
    public void openMainAdminPage(RequestContent requestContent) throws ReceiverException {

    }
}
