package by.epam.litvin.bean;


import java.math.BigDecimal;

public class CompetitorEntity extends Entity {

    private int id;
    private int commandId;
    private int competitionId;
    private BigDecimal winCoeff;
    private Boolean isWin;
    private int result;

    public CompetitorEntity() {}

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public Boolean getWin() {
        return isWin;
    }

    public void setWin(Boolean win) {
        isWin = win;
    }

    public BigDecimal getWinCoeff() {
        return winCoeff;
    }

    public void setWinCoeff(BigDecimal winCoeff) {
        this.winCoeff = winCoeff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompetitorEntity)) return false;

        CompetitorEntity that = (CompetitorEntity) o;

        if (id != that.id) return false;
        if (commandId != that.commandId) return false;
        if (competitionId != that.competitionId) return false;
        if (result != that.result) return false;
        if (winCoeff != null ? !winCoeff.equals(that.winCoeff) : that.winCoeff != null) return false;
        return isWin != null ? isWin.equals(that.isWin) : that.isWin == null;
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + commandId;
        result1 = 31 * result1 + competitionId;
        result1 = 31 * result1 + (winCoeff != null ? winCoeff.hashCode() : 0);
        result1 = 31 * result1 + (isWin != null ? isWin.hashCode() : 0);
        result1 = 31 * result1 + result;
        return result1;
    }
}
