package by.epam.totalizator.entity;


public class KindOfSportEntity extends Entity {

    private int id;
    private String name;
    private int competitorCount;

    /**
     * Default constructor.
     */
    public KindOfSportEntity() {}

    /**
     * Get ID.
     *
     * @return kind of sport id
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID.
     *
     * @param id kind of sport id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get name.
     *
     * @return kind of sport name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param name kind of sport name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get competitor count.
     *
     * @return competitor count
     */
    public int getCompetitorCount() {
        return competitorCount;
    }

    /**
     * Set competition count.
     *
     * @param competitorCount competitor count
     */
    public void setCompetitorCount(int competitorCount) {
        this.competitorCount = competitorCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KindOfSportEntity)) {
            return false;
        }

        KindOfSportEntity that = (KindOfSportEntity) o;

        if (id != that.id) {
            return false;
        }
        if (competitorCount != that.competitorCount) {
            return false;
        }
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + competitorCount;
        return result;
    }
}
