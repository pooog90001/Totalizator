package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.KindOfSportEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.impl.KindOfSportDAOImpl;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.KindOfSportReceiver;
import by.epam.litvin.validator.impl.KindOfSportValidatorImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;

public class KindOfSportReceiverImpl implements KindOfSportReceiver {

    @Override
    public void openKindOfSportSetting(RequestContent requestContent) throws ReceiverException {

        String[] wrongName = requestContent.getRequestParameters().get("wrongName");
        String[] duplicateName = requestContent.getRequestParameters().get("duplicateName");
        String[] wrongCount = requestContent.getRequestParameters().get("wrongCount");

        if (wrongName != null && !wrongName[0].isEmpty()) {
            requestContent.getRequestAttributes().put("wrongName", true);
        }
        if (duplicateName != null && !duplicateName[0].isEmpty()) {
            requestContent.getRequestAttributes().put("duplicateName", true);
        }
        if (wrongCount != null && !wrongCount[0].isEmpty()) {
            requestContent.getRequestAttributes().put("wrongCount", true);
        }

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            KindOfSportDAOImpl kindOfSportDAO = new KindOfSportDAOImpl();
            manager.beginTransaction(kindOfSportDAO);
            List<KindOfSportEntity> kindOfSportList = kindOfSportDAO.findAll();
            manager.commit();
            manager.endTransaction();

            requestContent.getRequestAttributes().put("kindOfSportList", kindOfSportList);

        } catch (DAOException e) {
            if (manager != null) {
                try {
                    manager.rollback();
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void updateKindOfSport(RequestContent requestContent) throws ReceiverException {
        KindOfSportValidatorImpl validator = new KindOfSportValidatorImpl();
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        String newName = requestContent.getRequestParameters().get("newName")[0].trim();
        String[] stringId = requestContent.getRequestParameters().get(KIND_OF_SPORT_ID);
        int id = Integer.valueOf(stringId[0]);

        KindOfSportEntity kindOfSport = new KindOfSportEntity();
        kindOfSport.setId(id);
        kindOfSport.setName(newName);

        if (!validator.isNameValid(kindOfSport.getName())) {
            JsonElement element = gson.toJsonTree(false);
            object.add(SUCCESS, element);
            requestContent.setAjaxResult(object);
            return;
        }

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            KindOfSportDAOImpl kindOfSportDAO = new KindOfSportDAOImpl();
            manager.beginTransaction(kindOfSportDAO);

            JsonElement element = gson.toJsonTree(kindOfSportDAO.update(kindOfSport));

            manager.commit();
            manager.endTransaction();

            object.add(SUCCESS, element);
            requestContent.setAjaxResult(object);

        } catch (DAOException e) {
            if (manager != null) {
                try {
                    manager.rollback();
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void createKindOfSport(RequestContent requestContent) throws ReceiverException {
        KindOfSportValidatorImpl validator = new KindOfSportValidatorImpl();
        String[] stringCompetitorsCount = requestContent.getRequestParameters().get("count");
        String kindOfSportName = requestContent.getRequestParameters().get("name")[0].trim();
        requestContent.getSessionAttributes().remove(TEMPORARY);

        int competitorsCount = Integer.valueOf(stringCompetitorsCount[0]);

        Map<String, Object> data = new HashMap<>();

        if (!validator.isNameValid(kindOfSportName)) {
            data.put("wrongName", true);
            requestContent.getSessionAttributes().put(TEMPORARY, data);
            return;

        } else if (!validator.isCompetitorsCountValid(competitorsCount)) {
            data.put("wrongCount", true);
            requestContent.getSessionAttributes().put(TEMPORARY, data);
            return;
        }
        KindOfSportEntity kindOfSport = new KindOfSportEntity();
        kindOfSport.setName(kindOfSportName);
        kindOfSport.setCompetitorCount(competitorsCount);

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            KindOfSportDAOImpl kindOfSportDAO = new KindOfSportDAOImpl();
            manager.beginTransaction(kindOfSportDAO);

            boolean isCreated = kindOfSportDAO.create(kindOfSport);

            manager.commit();
            manager.endTransaction();

            if (!isCreated) {
                data.put("duplicateName", true);
                requestContent.getSessionAttributes().put(TEMPORARY, data);
            }

        } catch (DAOException e) {
            if (manager != null) {
                try {
                    manager.rollback();
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }

    }

    @Override
    public void deleteKindOfSport(RequestContent requestContent) throws ReceiverException {
        String[] stringId = requestContent.getRequestParameters().get("kindOfSportId");
        int kindOfSportId =  Integer.valueOf(stringId[0]);

        Gson gson = new Gson();
        JsonObject object = new JsonObject();

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            KindOfSportDAOImpl kindOfSportDAO = new KindOfSportDAOImpl();
            manager.beginTransaction(kindOfSportDAO);

            JsonElement element = gson.toJsonTree(kindOfSportDAO.delete(kindOfSportId));

            manager.commit();
            manager.endTransaction();

            object.add(SUCCESS, element);
            requestContent.setAjaxResult(object);

        } catch (DAOException e) {
            if (manager != null) {
                try {
                    manager.rollback();
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }
    }

}
