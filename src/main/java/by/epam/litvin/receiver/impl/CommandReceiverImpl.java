package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.CommandEntity;
import by.epam.litvin.bean.KindOfSportEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.CommandDAO;
import by.epam.litvin.dao.KindOfSportDAO;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CommandReceiver;
import by.epam.litvin.validator.CommandValidator;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;

public class CommandReceiverImpl implements CommandReceiver {
    @Override
    public void openCommandSetting(RequestContent requestContent) throws ReceiverException {
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
            KindOfSportDAO kindOfSportDAO = new KindOfSportDAO();
            CommandDAO commandDAO = new CommandDAO();
            manager.beginTransaction(kindOfSportDAO, commandDAO);
            List<KindOfSportEntity> kindOfSportList = kindOfSportDAO.findAll();
            List<Map<String, Object>> commandList = commandDAO.findAllWithKindOfSport();
            manager.commit();
            manager.endTransaction();

            requestContent.getRequestAttributes().put("kindsOfSport", kindOfSportList);
            requestContent.getRequestAttributes().put("commands", commandList);

        } catch (DAOException e) {
            if (manager != null) {
                try {
                    manager.rollback();
                } catch (DAOException e1) {
                    throw new ReceiverException("Rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void updateCommand(RequestContent requestContent) throws ReceiverException {
        CommandValidator validator = new CommandValidator();
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        String newName = requestContent.getRequestParameters().get(NEW_NAME)[0].trim();
        String[] stringId = requestContent.getRequestParameters().get(COMMAND_ID);
        int id = Integer.valueOf(stringId[0]);

        CommandEntity command = new CommandEntity();
        command.setId(id);
        command.setName(newName);

        if (!validator.isNameValid(command.getName())) {
            JsonElement element = gson.toJsonTree(false);
            object.add(SUCCESS, element);
            requestContent.setAjaxResult(object);
            return;
        }

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CommandDAO commandDAO = new CommandDAO();
            manager.beginTransaction(commandDAO);

            JsonElement element = gson.toJsonTree(commandDAO.update(command));

            manager.commit();
            manager.endTransaction();

            object.add(SUCCESS, element);
            requestContent.setAjaxResult(object);

        } catch (DAOException e) {
            if (manager != null) {
                try {
                    manager.rollback();
                } catch (DAOException e1) {
                    throw new ReceiverException("Rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void createCommand(RequestContent requestContent) throws ReceiverException {
        CommandValidator validator = new CommandValidator();
        String[] stringKindOfSportId = requestContent.getRequestParameters().get(KIND_OF_SPORT_ID);
        String commandName = requestContent.getRequestParameters().get("name")[0].trim();

        int kindOfSportId = Integer.valueOf(stringKindOfSportId[0]);

        Map<String, Object> data = new HashMap<>();

        if (!validator.isNameValid(commandName)) {
            data.put("wrongName", true);
            requestContent.getSessionAttributes().put(TEMPORARY, data);
            return;
        }

        CommandEntity command = new CommandEntity();
        command.setName(commandName);
        command.setKindOfSportId(kindOfSportId);

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CommandDAO commandDAO = new CommandDAO();
            manager.beginTransaction(commandDAO);

            boolean isCreated = commandDAO.create(command);

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
                } catch (DAOException e1) {
                    throw new ReceiverException("Rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void deleteCommand(RequestContent requestContent) throws ReceiverException {
        String[] stringId = requestContent.getRequestParameters().get(COMMAND_ID);
        int commandId =  Integer.valueOf(stringId[0]);

        Gson gson = new Gson();
        JsonObject object = new JsonObject();

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CommandDAO commandDAO = new CommandDAO();
            manager.beginTransaction(commandDAO);

            JsonElement element = gson.toJsonTree(commandDAO.delete(commandId));

            manager.commit();
            manager.endTransaction();

            object.add(SUCCESS, element);
            requestContent.setAjaxResult(object);

        } catch (DAOException e) {
            if (manager != null) {
                try {
                    manager.rollback();
                } catch (DAOException e1) {
                    throw new ReceiverException("Rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }
    }
}
