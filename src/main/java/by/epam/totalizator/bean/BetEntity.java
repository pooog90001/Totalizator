package by.epam.totalizator.bean;

import by.epam.totalizator.type.ExpectResultType;

import java.math.BigDecimal;

public class BetEntity extends Entity {

    private int id;
    private int userId;
    private BigDecimal cash;
    private Boolean isWin;
    private Boolean isActive;
    private int competitorId;
    private ExpectResultType expectedResult;

    /**
     * Default constructor.
     *
     */
    public BetEntity() {
    }

    /**
     * Constructor with params.
     *
     * @param userId
     * @param cash
     * @param competitorId
     * @param expectedResult
     */
    public BetEntity(int userId, BigDecimal cash, int competitorId, ExpectResultType expectedResult) {
        this.userId = userId;
        this.cash = cash;
        this.competitorId = competitorId;
        this.expectedResult = expectedResult;
    }

    /**
     * Get id.
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
     * Get win.
     *
     * @return
     */
    public Boolean getWin() {
        return isWin;
    }

    /**
     * Set win.
     *
     * @param win
     */
    public void setWin(Boolean win) {
        isWin = win;
    }

    /**
     * Get active.
     *
     * @return
     */
    public Boolean getActive() {
        return isActive;
    }

    /**
     * Set active.
     *
     * @param active
     */
    public void setActive(Boolean active) {
        isActive = active;
    }

    /**
     * Get competitor ID.
     *
     * @return
     */
    public int getCompetitorId() {
        return competitorId;
    }

    /**
     * Set competitor ID.
     *
     * @param competitorId
     */
    public void setCompetitorId(int competitorId) {
        this.competitorId = competitorId;
    }

    /**
     * Get expected result.
     *
     * @return
     */
    public ExpectResultType getExpectedResult() {
        return expectedResult;
    }

    /**
     * Set expected result.
     *
     * @param expectedResult
     */
    public void setExpectedResult(ExpectResultType expectedResult) {
        this.expectedResult = expectedResult;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BetEntity)) return false;

        BetEntity betEntity = (BetEntity) o;

        if (id != betEntity.id) return false;
        if (userId != betEntity.userId) return false;
        if (competitorId != betEntity.competitorId) return false;
        if (cash != null ? !cash.equals(betEntity.cash) : betEntity.cash != null) return false;
        if (isWin != null ? !isWin.equals(betEntity.isWin) : betEntity.isWin != null) return false;
        if (isActive != null ? !isActive.equals(betEntity.isActive) : betEntity.isActive != null) return false;
        return expectedResult == betEntity.expectedResult;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (cash != null ? cash.hashCode() : 0);
        result = 31 * result + (isWin != null ? isWin.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + competitorId;
        result = 31 * result + (expectedResult != null ? expectedResult.hashCode() : 0);
        return result;
    }
}
