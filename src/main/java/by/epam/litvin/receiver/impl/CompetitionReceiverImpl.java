package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.*;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.dao.impl.*;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CompetitionReceiver;
import by.epam.litvin.type.ExpectResultType;
import by.epam.litvin.validator.CommonValidator;
import by.epam.litvin.validator.CompetitorValidator;
import by.epam.litvin.validator.UserValidator;
import by.epam.litvin.validator.impl.CommonValidatorImpl;
import by.epam.litvin.validator.impl.CompetitionValidatorImpl;
import by.epam.litvin.validator.impl.CompetitorValidatorImpl;
import by.epam.litvin.validator.impl.UserValidatorImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;

public class CompetitionReceiverImpl implements CompetitionReceiver {


    @Override
    public void openCompetitionSettings(RequestContent requestContent) throws ReceiverException {
        CommonValidator commonValidator = new CommonValidatorImpl();
        String[] errorNames = {"wrongName", "wrongDate", "wrongDateFormat",
                "wrongActive", "createError", "deactivateError", "fillError", "wrongNumberFormat"};

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

            requestContent.getRequestAttributes().put("kindsOfSport", kindOfSportList);
            requestContent.getRequestAttributes().put("competitionTypes", competitionTypes);
            requestContent.getRequestAttributes().put("upcomingActiveCompetitions", upcomingActiveCompetitions);
            requestContent.getRequestAttributes().put("upcomingDeactiveCompetitions", upcomingDeactiveCompetitions);
            requestContent.getRequestAttributes().put("nowActiveCompetitions", nowActiveCompetitions);
            requestContent.getRequestAttributes().put("nowDeactiveCompetitions", nowDeactiveCompetitions);
            requestContent.getRequestAttributes().put("pastUnfilledCompetitions", pastUnfilledCompetitions);
            requestContent.getRequestAttributes().put("pastFilledCompetitions", pastFilledCompetitions);
            requestContent.getRequestAttributes().put("pastDeactiveCompetitions", pastDeactivatedCompetitions);

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
            List<Map<String, Object>> competitors = competitorDAO.findWithCommandByGameId(compId);

            for (Map<String, Object> competitor : competitors) {
                int competitorId = (int) competitor.get("competitor_id");
                int betsCount = commonDAO.findCountBetsOnCompetitor(competitorId);
                BigDecimal amountOfMoney = betsCount == 0 ?
                        new BigDecimal("0") :
                        commonDAO.findAmountOfMoneyOnCompetitor(competitorId);

                competitor.put("betsCount", betsCount);
                competitor.put("amountOfMoney", amountOfMoney);

            }
            competition.put("competitors", competitors);

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

                competition.put("betsLessTotalCount", betsLessTotalCount);
                competition.put("betsMoreTotalCount", betsMoreTotalCount);
                competition.put("betsStandoffTotalCount", betsStandoffTotalCount);
                competition.put("lessAmountOfMoney", lessAmountOfMoney);
                competition.put("moreAmountOfMoney", moreAmountOfMoney);
                competition.put("standoffAmountOfMoney", standoffAmountOfMoney);
            }
        }
    }

    private void extractWithoutStatistic(List<Map<String, Object>> competitions,
                                         CompetitorDAOImpl competitorDAO) throws DAOException {

        for (Map<String, Object> competition : competitions) {
            int compId = (int) competition.get(SQLFieldConstant.Competition.ID);
            List<Map<String, Object>> competitors = competitorDAO.findWithCommandByGameId(compId);
            competition.put("competitors", competitors);
        }
    }

    @Override
    public void createCompetition(RequestContent content) throws ReceiverException {
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String competitionName = content.getRequestParameters().get("competitionName")[0].trim();
        int typeId = Integer.valueOf(content.getRequestParameters().get("typeId")[0]);
        String dateStart = content.getRequestParameters().get("dateStart")[0];
        String timeStart = content.getRequestParameters().get("timeStart")[0];
        String dateFinish = content.getRequestParameters().get("dateFinish")[0];
        String timeFinish = content.getRequestParameters().get("timeFinish")[0];
        String[] stringIsActive = content.getRequestParameters().get("isActive");
        String[] commandsId = content.getRequestParameters().get("commandId");
        String[] competitorsCoeff = content.getRequestParameters().get("competitorCoeff");
        boolean isActive = (stringIsActive != null && "on".equals(stringIsActive[0]));
        String[] stringTotal = content.getRequestParameters().get("total");
        String[] stringLessTotalCoeff = content.getRequestParameters().get("less_total_coeff");
        String[] stringMoreTotalCoeff = content.getRequestParameters().get("more_total_coeff");
        String[] stringStandoffCoeff = content.getRequestParameters().get("standoff_coeff");
        CompetitorEntity[] competitors = new CompetitorEntity[commandsId.length];
        CompetitionValidatorImpl competitionValidator = new CompetitionValidatorImpl();
        UserValidatorImpl userValidator = new UserValidatorImpl();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Map<String, Object> data = new HashMap<>();
        Date dStart;
        Date dFinish;
        content.getSessionAttributes().remove(TEMPORARY);

        if (!userValidator.isBookmaker(user) && isActive) {
            content.getRequestAttributes().put(ACCESS_DENIED, true);
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


        for (int i = 0; i < competitors.length; i++) {
            CompetitorEntity competitor = new CompetitorEntity();
            competitor.setCommandId(Integer.valueOf(commandsId[i]));
            competitor.setWinCoeff(competitorsCoeff == null ? null :
                    competitionValidator.checkStringCoeff(competitorsCoeff[i]));
            competitors[i] = competitor;
        }

        if (isActive) {
            boolean isValid = true;

            if (commandsId.length == 2) {
                if (total == null || lessTotalCoeff == null ||
                        moreTotalCoeff == null || standoffCoeff == null) {
                    isValid = false;
                }
            }
            for (CompetitorEntity competitor : competitors) {
                if (competitor == null) {
                    isValid = false;
                }
            }
            if (!isValid) {
                data.put("wrongActive", true);
                content.getSessionAttributes().put(TEMPORARY, data);
                return;
            }
        }

        try {
            dStart = formatter.parse(dateStart.concat(" ").concat(timeStart));
            dFinish = formatter.parse(dateFinish.concat(" ").concat(timeFinish));

        } catch (ParseException e) {
            data.put("wrongDateFormat", true);
            content.getSessionAttributes().put(TEMPORARY, data);
            return;
        }

        Date now = new Date();
        if (dStart.before(now) || dStart.after(dFinish)) {
            data.put("wrongDate", true);
            content.getSessionAttributes().put(TEMPORARY, data);
            return;
        }

        if (!competitionValidator.isNameValid(competitionName)) {
            data.put("wrongName", true);
            content.getSessionAttributes().put(TEMPORARY, data);
            return;
        }

        CompetitionEntity competition = new CompetitionEntity();
        competition.setDateStart(dStart);
        competition.setDateFinish(dFinish);
        competition.setActive(isActive);
        competition.setName(competitionName);
        competition.setTypeId(typeId);

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
            competitorEntity.setCommandId(0);

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
                data.put("createError", true);
                content.getSessionAttributes().put(TEMPORARY, data);
                return;
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
        String[] stringLessTotalCoeff = content.getRequestParameters().get("lessTotalCoeff");
        String[] stringMoreTotalCoeff = content.getRequestParameters().get("moreTotalCoeff");
        String[] stringStandoffCoeff = content.getRequestParameters().get("standoffCoeff");
        String[] stringCompetitorCoeff = content.getRequestParameters().get("competitorCoeff");
        String[] stringCompetitorId = content.getRequestParameters().get("competitorId");
        String[] stringCompetitionId = content.getRequestParameters().get("competitionId");
        CompetitorEntity[] competitors = new CompetitorEntity[stringCompetitorCoeff.length];

        CompetitionValidatorImpl competitionValidator = new CompetitionValidatorImpl();
        UserValidatorImpl userValidator = new UserValidatorImpl();
        boolean isDataValid = true;

        if (!userValidator.isBookmaker(user)) {
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
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String[] stringTotal = content.getRequestParameters().get("total");
        String[] stringLessTotalCoeff = content.getRequestParameters().get("lessTotalCoeff");
        String[] stringMoreTotalCoeff = content.getRequestParameters().get("moreTotalCoeff");
        String[] stringStandoffCoeff = content.getRequestParameters().get("standoffCoeff");
        String[] stringCompetitorCoeff = content.getRequestParameters().get("competitorCoeff");
        String[] stringCompetitorId = content.getRequestParameters().get("competitorId");
        String[] stringCompetitionId = content.getRequestParameters().get("competitionId");
        String[] stringCompetitionName = content.getRequestParameters().get("competitionName");
        CompetitorEntity[] competitors = new CompetitorEntity[stringCompetitorCoeff.length];

        CompetitionValidatorImpl competitionValidator = new CompetitionValidatorImpl();
        UserValidatorImpl userValidator = new UserValidatorImpl();
        boolean isDataValid = true;

        if (!userValidator.isBookmaker(user)) {
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


        for (int i = 0; i < competitors.length; i++) {
            CompetitorEntity competitor = new CompetitorEntity();
            competitor.setId(Integer.valueOf(stringCompetitorId[i]));
            competitor.setWinCoeff(competitionValidator.checkStringCoeff(stringCompetitorCoeff[i]));
            competitors[i] = competitor;
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

        if (!competitionValidator.isNameValid(competition.getName())) {
            isDataValid = false;
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
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        int competitionId = Integer.valueOf(content.getRequestParameters().get("competitionId")[0]);
        UserValidatorImpl userValidator = new UserValidatorImpl();
        JsonObject object = new JsonObject();

        if (!userValidator.isBookmaker(user)) {
            return;
        }

        TransactionManager manager = new TransactionManager();
        try {
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            BetDAOImpl betDAO = new BetDAOImpl();
            UserDAOImpl userDAO = new UserDAOImpl();
            manager.beginTransaction(competitorDAO, competitionDAO, betDAO, userDAO);
            boolean transactionSuccess = true;

            userDAO.returnMoneyForBets(competitionId);
            betDAO.deleteByCompetitionId(competitionId);
            competitorDAO.deleteByCompetitionId(competitionId);

            if (!competitionDAO.delete(competitionId)) {
                transactionSuccess = false;
            }

            if (transactionSuccess) {
                manager.commit();
            } else {
                manager.rollback();
            }

            manager.endTransaction();
            JsonElement element = new Gson().toJsonTree(transactionSuccess);
            object.add(SUCCESS, element);
            content.setAjaxResult(object);

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
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        int competitionId = Integer.valueOf(content.getRequestParameters().get("competitionId")[0]);
        UserValidatorImpl userValidator = new UserValidatorImpl();
        JsonObject object = new JsonObject();

        if (!userValidator.isBookmaker(user)) {
            return;
        }

        TransactionManager manager = new TransactionManager();
        try {
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            BetDAOImpl betDAO = new BetDAOImpl();
            manager.beginTransaction(competitorDAO, competitionDAO, betDAO);
            boolean transactionSuccess = true;

            betDAO.deleteByCompetitionId(competitionId);
            competitorDAO.deleteByCompetitionId(competitionId);

            if (!competitionDAO.delete(competitionId)) {
                transactionSuccess = false;
            }

            if (transactionSuccess) {
                manager.commit();
            } else {
                manager.rollback();
            }

            manager.endTransaction();
            JsonElement element = new Gson().toJsonTree(transactionSuccess);
            object.add(SUCCESS, element);
            content.setAjaxResult(object);

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
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        int competitionId = Integer.valueOf(content.getRequestParameters().get("competitionId")[0]);
        boolean state = Boolean.valueOf(content.getRequestParameters().get("state")[0]);
        UserValidatorImpl userValidator = new UserValidatorImpl();
        content.getSessionAttributes().remove(TEMPORARY);
        Map<String, Object> data = new HashMap<>();


        if (!userValidator.isBookmaker(user)) {
            return;
        }


        TransactionManager manager = new TransactionManager();
        try {
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            BetDAOImpl betDAO = new BetDAOImpl();
            UserDAOImpl userDAO = new UserDAOImpl();
            manager.beginTransaction(competitionDAO, betDAO, userDAO);
            boolean transactionSuccess = true;

            userDAO.returnMoneyForBets(competitionId);
            betDAO.deleteByCompetitionId(competitionId);

            if (!competitionDAO.updateActiveState(competitionId, state)) {
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
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String[] stringCompetitorResult = content.getRequestParameters().get("competitorResult");
        String[] stringCompetitorId = content.getRequestParameters().get("competitorId");
        int competitionId = Integer.valueOf(content.getRequestParameters().get("competitionId")[0]);
        CompetitorEntity[] competitors = new CompetitorEntity[stringCompetitorResult.length];
        Map<String, Object> data = new HashMap<>();
        boolean isValid = true;

        if (!userValidator.isBookmaker(user)) {
            content.getRequestAttributes().put(ACCESS_DENIED, true);
        }

        for (String result : stringCompetitorResult) {
            if (result.trim().isEmpty()) {
                isValid = false;
                break;
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
            data.put("wrongNumberFormat", true);
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
                betDAO.updateGameResultAndPayMoney(competitionId, competitors[0].getResult(), competitors[1].getResult());
            }
            if (isTransactionSuccess) {
                isTransactionSuccess =
                        competitionDAO.updateResultFillState(competitionId, true);
            }
            if (!isTransactionSuccess) {
                data.put("fillError", true);
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
    public void openUpcomingCompetition(RequestContent requestContent) throws ReceiverException {
        String[] page = requestContent.getRequestParameters().get("pageNumber");
        int startIndex = (page != null) ? Integer.valueOf(page[0]) : 1;
        startIndex = (startIndex - 1) * COUNT_COMPETITIONS_ON_PAGE;
        List<Map<String, Object>> upcomingGames;
        TransactionManager handler = new TransactionManager();
        try {
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();

            handler.beginTransaction(competitionDAO, competitorDAO);

            upcomingGames = competitionDAO.findLimitUpcomingGames(
                    startIndex, COUNT_COMPETITIONS_ON_PAGE, true);

            extractWithoutStatistic(upcomingGames, competitorDAO);

            int gamesCount = competitionDAO.findUpcomingGamesCount(true);

            handler.commit();
            handler.endTransaction();

            requestContent.getRequestAttributes().put(UPCOMING_GAMES, upcomingGames);
            requestContent.getRequestAttributes().put("limit", COUNT_COMPETITIONS_ON_PAGE);
            requestContent.getRequestAttributes().put("gamesCount", gamesCount);

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
    public void openPastCompetition(RequestContent requestContent) throws ReceiverException {
        String[] page = requestContent.getRequestParameters().get("pageNumber");
        int startIndex = (page != null) ? Integer.valueOf(page[0]) : 1;
        startIndex = (startIndex - 1) * COUNT_COMPETITIONS_ON_PAGE;
        List<Map<String, Object>> pastGames;
        TransactionManager handler = new TransactionManager();
        try {
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            handler.beginTransaction(competitionDAO, competitorDAO);

            pastGames = competitionDAO.findLimitPastGames(startIndex,
                    COUNT_COMPETITIONS_ON_PAGE, true, true );

            extractWithoutStatistic(pastGames, competitorDAO);

            int gamesCount = competitionDAO
                    .findPastGamesCount(true, true);

            handler.commit();
            handler.endTransaction();

            requestContent.getRequestAttributes().put(PAST_GAMES, pastGames);
            requestContent.getRequestAttributes().put("limit", COUNT_COMPETITIONS_ON_PAGE);
            requestContent.getRequestAttributes().put("gamesCount", gamesCount);

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
        int typeId = Integer.valueOf(content.getRequestParameters().get("typeId")[0]);
        int sportId = Integer.valueOf(content.getRequestParameters().get("sportId")[0]);

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
            content.getRequestAttributes().put("kindOfSport", sport);
            content.getRequestAttributes().put("competitionType", type);

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
        int competitionId = Integer.valueOf(content.getRequestParameters().get("competitionId")[0]);

        TransactionManager manager = new TransactionManager();
        try {
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            manager.beginTransaction(competitionDAO, competitorDAO);

            Map<String, Object> competition =
                    competitionDAO.findActiveUpcomingGameById(competitionId);

            if (competition != null) {
                int compId = (int) competition.get(SQLFieldConstant.Competition.ID);
                List<Map<String, Object>> competitors = competitorDAO.findWithCommandByGameId(compId);
                competition.put("competitors", competitors);
            }

            manager.commit();
            manager.endTransaction();

            content.getRequestAttributes().put("competition", competition);

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
