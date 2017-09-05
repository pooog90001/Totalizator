package by.epam.totalizator.receiver.impl;

import by.epam.totalizator.constant.RequestNameConstant;
import by.epam.totalizator.constant.SQLFieldConstant;
import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.dao.TransactionManager;
import by.epam.totalizator.dao.impl.*;
import by.epam.totalizator.entity.*;
import by.epam.totalizator.exception.DAOException;
import by.epam.totalizator.exception.ReceiverException;
import by.epam.totalizator.receiver.CompetitionReceiver;
import by.epam.totalizator.type.ExpectResultType;
import by.epam.totalizator.util.Formatter;
import by.epam.totalizator.validator.CommonValidator;
import by.epam.totalizator.validator.CompetitorValidator;
import by.epam.totalizator.validator.UserValidator;
import by.epam.totalizator.validator.impl.CommonValidatorImpl;
import by.epam.totalizator.validator.impl.CompetitionValidatorImpl;
import by.epam.totalizator.validator.impl.CompetitorValidatorImpl;
import by.epam.totalizator.validator.impl.UserValidatorImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.totalizator.constant.GeneralConstant.*;

public class CompetitionReceiverImpl implements CompetitionReceiver {


    @Override
    public void openCompetitionSettings(RequestContent requestContent) throws ReceiverException {
        CommonValidator commonValidator = new CommonValidatorImpl();
        String[] errorNames = {RequestNameConstant.WRONG_NAME, WRONG_DATE, WRONG_COMPETITORS, WRONG_TYPE,
                WRONG_ACTIVE, CREATE_ERROR, DEACTIVATE_ERROR, FILL_ERROR, WRONG_NUMBER_FORMAT};

        for (String name : errorNames) {
            String[] error = requestContent.getRequestParameters().get(name);

            if (commonValidator.isVarExist(error)) {
                requestContent.getRequestAttributes().put(name, true);
            }
        }

        TransactionManager manager = new TransactionManager();
        try {
            KindOfSportDAOImpl kindOfSportDAO = new KindOfSportDAOImpl();
            CompetitionTypeDAOImpl typeDAO = new CompetitionTypeDAOImpl();
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            CommonDAOImpl commonDAO = new CommonDAOImpl();
            manager.beginTransaction(kindOfSportDAO, typeDAO, competitorDAO, competitionDAO, commonDAO);
            List<KindOfSportEntity> kindOfSportList = kindOfSportDAO.findAll();
            List<CompetitionTypeEntity> competitionTypes = typeDAO.findAll();

            List<Map<String, Object>> upcomingActiveCompetitions =
                    competitionDAO.findAllUpcomingGames(true);
            extractWithStatistic(upcomingActiveCompetitions, competitorDAO, commonDAO);

            List<Map<String, Object>> upcomingDeactiveCompetitions =
                    competitionDAO.findAllUpcomingGames(false);
            extractWithoutStatistic(upcomingDeactiveCompetitions, competitorDAO);

            List<Map<String, Object>> nowActiveCompetitions =
                    competitionDAO.findAllNowGames(true);
            extractWithStatistic(nowActiveCompetitions, competitorDAO, commonDAO);

            List<Map<String, Object>> nowDeactiveCompetitions =
                    competitionDAO.findAllNowGames(false);
            extractWithoutStatistic(nowDeactiveCompetitions, competitorDAO);

            List<Map<String, Object>> pastDeactivatedCompetitions =
                    competitionDAO.findAllPastGames(false, false);
            extractWithoutStatistic(pastDeactivatedCompetitions, competitorDAO);

            List<Map<String, Object>> pastUnfilledCompetitions =
                    competitionDAO.findAllPastGames(false, true);
            extractWithStatistic(pastUnfilledCompetitions, competitorDAO, commonDAO);

            List<Map<String, Object>> pastFilledCompetitions =
                    competitionDAO.findAllPastGames(true, true);
            extractWithStatistic(pastFilledCompetitions, competitorDAO, commonDAO);

            manager.commit();
            manager.endTransaction();

            requestContent.getRequestAttributes().put(KINDS_OF_SPORT, kindOfSportList);
            requestContent.getRequestAttributes().put(COMPETITION_TYPES, competitionTypes);
            requestContent.getRequestAttributes().put(UPCOMING_ACTIVE_COMPETITIONS, upcomingActiveCompetitions);
            requestContent.getRequestAttributes().put(UPCOMING_DEACTIVE_COMPETITIONS, upcomingDeactiveCompetitions);
            requestContent.getRequestAttributes().put(NOW_ACTIVE_COMPETITIONS, nowActiveCompetitions);
            requestContent.getRequestAttributes().put(NOW_DEACTIVE_COMPETITIONS, nowDeactiveCompetitions);
            requestContent.getRequestAttributes().put(PAST_UNFILLED_COMPETITIONS, pastUnfilledCompetitions);
            requestContent.getRequestAttributes().put(PAST_FILLED_COMPETITIONS, pastFilledCompetitions);
            requestContent.getRequestAttributes().put(PAST_DEACTIVE_COMPETITIONS, pastDeactivatedCompetitions);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Rollback open competition settings error", e);
            }
            throw new ReceiverException(e);
        }
    }


    private void extractWithStatistic(List<Map<String, Object>> competitions,
                                      CompetitorDAOImpl competitorDAO,
                                      CommonDAOImpl commonDAO) throws DAOException {

        for (Map<String, Object> competition : competitions) {
            int compId = (int) competition.get(SQLFieldConstant.Competition.ID);
            List<Map<String, Object>> competitors = competitorDAO.findWithTeamByGameId(compId);

            for (Map<String, Object> competitor : competitors) {
                int competitorId = (int) competitor.get(SQLFieldConstant.Competitor.ID);
                int betsCount = commonDAO.findCountBetsOnCompetitor(competitorId);
                BigDecimal amountOfMoney = betsCount == 0 ? new BigDecimal("0") :
                        commonDAO.findAmountOfMoneyOnCompetitor(competitorId);

                competitor.put(BETS_COUNT, betsCount);
                competitor.put(AMOUNT_OF_MONEY, amountOfMoney);

            }
            competition.put(COMPETITORS, competitors);

            if (competitors.size() == 2) {
                int competitionId = (int) competition.get(SQLFieldConstant.Competition.ID);
                int betsLessTotalCount = commonDAO
                        .findCountBetsOnCompetition(competitionId, ExpectResultType.LESS);
                BigDecimal lessAmountOfMoney = betsLessTotalCount == 0 ? new BigDecimal("0") :
                        commonDAO.findAmountOfMoneyOnCompetition(competitionId, ExpectResultType.LESS);

                int betsMoreTotalCount = commonDAO
                        .findCountBetsOnCompetition(competitionId, ExpectResultType.MORE);
                BigDecimal moreAmountOfMoney = betsMoreTotalCount == 0 ? new BigDecimal("0") :
                        commonDAO.findAmountOfMoneyOnCompetition(competitionId, ExpectResultType.MORE);

                int betsStandoffTotalCount = commonDAO
                        .findCountBetsOnCompetition(competitionId, ExpectResultType.EQUALS);
                BigDecimal standoffAmountOfMoney = betsMoreTotalCount == 0 ? new BigDecimal("0") :
                        commonDAO.findAmountOfMoneyOnCompetition(competitionId, ExpectResultType.EQUALS);

                competition.put(BETS_LESS_TOTAL_COUNT, betsLessTotalCount);
                competition.put(BETS_MORE_TOTAL_COUNT, betsMoreTotalCount);
                competition.put(BETS_STANDOFF_TOTAL_COUNT, betsStandoffTotalCount);
                competition.put(LESS_AMOUNT_OF_MONEY, lessAmountOfMoney);
                competition.put(MORE_AMOUNT_OF_MONEY, moreAmountOfMoney);
                competition.put(STANDOFF_AMOUNT_OF_MONEY, standoffAmountOfMoney);
            }
        }
    }

    private void extractWithoutStatistic(List<Map<String, Object>> competitions,
                                         CompetitorDAOImpl competitorDAO) throws DAOException {

        for (Map<String, Object> competition : competitions) {
            int compId = (int) competition.get(SQLFieldConstant.Competition.ID);
            List<Map<String, Object>> competitors = competitorDAO.findWithTeamByGameId(compId);
            competition.put(COMPETITORS, competitors);
        }
    }

    private CompetitorEntity[] insertParamToCompetitorsArr(String[] teamsId, String[] competitorsCoeff) {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        CompetitionValidatorImpl competitionValidator = new CompetitionValidatorImpl();
        CompetitorEntity[] competitors = null;
        boolean isSuccess = true;

        if (teamsId == null || competitorsCoeff == null ||
                teamsId.length != competitorsCoeff.length) {
            isSuccess = false;
        }

        if (isSuccess) {
            competitors = new CompetitorEntity[teamsId.length];

            for (int i = 0; i < competitors.length; i++) {
                if (commonValidator.isInteger(teamsId[i]) &&
                        competitionValidator.isCoeff(competitorsCoeff[i])) {
                    CompetitorEntity competitor = new CompetitorEntity();
                    competitor.setTeamId(Integer.valueOf(teamsId[i]));
                    competitor.setWinCoeff(competitionValidator.checkStringCoeff(competitorsCoeff[i]));
                    competitors[i] = competitor;

                } else {
                    isSuccess = false;
                }
            }
        }

        return isSuccess ? competitors : null;
    }

    @Override
    public void createCompetition(RequestContent content) throws ReceiverException {
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String[] gameNameArr = content.getRequestParameters().get(COMPETITION_NAME);
        String[] stringTypeId = content.getRequestParameters().get(TYPE_ID);
        String[] stringDateStart = content.getRequestParameters().get(DATE_START);
        String[] stringTimeStart = content.getRequestParameters().get(TIME_START);
        String[] stringDateFinish = content.getRequestParameters().get(DATE_FINISH);
        String[] stringTimeFinish = content.getRequestParameters().get(TIME_FINISH);
        String[] stringIsActive = content.getRequestParameters().get(IS_ACTIVE);
        String[] teamsId = content.getRequestParameters().get(TEAM_ID);
        String[] competitorsCoeff = content.getRequestParameters().get(COMPETITOR_COEFF);
        boolean isActive = (stringIsActive != null && ON.equals(stringIsActive[0]));
        String[] stringTotal = content.getRequestParameters().get(TOTAL);
        String[] stringLessTotalCoeff = content.getRequestParameters().get(LESS_TOTAL_CEFF);
        String[] stringMoreTotalCoeff = content.getRequestParameters().get(MORE_TOTAL_CEFF);
        String[] stringStandoffCoeff = content.getRequestParameters().get(STANDOFF_CEFF);
        CompetitionValidatorImpl competitionValidator = new CompetitionValidatorImpl();
        UserValidatorImpl userValidator = new UserValidatorImpl();
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        Formatter formatter = new Formatter();
        Map<String, Object> data = new HashMap<>();
        Date dStart = null;
        Date dFinish = null;
        content.getSessionAttributes().remove(TEMPORARY);

        if (!userValidator.isBookmaker(user) && isActive) {
            content.getRequestAttributes().put(ACCESS_DENIED, true);
            return;
        }

        CompetitorEntity[] competitors =
                insertParamToCompetitorsArr(teamsId, competitorsCoeff);

        if (competitors == null) {
            data.put(WRONG_COMPETITORS, true);
            content.getSessionAttributes().put(TEMPORARY, data);
            return;
        }

        BigDecimal total = stringTotal == null ? null :
                competitionValidator.checkStringNumber(stringTotal[0]);
        BigDecimal lessTotalCoeff = stringLessTotalCoeff == null ? null :
                competitionValidator.checkStringCoeff(stringLessTotalCoeff[0]);
        BigDecimal moreTotalCoeff = stringMoreTotalCoeff == null ? null :
                competitionValidator.checkStringCoeff(stringMoreTotalCoeff[0]);
        BigDecimal standoffCoeff = stringStandoffCoeff == null ? null :
                competitionValidator.checkStringCoeff(stringStandoffCoeff[0]);

        if (isActive) {
            boolean isValid = true;

            if (teamsId.length == 2) {
                if (total == null || lessTotalCoeff == null ||
                        moreTotalCoeff == null || standoffCoeff == null) {
                    isValid = false;
                }
            }
            if (!isValid) {
                data.put(WRONG_ACTIVE, true);
                content.getSessionAttributes().put(TEMPORARY, data);
                return;
            }
        }

        boolean isDateFormatValid = false;
        if (commonValidator.isVarExist(stringDateStart) &&
                commonValidator.isVarExist(stringTimeStart) &&
                commonValidator.isVarExist(stringDateFinish) &&
                commonValidator.isVarExist(stringTimeFinish)) {
            dStart = formatter.formatToDate(stringDateStart[0], stringTimeStart[0]);
            dFinish = formatter.formatToDate(stringDateFinish[0], stringTimeFinish[0]);
            isDateFormatValid = (dStart != null && dFinish != null);
        }
        if (!isDateFormatValid || dStart.before(new Date()) || dStart.after(dFinish)) {
            data.put(WRONG_DATE, true);
            content.getSessionAttributes().put(TEMPORARY, data);
            return;
        }
        if (!commonValidator.isVarExist(gameNameArr) ||
                !competitionValidator.isNameValid(gameNameArr[0])) {
            data.put(RequestNameConstant.WRONG_NAME, true);
            content.getSessionAttributes().put(TEMPORARY, data);
            return;
        }
        if (!commonValidator.isVarExist(stringTypeId) ||
                !commonValidator.isInteger(stringTypeId[0])) {
            data.put(WRONG_TYPE, true);
            content.getSessionAttributes().put(TEMPORARY, data);
            return;
        }

        int typeId = Integer.valueOf(stringTypeId[0]);
        CompetitionEntity competition =
                new CompetitionEntity(gameNameArr[0], dStart, dFinish, isActive, typeId);

        if (competitors.length == 2) {
            competition.setTotal(total);
            competition.setLessTotal(lessTotalCoeff);
            competition.setMoreTotal(moreTotalCoeff);
            competition.setStandoff(standoffCoeff);
        }

        TransactionManager manager = new TransactionManager();
        try {
            boolean transactionAccess = true;
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            manager.beginTransaction(competitionDAO, competitorDAO);

            int competitionId = competitionDAO.createAndGetId(competition);
            CompetitorEntity competitorEntity = new CompetitorEntity();
            competitorEntity.setCompetitionId(competitionId);
            competitorEntity.setTeamId(0);

            if (!competitorDAO.create(competitorEntity)) {
                transactionAccess = false;
            }

            for (CompetitorEntity competitor : competitors) {
                competitor.setCompetitionId(competitionId);
                if (!competitorDAO.create(competitor)) {
                    transactionAccess = false;
                }
            }

            if (transactionAccess) {
                manager.commit();

            } else {
                manager.rollback();
                data.put(CREATE_ERROR, true);
                content.getSessionAttributes().put(TEMPORARY, data);
            }
            manager.endTransaction();

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("create competition rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void updateUpcomingActivated(RequestContent content) throws ReceiverException {
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String[] stringLessTotalCoeff = content.getRequestParameters().get(LESS_TOTAL_CEFF);
        String[] stringMoreTotalCoeff = content.getRequestParameters().get(MORE_TOTAL_CEFF);
        String[] stringStandoffCoeff = content.getRequestParameters().get(STANDOFF_CEFF);
        String[] stringCompetitorCoeff = content.getRequestParameters().get(COMPETITOR_COEFF);
        String[] stringCompetitorId = content.getRequestParameters().get(COMPETITOR_ID);
        String[] stringCompetitionId = content.getRequestParameters().get(COMPETITION_ID);
        CompetitorEntity[] competitors = new CompetitorEntity[stringCompetitorCoeff.length];

        CompetitionValidatorImpl competitionValidator = new CompetitionValidatorImpl();
        UserValidatorImpl userValidator = new UserValidatorImpl();
        boolean isDataValid = true;

        if (!userValidator.isBookmaker(user)) {
            content.setAjaxSuccess(false);
            return;
        }

        CompetitionEntity competition = new CompetitionEntity();
        competition.setLessTotal(stringLessTotalCoeff == null ? null :
                competitionValidator.checkStringCoeff(stringLessTotalCoeff[0]));
        competition.setMoreTotal(stringMoreTotalCoeff == null ? null :
                competitionValidator.checkStringCoeff(stringMoreTotalCoeff[0]));
        competition.setStandoff(stringStandoffCoeff == null ? null :
                competitionValidator.checkStringCoeff(stringStandoffCoeff[0]));
        competition.setId(Integer.valueOf(stringCompetitionId[0]));

        for (int i = 0; i < competitors.length; i++) {
            CompetitorEntity competitor = new CompetitorEntity();
            competitor.setId(Integer.valueOf(stringCompetitorId[i]));
            competitor.setWinCoeff(competitionValidator.checkStringCoeff(stringCompetitorCoeff[i]));
            competitors[i] = competitor;
        }

        for (CompetitorEntity competitor : competitors) {
            if (competitor.getWinCoeff() == null) {
                isDataValid = false;
            }
        }

        if (competitors.length == 2) {
            if (competition.getLessTotal() == null || competition.getMoreTotal() == null ||
                    competition.getStandoff() == null) {
                isDataValid = false;
            }
        }

        if (!isDataValid) {
            content.setAjaxSuccess(false);
            return;
        }

        TransactionManager manager = new TransactionManager();
        try {
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            manager.beginTransaction(competitorDAO, competitionDAO);
            boolean transactionSuccess = true;

            if (competitors.length == 2) {
                transactionSuccess = competitionDAO.updateUpcomingActivatedCoeffs(competition);
            }

            if (transactionSuccess) {
                for (CompetitorEntity competitor : competitors) {
                    if (!competitorDAO.update(competitor)) {
                        transactionSuccess = false;
                        break;
                    }
                }
            }

            if (transactionSuccess) {
                manager.commit();
            } else {
                manager.rollback();
            }

            manager.endTransaction();
            content.setAjaxSuccess(transactionSuccess);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Rollback edit upcoming activated competition error", e);
            }
            throw new ReceiverException(e);
        }

    }

    @Override
    public void updateUpcomingDeactivated(RequestContent content) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String[] stringTotal = content.getRequestParameters().get(TOTAL);
        String[] stringLessTotalCoeff = content.getRequestParameters().get(LESS_TOTAL_CEFF);
        String[] stringMoreTotalCoeff = content.getRequestParameters().get(MORE_TOTAL_CEFF);
        String[] stringStandoffCoeff = content.getRequestParameters().get(STANDOFF_CEFF);
        String[] stringCompetitorCoeff = content.getRequestParameters().get(COMPETITOR_COEFF);
        String[] stringCompetitorId = content.getRequestParameters().get(COMPETITOR_ID);
        String[] stringCompetitionId = content.getRequestParameters().get(COMPETITION_ID);
        String[] stringCompetitionName = content.getRequestParameters().get(COMPETITION_NAME);

        CompetitionValidatorImpl competitionValidator = new CompetitionValidatorImpl();
        UserValidatorImpl userValidator = new UserValidatorImpl();
        boolean isDataValid = true;

        if (!userValidator.isBookmaker(user) || !commonValidator.isVarExist(stringCompetitionName) ||
                !competitionValidator.isNameValid(stringCompetitionName[0]) ||
                !commonValidator.isVarExist(stringCompetitionName) ||
                !commonValidator.isVarExist(stringCompetitorId) ||
                !commonValidator.isVarExist(stringCompetitorCoeff) ||
                stringCompetitorCoeff.length != stringCompetitorId.length) {
            content.setAjaxSuccess(false);
            return;
        }

        CompetitionEntity competition = new CompetitionEntity();
        competition.setLessTotal(stringLessTotalCoeff == null ? null :
                competitionValidator.checkStringCoeff(stringLessTotalCoeff[0]));
        competition.setMoreTotal(stringMoreTotalCoeff == null ? null :
                competitionValidator.checkStringCoeff(stringMoreTotalCoeff[0]));
        competition.setStandoff(stringStandoffCoeff == null ? null :
                competitionValidator.checkStringCoeff(stringStandoffCoeff[0]));
        competition.setTotal(stringTotal == null ? null :
                competitionValidator.checkStringNumber(stringTotal[0]));
        competition.setId(Integer.valueOf(stringCompetitionId[0]));
        competition.setName(stringCompetitionName[0]);


        CompetitorEntity[] competitors = new CompetitorEntity[stringCompetitorCoeff.length];
        for (int i = 0; i < competitors.length; i++) {
            if (commonValidator.isInteger(stringCompetitorId[i]) &&
                    competitionValidator.isCoeff(stringCompetitorCoeff[i])) {
                CompetitorEntity competitor = new CompetitorEntity();
                competitor.setId(Integer.valueOf(stringCompetitorId[i]));
                competitor.setWinCoeff(competitionValidator.checkStringCoeff(stringCompetitorCoeff[i]));
                competitors[i] = competitor;

            } else {
                isDataValid = false;
            }
        }

        for (CompetitorEntity competitor : competitors) {
            if (competitor.getWinCoeff() == null) {
                isDataValid = false;
                break;
            }
        }

        if (competitors.length == 2) {
            if (competition.getLessTotal() == null || competition.getMoreTotal() == null ||
                    competition.getStandoff() == null || competition.getTotal() == null) {
                isDataValid = false;
            }
        }

        if (!isDataValid) {
            content.setAjaxSuccess(false);
            return;
        }

        TransactionManager manager = new TransactionManager();
        try {
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            manager.beginTransaction(competitorDAO, competitionDAO);
            boolean transactionSuccess = true;

            if (competitors.length == 2) {
                transactionSuccess = competitionDAO.updateUpcomingDeactivatedCoeffs(competition);
            }

            if (transactionSuccess) {
                transactionSuccess = competitionDAO.updateName(competition);
            }

            if (transactionSuccess) {
                for (CompetitorEntity competitor : competitors) {
                    if (!competitorDAO.update(competitor)) {
                        transactionSuccess = false;
                        break;
                    }
                }
            }

            if (transactionSuccess) {
                manager.commit();
            } else {
                manager.rollback();
            }

            manager.endTransaction();
            content.setAjaxSuccess(transactionSuccess);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Rollback edit upcoming deactivated competition error", e);
            }
            throw new ReceiverException(e);
        }
    }


    @Override
    public void deleteUnfilledCompetition(RequestContent content) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String[] stringGameId = content.getRequestParameters().get(COMPETITION_ID);
        UserValidatorImpl userValidator = new UserValidatorImpl();

        if (!userValidator.isBookmaker(user) || !commonValidator.isVarExist(stringGameId) ||
                commonValidator.isInteger(stringGameId[0])) {
            content.setAjaxSuccess(false);
            return;
        }
        int gameId = Integer.valueOf(stringGameId[0]);

        TransactionManager manager = new TransactionManager();
        try {
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            BetDAOImpl betDAO = new BetDAOImpl();
            UserDAOImpl userDAO = new UserDAOImpl();
            manager.beginTransaction(competitorDAO, competitionDAO, betDAO, userDAO);
            boolean transactionSuccess = true;
            CompetitionEntity competition = competitionDAO.findEntityById(gameId);

            if (competition.getResultFilled()) {
                manager.rollback();
                manager.endTransaction();
                content.setAjaxSuccess(false);
            }

            userDAO.returnMoneyForBets(gameId);
            betDAO.deleteByCompetitionId(gameId);
            competitorDAO.deleteByCompetitionId(gameId);

            if (!competitionDAO.delete(gameId)) {
                transactionSuccess = false;
            }

            if (transactionSuccess) {
                manager.commit();
            } else {
                manager.rollback();
            }

            manager.endTransaction();
            content.setAjaxSuccess(transactionSuccess);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Delete unfilled competition rollback error", e);
            }
            throw new ReceiverException(e);
        }

    }

    @Override
    public void deleteFilledCompetition(RequestContent content) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String[] stringGameId = content.getRequestParameters().get(COMPETITION_ID);
        UserValidatorImpl userValidator = new UserValidatorImpl();


        if (!userValidator.isBookmaker(user) || !commonValidator.isVarExist(stringGameId) ||
                commonValidator.isInteger(stringGameId[0])) {
            content.setAjaxSuccess(false);
            return;
        }
        int gameId = Integer.valueOf(stringGameId[0]);

        TransactionManager manager = new TransactionManager();
        try {
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            BetDAOImpl betDAO = new BetDAOImpl();
            manager.beginTransaction(competitorDAO, competitionDAO, betDAO);
            boolean transactionSuccess = true;

            betDAO.deleteByCompetitionId(gameId);
            competitorDAO.deleteByCompetitionId(gameId);

            if (!competitionDAO.delete(gameId)) {
                transactionSuccess = false;
            }

            if (transactionSuccess) {
                manager.commit();
            } else {
                manager.rollback();
            }

            manager.endTransaction();
            content.setAjaxSuccess(transactionSuccess);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Delete filled competition rollback error", e);
            }
            throw new ReceiverException(e);
        }

    }

    @Override
    public void updateStateCompetition(RequestContent content) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String[] stringGameId = content.getRequestParameters().get(COMPETITION_ID);
        String[] stringState = content.getRequestParameters().get(STATE);
        UserValidatorImpl userValidator = new UserValidatorImpl();
        content.getSessionAttributes().remove(TEMPORARY);
        Map<String, Object> data = new HashMap<>();


        if (!userValidator.isBookmaker(user) || !commonValidator.isVarExist(stringGameId) ||
                !commonValidator.isInteger(stringGameId[0]) || !commonValidator.isVarExist(stringState)) {
            content.setAjaxSuccess(false);
            return;
        }
        int gameId = Integer.valueOf(stringGameId[0]);
        boolean state = Boolean.valueOf(stringState[0]);

        TransactionManager manager = new TransactionManager();
        try {
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            BetDAOImpl betDAO = new BetDAOImpl();
            UserDAOImpl userDAO = new UserDAOImpl();
            manager.beginTransaction(competitionDAO, betDAO, userDAO);
            boolean transactionSuccess = true;

            userDAO.returnMoneyForBets(gameId);
            betDAO.deleteByCompetitionId(gameId);

            if (!competitionDAO.updateActiveState(gameId, state)) {
                transactionSuccess = false;
            }

            if (transactionSuccess) {
                manager.commit();
            } else {
                manager.rollback();
                data.put("deactivateError", true);
                content.getSessionAttributes().put(TEMPORARY, data);
                return;
            }

            manager.endTransaction();

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Deactivate competition rollback error", e);
            }
            throw new ReceiverException(e);
        }

    }

    @Override
    public void updateResultsCompetition(RequestContent content) throws ReceiverException {
        UserValidator userValidator = new UserValidatorImpl();
        CompetitorValidator competitorValidator = new CompetitorValidatorImpl();
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String[] stringCompetitorResult = content.getRequestParameters().get(COMPETITOR_RESULT);
        String[] stringCompetitorId = content.getRequestParameters().get(COMPETITOR_ID);
        int competitionId = Integer.valueOf(content.getRequestParameters().get(COMPETITION_ID)[0]);
        CompetitorEntity[] competitors = new CompetitorEntity[stringCompetitorResult.length];
        Map<String, Object> data = new HashMap<>();
        boolean isValid = true;

        if (!userValidator.isBookmaker(user)) {
            content.getRequestAttributes().put(ACCESS_DENIED, true);
        }

        if (stringCompetitorId.length != stringCompetitorResult.length) {
            isValid = false;
        }
        if (isValid) {
            for (int i = 0; i < stringCompetitorId.length; i++) {
                if (!commonValidator.isInteger(stringCompetitorId[i]) ||
                        !commonValidator.isInteger(stringCompetitorResult[i])) {
                    isValid = false;
                    break;
                }
            }
        }

        if (isValid) {
            for (int i = 0; i < competitors.length; i++) {
                CompetitorEntity competitor = new CompetitorEntity();
                competitor.setResult(Integer.valueOf(stringCompetitorResult[i]));
                competitor.setId(Integer.valueOf(stringCompetitorId[i]));
                competitor.setWin(false);
                competitors[i] = competitor;
            }

            isValid = (competitors.length == 2) ?
                    competitorValidator.isScoreValid(competitors) :
                    competitorValidator.isPlacesValid(competitors);
        }

        if (!isValid) {
            data.put(WRONG_NUMBER_FORMAT, true);
            content.getSessionAttributes().put(TEMPORARY, data);
            return;
        }

        if (competitors.length == 2) {
            if (competitors[0].getResult() > competitors[1].getResult()) {
                competitors[0].setWin(true);
            } else if (competitors[0].getResult() < competitors[1].getResult()) {
                competitors[1].setWin(true);
            }
        } else {
            for (CompetitorEntity competitor : competitors) {
                if (competitor.getResult() == 1) {
                    competitor.setWin(true);
                    break;
                }
            }
        }

        TransactionManager manager = new TransactionManager();
        try {
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            BetDAOImpl betDAO = new BetDAOImpl();
            boolean isTransactionSuccess = true;
            manager.beginTransaction(competitionDAO, competitorDAO, betDAO);

            for (CompetitorEntity competitor : competitors) {
                if (!competitorDAO.updateResult(competitor)) {
                    isTransactionSuccess = false;
                    break;
                }
            }

            if (isTransactionSuccess) {
                betDAO.updateCompetitorResultAndPayMoney(competitionId);

            }
            if (isTransactionSuccess && competitors.length == 2) {
                betDAO.updateGameResultAndPayMoney(competitionId,
                        competitors[0].getResult(), competitors[1].getResult());
            }
            if (isTransactionSuccess) {
                isTransactionSuccess =
                        competitionDAO.updateResultFillState(competitionId, true);
            }
            if (!isTransactionSuccess) {
                data.put(FILL_ERROR, true);
                content.getSessionAttributes().put(TEMPORARY, data);
                manager.rollback();

            } else {
                manager.commit();
            }

            manager.endTransaction();


        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Fill results competition rollback error", e);
            }
            throw new ReceiverException(e);
        }

    }


    @Override
    public void openUpcomingCompetition(RequestContent content) throws ReceiverException {
        Formatter formatter = new Formatter();
        String[] stringPage = content.getRequestParameters().get(PAGE_NUMBER);
        int page = formatter.formatToPage(stringPage);

        if (page == -1) {
            content.getRequestAttributes().put(PAGE_NOT_FOUND, true);
            return;
        }

        int startIndex = formatter.formatToStartIndex(page, COUNT_COMPETITIONS_ON_PAGE);
        List<Map<String, Object>> upcomingGames;

        TransactionManager handler = new TransactionManager();
        try {
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();

            handler.beginTransaction(competitionDAO, competitorDAO);

            upcomingGames = competitionDAO.findLimitUpcomingGames(
                    startIndex, COUNT_COMPETITIONS_ON_PAGE, true);

            if (upcomingGames.isEmpty() && page != 1) {
                handler.rollback();
                handler.endTransaction();
                content.getRequestAttributes().put(PAGE_NOT_FOUND, true);
                return;
            }

            extractWithoutStatistic(upcomingGames, competitorDAO);

            int gamesCount = competitionDAO.findUpcomingGamesCount(true);

            handler.commit();
            handler.endTransaction();

            content.getRequestAttributes().put(UPCOMING_GAMES, upcomingGames);
            content.getRequestAttributes().put(LIMIT, COUNT_COMPETITIONS_ON_PAGE);
            content.getRequestAttributes().put(GAMES_COUNT, gamesCount);

        } catch (DAOException e) {
            try {
                handler.rollback();
                handler.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Open all upcoming games rollback error", e);
            }

            throw new ReceiverException(e);
        }
    }

    @Override
    public void openPastCompetition(RequestContent content) throws ReceiverException {
        Formatter formatter = new Formatter();
        String[] stringPage = content.getRequestParameters().get(PAGE_NUMBER);
        int page = formatter.formatToPage(stringPage);

        if (page == -1) {
            content.getRequestAttributes().put(PAGE_NOT_FOUND, true);
            return;
        }

        int startIndex = formatter.formatToStartIndex(page, COUNT_COMPETITIONS_ON_PAGE);
        List<Map<String, Object>> pastGames;
        TransactionManager handler = new TransactionManager();
        try {
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            handler.beginTransaction(competitionDAO, competitorDAO);

            pastGames = competitionDAO.findLimitPastGames(startIndex,
                    COUNT_COMPETITIONS_ON_PAGE, true, true);

            if (pastGames.isEmpty() && page != 1) {
                handler.commit();
                handler.endTransaction();
                content.getRequestAttributes().put(PAGE_NOT_FOUND, true);
                return;
            }

            extractWithoutStatistic(pastGames, competitorDAO);

            int gamesCount = competitionDAO
                    .findPastGamesCount(true, true);

            handler.commit();
            handler.endTransaction();

            content.getRequestAttributes().put(PAST_GAMES, pastGames);
            content.getRequestAttributes().put(LIMIT, COUNT_COMPETITIONS_ON_PAGE);
            content.getRequestAttributes().put(GAMES_COUNT, gamesCount);

        } catch (DAOException e) {
            try {
                handler.rollback();
                handler.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Open all past games rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void openCompetitionsByType(RequestContent content) throws ReceiverException {
        int typeId = Integer.valueOf(content.getRequestParameters().get(TYPE_ID)[0]);
        int sportId = Integer.valueOf(content.getRequestParameters().get(SPORT_ID)[0]);

        TransactionManager manager = new TransactionManager();
        try {
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            KindOfSportDAOImpl kindOfSportDAO = new KindOfSportDAOImpl();
            CompetitionTypeDAOImpl competitionTypeDAO = new CompetitionTypeDAOImpl();
            manager.beginTransaction(competitionDAO, competitorDAO, kindOfSportDAO, competitionTypeDAO);

            List<Map<String, Object>> upcomingGames =
                    competitionDAO.findUpcomingGamesByType(sportId, typeId, true);
            extractWithoutStatistic(upcomingGames, competitorDAO);

            List<Map<String, Object>> pastGames =
                    competitionDAO.findPastGamesByType(sportId, typeId, true, true);
            extractWithoutStatistic(pastGames, competitorDAO);

            KindOfSportEntity sport = kindOfSportDAO.findEntityById(sportId);
            CompetitionTypeEntity type = competitionTypeDAO.findEntityById(typeId);

            manager.commit();
            manager.endTransaction();

            content.getRequestAttributes().put(PAST_GAMES, pastGames);
            content.getRequestAttributes().put(UPCOMING_GAMES, upcomingGames);
            content.getRequestAttributes().put(KIND_OF_SPORT, sport);
            content.getRequestAttributes().put(COMPETITION_TYPE, type);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Open competitions by type rollback error", e);
            }

            throw new ReceiverException(e);
        }

    }

    @Override
    public void openConcreteCompetition(RequestContent content) throws ReceiverException {
        CommonValidator commonValidator = new CommonValidatorImpl();
        String[] stringGameId = content.getRequestParameters().get(COMPETITION_ID);

        if (!commonValidator.isVarExist(stringGameId) ||
                !commonValidator.isInteger(stringGameId[0])) {
            content.getRequestAttributes().put(PAGE_NOT_FOUND, true);
            return;
        }
        int gameId = Integer.valueOf(stringGameId[0]);

        TransactionManager manager = new TransactionManager();
        try {
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            manager.beginTransaction(competitionDAO, competitorDAO);

            Map<String, Object> competition =
                    competitionDAO.findActiveUpcomingGameById(gameId);

            if (competition.isEmpty()) {
                manager.rollback();
                manager.endTransaction();
                content.getRequestAttributes().put(PAGE_NOT_FOUND, true);
                return;
            }

            int compId = (int) competition.get(SQLFieldConstant.Competition.ID);
            List<Map<String, Object>> competitors = competitorDAO.findWithTeamByGameId(compId);
            competition.put(COMPETITORS, competitors);
            manager.commit();
            manager.endTransaction();

            content.getRequestAttributes().put(COMPETITION, competition);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Open concrete competition rollback error", e);
            }

            throw new ReceiverException(e);
        }
    }
}
