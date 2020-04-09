package ro.ubb.iss.core.model;

import javax.persistence.*;

@Entity
@Table(name = "papersubjects", schema = "dbschema", catalog = "cms")
@IdClass(PapersubjectsEntityPK.class)
public class PapersubjectsEntity {
    private int pid;
    private int kid;

    @Id
    @Column(name = "pid", nullable = false)
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Id
    @Column(name = "kid", nullable = false)
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

        PapersubjectsEntity that = (PapersubjectsEntity) o;

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
