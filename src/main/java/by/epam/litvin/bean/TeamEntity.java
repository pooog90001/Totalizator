package by.epam.litvin.bean;

public class TeamEntity extends Entity {

    private int id;
    private String name;
    private int kindOfSportId;

    /**
     * Default constructor.
     */
    public TeamEntity() {
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
     * Get name.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get kind of sport ID.
     *
     * @return
     */
    public int getKindOfSportId() {
        return kindOfSportId;
    }

    /**
     * Set kind of sport ID.
     *
     * @param kindOfSportId
     */
    public void setKindOfSportId(int kindOfSportId  ) {
        this.kindOfSportId = kindOfSportId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamEntity)) return false;

        TeamEntity that = (TeamEntity) o;

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
