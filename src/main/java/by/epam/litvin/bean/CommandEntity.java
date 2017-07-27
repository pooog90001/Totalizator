package by.epam.litvin.bean;

public class CommandEntity extends Entity {

    private Integer id;
    private String name;
    private String kindOfSport;

    public CommandEntity() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKindOfSport() {
        return kindOfSport;
    }

    public void setKindOfSport(String kindOfSport) {
        this.kindOfSport = kindOfSport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandEntity command = (CommandEntity) o;

        if (id != null ? !id.equals(command.id) : command.id != null) return false;
        if (name != null ? !name.equals(command.name) : command.name != null) return false;
        return kindOfSport != null ? kindOfSport.equals(command.kindOfSport) : command.kindOfSport == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (kindOfSport != null ? kindOfSport.hashCode() : 0);
        return result;
    }
}
