package ro.ubb.iss.core.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class PapersubjectsEntityPK implements Serializable {
    private int pid;
    private int kid;

    @Column(name = "pid", nullable = false)
    @Id
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Column(name = "kid", nullable = false)
    @Id
    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PapersubjectsEntityPK that = (PapersubjectsEntityPK) o;

        if (pid != that.pid) return false;
        if (kid != that.kid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pid;
        result = 31 * result + kid;
        return result;
    }
}
