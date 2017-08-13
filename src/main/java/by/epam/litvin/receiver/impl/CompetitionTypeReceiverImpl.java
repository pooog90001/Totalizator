package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.CompetitionTypeEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.impl.CompetitionTypeDAOImpl;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CompetitonTypeReceiver;
import by.epam.litvin.validator.impl.CompetitionTypeValidatorImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;
import static by.epam.litvin.constant.GeneralConstant.TEMPORARY;

public class CompetitionTypeReceiverImpl implements CompetitonTypeReceiver {
    @Override
    public void openCompetitionTypeSetting(RequestContent requestContent) throws ReceiverException {
        String[] wrongName = requestContent.getRequestParameters().get("wrongName");
        String[] duplicateName = requestContent.getRequestParameters().get("duplicateName");

        if (wrongName != null && !wrongName[0].isEmpty()) {
            requestContent.getRequestAttributes().put("wrongName", true);
        }
        if (duplicateName != null && !duplicateName[0].isEmpty()) {
            requestContent.getRequestAttributes().put("duplicateName", true);
        }

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CompetitionTypeDAOImpl competitionTypeDAO = new CompetitionTypeDAOImpl();
            manager.beginTransaction( competitionTypeDAO);
            List<CompetitionTypeEntity> typesList = competitionTypeDAO.findAll();
            manager.commit();
            manager.endTransaction();

            requestContent.getRequestAttributes().put("competitionTypes", typesList);

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
    public void updateCompetitionType(RequestContent requestContent) throws ReceiverException {
        CompetitionTypeValidatorImpl validator = new CompetitionTypeValidatorImpl();
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        String newName = requestContent.getRequestParameters().get(NEW_NAME)[0].trim();
        String[] stringId = requestContent.getRequestParameters().get("competitionTypeId");
        int id = Integer.valueOf(stringId[0]);

        CompetitionTypeEntity type = new CompetitionTypeEntity();
        type.setId(id);
        type.setName(newName);

        if (!validator.isNameValid(type.getName())) {
            JsonElement element = gson.toJsonTree(false);
            object.add(SUCCESS, element);
            requestContent.setAjaxResult(object);
            return;
        }

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CompetitionTypeDAOImpl typeDAO = new CompetitionTypeDAOImpl();
            manager.beginTransaction(typeDAO);

            JsonElement element = gson.toJsonTree(typeDAO.update(type));

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
    public void createCompetitionType(RequestContent requestContent) throws ReceiverException {
        CompetitionTypeValidatorImpl validator = new CompetitionTypeValidatorImpl();
        String typeName = requestContent.getRequestParameters().get("name")[0].trim();
        requestContent.getSessionAttributes().remove(TEMPORARY);


        Map<String, Object> data = new HashMap<>();

        if (!validator.isNameValid(typeName)) {
            data.put("wrongName", true);
            requestContent.getSessionAttributes().put(TEMPORARY, data);
            return;
        }

        CompetitionTypeEntity type = new CompetitionTypeEntity();
        type.setName(typeName);

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CompetitionTypeDAOImpl typeDAO = new CompetitionTypeDAOImpl();
            manager.beginTransaction(typeDAO);

            boolean isCreated = typeDAO.create(type);

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
    public void deleteCompetitionType(RequestContent requestContent) throws ReceiverException {
        String[] stringId = requestContent.getRequestParameters().get("competitionTypeId");
        int typeId =  Integer.valueOf(stringId[0]);

        JsonObject object = new JsonObject();

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CompetitionTypeDAOImpl typeDAO = new CompetitionTypeDAOImpl();
            manager.beginTransaction(typeDAO);

            JsonElement element = new Gson().toJsonTree(typeDAO.delete(typeId));

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
