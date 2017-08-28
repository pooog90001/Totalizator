package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.KindOfSportEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.dao.impl.KindOfSportDAOImpl;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.KindOfSportReceiver;
import by.epam.litvin.validator.impl.CommonValidatorImpl;
import by.epam.litvin.validator.impl.KindOfSportValidatorImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;

public class KindOfSportReceiverImpl implements KindOfSportReceiver {

    @Override
    public void openKindOfSportSetting(RequestContent requestContent) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        String[] errorNames = {WRONG_DATA, DUPLICATE_NAME, WRONG_COUNT};

        for (String errorName : errorNames) {
            String[] error = requestContent.getRequestParameters().get(errorName);

            if (commonValidator.isVarExist(error)) {
                requestContent.getRequestAttributes().put(errorName, true);
            }
        }

        TransactionManager manager = new TransactionManager();
        try {
            KindOfSportDAOImpl kindOfSportDAO = new KindOfSportDAOImpl();
            manager.beginTransaction(kindOfSportDAO);
            List<KindOfSportEntity> kindOfSportList = kindOfSportDAO.findAll();
            manager.commit();
            manager.endTransaction();

            requestContent.getRequestAttributes().put(KIND_OF_SPORT_LIST, kindOfSportList);

        } catch (DAOException e) {
                try {
                    manager.rollback();
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Open sport setting rollback error", e);
                }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void updateKindOfSport(RequestContent requestContent) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        KindOfSportValidatorImpl validator = new KindOfSportValidatorImpl();
        String[] newNameArr = requestContent.getRequestParameters().get(NEW_NAME);
        String[] stringId = requestContent.getRequestParameters().get(KIND_OF_SPORT_ID);

        if (!commonValidator.isVarExist(newNameArr) || !commonValidator.isVarExist(stringId) ||
                !validator.isNameValid(newNameArr[0].trim()) || !commonValidator.isInteger(stringId[0])) {
            requestContent.setAjaxSuccess(false);
            return;
        }

        KindOfSportEntity kindOfSport = new KindOfSportEntity();
        kindOfSport.setId(Integer.valueOf(stringId[0]));
        kindOfSport.setName(newNameArr[0].trim());

        TransactionManager manager = new TransactionManager();
        try {
            KindOfSportDAOImpl kindOfSportDAO = new KindOfSportDAOImpl();
            manager.beginTransaction(kindOfSportDAO);

            requestContent.setAjaxSuccess(kindOfSportDAO.update(kindOfSport));

            manager.commit();
            manager.endTransaction();

        } catch (DAOException e) {
                try {
                    manager.rollback();
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Update sport rollback error", e);
                }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void createKindOfSport(RequestContent requestContent) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        KindOfSportValidatorImpl validator = new KindOfSportValidatorImpl();
        String[] stringCompetitorsCount = requestContent.getRequestParameters().get(COUNT);
        String[] sportNameArr = requestContent.getRequestParameters().get(NAME);
        Map<String, Object> data = new HashMap<>();

        if (!commonValidator.isVarExist(stringCompetitorsCount) ||
                !commonValidator.isInteger(stringCompetitorsCount[0]) ||
                !commonValidator.isVarExist(sportNameArr) ||
                !validator.isNameValid(sportNameArr[0].trim())) {
            data.put(WRONG_DATA, true);
            requestContent.getSessionAttributes().put(TEMPORARY, data);
            return;
        }

        int competitorsCount = Integer.valueOf(stringCompetitorsCount[0]);

        if (!validator.isCompetitorsCountValid(competitorsCount)) {
            data.put(WRONG_COUNT, true);
            requestContent.getSessionAttributes().put(TEMPORARY, data);
            return;
        }


        KindOfSportEntity kindOfSport = new KindOfSportEntity();
        kindOfSport.setName(sportNameArr[0].trim());
        kindOfSport.setCompetitorCount(competitorsCount);

        TransactionManager manager = new TransactionManager();
        try {
            KindOfSportDAOImpl kindOfSportDAO = new KindOfSportDAOImpl();
            manager.beginTransaction(kindOfSportDAO);

            boolean isCreated = kindOfSportDAO.create(kindOfSport);

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
                    throw new ReceiverException("Create sport rollback error", e);
                }
            throw new ReceiverException(e);
        }

    }

    @Override
    public void deleteKindOfSport(RequestContent requestContent) throws ReceiverException {
        String[] stringId = requestContent.getRequestParameters().get(KIND_OF_SPORT_ID);
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();

        if (!commonValidator.isVarExist(stringId) || !commonValidator.isInteger(stringId[0])) {
            requestContent.setAjaxSuccess(false);
            return;
        }

        int kindOfSportId = Integer.valueOf(stringId[0]);
        TransactionManager manager = new TransactionManager();
        try {
            KindOfSportDAOImpl kindOfSportDAO = new KindOfSportDAOImpl();
            manager.beginTransaction(kindOfSportDAO);

            requestContent.setAjaxSuccess(kindOfSportDAO.delete(kindOfSportId));

            manager.commit();
            manager.endTransaction();


        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Delete sport rollback error", e);
            }

            throw new ReceiverException(e);
        }
    }

}
