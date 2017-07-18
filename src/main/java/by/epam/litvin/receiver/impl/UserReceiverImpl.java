package by.epam.litvin.receiver.impl;


import by.epam.litvin.bean.User;
import by.epam.litvin.dao.UserDAO;
import by.epam.litvin.receiver.UserReceiver;
import by.epam.litvin.util.StringEncoder;
import by.epam.litvin.exception.ConnectionPoolException;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.dao.TransactionHandler;
import by.epam.litvin.validator.UserValidator;

public class UserReceiverImpl implements UserReceiver {

    @Override
    public void signUp(RequestContent requestContent) throws ReceiverException {
        UserValidator validator = new UserValidator();
        String name = requestContent.getRequestParameters().get("name")[0];
        String email = requestContent.getRequestParameters().get("email")[0];
        String password = requestContent.getRequestParameters().get("password")[0];
        String repeatPassword = requestContent.getRequestParameters().get("repeatPassword")[0];
        String confirmURL;
        String dbPassword;
        boolean isValidData = true;

        requestContent.getRequestAttributes().put("name", name);
        requestContent.getRequestAttributes().put("email", email);
        requestContent.getRequestAttributes().put("password", password);
        requestContent.getRequestAttributes().put("repeatPassword", repeatPassword);

        if (!validator.checkPassword(password)) {
            requestContent.getRequestAttributes().put("wrongPassword", new Object());
            isValidData = false;

        } if (!password.equals(repeatPassword)) {
            requestContent.getRequestAttributes().put("wrongRepeatPassword", new Object());
            isValidData = false;

        } if (!validator.checkEmail(email)) {
            requestContent.getRequestAttributes().put("wrongEmail", new Object());
            isValidData = false;

        } if (!validator.checkName(name)) {
            requestContent.getRequestAttributes().put("wrongName", new Object());
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

        try (TransactionHandler handler = new TransactionHandler()) {

            UserDAO userDao = new UserDAO();
            handler.beginTransaction(userDao);

            if (userDao.create(user)) {
                handler.commit();

            } else {
                requestContent.getRequestAttributes().put("emailExists", new Object());
            }
        } catch (DAOException | ConnectionPoolException e) {
            throw new ReceiverException(e);
        }

    }

    @Override
    public void signIn(RequestContent requestContent) throws ReceiverException {
        UserValidator validator = new UserValidator();
        String email = requestContent.getRequestParameters().get("email")[0];
        String password = requestContent.getRequestParameters().get("password")[0];
        String dbPassword;
        boolean isValidData = true;

        requestContent.getRequestAttributes().put("email", email);
        requestContent.getRequestAttributes().put("password", password);

        if (!validator.checkPassword(password)) {
            requestContent.getRequestAttributes().put("wrongPassword", new Object());
            isValidData = false;

        } if (!validator.checkEmail(email)) {
            requestContent.getRequestAttributes().put("wrongEmail", new Object());
            isValidData = false;

        } if (!isValidData) {
            return;
        }

        dbPassword = StringEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(dbPassword);

        try (TransactionHandler handler = new TransactionHandler()) {

            UserDAO userDao = new UserDAO();
            handler.beginTransaction(userDao);
            User foundUser = userDao.findUser(user);

            if (foundUser != null) {
                handler.commit();

            } else {
                requestContent.getRequestAttributes().put("wrongData", new Object());
            }
        } catch (DAOException | ConnectionPoolException e) {
            throw new ReceiverException(e);
        }

    }

    @Override
    public void signOut(RequestContent requestContent) {

    }
}
