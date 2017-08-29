package by.epam.litvin.bean;


import java.math.BigDecimal;

public class CompetitorEntity extends Entity {

    private int id;
    private int teamId;
    private int competitionId;
    private BigDecimal winCoeff;
    private Boolean isWin;
    private int result;

    /**
     * Default constructor.
     */
    public CompetitorEntity() {}

    /**
     * Get result.
     *
     * @return
     */
    public int getResult() {
        return result;
    }

    /**
     * Set result.
     *
     * @param result
     */
    public void setResult(int result) {
        this.result = result;
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
     * Get team ID.
     *
     * @return
     */
    public int getTeamId() {
        return teamId;
    }

    /**
     * Set team ID.
     *
     * @param teamId
     */
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    /**
     * Get competition ID.
     *
     * @return
     */
    public int getCompetitionId() {
        return competitionId;
    }

    /**
     * Set competition ID.
     *
     * @param competitionId
     */
    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
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
     * Get win coefficient.
     *
     * @return
     */
    public BigDecimal getWinCoeff() {
        return winCoeff;
    }

    /**
     * Set win coefficient.
     *
     * @param winCoeff
     */
    public void setWinCoeff(BigDecimal winCoeff) {
        this.winCoeff = winCoeff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompetitorEntity)) return false;

        CompetitorEntity that = (CompetitorEntity) o;

        if (id != that.id) return false;
        if (teamId != that.teamId) return false;
        if (competitionId != that.competitionId) return false;
        if (result != that.result) return false;
        if (winCoeff != null ? !winCoeff.equals(that.winCoeff) : that.winCoeff != null) return false;
        return isWin != null ? isWin.equals(that.isWin) : that.isWin == null;
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + teamId;
        result1 = 31 * result1 + competitionId;
        result1 = 31 * result1 + (winCoeff != null ? winCoeff.hashCode() : 0);
        result1 = 31 * result1 + (isWin != null ? isWin.hashCode() : 0);
        result1 = 31 * result1 + result;
        return result1;
    }
}
