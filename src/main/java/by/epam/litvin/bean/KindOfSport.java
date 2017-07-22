package by.epam.litvin.bean;


public class KindOfSport extends Entity {

    private Integer id;
    private String name;
    private Integer competitorCount;

    public KindOfSport() {}

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

    public Integer getCompetitorCount() {
        return competitorCount;
    }

    public void setCompetitorCount(Integer competitorCount) {
        this.competitorCount = competitorCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KindOfSport that = (KindOfSport) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return competitorCount != null ? competitorCount.equals(that.competitorCount) : that.competitorCount == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (competitorCount != null ? competitorCount.hashCode() : 0);
        return result;
    }
}
