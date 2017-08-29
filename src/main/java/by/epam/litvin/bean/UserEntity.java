package by.epam.litvin.bean;

import by.epam.litvin.type.UserType;

import java.math.BigDecimal;

public class UserEntity extends Entity {
     private int id;
     private String name;
     private String email;
     private String password;
     private UserType type;
     private boolean isConfirm;
     private String confirmUrl;
     private boolean isBlocked;
     private String blockedText;
     private BigDecimal cash;
     private String avatarURL;

    /**
     * Default constructor.
     */
    public UserEntity() {
    }

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
     * Get name.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get email.
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email.
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get password.
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get type.
     *
     * @return
     */
    public UserType getType() {
        return type;
    }

    /**
     * Set type.
     *
     * @param type
     */
    public void setType(UserType type) {
        this.type = type;
    }

    /**
     * Get is confirm.
     *
     * @return
     */
    public boolean getIsConfirm() {
        return isConfirm;
    }

    /**
     * Set confirm.
     *
     * @param confirm
     */
    public void setConfirm(boolean confirm) {
        isConfirm = confirm;
    }

    /**
     * Get is blocked.
     *
     * @return
     */
    public boolean getIsBlocked() {
        return isBlocked;
    }

    /**
     * Set blocked.
     *
     * @param blocked
     */
    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    /**
     * Get cash.
     *
     * @return
     */
    public BigDecimal getCash() {
        return cash;
    }

    /**
     * Set cash.
     *
     * @param cash
     */
    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    /**
     * Get confirm URL.
     *
     * @return
     */
    public String getConfirmUrl() {
        return confirmUrl;
    }

    /**
     * Set confirm URL.
     *
     * @param confirmUrl
     */
    public void setConfirmUrl(String confirmUrl) {
        this.confirmUrl = confirmUrl;
    }

    /**
     * Get avatar URL.
     *
     * @return
     */
    public String getAvatarURL() {
        return avatarURL;
    }

    /**
     * Set avatar URL.
     *
     * @param avatarURL
     */
    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    /**
     * Get blocked text.
     *
     * @return
     */
    public String getBlockedText() {
        return blockedText;
    }

    /**
     * Set blocked text.
     *
     * @param blockedText
     */
    public void setBlockedText(String blockedText) {
        this.blockedText = blockedText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;

        UserEntity user = (UserEntity) o;

        if (id != user.id) return false;
        if (isConfirm != user.isConfirm) return false;
        if (isBlocked != user.isBlocked) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (type != user.type) return false;
        if (confirmUrl != null ? !confirmUrl.equals(user.confirmUrl) : user.confirmUrl != null) return false;
        if (blockedText != null ? !blockedText.equals(user.blockedText) : user.blockedText != null)
            return false;
        if (cash != null ? !cash.equals(user.cash) : user.cash != null) return false;
        return avatarURL != null ? avatarURL.equals(user.avatarURL) : user.avatarURL == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (isConfirm ? 1 : 0);
        result = 31 * result + (confirmUrl != null ? confirmUrl.hashCode() : 0);
        result = 31 * result + (isBlocked ? 1 : 0);
        result = 31 * result + (blockedText != null ? blockedText.hashCode() : 0);
        result = 31 * result + (cash != null ? cash.hashCode() : 0);
        result = 31 * result + (avatarURL != null ? avatarURL.hashCode() : 0);
        return result;
    }

}
