package by.epam.litvin.util;

import by.epam.litvin.bean.NewsEntity;

import java.util.List;

public class NewsFormatter {
    public void formatNewsforPreview(List<NewsEntity> newsList) {
        for (NewsEntity news: newsList) {
            if (news.getText().length() > 100) {
                news.setText(news.getText().substring(0, 100).concat("..."));
            }
        }
    }
}
