package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.CommandEntity;
import by.epam.litvin.bean.KindOfSportEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.impl.CommandDAOImpl;
import by.epam.litvin.dao.impl.KindOfSportDAOImpl;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CommandReceiver;
import by.epam.litvin.validator.impl.CommandValidatorImpl;
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

        String[] errorNames = {"wrongName", "duplicateName"};

        for (String name : errorNames) {
            String[] error = requestContent.getRequestParameters().get(name);

            if (error != null && !error[0].isEmpty()) {
                requestContent.getRequestAttributes().put(name, true);
            }
        }

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            KindOfSportDAOImpl kindOfSportDAO = new KindOfSportDAOImpl();
            CommandDAOImpl commandDAO = new CommandDAOImpl();
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
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Open command setting rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void updateCommand(RequestContent requestContent) throws ReceiverException {
        CommandValidatorImpl validator = new CommandValidatorImpl();
        String newName = requestContent.getRequestParameters().get(NEW_NAME)[0].trim();
        String[] stringId = requestContent.getRequestParameters().get(COMMAND_ID);
        int id = Integer.valueOf(stringId[0]);

        CommandEntity command = new CommandEntity();
        command.setId(id);
        command.setName(newName);

        if (!validator.isNameValid(command.getName())) {
            requestContent.setAjaxSuccess(false);
            return;
        }

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CommandDAOImpl commandDAO = new CommandDAOImpl();
            manager.beginTransaction(commandDAO);

            requestContent.setAjaxSuccess(commandDAO.update(command));

            manager.commit();
            manager.endTransaction();

        } catch (DAOException e) {
            if (manager != null) {
                try {
                    manager.rollback();
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Update command rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void createCommand(RequestContent requestContent) throws ReceiverException {
        CommandValidatorImpl validator = new CommandValidatorImpl();
        String[] stringKindOfSportId = requestContent.getRequestParameters().get(KIND_OF_SPORT_ID);
        String commandName = requestContent.getRequestParameters().get("name")[0].trim();
        requestContent.getSessionAttributes().remove(TEMPORARY);

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
            CommandDAOImpl commandDAO = new CommandDAOImpl();
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
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Create command rollback error", e);
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
            CommandDAOImpl commandDAO = new CommandDAOImpl();
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
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Delete command rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void findCommand(RequestContent requestContent) throws ReceiverException {
        String[] stringId = requestContent.getRequestParameters().get(KIND_OF_SPORT_ID);
        int sportId =  Integer.valueOf(stringId[0]);

        Gson gson = new Gson();
        JsonObject object = new JsonObject();

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CommandDAOImpl commandDAO = new CommandDAOImpl();
            manager.beginTransaction(commandDAO);

            JsonElement element = gson.toJsonTree(commandDAO.findBySportId(sportId));

            manager.commit();
            manager.endTransaction();

            object.add("commands", element);
            requestContent.setAjaxResult(object);

        } catch (DAOException e) {
            if (manager != null) {
                try {
                    manager.rollback();
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Find command rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }
    }
}
