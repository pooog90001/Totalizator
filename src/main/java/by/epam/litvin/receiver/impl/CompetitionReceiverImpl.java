package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.*;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.*;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CompetitionReceiver;
import by.epam.litvin.type.ExpectResultType;
import by.epam.litvin.util.Packer;
import by.epam.litvin.validator.CompetitionValidator;
import by.epam.litvin.validator.UserValidator;
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
    public void getLiveCompetitions(RequestContent requestContent) throws ReceiverException {
        String[] stringId = requestContent.getRequestParameters().get(KIND_OF_SPORT_ID);

        int kindOfSportId = (stringId != null) ? Integer.valueOf(stringId[0]) : 0;

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CompetitionDAO competitionDAO = new CompetitionDAO();
            KindOfSportDAO kindOfSportDAO = new KindOfSportDAO();
            manager.beginTransaction(competitionDAO, kindOfSportDAO);

            List<Map<String, Object>> liveGameList = (kindOfSportId != 0) ?
                    competitionDAO.findFilteredLiveCompetitions(kindOfSportId) :
                    competitionDAO.findAllLiveCompetitions();

            List<KindOfSportEntity> kindOfSportList = kindOfSportDAO.findLiveGamesKindsOfSport();

            manager.commit();
            manager.endTransaction();

            Packer packer = new Packer();


            List<Map<String, Object>> orderedLiveGameList = packer.orderLiveAndUpcomingGames(liveGameList);

            requestContent.getRequestAttributes().put(LIVE_GAMES, orderedLiveGameList);
            requestContent.getRequestAttributes().put(KINDS_OF_SPORT_LIST, kindOfSportList);
            requestContent.getRequestAttributes().put(CURRENT_ID, kindOfSportId);


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

    public void filterLiveCompetitions(RequestContent requestContent) throws ReceiverException {
        String[] stringId = requestContent.getRequestParameters().get(KIND_OF_SPORT_ID);

        int kindOfSportId = (stringId != null) ? Integer.valueOf(stringId[0]) : 0;
        Gson gson = new Gson();
        JsonObject object = new JsonObject();

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CompetitionDAO competitionDAO = new CompetitionDAO();

            manager.beginTransaction(competitionDAO);

            List<Map<String, Object>> liveGameList = (kindOfSportId != 0) ?
                    competitionDAO.findFilteredLiveCompetitions(kindOfSportId) :
                    competitionDAO.findAllLiveCompetitions();


            manager.commit();
            manager.endTransaction();

            Packer packer = new Packer();

            List<Map<String, Object>> orderedLiveGameList = packer.orderLiveAndUpcomingGames(liveGameList);

            JsonElement element = gson.toJsonTree(orderedLiveGameList);
            object.add(LIVE_GAMES, element);
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
    public void openCompetitionSettings(RequestContent requestContent) throws ReceiverException {
        String[] wrongName = requestContent.getRequestParameters().get("wrongName");
        String[] wrongDate = requestContent.getRequestParameters().get("wrongDate");
        String[] wrongDateFormat = requestContent.getRequestParameters().get("wrongDateFormat");
        String[] wrongActive = requestContent.getRequestParameters().get("wrongActive");
        String[] createError = requestContent.getRequestParameters().get("createError");
        String[] deactivateError = requestContent.getRequestParameters().get("deactivateError");

        if (wrongName != null && !wrongName[0].isEmpty()) {
            requestContent.getRequestAttributes().put("wrongName", true);
        }
        if (wrongDate != null && !wrongDate[0].isEmpty()) {
            requestContent.getRequestAttributes().put("wrongDate", true);
        }
        if (wrongDateFormat != null && !wrongDateFormat[0].isEmpty()) {
            requestContent.getRequestAttributes().put("wrongDateFormat", true);
        }
        if (wrongActive != null && !wrongActive[0].isEmpty()) {
            requestContent.getRequestAttributes().put("wrongActive", true);
        }
        if (createError != null && !createError[0].isEmpty()) {
            requestContent.getRequestAttributes().put("createError", true);
        }
        if (createError != null && !deactivateError[0].isEmpty()) {
            requestContent.getRequestAttributes().put("deactivateError", true);
        }

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            KindOfSportDAO kindOfSportDAO = new KindOfSportDAO();
            CompetitionTypeDAO typeDAO = new CompetitionTypeDAO();
            CompetitionDAO competitionDAO = new CompetitionDAO();
            CompetitorDAO competitorDAO = new CompetitorDAO();
            CommonDAO commonDAO = new CommonDAO();
            manager.beginTransaction(kindOfSportDAO, typeDAO, competitorDAO, competitionDAO, commonDAO);
            List<KindOfSportEntity> kindOfSportList = kindOfSportDAO.findAll();
            List<CompetitionTypeEntity> competitionTypes = typeDAO.findAll();
            List<Map<String, Object>> upcomingActiveCompetitions = competitionDAO.findActiveUpcomingCompetitions();

            for (Map<String, Object> competition : upcomingActiveCompetitions) {
                int compId = (int) competition.get(SQLFieldConstant.Competition.ID);
                List<Map<String, Object>> competitors = competitorDAO.findWithCommandByCompetitionId(compId);

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
                    String resultTypeLess = ExpectResultType.LESS.toString();
                    int betsLessTotalCount =
                            commonDAO.findCountBetsOnCompetition(competitionId, resultTypeLess);
                    BigDecimal lessAmountOfMoney = betsLessTotalCount == 0 ?
                            new BigDecimal("0") :
                            commonDAO.findAmountOfMoneyOnCompetition(competitionId, resultTypeLess);

                    String resultTypeMore = ExpectResultType.MORE.toString();
                    int betsMoreTotalCount =
                            commonDAO.findCountBetsOnCompetition(competitionId, resultTypeMore);
                    BigDecimal moreAmountOfMoney = betsMoreTotalCount == 0 ?
                            new BigDecimal("0") :
                            commonDAO.findAmountOfMoneyOnCompetition(competitionId, resultTypeMore);

                    String resultTypeStandoff = ExpectResultType.EQUALS.toString();
                    int betsStandoffTotalCount =
                            commonDAO.findCountBetsOnCompetition(competitionId, resultTypeStandoff);
                    BigDecimal standoffAmountOfMoney = betsMoreTotalCount == 0 ?
                            new BigDecimal("0") :
                            commonDAO.findAmountOfMoneyOnCompetition(competitionId, resultTypeStandoff);

                    competition.put("betsLessTotalCount", betsLessTotalCount);
                    competition.put("betsMoreTotalCount", betsMoreTotalCount);
                    competition.put("betsStandoffTotalCount", betsStandoffTotalCount);
                    competition.put("lessAmountOfMoney", lessAmountOfMoney);
                    competition.put("moreAmountOfMoney", moreAmountOfMoney);
                    competition.put("standoffAmountOfMoney", standoffAmountOfMoney);
                }
            }


            manager.commit();
            manager.endTransaction();

            requestContent.getRequestAttributes().put("kindsOfSport", kindOfSportList);
            requestContent.getRequestAttributes().put("competitionTypes", competitionTypes);
            requestContent.getRequestAttributes().put("upcomingActiveCompetitions", upcomingActiveCompetitions);

        } catch (DAOException e) {
            if (manager != null) {
                try {
                    manager.rollback();
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Rollback open competition settings error", e);
                }
            }
            throw new ReceiverException(e);
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
        CompetitionValidator competitionValidator = new CompetitionValidator();
        UserValidator userValidator = new UserValidator();
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

        TransactionManager manager = null;
        try {
            boolean transactionAccess = true;
            manager = new TransactionManager();
            CompetitorDAO competitorDAO = new CompetitorDAO();
            CompetitionDAO competitionDAO = new CompetitionDAO();
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
                if(!competitorDAO.create(competitor)) {
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
            if (manager != null) {
                try {
                    manager.rollback();
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("create competition rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void editUpcomingActive(RequestContent content) throws ReceiverException {
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String[] stringLessTotalCoeff = content.getRequestParameters().get("lessTotalCoeff");
        String[] stringMoreTotalCoeff = content.getRequestParameters().get("moreTotalCoeff");
        String[] stringStandoffCoeff = content.getRequestParameters().get("standoffCoeff");
        String[] stringCompetitorCoeff = content.getRequestParameters().get("competitorCoeff");
        String[] stringCompetitorId = content.getRequestParameters().get("competitorId");
        String[] stringCompetitionId = content.getRequestParameters().get("competitionId");
        CompetitorEntity[] competitors = new CompetitorEntity[stringCompetitorCoeff.length];

        CompetitionValidator competitionValidator = new CompetitionValidator();
        UserValidator userValidator = new UserValidator();
        JsonObject object = new JsonObject();
        boolean isDataValid = true;

        if (!userValidator.isBookmaker(user)) {
            isDataValid = false;
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
            JsonElement element = new Gson().toJsonTree(false);
            object.add(SUCCESS, element);
            content.setAjaxResult(object);
            return;
        }

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CompetitorDAO competitorDAO = new CompetitorDAO();
            CompetitionDAO competitionDAO = new CompetitionDAO();
            manager.beginTransaction(competitorDAO, competitionDAO);
            boolean transactionSuccess = true;

            if (competitors.length == 2) {
                transactionSuccess = competitionDAO.update(competition);
            }

            for (CompetitorEntity competitor : competitors) {
                if (!competitorDAO.update(competitor)) {
                    transactionSuccess = false;
                }
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
            if (manager != null) {
                try {
                    manager.rollback();
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Rollback edit upcoming active competition error", e);
                }
            }
            throw new ReceiverException(e);
        }

    }

    @Override
    public void deleteUpcomingCompetition(RequestContent content) throws ReceiverException {
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        int competitionId = Integer.valueOf(content.getRequestParameters().get("competitionId")[0]);
        UserValidator userValidator = new UserValidator();
        JsonObject object = new JsonObject();
        boolean isDataValid = true;

        if (!userValidator.isBookmaker(user)) {
            isDataValid = false;
        }


        if (!isDataValid) {
            JsonElement element = new Gson().toJsonTree(false);
            object.add(SUCCESS, element);
            content.setAjaxResult(object);
            return;
        }

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CompetitorDAO competitorDAO = new CompetitorDAO();
            CompetitionDAO competitionDAO = new CompetitionDAO();
            BetDAO betDAO = new BetDAO();
            UserDAO userDAO = new UserDAO();
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
            if (manager != null) {
                try {
                    manager.rollback();
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Delete competition rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }

    }

    @Override
    public void deactivateCompetition(RequestContent content) throws ReceiverException {
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        int competitionId = Integer.valueOf(content.getRequestParameters().get("competitionId")[0]);
        UserValidator userValidator = new UserValidator();
        content.getSessionAttributes().remove(TEMPORARY);
        Map<String, Object> data = new HashMap<>();


        if (!userValidator.isBookmaker(user)) {
            content.getRequestAttributes().put(ACCESS_DENIED, true);
            return;
        }


        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CompetitionDAO competitionDAO = new CompetitionDAO();
            BetDAO betDAO = new BetDAO();
            UserDAO userDAO = new UserDAO();
            manager.beginTransaction(competitionDAO, betDAO, userDAO);
            boolean transactionSuccess = true;

            userDAO.returnMoneyForBets(competitionId);
            betDAO.deleteByCompetitionId(competitionId);

            if (!competitionDAO.changeActiveState(competitionId, false)) {
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
            if (manager != null) {
                try {
                    manager.rollback();
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Deactivate competition rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }

    }

    @Override
    public void activateCompetition(RequestContent content) throws ReceiverException {
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        int competitionId = Integer.valueOf(content.getRequestParameters().get("competitionId")[0]);
        UserValidator userValidator = new UserValidator();
        JsonObject object = new JsonObject();
        boolean isDataValid = true;

        if (!userValidator.isBookmaker(user)) {
            isDataValid = false;
        }


        if (!isDataValid) {
            JsonElement element = new Gson().toJsonTree(false);
            object.add(SUCCESS, element);
            content.setAjaxResult(object);
            return;
        }

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CompetitionDAO competitionDAO = new CompetitionDAO();
            BetDAO betDAO = new BetDAO();
            UserDAO userDAO = new UserDAO();
            manager.beginTransaction(competitionDAO, betDAO, userDAO);
            boolean transactionSuccess = true;

            userDAO.returnMoneyForBets(competitionId);
            betDAO.deleteByCompetitionId(competitionId);

            if (!competitionDAO.changeActiveState(competitionId, true)) {
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
            if (manager != null) {
                try {
                    manager.rollback();
                    manager.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Activate competition rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }

    }


    @Override
    public void getUpcomingCompetition(RequestContent requestContent) {

    }

    @Override
    public void getPastCompetition(RequestContent requestContent) {

    }
}
