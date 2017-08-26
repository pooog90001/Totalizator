package by.epam.litvin.receiver.impl;


import by.epam.litvin.bean.CompetitionEntity;
import by.epam.litvin.bean.UserEntity;
import by.epam.litvin.constant.RequestNameConstant;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.dao.impl.*;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.UserReceiver;
import by.epam.litvin.type.UploadType;
import by.epam.litvin.type.UserType;
import by.epam.litvin.util.Formatter;
import by.epam.litvin.util.StringEncoder;
import by.epam.litvin.validator.impl.CommonValidatorImpl;
import by.epam.litvin.validator.impl.UserValidatorImpl;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;
import static by.epam.litvin.constant.GeneralConstant.USER;
import static by.epam.litvin.constant.GeneralConstant.WRONG_DATA;
import static by.epam.litvin.constant.RequestNameConstant.*;

public class UserReceiverImpl implements UserReceiver {

    @Override
    public void signUp(RequestContent requestContent) throws ReceiverException {
        UserValidatorImpl validator = new UserValidatorImpl();
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        String[] name = requestContent.getRequestParameters().get(NAME);
        String[] email = requestContent.getRequestParameters().get(EMAIL);
        String[] password = requestContent.getRequestParameters().get(PASSWORD);
        String[] repeatPassword = requestContent.getRequestParameters().get(REPEAT_PASSWORD);
        String confirmURL;
        String dbPassword;
        boolean isValidData = true;

        requestContent.getRequestAttributes().put(NAME, name);
        requestContent.getRequestAttributes().put(EMAIL, email);
        requestContent.getRequestAttributes().put(PASSWORD, password);
        requestContent.getRequestAttributes().put(REPEAT_PASSWORD, repeatPassword);

        if (!commonValidator.isVarExist(password) || !validator.checkPassword(password[0])) {
            requestContent.getRequestAttributes().put(WRONG_PASSWORD, true);
            return;

        }
        if (!commonValidator.isVarExist(repeatPassword) || !password[0].equals(repeatPassword[0])) {
            requestContent.getRequestAttributes().put(WRONG_REPEAT_PASSWORD, true);
            isValidData = false;

        }
        if (!commonValidator.isVarExist(email) || !validator.checkEmail(email[0])) {
            requestContent.getRequestAttributes().put(WRONG_EMAIL, true);
            isValidData = false;

        }
        if (!commonValidator.isVarExist(name) || !validator.checkName(name[0])) {
            requestContent.getRequestAttributes().put(WRONG_NAME, true);
            isValidData = false;

        }
        if (!isValidData) {
            return;
        }

        dbPassword = StringEncoder.encode(password[0]);
        confirmURL = StringEncoder.encode(email[0]);
        UserEntity user = new UserEntity();
        user.setName(name[0]);
        user.setEmail(email[0]);
        user.setPassword(dbPassword);
        user.setConfirmUrl(confirmURL);

        TransactionManager handler = new TransactionManager();
        try {
            UserDAOImpl userDao = new UserDAOImpl();
            handler.beginTransaction(userDao);

            if (userDao.create(user)) {
                handler.commit();

            } else {
                requestContent.getRequestAttributes().put(EMAIL_EXISTS, true);
            }

            handler.endTransaction();

        } catch (DAOException e) {
            try {
                handler.rollback();
                handler.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Sign up rollback error", e);
            }
            throw new ReceiverException(e);
        }

    }

