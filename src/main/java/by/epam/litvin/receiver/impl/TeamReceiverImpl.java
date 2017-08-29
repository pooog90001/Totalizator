package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.KindOfSportEntity;
import by.epam.litvin.bean.TeamEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.dao.impl.KindOfSportDAOImpl;
import by.epam.litvin.dao.impl.TeamDAOImpl;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.TeamReceiver;
import by.epam.litvin.validator.impl.CommonValidatorImpl;
import by.epam.litvin.validator.impl.TeamValidatorImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;

public class TeamReceiverImpl implements TeamReceiver {
    @Override
    public void openTeamSetting(RequestContent requestContent) throws ReceiverException {
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
            TeamDAOImpl teamDAO = new TeamDAOImpl();
            manager.beginTransaction(kindOfSportDAO, teamDAO);
            List<KindOfSportEntity> kindOfSportList = kindOfSportDAO.findAll();
            List<Map<String, Object>> teamList = teamDAO.findAllWithKindOfSport();
            manager.commit();
            manager.endTransaction();

            requestContent.getRequestAttributes().put(KINDS_OF_SPORT, kindOfSportList);
            requestContent.getRequestAttributes().put(TEAMS, teamList);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Open team setting rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void updateTeam(RequestContent requestContent) throws ReceiverException {
        TeamValidatorImpl validator = new TeamValidatorImpl();
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        String[] newNameArr = requestContent.getRequestParameters().get(NEW_NAME);
        String[] stringId = requestContent.getRequestParameters().get(TEAM_ID);

        if (!commonValidator.isVarExist(newNameArr) || !commonValidator.isVarExist(stringId) ||
                !validator.isNameValid(newNameArr[0].trim())) {
            requestContent.setAjaxSuccess(false);
            return;
        }

        TeamEntity team = new TeamEntity();
        team.setId(Integer.valueOf(stringId[0]));
        team.setName(newNameArr[0].trim());

        TransactionManager manager = new TransactionManager();
        try {
            TeamDAOImpl teamDAO = new TeamDAOImpl();
            manager.beginTransaction(teamDAO);

            requestContent.setAjaxSuccess(teamDAO.update(team));

            manager.commit();
            manager.endTransaction();

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Update team rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void createTeam(RequestContent requestContent) throws ReceiverException {
        TeamValidatorImpl validator = new TeamValidatorImpl();
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        String[] stringSportId = requestContent.getRequestParameters().get(KIND_OF_SPORT_ID);
        String[] teamNameArr = requestContent.getRequestParameters().get(NAME);
        requestContent.getSessionAttributes().remove(TEMPORARY);
        Map<String, Object> data = new HashMap<>();

        if (!commonValidator.isVarExist(teamNameArr) || !validator.isNameValid(teamNameArr[0]) ||
                !commonValidator.isVarExist(stringSportId) || !commonValidator.isInteger(stringSportId[0])) {
            data.put(WRONG_DATA, true);
            requestContent.getSessionAttributes().put(TEMPORARY, data);
            return;
        }

        TeamEntity team = new TeamEntity();
        team.setName(teamNameArr[0]);
        team.setKindOfSportId(Integer.valueOf(stringSportId[0]));

        TransactionManager manager = new TransactionManager();
        try {
            TeamDAOImpl teamDAO = new TeamDAOImpl();
            manager.beginTransaction(teamDAO);

            boolean isCreated = teamDAO.create(team);

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
                throw new ReceiverException("Create team rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void deleteTeam(RequestContent requestContent) throws ReceiverException {
        CommonValidatorImpl validator = new CommonValidatorImpl();
        String[] stringId = requestContent.getRequestParameters().get(TEAM_ID);

        if (!validator.isVarExist(stringId) || !validator.isInteger(stringId[0])) {
            requestContent.setAjaxSuccess(false);
            return;
        }

        int teamId = Integer.valueOf(stringId[0]);

        TransactionManager manager = new TransactionManager();
        try {
            TeamDAOImpl teamDAO = new TeamDAOImpl();
            manager.beginTransaction(teamDAO);

            boolean isDeleted = teamDAO.delete(teamId);

            manager.commit();
            manager.endTransaction();
            requestContent.setAjaxSuccess(isDeleted);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Delete team rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void findTeam(RequestContent requestContent) throws ReceiverException {
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
            TeamDAOImpl teamDAO = new TeamDAOImpl();
            manager.beginTransaction(teamDAO);

            JsonElement element = gson.toJsonTree(teamDAO.findBySportId(sportId));

            manager.commit();
            manager.endTransaction();

            object.add(TEAMS, element);
            requestContent.setAjaxResult(object);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Find team rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }
}
