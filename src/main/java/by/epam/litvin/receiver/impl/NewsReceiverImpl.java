package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.NewsEntity;
import by.epam.litvin.constant.PageConstant;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.NewsDAO;
import by.epam.litvin.dao.impl.CommentDAOImpl;
import by.epam.litvin.dao.impl.NewsDAOImpl;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.NewsReceiver;
import by.epam.litvin.type.ImageFormatType;
import by.epam.litvin.util.NewsFormatter;
import by.epam.litvin.validator.impl.NewsValidatorImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;

public class NewsReceiverImpl implements NewsReceiver {

    @Override
    public void openAllNewsPage(RequestContent requestContent) throws ReceiverException {

        String[] page = requestContent.getRequestParameters().get("pageNumber");
        int startIndex = (page != null) ? Integer.valueOf(page[0]) : 1;
        startIndex = (startIndex - 1) * COUNT_NEWS_ON_PAGE;

        TransactionManager handler = new TransactionManager();
        try {
            NewsDAOImpl newsDAO = new NewsDAOImpl();
            handler.beginTransaction(newsDAO);
            List<NewsEntity> newsList = newsDAO.find(startIndex, COUNT_NEWS_ON_PAGE);
            int newsCount = newsDAO.findNewsCount();
            handler.commit();
            handler.endTransaction();

            NewsFormatter newsFormatter = new NewsFormatter();
            newsFormatter.formatNewsforPreview(newsList);


            requestContent.getRequestAttributes().put(NEWS_LIST, newsList);
            requestContent.getRequestAttributes().put("limit", COUNT_NEWS_ON_PAGE);
            requestContent.getRequestAttributes().put("newsCount", newsCount);
            requestContent.getRequestAttributes().put("newsImagePath", PageConstant.PATH_TO_UPLOAD_NEWS);

        } catch (DAOException e) {
            try {
                handler.rollback();
                handler.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Open all news rollback error", e);
            }

            throw new ReceiverException(e);
        }
    }

    @Override
    public void openNewsSettings(RequestContent requestContent) throws ReceiverException {
        String[] page = requestContent.getRequestParameters().get("pageNumber");
        int startIndex = (page != null) ? Integer.valueOf(page[0]) : 1;
        startIndex = (startIndex - 1) * 10;

        TransactionManager handler = new TransactionManager();
        try {
            NewsDAOImpl newsDAO = new NewsDAOImpl();
            handler.beginTransaction(newsDAO);
            List<NewsEntity> newsList = newsDAO.find(startIndex, 10);
            int newsCount = newsDAO.findNewsCount();
            handler.commit();
            handler.endTransaction();

            NewsFormatter newsFormatter = new NewsFormatter();
            newsFormatter.formatNewsforPreview(newsList);


            requestContent.getRequestAttributes().put(NEWS_LIST, newsList);
            requestContent.getRequestAttributes().put("limit", 10);
            requestContent.getRequestAttributes().put("newsCount", newsCount);
            requestContent.getRequestAttributes().put("newsImagePath", PageConstant.PATH_TO_UPLOAD_NEWS);

        } catch (DAOException e) {
            try {
                handler.rollback();
                handler.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Open all news rollback error", e);
            }

            throw new ReceiverException(e);
        }
    }

