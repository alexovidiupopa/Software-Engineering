package ro.ubb.iss.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "author", schema = "dbschema", catalog = "cms")
public class AuthorEntity {
    private int aid;

    @Id
    @Column(name = "aid", nullable = false)
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorEntity that = (AuthorEntity) o;

        if (aid != that.aid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return aid;
    }
}
