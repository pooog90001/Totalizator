package by.epam.litvin.bean;


public class KindOfSportEntity extends Entity {

    private int id;
    private String name;
    private int competitorCount;

    public KindOfSportEntity() {}

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

    public int getCompetitorCount() {
        return competitorCount;
    }

    public void setCompetitorCount(int competitorCount) {
        this.competitorCount = competitorCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KindOfSportEntity)) return false;

        KindOfSportEntity that = (KindOfSportEntity) o;

        if (id != that.id) return false;
        if (competitorCount != that.competitorCount) return false;
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
