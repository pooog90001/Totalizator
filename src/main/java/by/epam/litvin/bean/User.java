package by.epam.litvin.bean;

import by.epam.litvin.type.UserType;

import java.math.BigDecimal;

public class User extends Entity {
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

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public boolean isConfirm() {
        return isConfirm;
    }

    public void setConfirm(boolean confirm) {
        isConfirm = confirm;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public String getConfirmUrl() {
        return confirmUrl;
    }

    public void setConfirmUrl(String confirmUrl) {
        this.confirmUrl = confirmUrl;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getBlockedText() {
        return blockedText;
    }

    public void setBlockedText(String blockedText) {
        this.blockedText = blockedText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

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
