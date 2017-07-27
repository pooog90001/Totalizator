package by.epam.litvin.bean;

import java.math.BigDecimal;

public class BetEntity extends Entity {

    public BetEntity() {}

    private int id;
    private int userId;
    private BigDecimal cash;
    private Boolean isWin;
    private Boolean isActive;
    private int commandM2MCompetionId;
    private Total total;

    public enum Total {
        MORE, LESS, EQUALS
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public Boolean getWin() {
        return isWin;
    }

    public void setWin(Boolean win) {
        isWin = win;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public int getCommandM2MCompetionId() {
        return commandM2MCompetionId;
    }

    public void setCommandM2MCompetionId(int commandM2MCompetionId) {
        this.commandM2MCompetionId = commandM2MCompetionId;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

}
