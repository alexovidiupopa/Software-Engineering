package ro.ubb.iss.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pc", schema = "dbschema", catalog = "cms")
public class PcEntity {
    private int pcid;

    @Id
    @Column(name = "pcid", nullable = false)
    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PcEntity pcEntity = (PcEntity) o;

        if (pcid != pcEntity.pcid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return pcid;
    }
}
