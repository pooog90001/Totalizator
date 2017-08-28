package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.BetEntity;
import by.epam.litvin.bean.CompetitionEntity;
import by.epam.litvin.bean.CompetitorEntity;
import by.epam.litvin.bean.UserEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.dao.impl.BetDAOImpl;
import by.epam.litvin.dao.impl.CompetitionDAOImpl;
import by.epam.litvin.dao.impl.CompetitorDAOImpl;
import by.epam.litvin.dao.impl.UserDAOImpl;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.BetReceiver;
import by.epam.litvin.type.ExpectResultType;
import by.epam.litvin.util.Formatter;
import by.epam.litvin.validator.CompetitionValidator;
import by.epam.litvin.validator.UserValidator;
import by.epam.litvin.validator.impl.BetValidatorImpl;
import by.epam.litvin.validator.impl.CommonValidatorImpl;
import by.epam.litvin.validator.impl.CompetitionValidatorImpl;
import by.epam.litvin.validator.impl.UserValidatorImpl;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.List;

import static by.epam.litvin.constant.GeneralConstant.*;

public class BetReceiverImpl implements BetReceiver {
    @Override
    public void createBet(RequestContent content) throws ReceiverException {
        String[] stringOnCommand = content.getRequestParameters().get(ON_COMMAND);
        String[] stringCompetitionId = content.getRequestParameters().get(COMPETITION_ID);
        String[] stringValue = content.getRequestParameters().get(VALUE);
        String[] stringCash = content.getRequestParameters().get(CASH);
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        UserValidator userValidator = new UserValidatorImpl();
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        CompetitionValidator competitionValidator = new CompetitionValidatorImpl();
        BetValidatorImpl betValidator = new BetValidatorImpl();
        Formatter formatter = new Formatter();

        if (!userValidator.isUser(user)) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(ACCESS_DENIED, new Gson().toJsonTree(true));
            return;
        }

        if (!commonValidator.isVarExist(stringOnCommand) ||
                !commonValidator.isVarExist(stringCash)) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(DATA_EMPTY, new Gson().toJsonTree(true));
            return;
        }

        BigDecimal cash = formatter.formatToCash(new BigDecimal(stringCash[0]));
        int competitionId = Integer.valueOf(stringCompetitionId[0]);
        boolean onCommand = Boolean.valueOf(stringOnCommand[0]);
        int competitorId = 0;
        ExpectResultType expectedResult = null;

        if (onCommand) {
            competitorId = Integer.valueOf(stringValue[0]);
        } else {
            expectedResult = ExpectResultType.valueOf(stringValue[0].toUpperCase());
        }

        if (!betValidator.checkBetSize(cash)) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(WRONG_CASH, new Gson().toJsonTree(true));
            return;
        }

        TransactionManager manager = new TransactionManager();
        try {
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            BetDAOImpl betDAO = new BetDAOImpl();
            UserDAOImpl userDAO = new UserDAOImpl();
            boolean isTransactionSuccess = true;
            manager.beginTransaction(competitionDAO, betDAO, competitorDAO, userDAO);

            UserEntity actualUser = userDAO.findEntityById(user.getId());

            if (cash.compareTo(actualUser.getCash()) == 1) {
                content.setAjaxSuccess(false);
                content.getAjaxResult().add(LITTLE_MONEY, new Gson().toJsonTree(true));
                manager.rollback();
                manager.endTransaction();
                return;
            }

            CompetitionEntity game = competitionDAO.findEntityById(competitionId);

            if (!competitionValidator.isValidForBet(game)) {
                manager.rollback();
                manager.endTransaction();
                content.getAjaxResult().add(WRONG_COMPETITION,
                        new Gson().toJsonTree(true));
                return;
            }
            List<CompetitorEntity> competitors = competitorDAO.findByGameId(competitionId);
            int finalCompetitionId = competitorId;

            if (!onCommand) {
                competitorId = competitors.stream()
                        .findFirst().filter(c -> c.getCommandId() == finalCompetitionId)
                        .orElse(null).getId();
            } else {
                isTransactionSuccess = competitors.stream()
                        .anyMatch(c -> c.getId() == finalCompetitionId);
            }

            if (isTransactionSuccess) {
                BetEntity bet = new BetEntity(user.getId(), cash, competitorId, expectedResult);
                isTransactionSuccess = betDAO.create(bet);
            }
            if (isTransactionSuccess) {
                actualUser.setCash(actualUser.getCash().subtract(cash));
                isTransactionSuccess = userDAO.updateCash(actualUser);
            }

            content.setAjaxSuccess(isTransactionSuccess);

            if (isTransactionSuccess) {
                manager.commit();
                content.getSessionAttributes().put(USER, actualUser);
            } else {
                content.getAjaxResult().add(WRONG_CREATION, new Gson().toJsonTree(true));
                manager.rollback();
            }


            manager.endTransaction();

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("create bet rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }


}
