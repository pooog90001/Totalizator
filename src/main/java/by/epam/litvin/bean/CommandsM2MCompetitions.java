package by.epam.litvin.bean;


public class CommandsM2MCompetitions extends Entity {

    private Integer id;
    private Integer commandId;
    private Integer competitionId;
    private Boolean isWin;

    public CommandsM2MCompetitions() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommandId() {
        return commandId;
    }

    public void setCommandId(Integer commandId) {
        this.commandId = commandId;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public Boolean getWin() {
        return isWin;
    }

    public void setWin(Boolean win) {
        isWin = win;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandsM2MCompetitions that = (CommandsM2MCompetitions) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (commandId != null ? !commandId.equals(that.commandId) : that.commandId != null) return false;
        if (competitionId != null ? !competitionId.equals(that.competitionId) : that.competitionId != null)
            return false;
        return isWin != null ? isWin.equals(that.isWin) : that.isWin == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (commandId != null ? commandId.hashCode() : 0);
        result = 31 * result + (competitionId != null ? competitionId.hashCode() : 0);
        result = 31 * result + (isWin != null ? isWin.hashCode() : 0);
        return result;
    }
}
