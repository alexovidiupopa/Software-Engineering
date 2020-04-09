package ro.ubb.iss.core.model;

import javax.persistence.*;

@Entity
@Table(name = "bidding", schema = "dbschema", catalog = "cms")
@IdClass(BiddingEntityPK.class)
public class BiddingEntity {
    private int pcid;
    private int pid;

    @Id
    @Column(name = "pcid", nullable = false)
    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }

    @Id
    @Column(name = "pid", nullable = false)
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BiddingEntity that = (BiddingEntity) o;

        if (pcid != that.pcid) return false;
        if (pid != that.pid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pcid;
        result = 31 * result + pid;
        return result;
    }
}
