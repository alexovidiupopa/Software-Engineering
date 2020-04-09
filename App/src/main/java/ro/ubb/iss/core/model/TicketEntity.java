package ro.ubb.iss.core.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "ticket", schema = "dbschema", catalog = "cms")
public class TicketEntity {
    private int tid;
    private BigDecimal price;
    private Integer seatno;
    private Date datepurchased;
    private String name;

    @Id
    @Column(name = "tid", nullable = false)
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "seatno", nullable = true)
    public Integer getSeatno() {
        return seatno;
    }

    public void setSeatno(Integer seatno) {
        this.seatno = seatno;
    }

    @Basic
    @Column(name = "datepurchased", nullable = true)
    public Date getDatepurchased() {
        return datepurchased;
    }

    public void setDatepurchased(Date datepurchased) {
        this.datepurchased = datepurchased;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketEntity that = (TicketEntity) o;

        if (tid != that.tid) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (seatno != null ? !seatno.equals(that.seatno) : that.seatno != null) return false;
        if (datepurchased != null ? !datepurchased.equals(that.datepurchased) : that.datepurchased != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tid;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (seatno != null ? seatno.hashCode() : 0);
        result = 31 * result + (datepurchased != null ? datepurchased.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