    @Override
    public void signIn(RequestContent requestContent) throws ReceiverException {
        String[] email = requestContent.getRequestParameters().get(EMAIL);
        String[] password = requestContent.getRequestParameters().get(PASSWORD);
        String dbPassword;

        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        UserValidatorImpl validator = new UserValidatorImpl();
        requestContent.getRequestAttributes().put(EMAIL, email);
        requestContent.getRequestAttributes().put(PASSWORD, password);

        if (!commonValidator.isVarExist(password) || !commonValidator.isVarExist(email) ||
                !validator.checkPassword(password[0]) || !validator.checkEmail(email[0])) {
            requestContent.getRequestAttributes().put(WRONG_DATA, true);
            return;
        }

        dbPassword = StringEncoder.encode(password[0]);
        UserEntity user = new UserEntity();
        user.setEmail(email[0]);
        user.setPassword(dbPassword);

        TransactionManager handler = new TransactionManager();
        try {
            UserDAOImpl userDao = new UserDAOImpl();
            handler.beginTransaction(userDao);
            UserEntity foundUser = userDao.findUser(user);

            handler.commit();
            handler.endTransaction();

            if (foundUser != null) {
                if (foundUser.getIsBlocked()) {
                    requestContent.getSessionAttributes().put(TEXT, foundUser.getBlockedText());
                    requestContent.getRequestAttributes().put(IS_BLOCKED, true);

                } else if (!foundUser.getIsConfirm()) {
                    requestContent.getRequestAttributes().put(IS_NOT_CONFIRMED, true);

                } else {
                    requestContent.getSessionAttributes().put(USER, foundUser);
                }
            } else {
                requestContent.getRequestAttributes().put(WRONG_DATA, true);
            }

        } catch (DAOException e) {
            try {
                handler.rollback();
                handler.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Sign in rollback error", e);
            }
            throw new ReceiverException(e);
        }

    }

    @Override
    public void signOut(RequestContent requestContent) {
        requestContent.getSessionAttributes().remove(RequestNameConstant.USER);
    }

