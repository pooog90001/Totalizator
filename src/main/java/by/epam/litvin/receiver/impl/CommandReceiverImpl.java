package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.CommandEntity;
import by.epam.litvin.bean.KindOfSportEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.dao.impl.CommandDAOImpl;
import by.epam.litvin.dao.impl.KindOfSportDAOImpl;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CommandReceiver;
import by.epam.litvin.validator.impl.CommandValidatorImpl;
import by.epam.litvin.validator.impl.CommonValidatorImpl;
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
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        String[] errorNames = {WRONG_DATA, DUPLICATE_NAME};

        for (String name : errorNames) {
            String[] error = requestContent.getRequestParameters().get(name);

            if (commonValidator.isVarExist(error)) {
                requestContent.getRequestAttributes().put(name, true);
            }
        }

        TransactionManager manager = new TransactionManager();
        try {
            KindOfSportDAOImpl kindOfSportDAO = new KindOfSportDAOImpl();
            CommandDAOImpl commandDAO = new CommandDAOImpl();
            manager.beginTransaction(kindOfSportDAO, commandDAO);
            List<KindOfSportEntity> kindOfSportList = kindOfSportDAO.findAll();
            List<Map<String, Object>> commandList = commandDAO.findAllWithKindOfSport();
            manager.commit();
            manager.endTransaction();

            requestContent.getRequestAttributes().put(KINDS_OF_SPORT, kindOfSportList);
            requestContent.getRequestAttributes().put(COMMANDS, commandList);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Open command setting rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void updateCommand(RequestContent requestContent) throws ReceiverException {
        CommandValidatorImpl validator = new CommandValidatorImpl();
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        String[] newNameArr = requestContent.getRequestParameters().get(NEW_NAME);
        String[] stringId = requestContent.getRequestParameters().get(COMMAND_ID);

        if (!commonValidator.isVarExist(newNameArr) || !commonValidator.isVarExist(stringId) ||
                !validator.isNameValid(newNameArr[0].trim())) {
            requestContent.setAjaxSuccess(false);
            return;
        }

        CommandEntity command = new CommandEntity();
        command.setId(Integer.valueOf(stringId[0]));
        command.setName(newNameArr[0].trim());

        TransactionManager manager = new TransactionManager();
        try {
            CommandDAOImpl commandDAO = new CommandDAOImpl();
            manager.beginTransaction(commandDAO);

            requestContent.setAjaxSuccess(commandDAO.update(command));

            manager.commit();
            manager.endTransaction();

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Update command rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void createCommand(RequestContent requestContent) throws ReceiverException {
        CommandValidatorImpl validator = new CommandValidatorImpl();
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        String[] stringSportId = requestContent.getRequestParameters().get(KIND_OF_SPORT_ID);
        String[] commandNameArr = requestContent.getRequestParameters().get(NAME);
        requestContent.getSessionAttributes().remove(TEMPORARY);
        Map<String, Object> data = new HashMap<>();

        if (!commonValidator.isVarExist(commandNameArr) || !validator.isNameValid(commandNameArr[0]) ||
                !commonValidator.isVarExist(stringSportId) || !commonValidator.isInteger(stringSportId[0])) {
            data.put("wrongData", true);
            requestContent.getSessionAttributes().put(TEMPORARY, data);
            return;
        }

        CommandEntity command = new CommandEntity();
        command.setName(commandNameArr[0]);
        command.setKindOfSportId(Integer.valueOf(stringSportId[0]));

        TransactionManager manager = new TransactionManager();
        try {
            CommandDAOImpl commandDAO = new CommandDAOImpl();
            manager.beginTransaction(commandDAO);

            boolean isCreated = commandDAO.create(command);

            manager.commit();
            manager.endTransaction();

            if (!isCreated) {
                data.put(DUPLICATE_NAME, true);
                requestContent.getSessionAttributes().put(TEMPORARY, data);
            }

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Create command rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void deleteCommand(RequestContent requestContent) throws ReceiverException {
        CommonValidatorImpl validator = new CommonValidatorImpl();
        String[] stringId = requestContent.getRequestParameters().get(COMMAND_ID);

        if (!validator.isVarExist(stringId) || !validator.isInteger(stringId[0])) {
            requestContent.setAjaxSuccess(false);
            return;
        }

        int commandId = Integer.valueOf(stringId[0]);

        TransactionManager manager = new TransactionManager();
        try {
            CommandDAOImpl commandDAO = new CommandDAOImpl();
            manager.beginTransaction(commandDAO);

            boolean isDeleted = commandDAO.delete(commandId);

            manager.commit();
            manager.endTransaction();
            requestContent.setAjaxSuccess(isDeleted);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Delete command rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void findCommand(RequestContent requestContent) throws ReceiverException {
        CommonValidatorImpl validator = new CommonValidatorImpl();
        String[] stringId = requestContent.getRequestParameters().get(KIND_OF_SPORT_ID);

        if (!validator.isVarExist(stringId) || !validator.isInteger(stringId[0])) {
            requestContent.setAjaxSuccess(false);
            return;
        }

        int sportId = Integer.valueOf(stringId[0]);
        Gson gson = new Gson();
        JsonObject object = new JsonObject();

        TransactionManager manager = new TransactionManager();
        try {
            CommandDAOImpl commandDAO = new CommandDAOImpl();
            manager.beginTransaction(commandDAO);

            JsonElement element = gson.toJsonTree(commandDAO.findBySportId(sportId));

            manager.commit();
            manager.endTransaction();

            object.add(COMMANDS, element);
            requestContent.setAjaxResult(object);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Find command rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }
}
