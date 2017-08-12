package by.epam.litvin.receiver.impl;


import by.epam.litvin.bean.UserEntity;
import by.epam.litvin.constant.RequestNameConstant;
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
            requestContent.getRequestAttributes().put(WRONG_PASSWORD, true);
            isValidData = false;

        } if (!password.equals(repeatPassword)) {
            requestContent.getRequestAttributes().put(WRONG_REPEAT_PASSWORD, true);
            isValidData = false;

        } if (!validator.checkEmail(email)) {
            requestContent.getRequestAttributes().put(WRONG_EMAIL, true);
            isValidData = false;

        } if (!validator.checkName(name)) {
            requestContent.getRequestAttributes().put(WRONG_NAME, true);
            isValidData = false;

        } if (!isValidData) {
            return;
        }

        dbPassword = StringEncoder.encode(password);
        confirmURL = StringEncoder.encode(email);
        UserEntity user = new UserEntity();
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
                requestContent.getRequestAttributes().put(EMAIL_EXISTS, true);
            }

            handler.endTransaction();

        } catch (DAOException e) {
            if (handler != null) {
                try {
                    handler.rollback();
                    handler.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Rollback error", e);
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

        email = requestContent.getRequestParameters().get(EMAIL)[0];
        password = requestContent.getRequestParameters().get(PASSWORD)[0];




        UserValidator validator = new UserValidator();
        boolean isValidData = true;
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
        TransactionManager handler = null;

        try {
            handler = new TransactionManager();
            UserDAO userDao = new UserDAO();
            handler.beginTransaction(userDao);
            UserEntity foundUser = userDao.findUser(user);
            handler.commit();

            if (foundUser != null) {
                requestContent.getSessionAttributes().put(USER, foundUser);
            } else {
                requestContent.getRequestAttributes().put(WRONG_DATA, true);
            }
            handler.endTransaction();

        } catch (DAOException e) {
            if (handler != null) {
                try {
                    handler.rollback();
                    handler.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }

    }

    @Override
    public void signOut(RequestContent requestContent) {
       requestContent.getSessionAttributes().remove(RequestNameConstant.USER);
    }
}
