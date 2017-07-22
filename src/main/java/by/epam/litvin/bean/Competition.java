package by.epam.litvin.bean;

import java.math.BigDecimal;
import java.util.Date;


public class Competition extends Entity {

    private Integer id;
    private String name;
    private String descripton;
    private Date dateStart;
    private Date dateFinish;
    private Boolean isActive;
    private Integer typeId;
    private BigDecimal total;
    private BigDecimal moreTotal;
    private BigDecimal lessTotal;
    private BigDecimal resultTotal;

    public Competition() {}

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

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getMoreTotal() {
        return moreTotal;
    }

    public void setMoreTotal(BigDecimal moreTotal) {
        this.moreTotal = moreTotal;
    }

    public BigDecimal getLessTotal() {
        return lessTotal;
    }

    public void setLessTotal(BigDecimal lessTotal) {
        this.lessTotal = lessTotal;
    }

    public BigDecimal getResultTotal() {
        return resultTotal;
    }

    public void setResultTotal(BigDecimal resultTotal) {
        this.resultTotal = resultTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Competition that = (Competition) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (descripton != null ? !descripton.equals(that.descripton) : that.descripton != null) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (dateFinish != null ? !dateFinish.equals(that.dateFinish) : that.dateFinish != null) return false;
        if (isActive != null ? !isActive.equals(that.isActive) : that.isActive != null) return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (moreTotal != null ? !moreTotal.equals(that.moreTotal) : that.moreTotal != null) return false;
        if (lessTotal != null ? !lessTotal.equals(that.lessTotal) : that.lessTotal != null) return false;
        return resultTotal != null ? resultTotal.equals(that.resultTotal) : that.resultTotal == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (descripton != null ? descripton.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateFinish != null ? dateFinish.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (moreTotal != null ? moreTotal.hashCode() : 0);
        result = 31 * result + (lessTotal != null ? lessTotal.hashCode() : 0);
        result = 31 * result + (resultTotal != null ? resultTotal.hashCode() : 0);
        return result;
    }
}
