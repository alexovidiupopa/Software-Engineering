package ro.ubb.iss.core.model;

import javax.persistence.*;

@Entity
@Table(name = "keyword", schema = "dbschema", catalog = "cms")
public class KeywordEntity {
    private int kid;
    private String name;

    @Id
    @Column(name = "kid", nullable = false)
    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
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

        KeywordEntity that = (KeywordEntity) o;

        if (kid != that.kid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = kid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
