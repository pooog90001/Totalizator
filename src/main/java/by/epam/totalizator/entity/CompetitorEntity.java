package by.epam.totalizator.entity;


import java.math.BigDecimal;

public class CompetitorEntity extends Entity {

    private int id;
    private int teamId;
    private int competitionId;
    private BigDecimal winCoeff;
    private boolean isWin;
    private int result;

    /**
     * Default constructor.
     */
    public CompetitorEntity() {}

    /**
     * Get result.
     *
     * @return competitor result
     */
    public int getResult() {
        return result;
    }

    /**
     * Set result.
     *
     * @param result competitor result
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * Get ID.
     *
     * @return competitor id
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID.
     *
     * @param id competitor id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get team ID.
     *
     * @return team id
     *
     * @see TeamEntity
     */
    public int getTeamId() {
        return teamId;
    }

    /**
     * Set team ID.
     *
     * @param teamId competitor id
     *
     * @see TeamEntity
     */
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    /**
     * Get competition ID.
     *
     * @return competition id
     *
     * @see CompetitionEntity
     */
    public int getCompetitionId() {
        return competitionId;
    }

    /**
     * Set competition ID.
     *
     * @param competitionId competition id
     *
     * @see CompetitionEntity
     */
    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    /**
     * Get win.
     *
     * @return competitor win state
     */
    public boolean getWin() {
        return isWin;
    }

    /**
     * Set win.
     *
     * @param win competitor win state
     */
    public void setWin(boolean win) {
        isWin = win;
    }

    /**
     * Get win coefficient.
     *
     * @return competitor win state
     */
    public BigDecimal getWinCoeff() {
        return winCoeff;
    }

    /**
     * Set win coefficient.
     *
     * @param winCoeff competitor win state
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
        if (isWin != that.isWin) return false;
        if (result != that.result) return false;
        return winCoeff != null ? winCoeff.equals(that.winCoeff) : that.winCoeff == null;
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + teamId;
        result1 = 31 * result1 + competitionId;
        result1 = 31 * result1 + (winCoeff != null ? winCoeff.hashCode() : 0);
        result1 = 31 * result1 + (isWin ? 1 : 0);
        result1 = 31 * result1 + result;
        return result1;
    }
}
