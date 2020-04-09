package ro.ubb.iss.core.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "session", schema = "dbschema", catalog = "cms")
public class SessionEntity {
    private int sid;
    private Time time;

    @Id
    @Column(name = "sid", nullable = false)
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionEntity that = (SessionEntity) o;

        if (sid != that.sid) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sid;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
