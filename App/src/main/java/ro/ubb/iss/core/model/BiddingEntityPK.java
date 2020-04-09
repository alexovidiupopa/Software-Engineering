package ro.ubb.iss.core.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class BiddingEntityPK implements Serializable {
    private int pcid;
    private int pid;

    @Column(name = "pcid", nullable = false)
    @Id
    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }

    @Column(name = "pid", nullable = false)
    @Id
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

        BiddingEntityPK that = (BiddingEntityPK) o;

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
