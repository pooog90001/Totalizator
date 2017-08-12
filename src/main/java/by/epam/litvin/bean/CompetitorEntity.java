package by.epam.litvin.bean;


import java.math.BigDecimal;

public class CompetitorEntity extends Entity {

    private int id;
    private int commandId;
    private int competitionId;
    private BigDecimal winCoeff;
    private Boolean isWin;

    public CompetitorEntity() {}

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
        if (winCoeff != null ? !winCoeff.equals(that.winCoeff) : that.winCoeff != null) return false;
        return isWin != null ? isWin.equals(that.isWin) : that.isWin == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + commandId;
        result = 31 * result + competitionId;
        result = 31 * result + (winCoeff != null ? winCoeff.hashCode() : 0);
        result = 31 * result + (isWin != null ? isWin.hashCode() : 0);
        return result;
    }
}
