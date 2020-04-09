package ro.ubb.iss.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chair", schema = "dbschema", catalog = "cms")
public class ChairEntity {
    private int cid;

    @Id
    @Column(name = "cid", nullable = false)
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChairEntity that = (ChairEntity) o;

        if (cid != that.cid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return cid;
    }
}
