package by.epam.totalizator.entity;


public class CompetitionTypeEntity extends Entity {

    private int id;
    private String name;

    /**
     * Default constructor.
     */
    public CompetitionTypeEntity() {}

    /**
     * Get id
     *
     * @return competition type id
     */
    public int getId() {
        return id;
    }

    /**
     * Set id
     *
     * @param id competition type id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get name
     *
     * @return competition type name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     *
     * @param name competition type name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompetitionTypeEntity)) {
            return false;
        }

        CompetitionTypeEntity that = (CompetitionTypeEntity) o;

        if (id != that.id) {
            return false;
        }
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
