package by.epam.litvin.bean;

import java.math.BigDecimal;

public class BetEntity extends Entity {

    public BetEntity() {}

    private int id;
    private int userId;
    private BigDecimal cash;
    private Boolean isWin;
    private Boolean isActive;
    private int CompetitorId;
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

    public int getCompetitorId() {
        return CompetitorId;
    }

    public void setCompetitorId(int competitorId) {
        this.CompetitorId = competitorId;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BetEntity)) return false;

        BetEntity betEntity = (BetEntity) o;

        if (id != betEntity.id) return false;
        if (userId != betEntity.userId) return false;
        if (CompetitorId != betEntity.CompetitorId) return false;
        if (cash != null ? !cash.equals(betEntity.cash) : betEntity.cash != null) return false;
        if (isWin != null ? !isWin.equals(betEntity.isWin) : betEntity.isWin != null) return false;
        if (isActive != null ? !isActive.equals(betEntity.isActive) : betEntity.isActive != null) return false;
        return total == betEntity.total;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (cash != null ? cash.hashCode() : 0);
        result = 31 * result + (isWin != null ? isWin.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + CompetitorId;
        result = 31 * result + (total != null ? total.hashCode() : 0);
        return result;
    }
}
