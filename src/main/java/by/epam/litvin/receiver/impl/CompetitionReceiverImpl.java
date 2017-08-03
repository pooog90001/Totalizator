package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.KindOfSportEntity;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.CompetitionDAO;
import by.epam.litvin.dao.KindOfSportDAO;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CompetitionReceiver;
import by.epam.litvin.util.Packer;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;

public class CompetitionReceiverImpl implements CompetitionReceiver {

    @Override
    public void getLiveCompetitions(RequestContent requestContent) throws ReceiverException {
        String[] stringId = requestContent.getRequestParameters().get(KIND_OF_SPORT_ID);

        int kindOfSportId = (stringId != null) ? Integer.valueOf(stringId[0]) : 0;

        TransactionManager handler = null;
        try {
            handler = new TransactionManager();
            CompetitionDAO competitionDAO = new CompetitionDAO();
            KindOfSportDAO kindOfSportDAO = new KindOfSportDAO();
            handler.beginTransaction(competitionDAO, kindOfSportDAO);

            List<Map<String, Object>> liveGameList = (kindOfSportId != 0) ?
                    competitionDAO.findFilteredLiveCompetitions(kindOfSportId) :
                    competitionDAO.findAllLiveCompetitions();

            List<KindOfSportEntity> kindOfSportList = kindOfSportDAO.findLiveGamesKindsOfSport();

            handler.commit();
            handler.endTransaction();

            Packer packer = new Packer();


            List<Map<String, Object>> orderedLiveGameList = packer.orderLiveAndUpcomingGames(liveGameList);

            requestContent.getRequestAttributes().put(LIVE_GAMES, orderedLiveGameList);
            requestContent.getRequestAttributes().put(KINDS_OF_SPORT_LIST, kindOfSportList);
            requestContent.getRequestAttributes().put(CURRENT_ID , kindOfSportId);


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

    public void filterLiveCompetitions(RequestContent requestContent) throws ReceiverException {
        String[] stringId = requestContent.getRequestParameters().get(KIND_OF_SPORT_ID);

        int kindOfSportId = (stringId != null) ? Integer.valueOf(stringId[0]) : 0;
        Gson gson = new Gson();
        JsonObject object = new JsonObject();

        TransactionManager handler = null;
        try {
            handler = new TransactionManager();
            CompetitionDAO competitionDAO = new CompetitionDAO();

            handler.beginTransaction(competitionDAO);

            List<Map<String, Object>> liveGameList = (kindOfSportId != 0) ?
                    competitionDAO.findFilteredLiveCompetitions(kindOfSportId) :
                    competitionDAO.findAllLiveCompetitions();


            handler.commit();
            handler.endTransaction();

            Packer packer = new Packer();

            List<Map<String, Object>> orderedLiveGameList = packer.orderLiveAndUpcomingGames(liveGameList);

            JsonElement element = gson.toJsonTree(orderedLiveGameList);
            object.add(LIVE_GAMES, element);
            requestContent.setAjaxResult(object);

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
    public void getUpcomingCompetition(RequestContent requestContent) {

    }

    @Override
    public void getPastCompetition(RequestContent requestContent) {

    }
}
