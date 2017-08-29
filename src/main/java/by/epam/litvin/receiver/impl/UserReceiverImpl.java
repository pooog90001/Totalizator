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
import by.epam.litvin.util.ImageUploader;
import by.epam.litvin.util.StringEncoder;
import by.epam.litvin.validator.impl.CommonValidatorImpl;
import by.epam.litvin.validator.impl.UserValidatorImpl;
import com.google.gson.Gson;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.Part;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;
import static by.epam.litvin.constant.GeneralConstant.USER;
import static by.epam.litvin.constant.GeneralConstant.WRONG_DATA;
import static by.epam.litvin.constant.RequestNameConstant.*;
import static by.epam.litvin.constant.RequestNameConstant.NAME;

public class UserReceiverImpl implements UserReceiver {

    @Override
    public void changeAvatar(RequestContent content) throws ReceiverException {
        CommonValidatorImpl validator = new CommonValidatorImpl();
        ImageUploader uploader = new ImageUploader();
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        File uploadPath = new File(content.getRealPath(), UploadType.AVATARS.getUploadFolder());
        Part imagePart = content.getRequestParts().get(IMAGE);

        String[] stringPointX1 = content.getRequestParameters().get(POINT_X1);
        String[] stringPointX2 = content.getRequestParameters().get(POINT_X2);
        String[] stringPointY1 = content.getRequestParameters().get(POINT_Y1);
        String[] stringPointY2 = content.getRequestParameters().get(POINT_Y2);
        String[] stringHeight = content.getRequestParameters().get(HEIGHT);
        String[] stringWidth = content.getRequestParameters().get(WIDTH);

        if (user == null) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(ACCESS_DENIED, new Gson().toJsonTree(true));
            return;
        }