    @Override
    public void createNews(RequestContent content) throws ReceiverException {
        int pointX1 = Integer.valueOf(content.getRequestParameters().get("x1")[0]);
        int pointX2 = Integer.valueOf(content.getRequestParameters().get("x2")[0]);
        int pointY1 = Integer.valueOf(content.getRequestParameters().get("y1")[0]);
        int pointY2 = Integer.valueOf(content.getRequestParameters().get("y2")[0]);
        int height = Integer.valueOf(content.getRequestParameters().get("height")[0]);
        int width = Integer.valueOf(content.getRequestParameters().get("width")[0]);
        String text = content.getRequestParameters().get("text")[0].trim();
        String title = content.getRequestParameters().get("title")[0].trim();
        Part imagePart = content.getRequestParts().get(IMAGE);
        File uploadPath = new File(content.getRealPath(), PageConstant.PATH_TO_UPLOAD_NEWS);

        ImageFormatType formatImage;


        NewsValidatorImpl newsValidator = new NewsValidatorImpl();

        if (!newsValidator.isTitleValid(title) ||
                !newsValidator.isTextValid(text) ||
                pointX1 == pointX2 || pointY1 == pointY2) {
            content.setAjaxSuccess(false);
            return;
        }

        String[] extension = imagePart.getSubmittedFileName().split("[.]");
        try {
            formatImage = ImageFormatType.valueOf(extension[extension.length - 1].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ReceiverException("Illegal image format error", e);
        }

        NewsEntity news = new NewsEntity();
        news.setTitle(title);
        news.setText(text);


        TransactionManager manager = new TransactionManager();
        try {
            NewsDAOImpl newsDAO = new NewsDAOImpl();
            manager.beginTransaction(newsDAO);
            news.setId(newsDAO.createAndGetId(news));
            news.setImageUrl(news.getId() + _NEWS_DOT + formatImage);

            try {
                BufferedImage image = ImageIO.read(imagePart.getInputStream());

                if (image == null) {
                    content.setAjaxSuccess(false);
                    return;
                }

                float scale = (float) (image.getHeight()-1)  / (float) height;
                pointX1 = (int) (pointX1 * scale);
                pointX2 = (int) (pointX2 * scale);
                pointY1 = (int) (pointY1 * scale);
                pointY2 = (int) (pointY2 * scale);
                image = image.getSubimage(pointX1, pointY1, pointX2 - pointX1, pointY2 - pointY1);


                File path = new File(uploadPath, news.getImageUrl());
                ImageIO.write(image, formatImage.toString(), path);

            } catch (IOException e) {
                manager.rollback();
                manager.endTransaction();
                throw new ReceiverException("Upload image error", e);
            }

            content.setAjaxSuccess( newsDAO.updateImagePath(news));
            manager.commit();
            manager.endTransaction();

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Create news rollback error", e1);
            }

            throw new ReceiverException(e);
        }
    }

    @Override
    public void deleteNews(RequestContent content) throws ReceiverException {
        int newsId = Integer.valueOf(content.getRequestParameters().get("newsId")[0]);
        String newsImageUrl = content.getRequestParameters().get("newsImageUrl")[0];

        File directoryPath = new File(content.getRealPath(), PageConstant.PATH_TO_UPLOAD_NEWS);
        File file = new File(directoryPath, newsImageUrl);


        TransactionManager manager = new TransactionManager();
        try {
            NewsDAOImpl newsDAO = new NewsDAOImpl();
            CommentDAOImpl commentDAO = new CommentDAOImpl();
            manager.beginTransaction(newsDAO, commentDAO);
            boolean isTransactionSuccess = commentDAO.deleteByNewsId(newsId);

            if (isTransactionSuccess) {
                isTransactionSuccess = newsDAO.delete(newsId);
            }

            if (isTransactionSuccess && file.exists()) {
                isTransactionSuccess = file.delete();
            }

            if (isTransactionSuccess) {
                manager.commit();

            } else {
                manager.rollback();
            }

            manager.endTransaction();
            content.setAjaxSuccess(isTransactionSuccess);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Delete news rollback error", e);
            }

            throw new ReceiverException(e);
        }

    }

    @Override
    public void openConcreteNewsPage(RequestContent requestContent) throws ReceiverException {
        String[] newsIdString = requestContent.getRequestParameters().get(NEWS_ID);
        String[] invalidText = requestContent.getRequestParameters().get(INVALID_TEXT);
        int newsId = Integer.valueOf(newsIdString[0]);

        if (invalidText != null && !invalidText[0].isEmpty()) {
            requestContent.getRequestAttributes().put(INVALID_TEXT, invalidText[0]);
        }

        TransactionManager handler = new TransactionManager();
        try {
            NewsDAOImpl newsDAO = new NewsDAOImpl();
            CommentDAOImpl commentDAO = new CommentDAOImpl();
            handler.beginTransaction(newsDAO, commentDAO);
            NewsEntity news = newsDAO.findEntityById(newsId);

            if (news == null) {
                requestContent.getRequestAttributes().put(WRONG_NEWS, true);
                handler.commit();
                handler.endTransaction();
                return;
            }

            List<Map<String, Object>> newsCommentList = commentDAO.findNewsComments(newsId);
            handler.commit();
            handler.endTransaction();

            requestContent.getRequestAttributes().put("newsCommentList", newsCommentList);
            requestContent.getRequestAttributes().put("attrNews", news);


        } catch (DAOException e) {
            try {
                handler.rollback();
                handler.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Open concrete news rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }
}
