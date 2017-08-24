package by.epam.litvin.util;

import by.epam.litvin.bean.NewsEntity;

import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.math.BigDecimal;
import java.util.List;

public class Formatter {
    private static final int FAULT = 1;
    private static final int BET_CASH_SCALE = 0;
    private static final int COUNT_SYMBOLS_ON_NEWS_PREVIEW = 100;

    public void formatNewsForPreview(List<NewsEntity> newsList) {
        for (NewsEntity news : newsList) {
            if (news.getText().length() > COUNT_SYMBOLS_ON_NEWS_PREVIEW) {
                news.setText(news.getText()
                        .substring(0, COUNT_SYMBOLS_ON_NEWS_PREVIEW).concat("..."));
            }
        }
    }

    public BigDecimal formatToCash(BigDecimal decimal) {
        return decimal.setScale(BET_CASH_SCALE, BigDecimal.ROUND_DOWN);
    }

    public BufferedImage formatImage(BufferedImage image,
                                     int pointX1,
                                     int pointX2,
                                     int pointY1,
                                     int pointY2,
                                     int height,
                                     int width) {

        if (image == null) {
            return null;
        }

        double originalImageSquare = image.getHeight() * image.getWidth();
        double displayedImageSquare = (height - FAULT) * (width - FAULT);
        double scale = Math.sqrt(originalImageSquare / displayedImageSquare);
        pointX1 = (int) (pointX1 * scale);
        pointX2 = (int) (pointX2 * scale);
        pointY1 = (int) (pointY1 * scale);
        pointY2 = (int) (pointY2 * scale);

        try {
            image = image.getSubimage(pointX1, pointY1, pointX2 - pointX1, pointY2 - pointY1);
        } catch (RasterFormatException e) {
            image = null;
        }

        return image;

    }
}
