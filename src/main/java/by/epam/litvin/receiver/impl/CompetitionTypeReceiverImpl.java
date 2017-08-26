package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.CompetitionTypeEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.dao.impl.CompetitionTypeDAOImpl;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CompetitonTypeReceiver;
import by.epam.litvin.validator.impl.CommonValidatorImpl;
import by.epam.litvin.validator.impl.CompetitionTypeValidatorImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.NEW_NAME;
import static by.epam.litvin.constant.GeneralConstant.TEMPORARY;

public class CompetitionTypeReceiverImpl implements CompetitonTypeReceiver {
    @Override
    public void openCompetitionTypeSetting(RequestContent requestContent) throws ReceiverException {
        CommonValidatorImpl validator = new CommonValidatorImpl();
        String[] errorNames = {"wrongName", "duplicateName"};

        for (String errorName : errorNames) {
            String[] error = requestContent.getRequestParameters().get(errorName);

            if (validator.isVarExist(error)) {
                requestContent.getRequestAttributes().put(errorName, true);
            }
        }

        TransactionManager manager = new TransactionManager();
        try {
            CompetitionTypeDAOImpl competitionTypeDAO = new CompetitionTypeDAOImpl();
            manager.beginTransaction(competitionTypeDAO);
            List<CompetitionTypeEntity> typesList = competitionTypeDAO.findAll();
            manager.commit();
            manager.endTransaction();

            requestContent.getRequestAttributes().put("competitionTypes", typesList);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Open competition type rollback error ", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void updateCompetitionType(RequestContent requestContent) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        CompetitionTypeValidatorImpl validator = new CompetitionTypeValidatorImpl();
        String[] newNameArr = requestContent.getRequestParameters().get(NEW_NAME);
        String[] stringId = requestContent.getRequestParameters().get("competitionTypeId");

        if (!commonValidator.isVarExist(newNameArr) || !validator.isNameValid(newNameArr[0].trim()) ||
                !commonValidator.isVarExist(stringId) || !commonValidator.isInteger(stringId[0])) {
            requestContent.setAjaxSuccess(false);
            return;
        }

        CompetitionTypeEntity type = new CompetitionTypeEntity();
        type.setId(Integer.valueOf(stringId[0]));
        type.setName(newNameArr[0].trim());

        TransactionManager manager = new TransactionManager();
        try {
            CompetitionTypeDAOImpl typeDAO = new CompetitionTypeDAOImpl();
            manager.beginTransaction(typeDAO);

            requestContent.setAjaxSuccess(typeDAO.update(type));

            manager.commit();
            manager.endTransaction();


        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Update competition type rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void createCompetitionType(RequestContent requestContent) throws ReceiverException {
        CompetitionTypeValidatorImpl validator = new CompetitionTypeValidatorImpl();
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        String[] typeNameArr = requestContent.getRequestParameters().get("name");
        Map<String, Object> data = new HashMap<>();

        if (!commonValidator.isVarExist(typeNameArr) || !validator.isNameValid(typeNameArr[0].trim())) {
            data.put("wrongName", true);
            requestContent.getSessionAttributes().put(TEMPORARY, data);
            return;
        }

        CompetitionTypeEntity type = new CompetitionTypeEntity();
        type.setName(typeNameArr[0].trim());

        TransactionManager manager = new TransactionManager();
        try {
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
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Create competition type rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void deleteCompetitionType(RequestContent requestContent) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        String[] stringId = requestContent.getRequestParameters().get("competitionTypeId");

        if (!commonValidator.isVarExist(stringId) || !commonValidator.isInteger(stringId[0])) {
            requestContent.setAjaxSuccess(false);
            return;
        }

        int typeId = Integer.valueOf(stringId[0]);

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CompetitionTypeDAOImpl typeDAO = new CompetitionTypeDAOImpl();
            manager.beginTransaction(typeDAO);

            boolean isCreated = typeDAO.delete(typeId);

            manager.commit();
            manager.endTransaction();

            requestContent.setAjaxSuccess(isCreated);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Delete competition type rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }
}
