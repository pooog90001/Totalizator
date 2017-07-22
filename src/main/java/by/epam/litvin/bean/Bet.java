package by.epam.litvin.bean;

import java.math.BigDecimal;

public class Bet extends Entity {

    public Bet() {}

    private Integer id;
    private Integer userId;
    private BigDecimal cash;
    private Boolean isWin;
    private Boolean isActive;
    private Integer commandM2MCompetionId;
    private Total total;

    public enum Total {
        MORE, LESS, EQUALS
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public Integer getCommandM2MCompetionId() {
        return commandM2MCompetionId;
    }

    public void setCommandM2MCompetionId(Integer commandM2MCompetionId) {
        this.commandM2MCompetionId = commandM2MCompetionId;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bet)) {
            return false;
        }

        Bet bet = (Bet) o;

        if (id != null ? !id.equals(bet.id) : bet.id != null) {
            return false;
        }
        if (userId != null ? !userId.equals(bet.userId) : bet.userId != null) {
            return false;
        }
        if (cash != null ? !cash.equals(bet.cash) : bet.cash != null) {
            return false;
        }
        if (isWin != null ? !isWin.equals(bet.isWin) : bet.isWin != null) {
            return false;
        }
        if (isActive != null ? !isActive.equals(bet.isActive) : bet.isActive != null) {
            return false;
        }
        if (commandM2MCompetionId != null ? !commandM2MCompetionId.equals(bet.commandM2MCompetionId) : bet.commandM2MCompetionId != null) {
            return false;
        }
        return total == bet.total;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (cash != null ? cash.hashCode() : 0);
        result = 31 * result + (isWin != null ? isWin.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (commandM2MCompetionId != null ? commandM2MCompetionId.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        return result;
    }
}
