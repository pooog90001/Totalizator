package by.epam.litvin.bean;

import java.math.BigDecimal;
import java.util.Date;


public class CompetitionEntity extends Entity {

    private int id;
    private String name;
    private String descripton;
    private Date dateStart;
    private Date dateFinish;
    private boolean isResultFilled;
    private boolean isActive;
    private int typeId;
    private BigDecimal total;
    private BigDecimal moreTotal;
    private BigDecimal lessTotal;
    private BigDecimal standoff;

    /**
     * Default constructor.
     */
    public CompetitionEntity() {}

    /**
     * Constructor with params.
     *
     * @param name
     * @param dateStart
     * @param dateFinish
     * @param isActive
     * @param typeId
     */
    public CompetitionEntity(String name, Date dateStart, Date dateFinish, boolean isActive, int typeId) {
        this.name = name;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.isActive = isActive;
        this.typeId = typeId;
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
     * Get description.
     *
     * @return
     */
    public String getDescripton() {
        return descripton;
    }

    /**
     * Set description.
     *
     * @param descripton
     */
    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    /**
     * Get date start.
     *
     * @return
     */
    public Date getDateStart() {
        return dateStart;
    }

    /**
     * Set date start.
     *
     * @param dateStart
     */
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * Get date finish.
     *
     * @return
     */
    public Date getDateFinish() {
        return dateFinish;
    }

    /**
     * Set date finish.
     *
     * @param dateFinish
     */
    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    /**
     * Get active.
     *
     * @return
     */
    public Boolean getActive() {
        return isActive;
    }

    /**
     * Set active.
     *
     * @param active
     */
    public void setActive(Boolean active) {
        isActive = active;
    }

    /**
     * Get type ID.
     *
     * @return
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * Set type ID.
     *
     * @param typeId
     */
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    /**
     * Get total.
     *
     * @return
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Set total.
     *
     * @param total
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * Get more total.
     *
     * @return
     */
    public BigDecimal getMoreTotal() {
        return moreTotal;
    }

    /**
     * Set more total.
     *
     * @param moreTotal
     */
    public void setMoreTotal(BigDecimal moreTotal) {
        this.moreTotal = moreTotal;
    }

    /**
     * Get less total.
     *
     * @return
     */
    public BigDecimal getLessTotal() {
        return lessTotal;
    }

    /**
     * Set less total.
     *
     * @param lessTotal
     */
    public void setLessTotal(BigDecimal lessTotal) {
        this.lessTotal = lessTotal;
    }

    /**
     * Get standoff.
     *
     * @return
     */
    public BigDecimal getStandoff() {
        return standoff;
    }

    /**
     * Set standoff.
     *
     * @param standoff
     */
    public void setStandoff(BigDecimal standoff) {
        this.standoff = standoff;
    }

    /**
     * Get result filled.
     *
     * @return
     */
    public boolean getResultFilled() {
        return isResultFilled;
    }

    /**
     * Set result filled.
     *
     * @param resultFilled
     */
    public void setResultFilled(boolean resultFilled) {
        isResultFilled = resultFilled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompetitionEntity)) return false;

        CompetitionEntity that = (CompetitionEntity) o;

        if (id != that.id) return false;
        if (isResultFilled != that.isResultFilled) return false;
        if (isActive != that.isActive) return false;
        if (typeId != that.typeId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (descripton != null ? !descripton.equals(that.descripton) : that.descripton != null) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (dateFinish != null ? !dateFinish.equals(that.dateFinish) : that.dateFinish != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (moreTotal != null ? !moreTotal.equals(that.moreTotal) : that.moreTotal != null) return false;
        if (lessTotal != null ? !lessTotal.equals(that.lessTotal) : that.lessTotal != null) return false;
        return standoff != null ? standoff.equals(that.standoff) : that.standoff == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (descripton != null ? descripton.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateFinish != null ? dateFinish.hashCode() : 0);
        result = 31 * result + (isResultFilled ? 1 : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + typeId;
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (moreTotal != null ? moreTotal.hashCode() : 0);
        result = 31 * result + (lessTotal != null ? lessTotal.hashCode() : 0);
        result = 31 * result + (standoff != null ? standoff.hashCode() : 0);
        return result;
    }
}
