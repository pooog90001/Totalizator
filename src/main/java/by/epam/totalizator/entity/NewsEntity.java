package by.epam.totalizator.entity;

import java.util.Date;

public class NewsEntity extends Entity {
    private int id;
    private String title;
    private String text;
    private String imageUrl;
    private Date dateCreation;

    /**
     * Default constructor.
     */
    public NewsEntity() {}

    /**
     * Get ID.
     *
     * @return news id
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID.
     *
     * @param id news id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get title.
     *
     * @return news title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title.
     *
     * @param title news title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get text.
     *
     * @return news title
     */
    public String getText() {
        return text;
    }

    /**
     * Set text.
     *
     * @param text news title
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get image URL.
     *
     * @return news image url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Set image URL.
     *
     * @param imageUrl news image url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Get date creation.
     *
     * @return news date creation
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * Set date creation.
     *
     * @param dateCreation news date creation
     */
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsEntity)) return false;

        NewsEntity news = (NewsEntity) o;

        if (id != news.id) return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;
        if (text != null ? !text.equals(news.text) : news.text != null) return false;
        if (imageUrl != null ? !imageUrl.equals(news.imageUrl) : news.imageUrl != null) return false;
        return dateCreation != null ? dateCreation.equals(news.dateCreation) : news.dateCreation == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (dateCreation != null ? dateCreation.hashCode() : 0);
        return result;
    }
}