    @Override
    public void openUserSettings(RequestContent content) throws ReceiverException {
        Formatter formatter = new Formatter();
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        String[] stringPage = content.getRequestParameters().get(PAGE_NUMBER);
        int page = 1;

        if (commonValidator.isVarExist(stringPage)) {
            if (commonValidator.isPageNumber(stringPage[0])) {
                page = Integer.valueOf(stringPage[0]);

            } else {
                content.getRequestAttributes().put(PAGE_NOT_FOUND, true);
                return;
            }
        }
        int startIndex = formatter.formatToStartIndex(page, COUNT_USERS_ON_PAGE);

        TransactionManager manager = new TransactionManager();
        try {
            UserDAOImpl userDAO = new UserDAOImpl();
            manager.beginTransaction(userDAO);
            List<UserEntity> userList = userDAO.findLimit(startIndex, COUNT_USERS_ON_PAGE);
            int usersCount = userDAO.findUsersCount();
            manager.commit();
            manager.endTransaction();

            content.getRequestAttributes().put("userList", userList);
            content.getRequestAttributes().put("limit", COUNT_USERS_ON_PAGE);
            content.getRequestAttributes().put("usersCount", usersCount);
            content.getRequestAttributes().put("userImagePath", UploadType.AVATARS.getUploadFolder());

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Open users setting rollback error", e);
            }

            throw new ReceiverException(e);
        }
    }

    @Override
    public void openProfile(RequestContent content) throws ReceiverException {
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);

        if (user == null) {
            content.getRequestAttributes().put(ACCESS_DENIED, true);
            return;
        }

        TransactionManager manager = new TransactionManager();
        try {
            UserDAOImpl userDAO = new UserDAOImpl();
            CompetitionDAOImpl competitionDAO = new CompetitionDAOImpl();
            CompetitorDAOImpl competitorDAO = new CompetitorDAOImpl();
            BetDAOImpl betDAO = new BetDAOImpl();
            CommonDAOImpl commonDAO = new CommonDAOImpl();
            manager.beginTransaction(userDAO, betDAO, commonDAO, competitionDAO, competitorDAO);
            UserEntity actualUser = userDAO.findEntityById(user.getId());

            if (actualUser.getIsBlocked()) {

                content.getRequestAttributes().put(IS_BLOCKED, true);
                content.getSessionAttributes().put(TEXT, user.getBlockedText());
                content.getSessionAttributes().remove(USER);
                return;
            }

            List<Map<String, Object>> pastBetsAndGames = betDAO.findPastBetsByUserId(user.getId());
            List<Map<String, Object>> upcomingBetsAndGames = betDAO.findUpcomingBetsByUserId(user.getId());
            extractCompetitors(pastBetsAndGames, competitorDAO);
            extractCompetitors(upcomingBetsAndGames, competitorDAO);

            manager.commit();
            manager.endTransaction();

            content.getSessionAttributes().put(USER, actualUser);
            content.getRequestAttributes().put("pastBetsAndGames", pastBetsAndGames);
            content.getRequestAttributes().put("upcomingBetsAndGames", upcomingBetsAndGames);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Open users setting rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    private void extractCompetitors(List<Map<String, Object>> betsAndGames,
                                    CompetitorDAOImpl competitorDAO) throws DAOException {
        for (Map<String, Object> betAndGame : betsAndGames) {
            CompetitionEntity game = (CompetitionEntity) betAndGame.get("competition");
            List<Map<String, Object>> competitors = competitorDAO.findWithCommandByGameId(game.getId());
            betAndGame.put("competitors", competitors);
        }
    }

    @Override
    public void changeRole(RequestContent content) throws ReceiverException {
        int userId = Integer.valueOf(content.getRequestParameters().get("userId")[0]);
        UserType userType = UserType.valueOf(content.getRequestParameters().get("userType")[0]);
        UserEntity currentUser = (UserEntity) content.getSessionAttributes().get("user");

        if (!UserType.BOOKMAKER.equals(currentUser.getType()) || currentUser.getId() == userId) {
            content.setAjaxSuccess(false);
            return;
        }

        UserEntity updateUser = new UserEntity();
        updateUser.setId(userId);
        updateUser.setType(userType);


        TransactionManager manager = new TransactionManager();
        try {
            UserDAOImpl userDAO = new UserDAOImpl();
            manager.beginTransaction(userDAO);
            boolean isUpdated = userDAO.updateRole(updateUser);
            content.setAjaxSuccess(isUpdated);
            if (isUpdated) {
                manager.commit();
            } else {
                manager.rollback();
            }
            manager.endTransaction();


        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Update user role rollback error", e);
            }
            throw new ReceiverException(e);
        }

    }

    @Override
    public void changeLock(RequestContent content) throws ReceiverException {
        int userId = Integer.valueOf(content.getRequestParameters().get("userId")[0]);
        boolean isBlocked = !Boolean.valueOf(content.getRequestParameters().get("blockState")[0]);
        String[] arrayText = content.getRequestParameters().get("textBlock");
        String blockedText = arrayText == null ? "" : arrayText[0].trim();
        UserEntity currentUser = (UserEntity) content.getSessionAttributes().get("user");

        if (!UserType.BOOKMAKER.equals(currentUser.getType()) ||
                currentUser.getId() == userId || (isBlocked && blockedText.isEmpty())) {
            content.setAjaxSuccess(false);
            return;
        }

        UserEntity updateUser = new UserEntity();
        updateUser.setId(userId);
        updateUser.setBlocked(isBlocked);
        updateUser.setBlockedText(blockedText);

        TransactionManager manager = new TransactionManager();
        try {
            UserDAOImpl userDAO = new UserDAOImpl();
            manager.beginTransaction(userDAO);
            boolean isUpdated = userDAO.updateLock(updateUser);

            if (isUpdated) {
                manager.commit();
            } else {
                manager.rollback();
            }
            manager.endTransaction();

            content.setAjaxSuccess(isUpdated);
        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Update user lock rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void addMoney(RequestContent content) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        UserValidatorImpl userValidator = new UserValidatorImpl();
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String[] stringAddCash = content.getRequestParameters().get(CASH);
        boolean isDataValid = true;

        if (user == null) {
            content.getAjaxResult().add(ACCESS_DENIED, new Gson().toJsonTree(true));
            isDataValid = false;
        }
        if (isDataValid) {
            if (!commonValidator.isVarExist(stringAddCash) ||
                    !commonValidator.isInteger(stringAddCash[0])) {
                content.getAjaxResult().add(WRONG_DATA, new Gson().toJsonTree(true));
                isDataValid = false;
            }
        }
        if (!isDataValid) {
            content.setAjaxSuccess(false);
            return;
        }
        int cash = Integer.valueOf(stringAddCash[0]);

        if (!userValidator.isIsCashValid(cash)) {
            content.getAjaxResult().add(WRONG_DATA, new Gson().toJsonTree(true));
            content.setAjaxSuccess(false);
            return;
        }

        TransactionManager manager = new TransactionManager();
        try {
            UserDAOImpl userDAO = new UserDAOImpl();
            manager.beginTransaction(userDAO);
            UserEntity actualUser = userDAO.findEntityById(user.getId());

            if (actualUser.getIsBlocked()) {
                manager.commit();
                manager.endTransaction();
                content.setAjaxSuccess(false);
                content.getAjaxResult().add(ACCESS_DENIED, new Gson().toJsonTree(true));
                return;
            }

            actualUser.setCash(actualUser.getCash().add(new BigDecimal(cash)));
            boolean isUpdated = userDAO.updateCash(actualUser);

            if (isUpdated) {
                manager.commit();
                content.getSessionAttributes().put(USER, actualUser);

            } else {
                manager.rollback();
                content.getAjaxResult().add("updateError", new Gson().toJsonTree(true));
            }

            content.setAjaxSuccess(isUpdated);
            manager.endTransaction();

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Open users setting rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void withdrawMoney(RequestContent content) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        UserValidatorImpl userValidator = new UserValidatorImpl();
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String[] stringAddCash = content.getRequestParameters().get(CASH);
        boolean isDataValid = true;

        if (user == null) {
            content.getAjaxResult().add(ACCESS_DENIED, new Gson().toJsonTree(true));
            isDataValid = false;
        }
        if (isDataValid) {
            if (!commonValidator.isVarExist(stringAddCash) ||
                    !commonValidator.isInteger(stringAddCash[0])) {
                content.getAjaxResult().add(WRONG_DATA, new Gson().toJsonTree(true));
                isDataValid = false;
            }
        }
        if (!isDataValid) {
            content.setAjaxSuccess(false);
            return;
        }
        int cash = Integer.valueOf(stringAddCash[0]);

        if (!userValidator.isIsCashValid(cash)) {
            content.getAjaxResult().add(WRONG_DATA, new Gson().toJsonTree(true));
            content.setAjaxSuccess(false);
            return;
        }

        TransactionManager manager = new TransactionManager();
        try {
            UserDAOImpl userDAO = new UserDAOImpl();
            manager.beginTransaction(userDAO);
            UserEntity actualUser = userDAO.findEntityById(user.getId());
            boolean isUserDataValid = true;

            if (actualUser.getIsBlocked()) {
                isUserDataValid = false;
                content.getAjaxResult().add(ACCESS_DENIED, new Gson().toJsonTree(true));
            }
            if (isUserDataValid) {
                if (actualUser.getCash().compareTo(new BigDecimal(cash)) == -1) {
                    content.getAjaxResult().add(LITTLE_MONEY, new Gson().toJsonTree(true));
                    isUserDataValid = false;
                }
            }
            if (!isUserDataValid) {
                manager.commit();
                manager.endTransaction();
                content.setAjaxSuccess(false);
                return;
            }

            actualUser.setCash(actualUser.getCash().subtract(new BigDecimal(cash)));
            boolean isUpdated = userDAO.updateCash(actualUser);

            if (isUpdated) {
                manager.commit();
                content.getSessionAttributes().put(USER, actualUser);

            } else {
                manager.rollback();
                content.getAjaxResult().add("updateError", new Gson().toJsonTree(true));
            }

            content.setAjaxSuccess(isUpdated);
            manager.endTransaction();

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Open users setting rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }
}