        if (imagePart == null || !validator.checkParamsForInteger(stringPointX1,
                stringPointX2, stringPointY1, stringPointY2, stringHeight, stringWidth)) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(WRONG_DATA, new Gson().toJsonTree(true));
            return;
        }

        String imageExtension = FilenameUtils.getExtension(imagePart.getSubmittedFileName());
        int pointX1 = Integer.valueOf(stringPointX1[0]);
        int pointX2 = Integer.valueOf(stringPointX2[0]);
        int pointY1 = Integer.valueOf(stringPointY1[0]);
        int pointY2 = Integer.valueOf(stringPointY2[0]);
        int height = Integer.valueOf(stringHeight[0]);
        int width = Integer.valueOf(stringWidth[0]);

        if (!validator.isImageExtensionValid(imageExtension) ||
                pointX1 == pointX2 || pointY1 == pointY2) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(WRONG_DATA, new Gson().toJsonTree(true));
            return;
        }

        TransactionManager manager = new TransactionManager();
        try {
            UserDAOImpl userDAO = new UserDAOImpl();
            manager.beginTransaction(userDAO);

            user.setAvatarURL(user.getId() + _AVATAR_DOT + imageExtension);
            File path = new File(uploadPath, user.getAvatarURL());
            boolean isUploaded = uploader.uploadImage(imagePart, path, imageExtension,
                    pointX1, pointX2, pointY1, pointY2, height, width);

            if (!isUploaded) {
                content.getAjaxResult().add(WRONG_UPLOAD, new Gson().toJsonTree(true));
            }

            boolean isUpdated = isUploaded && userDAO.updateAvatarPath(user);

            if (isUploaded && isUpdated) {
                manager.commit();

            } else {
                manager.rollback();
            }

            content.setAjaxSuccess(isUploaded && isUpdated);
            content.getSessionAttributes().put(USER, userDAO.findEntityById(user.getId()));
            manager.commit();
            manager.endTransaction();

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Change avatar rollback error", e1);
            }
            throw new ReceiverException(e);
        }
    }


    @Override
    public void changePassword(RequestContent content) throws ReceiverException {
        UserValidatorImpl validator = new UserValidatorImpl();
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);

        String[] oldPassword = content.getRequestParameters().get(OLD_PASSWORD);
        String[] password = content.getRequestParameters().get(PASSWORD);
        String[] repeatPassword = content.getRequestParameters().get(REPEAT_PASSWORD);
        String newDBPassword;
        String oldDBPassword;

        if (user == null) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(ACCESS_DENIED, new Gson().toJsonTree(true));
            return;
        }
        if (!commonValidator.isVarExist(password) || !validator.checkPassword(password[0]) ||
                !commonValidator.isVarExist(oldPassword) || !validator.checkPassword(oldPassword[0]) ||
                !commonValidator.isVarExist(repeatPassword) || !validator.checkPassword(repeatPassword[0])) {
            content.getAjaxResult().add(WRONG_DATA, new Gson().toJsonTree(true));
            content.setAjaxSuccess(false);
            return;
        }
        if (!password[0].equals(repeatPassword[0])) {
            content.getAjaxResult().add(WRONG_REPEAT_PASSWORD, new Gson().toJsonTree(true));
            content.setAjaxSuccess(false);
            return;
        }

        newDBPassword = StringEncoder.encode(password[0]);
        oldDBPassword = StringEncoder.encode(oldPassword[0]);
        TransactionManager handler = new TransactionManager();
        try {
            UserDAOImpl userDao = new UserDAOImpl();
            handler.beginTransaction(userDao);
            UserEntity actualUser = userDao.findEntityById(user.getId());
            boolean isPasswordValid = true;

            if (!oldDBPassword.equals(actualUser.getPassword())) {
                isPasswordValid = false;
                content.getAjaxResult().add(WRONG_OLD_PASSWORD, new Gson().toJsonTree(true));
            }
            if (isPasswordValid && newDBPassword.equals(actualUser.getPassword())) {
                isPasswordValid = false;
                content.getAjaxResult().add(EQUALS_PASSWORDS, new Gson().toJsonTree(true));
            }
            if (!isPasswordValid) {
                handler.rollback();
                handler.endTransaction();
                content.setAjaxSuccess(false);
                return;
            }
            actualUser.setPassword(newDBPassword);
            boolean isUpdated = userDao.updatePassword(actualUser);

            if (isUpdated) {
                content.getSessionAttributes().put(USER, actualUser);
            }

            content.setAjaxSuccess(isUpdated);
            handler.commit();
            handler.endTransaction();

        } catch (DAOException e) {
            try {
                handler.rollback();
                handler.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Change password rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void signUp(RequestContent content) throws ReceiverException {
        UserValidatorImpl validator = new UserValidatorImpl();
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        String[] name = content.getRequestParameters().get(NAME);
        String[] email = content.getRequestParameters().get(EMAIL);
        String[] password = content.getRequestParameters().get(PASSWORD);
        String[] repeatPassword = content.getRequestParameters().get(REPEAT_PASSWORD);
        String dbPassword;
        boolean isValidData = true;

        content.getRequestAttributes().put(NAME, name);
        content.getRequestAttributes().put(EMAIL, email);
        content.getRequestAttributes().put(PASSWORD, password);
        content.getRequestAttributes().put(REPEAT_PASSWORD, repeatPassword);

        if (!commonValidator.isVarExist(password) || !validator.checkPassword(password[0])) {
            content.getRequestAttributes().put(WRONG_PASSWORD, true);
            return;

        }
        if (!commonValidator.isVarExist(repeatPassword) || !password[0].equals(repeatPassword[0])) {
            content.getRequestAttributes().put(WRONG_REPEAT_PASSWORD, true);
            isValidData = false;

        }
        if (!commonValidator.isVarExist(email) || !validator.checkEmail(email[0])) {
            content.getRequestAttributes().put(WRONG_EMAIL, true);
            isValidData = false;

        }
        if (!commonValidator.isVarExist(name) || !validator.checkName(name[0])) {
            content.getRequestAttributes().put(WRONG_NAME, true);
            isValidData = false;

        }
        if (!isValidData) {
            return;
        }

        dbPassword = StringEncoder.encode(password[0]);

        UserEntity user = new UserEntity();
        user.setName(name[0]);
        user.setEmail(email[0]);
        user.setPassword(dbPassword);

        TransactionManager handler = new TransactionManager();
        try {
            UserDAOImpl userDao = new UserDAOImpl();
            handler.beginTransaction(userDao);

            if (userDao.create(user)) {
                handler.commit();

            } else {
                content.getRequestAttributes().put(EMAIL_EXISTS, true);
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
    public void signIn(RequestContent content) throws ReceiverException {
        String[] email = content.getRequestParameters().get(EMAIL);
        String[] password = content.getRequestParameters().get(PASSWORD);
        String dbPassword;

        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        UserValidatorImpl validator = new UserValidatorImpl();
        content.getRequestAttributes().put(EMAIL, email);
        content.getRequestAttributes().put(PASSWORD, password);

        if (!commonValidator.isVarExist(password) || !commonValidator.isVarExist(email) ||
                !validator.checkPassword(password[0]) || !validator.checkEmail(email[0])) {
            content.getRequestAttributes().put(WRONG_DATA, true);
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
                    content.getSessionAttributes().put(TEXT, foundUser.getBlockedText());
                    content.getRequestAttributes().put(IS_BLOCKED, true);

                } else {
                    content.getSessionAttributes().put(USER, foundUser);
                }
            } else {
                content.getRequestAttributes().put(WRONG_DATA, true);
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
    public void signOut(RequestContent content) {
        content.getSessionAttributes().remove(RequestNameConstant.USER);
    }

    @Override
    public void openUserSettings(RequestContent content) throws ReceiverException {
        Formatter formatter = new Formatter();
        String[] stringPage = content.getRequestParameters().get(PAGE_NUMBER);
        int page = formatter.formatToPage(stringPage);

        if (page == -1) {
            content.getRequestAttributes().put(PAGE_NOT_FOUND, true);
            return;
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

            if (userList.isEmpty() && page != 1) {
                content.getRequestAttributes().put(PAGE_NOT_FOUND, true);
                return;
            }

            content.getRequestAttributes().put(USER_LIST, userList);
            content.getRequestAttributes().put(LIMIT, COUNT_USERS_ON_PAGE);
            content.getRequestAttributes().put(USERS_COUNT, usersCount);
            content.getRequestAttributes().put(USER_IMAGE_PATH, UploadType.AVATARS.getUploadFolder());

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
            content.getRequestAttributes().put(PAST_BETS_AND_GAMES, pastBetsAndGames);
            content.getRequestAttributes().put(UPCOMING_BETS_AND_GAMES, upcomingBetsAndGames);

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
            CompetitionEntity game = (CompetitionEntity) betAndGame.get(COMPETITION);
            List<Map<String, Object>> competitors = competitorDAO.findWithTeamByGameId(game.getId());
            betAndGame.put(COMPETITORS, competitors);
        }
    }

    @Override
    public void changeRole(RequestContent content) throws ReceiverException {
        int userId = Integer.valueOf(content.getRequestParameters().get(USER_ID)[0]);
        UserType userType = UserType.valueOf(content.getRequestParameters().get(USER_TYPE)[0]);
        UserEntity currentUser = (UserEntity) content.getSessionAttributes().get(USER);

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
        int userId = Integer.valueOf(content.getRequestParameters().get(USER_ID)[0]);
        boolean isBlocked = !Boolean.valueOf(content.getRequestParameters().get(BLOCK_STATE)[0]);
        String[] arrayText = content.getRequestParameters().get(TEXT_BLOCK);
        String blockedText = arrayText == null ? "" : arrayText[0].trim();
        UserEntity currentUser = (UserEntity) content.getSessionAttributes().get(USER);

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
                content.getAjaxResult().add(UPDATE_ERROR, new Gson().toJsonTree(true));
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
                content.getAjaxResult().add(UPDATE_ERROR, new Gson().toJsonTree(true));
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
