package by.epam.litvin.bean;

public class CommandEntity extends Entity {

    private int id;
    private String name;
    private int kindOfSportId;

    public CommandEntity() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKindOfSportId() {
        return kindOfSportId;
    }

    public void setKindOfSportId(int kindOfSportId  ) {
        this.kindOfSportId = kindOfSportId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandEntity)) return false;

        CommandEntity that = (CommandEntity) o;

        if (id != that.id) return false;
        if (kindOfSportId != that.kindOfSportId) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + kindOfSportId;
        return result;
    }
}
