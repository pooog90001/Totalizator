package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.NewsEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.dao.impl.CommentDAOImpl;
import by.epam.litvin.dao.impl.NewsDAOImpl;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.NewsReceiver;
import by.epam.litvin.type.UploadType;
import by.epam.litvin.util.Formatter;
import by.epam.litvin.validator.impl.CommonValidatorImpl;
import by.epam.litvin.validator.impl.NewsValidatorImpl;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;

public class NewsReceiverImpl implements NewsReceiver {

    @Override
    public void openAllNewsPage(RequestContent content) throws ReceiverException {
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

        int startIndex = formatter.formatToStartIndex(page, COUNT_NEWS_ON_PAGE);

        TransactionManager handler = new TransactionManager();
        try {
            NewsDAOImpl newsDAO = new NewsDAOImpl();
            handler.beginTransaction(newsDAO);
            List<NewsEntity> newsList = newsDAO.find(startIndex, COUNT_NEWS_ON_PAGE);
            int newsCount = newsDAO.findNewsCount();
            handler.commit();
            handler.endTransaction();

            if (newsList.isEmpty() && page != 1) {
                content.getRequestAttributes().put(PAGE_NOT_FOUND, true);
                return;
            }

            formatter.formatNewsForPreview(newsList);
            content.getRequestAttributes().put(NEWS_LIST, newsList);
            content.getRequestAttributes().put("limit", COUNT_NEWS_ON_PAGE);
            content.getRequestAttributes().put("newsCount", newsCount);
            content.getRequestAttributes().put("newsImagePath", UploadType.NEWS.getUploadFolder());

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
    public void openNewsSettings(RequestContent content) throws ReceiverException {
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

        int startIndex = formatter.formatToStartIndex(page, COUNT_NEWS_ON_PAGE);

        TransactionManager handler = new TransactionManager();
        try {
            NewsDAOImpl newsDAO = new NewsDAOImpl();
            handler.beginTransaction(newsDAO);
            List<NewsEntity> newsList = newsDAO.find(startIndex, COUNT_NEWS_ON_PAGE);
            int newsCount = newsDAO.findNewsCount();
            handler.commit();
            handler.endTransaction();

            if (newsList.isEmpty() && page != 1) {
                content.getRequestAttributes().put(PAGE_NOT_FOUND, true);
                return;
            }


            formatter.formatNewsForPreview(newsList);
            content.getRequestAttributes().put(NEWS_LIST, newsList);
            content.getRequestAttributes().put("limit", COUNT_NEWS_ON_PAGE);
            content.getRequestAttributes().put("newsCount", newsCount);
            content.getRequestAttributes().put("newsImagePath", UploadType.NEWS.getUploadFolder());

        } catch (DAOException e) {
            try {
                handler.rollback();
                handler.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Open all news setttings rollback error", e);
            }

            throw new ReceiverException(e);
        }
    }


    @Override
    public void createNews(RequestContent content) throws ReceiverException {
        NewsValidatorImpl newsValidator = new NewsValidatorImpl();
        CommonValidatorImpl validator = new CommonValidatorImpl();
        Formatter formatter = new Formatter();

        String[] stringPointX1 = content.getRequestParameters().get("x1");
        String[] stringPointX2 = content.getRequestParameters().get("x2");
        String[] stringPointY1 = content.getRequestParameters().get("y1");
        String[] stringPointY2 = content.getRequestParameters().get("y2");
        String[] stringHeight = content.getRequestParameters().get("height");
        String[] stringWidth = content.getRequestParameters().get("width");
        String[] textArray = content.getRequestParameters().get("text");
        String[] titleArray = content.getRequestParameters().get("title");

        if (!validator.checkParamsForInteger(stringPointX1, stringPointX2,
                stringPointY1, stringPointY2, stringHeight, stringWidth) ||
                !validator.isVarExist(textArray) || !validator.isVarExist(titleArray)) {

            content.setAjaxSuccess(false);
            return;
        }

        int pointX1 = Integer.valueOf(stringPointX1[0]);
        int pointX2 = Integer.valueOf(stringPointX2[0]);
        int pointY1 = Integer.valueOf(stringPointY1[0]);
        int pointY2 = Integer.valueOf(stringPointY2[0]);
        int height = Integer.valueOf(stringHeight[0]);
        int width = Integer.valueOf(stringWidth[0]);
        String title = titleArray[0];
        String text = textArray[0];
        Part imagePart = content.getRequestParts().get(IMAGE);
        File uploadPath = new File(content.getRealPath(), UploadType.NEWS.getUploadFolder());
        String imageExtension = FilenameUtils.getExtension(imagePart.getSubmittedFileName());


        if (!newsValidator.isTitleValid(title) || !newsValidator.isTextValid(text) ||
                !validator.isImageExtensionValid(imageExtension) ||
                pointX1 == pointX2 || pointY1 == pointY2) {

            content.setAjaxSuccess(false);
            return;
        }

        NewsEntity news = new NewsEntity();
        news.setTitle(title);
        news.setText(text);

        TransactionManager manager = new TransactionManager();
        try {
            NewsDAOImpl newsDAO = new NewsDAOImpl();
            manager.beginTransaction(newsDAO);
            news.setId(newsDAO.createAndGetId(news));
            news.setImageUrl(news.getId() + _NEWS_DOT + imageExtension);

            try {
                ImageIO.setUseCache(false);
                BufferedImage image = ImageIO.read(imagePart.getInputStream());
                image = formatter.formatImage(image, pointX1, pointX2, pointY1, pointY2, height, width);

                if (image == null) {
                    content.setAjaxSuccess(false);
                    return;
                }

                File path = new File(uploadPath, news.getImageUrl());
                ImageIO.write(image, imageExtension, path);

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

        File directoryPath = new File(content.getRealPath(), UploadType.NEWS.getUploadFolder());
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
    public void openConcreteNewsPage(RequestContent content) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        String[] newsIdString = content.getRequestParameters().get(NEWS_ID);

        if (!commonValidator.checkParamsForInteger(newsIdString)) {
            content.getRequestAttributes().put(PAGE_NOT_FOUND, true);
            return;
        }

        int newsId = Integer.valueOf(newsIdString[0]);


        TransactionManager handler = new TransactionManager();
        try {
            NewsDAOImpl newsDAO = new NewsDAOImpl();
            CommentDAOImpl commentDAO = new CommentDAOImpl();
            handler.beginTransaction(newsDAO, commentDAO);
            NewsEntity news = newsDAO.findEntityById(newsId);

            if (news.equals(new NewsEntity())) {
                content.getRequestAttributes().put(PAGE_NOT_FOUND, true);
                handler.rollback();
                handler.endTransaction();
                return;
            }

            List<Map<String, Object>> newsCommentList = commentDAO.findCommentsByNewsId(newsId);
            handler.commit();
            handler.endTransaction();

            content.getRequestAttributes().put("newsCommentList", newsCommentList);
            content.getRequestAttributes().put("attrNews", news);

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
