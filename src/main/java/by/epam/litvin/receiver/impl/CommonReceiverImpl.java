package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.News;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.NewsDao;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.dao.UserDAO;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CommonReceiver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.epam.litvin.constant.RequestNameConstant.EMAIL_EXISTS;

public class CommonReceiverImpl implements CommonReceiver{
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void openMainPage(RequestContent requestContent) throws ReceiverException {
        TransactionManager handler = null;
        try {
            handler = new TransactionManager();
            NewsDao newsDao = new NewsDao();
            handler.beginTransaction(newsDao);

            List<News> newsList = newsDao.find(3);

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
}
