package by.epam.litvin.bean;

import java.util.Date;


public class CommentEntity extends Entity {

    private int id;
    private String text;
    private Boolean isBlocked;
    private Date postDate;
    private int newsId;
    private int userId;

    /**
     * Default constructor.
     */
    public CommentEntity() {}

    /**
     * Get ID.
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID.
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get text.
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * Set text.
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get blocked.
     *
     * @return
     */
    public Boolean getBlocked() {
        return isBlocked;
    }

    /**
     * Set blocked.
     *
     * @param blocked
     */
    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    /**
     * Get post date.
     *
     * @return
     */
    public Date getPostDate() {
        return postDate;
    }

    /**
     * Set post date.
     *
     * @param postDate
     */
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    /**
     * Get news ID.
     *
     * @return
     */
    public int getNewsId() {
        return newsId;
    }

    /**
     * Set news ID.
     *
     * @param newsId
     */
    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    /**
     * Get user ID.
     *
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set user ID.
     *
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentEntity)) return false;

        CommentEntity that = (CommentEntity) o;

        if (id != that.id) return false;
        if (newsId != that.newsId) return false;
        if (userId != that.userId) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (isBlocked != null ? !isBlocked.equals(that.isBlocked) : that.isBlocked != null) return false;
        return postDate != null ? postDate.equals(that.postDate) : that.postDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (isBlocked != null ? isBlocked.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + newsId;
        result = 31 * result + userId;
        return result;
    }
}
