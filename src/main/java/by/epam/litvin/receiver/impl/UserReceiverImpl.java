package by.epam.litvin.receiver.impl;


import by.epam.litvin.bean.User;
import by.epam.litvin.dao.UserDAO;
import by.epam.litvin.receiver.UserReceiver;
import by.epam.litvin.util.StringEncoder;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.validator.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.litvin.constant.RequestNameConstant.*;

public class UserReceiverImpl implements UserReceiver {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void signUp(RequestContent requestContent) throws ReceiverException {
        UserValidator validator = new UserValidator();
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
            requestContent.getRequestAttributes().put(WRONG_PASSWORD, new Object());
            isValidData = false;

        } if (!password.equals(repeatPassword)) {
            requestContent.getRequestAttributes().put(WRONG_REPEAT_PASSWORD, new Object());
            isValidData = false;

        } if (!validator.checkEmail(email)) {
            requestContent.getRequestAttributes().put(WRONG_EMAIL, new Object());
            isValidData = false;

        } if (!validator.checkName(name)) {
            requestContent.getRequestAttributes().put(WRONG_NAME, new Object());
            isValidData = false;

        } if (!isValidData) {
            return;
        }

        dbPassword = StringEncoder.encode(password);
        confirmURL = StringEncoder.encode(email);
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(dbPassword);
        user.setConfirmUrl(confirmURL);
        TransactionManager handler = null;
        try {
            handler = new TransactionManager();
            UserDAO userDao = new UserDAO();
            handler.beginTransaction(userDao);

            if (userDao.create(user)) {
                handler.commit();

            } else {
                requestContent.getRequestAttributes().put(EMAIL_EXISTS, new Object());
            }

            handler.endTransaction();

        } catch (DAOException e) {
            if (handler != null) {
                try {
                    handler.rollback();
                } catch (DAOException e1) {
                    LOGGER.log(Level.ERROR, "This exception never happens", e);
                }
            }
            throw new ReceiverException(e);
        }

    }

    @Override
    public void signIn(RequestContent requestContent) throws ReceiverException {
        String email;
        String password;
        String dbPassword;

        try {
            email = requestContent.getRequestParameters().get(EMAIL)[0];
            password = requestContent.getRequestParameters().get(PASSWORD)[0];

        } catch (NullPointerException e) {
            throw new ReceiverException("Input parameters don't exist", e);
        }

        UserValidator validator = new UserValidator();
        boolean isValidData = true;
        requestContent.getRequestAttributes().put(EMAIL, email);
        requestContent.getRequestAttributes().put(PASSWORD, password);

        if (!validator.checkPassword(password) || !validator.checkEmail(email)) {
            requestContent.getRequestAttributes().put(WRONG_DATA, new Object());
            return;
        }

        dbPassword = StringEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(dbPassword);
        TransactionManager handler = null;
        try {
            handler = new TransactionManager();
            UserDAO userDao = new UserDAO();
            handler.beginTransaction(userDao);
            User foundUser = userDao.findUser(user);

            if (foundUser != null) {
                handler.commit();
                requestContent.getSessionAttributes().put(USER, foundUser);
            } else {
                requestContent.getRequestAttributes().put(WRONG_DATA, new Object());
            }
            handler.endTransaction();
        } catch (DAOException e) {
            if (handler != null) {
                try {
                    handler.rollback();
                } catch (DAOException e1) {
                    LOGGER.log(Level.ERROR, "This exception never happens", e);
                }
            }
            throw new ReceiverException(e);
        }

    }

    @Override
    public void signOut(RequestContent requestContent) {

    }
}
