package by.epam.litvin.receiver.impl;


import by.epam.litvin.bean.UserEntity;
import by.epam.litvin.constant.PageConstant;
import by.epam.litvin.constant.RequestNameConstant;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.dao.impl.UserDAOImpl;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.UserReceiver;
import by.epam.litvin.type.UserType;
import by.epam.litvin.util.StringEncoder;
import by.epam.litvin.validator.impl.UserValidatorImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.COUNT_USERS_ON_PAGE;
import static by.epam.litvin.constant.GeneralConstant.TEMPORARY;
import static by.epam.litvin.constant.RequestNameConstant.*;

public class UserReceiverImpl implements UserReceiver {

    @Override
    public void signUp(RequestContent requestContent) throws ReceiverException {
        UserValidatorImpl validator = new UserValidatorImpl();
        String name = requestContent.getRequestParameters().get(NAME)[0];
        String email = requestContent.getRequestParameters().get(EMAIL)[0];
        String password = requestContent.getRequestParameters().get(PASSWORD)[0];
        String repeatPassword = requestContent.getRequestParameters().get(REPEAT_PASSWORD)[0];
        String confirmURL;
        String dbPassword;
        boolean isValidData = true;

        requestContent.getRequestAttributes().put(NAME, name);
        requestContent.getRequestAttributes().put(EMAIL, email);
        requestContent.getRequestAttributes().put(PASSWORD, password);
        requestContent.getRequestAttributes().put(REPEAT_PASSWORD, repeatPassword);

        if (!validator.checkPassword(password)) {
            requestContent.getRequestAttributes().put(WRONG_PASSWORD, true);
            isValidData = false;

        }
        if (!password.equals(repeatPassword)) {
            requestContent.getRequestAttributes().put(WRONG_REPEAT_PASSWORD, true);
            isValidData = false;

        }
        if (!validator.checkEmail(email)) {
            requestContent.getRequestAttributes().put(WRONG_EMAIL, true);
            isValidData = false;

        }
        if (!validator.checkName(name)) {
            requestContent.getRequestAttributes().put(WRONG_NAME, true);
            isValidData = false;

        }
        if (!isValidData) {
            return;
        }

        dbPassword = StringEncoder.encode(password);
        confirmURL = StringEncoder.encode(email);
        UserEntity user = new UserEntity();
        user.setName(name);
        user.setEmail(email);
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
        String email;
        String password;
        String dbPassword;
        Map<String, Object> data = new HashMap<>();

        email = requestContent.getRequestParameters().get(EMAIL)[0];
        password = requestContent.getRequestParameters().get(PASSWORD)[0];

        UserValidatorImpl validator = new UserValidatorImpl();
        requestContent.getRequestAttributes().put(EMAIL, email);
        requestContent.getRequestAttributes().put(PASSWORD, password);

        if (!validator.checkPassword(password) || !validator.checkEmail(email)) {
            requestContent.getRequestAttributes().put(WRONG_DATA, true);
            return;
        }

        dbPassword = StringEncoder.encode(password);
        UserEntity user = new UserEntity();
        user.setEmail(email);
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
                    data.put("blockedText", foundUser.getBlockedText());
                    requestContent.getSessionAttributes().put(TEMPORARY, data);
                    requestContent.getRequestAttributes().put("isBlocked", true);

                } else if (!foundUser.getIsConfirm()) {
                    requestContent.getRequestAttributes().put("isNotConfirmed", true);

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
    public void openUserSettings(RequestContent requestContent) throws ReceiverException {
        String[] page = requestContent.getRequestParameters().get("pageNumber");
        int startIndex = (page != null) ? Integer.valueOf(page[0]) : 1;
        startIndex = (startIndex - 1) * COUNT_USERS_ON_PAGE;

        TransactionManager handler = new TransactionManager();
        try {
            UserDAOImpl userDAO = new UserDAOImpl();
            handler.beginTransaction(userDAO);
            List<UserEntity> userList = userDAO.findLimit(startIndex, COUNT_USERS_ON_PAGE);
            int usersCount = userDAO.findUsersCount();
            handler.commit();
            handler.endTransaction();

            requestContent.getRequestAttributes().put("userList", userList);
            requestContent.getRequestAttributes().put("limit", COUNT_USERS_ON_PAGE);
            requestContent.getRequestAttributes().put("usersCount", usersCount);
            requestContent.getRequestAttributes().put("userImagePath", PageConstant.PATH_TO_UPLOAD_AVATARS);

        } catch (DAOException e) {
            try {
                handler.rollback();
                handler.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Open users setting rollback error", e);
            }

            throw new ReceiverException(e);
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
}
