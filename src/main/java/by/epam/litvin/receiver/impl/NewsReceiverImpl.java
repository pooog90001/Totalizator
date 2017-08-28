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
import by.epam.litvin.util.Uploader;
import by.epam.litvin.validator.impl.CommonValidatorImpl;
import by.epam.litvin.validator.impl.NewsValidatorImpl;
import com.google.gson.Gson;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.Part;
import java.io.File;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;

public class NewsReceiverImpl implements NewsReceiver {

    @Override
    public void openAllNewsPage(RequestContent content) throws ReceiverException {
        Formatter formatter = new Formatter();
        String[] stringPage = content.getRequestParameters().get(PAGE_NUMBER);
        int page = formatter.formatToPage(stringPage);

        if (page == -1) {
            content.getRequestAttributes().put(PAGE_NOT_FOUND, true);
            return;
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
            content.getRequestAttributes().put(LIMIT, COUNT_NEWS_ON_PAGE);
            content.getRequestAttributes().put(NEWS_COUNT, newsCount);
            content.getRequestAttributes().put(NEWS_IMAGE_PATH, UploadType.NEWS.getUploadFolder());

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
        NewsValidatorImpl newsValidator = new NewsValidatorImpl();
        CommonValidatorImpl validator = new CommonValidatorImpl();
        File uploadPath = new File(content.getRealPath(), UploadType.NEWS.getUploadFolder());
        Uploader uploader = new Uploader();

        String[] stringPointX1 = content.getRequestParameters().get(POINT_X1);
        String[] stringPointX2 = content.getRequestParameters().get(POINT_X2);
        String[] stringPointY1 = content.getRequestParameters().get(POINT_Y1);
        String[] stringPointY2 = content.getRequestParameters().get(POINT_Y2);
        String[] stringHeight = content.getRequestParameters().get(HEIGHT);
        String[] stringWidth = content.getRequestParameters().get(WIDTH);
        String[] textArray = content.getRequestParameters().get(TEXT);
        String[] titleArray = content.getRequestParameters().get(TITLE);
        Part imagePart = content.getRequestParts().get(IMAGE);

        if (imagePart == null || !validator.checkParamsForInteger(stringPointX1, stringPointX2,
                stringPointY1, stringPointY2, stringHeight, stringWidth) ||
                !validator.isVarExist(textArray) || !validator.isVarExist(titleArray)) {
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

        if (!newsValidator.isTitleValid(titleArray[0]) ||
                !newsValidator.isTextValid(textArray[0]) ||
                !validator.isImageExtensionValid(imageExtension) ||
                pointX1 == pointX2 || pointY1 == pointY2) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(WRONG_DATA, new Gson().toJsonTree(true));
            return;
        }

        NewsEntity news = new NewsEntity();
        news.setTitle(titleArray[0]);
        news.setText(textArray[0]);

        TransactionManager manager = new TransactionManager();
        try {
            NewsDAOImpl newsDAO = new NewsDAOImpl();
            manager.beginTransaction(newsDAO);
            news.setId(newsDAO.createAndGetId(news));
            news.setImageUrl(news.getId() + _NEWS_DOT + imageExtension);
            File path = new File(uploadPath, news.getImageUrl());
            boolean isUploaded = uploader.uploadImage(imagePart, path, imageExtension,
                    pointX1, pointX2, pointY1, pointY2, height, width);

            if (!isUploaded) {
                content.getAjaxResult().add(WRONG_UPLOAD, new Gson().toJsonTree(true));
            }

            boolean isUpdated = isUploaded && newsDAO.updateImagePath(news);

            if (isUploaded && isUpdated) {
                manager.commit();

            } else {
                manager.rollback();
                if (path.exists()) {
                    isUploaded = !path.delete();
                }
            }

            content.setAjaxSuccess(isUploaded && isUpdated);
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
        int newsId = Integer.valueOf(content.getRequestParameters().get(NEWS_ID)[0]);
        String newsImageUrl = content.getRequestParameters().get(NEWS_IMAGE_URL)[0];

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

            content.getRequestAttributes().put(NEWS_COMMENT_LIST, newsCommentList);
            content.getRequestAttributes().put(ATTR_NEWS, news);

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
