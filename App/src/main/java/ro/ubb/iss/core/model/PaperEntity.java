package ro.ubb.iss.core.model;

import javax.persistence.*;

@Entity
@Table(name = "paper", schema = "dbschema", catalog = "cms")
public class PaperEntity {
    private int pid;
    private String topic;
    private String accepted;
    private String abstracturl;
    private String contenturl;
    private String presentationurl;

    @Id
    @Column(name = "pid", nullable = false)
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "topic", nullable = true, length = 50)
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Basic
    @Column(name = "accepted", nullable = true, length = 10)
    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    @Basic
    @Column(name = "abstracturl", nullable = true, length = 50)
    public String getAbstracturl() {
        return abstracturl;
    }

    public void setAbstracturl(String abstracturl) {
        this.abstracturl = abstracturl;
    }

    @Basic
    @Column(name = "contenturl", nullable = true, length = 50)
    public String getContenturl() {
        return contenturl;
    }

    public void setContenturl(String contenturl) {
        this.contenturl = contenturl;
    }

    @Basic
    @Column(name = "presentationurl", nullable = true, length = 50)
    public String getPresentationurl() {
        return presentationurl;
    }

    public void setPresentationurl(String presentationurl) {
        this.presentationurl = presentationurl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaperEntity that = (PaperEntity) o;

        if (pid != that.pid) return false;
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;
        if (accepted != null ? !accepted.equals(that.accepted) : that.accepted != null) return false;
        if (abstracturl != null ? !abstracturl.equals(that.abstracturl) : that.abstracturl != null) return false;
        if (contenturl != null ? !contenturl.equals(that.contenturl) : that.contenturl != null) return false;
        if (presentationurl != null ? !presentationurl.equals(that.presentationurl) : that.presentationurl != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pid;
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (accepted != null ? accepted.hashCode() : 0);
        result = 31 * result + (abstracturl != null ? abstracturl.hashCode() : 0);
        result = 31 * result + (contenturl != null ? contenturl.hashCode() : 0);
        result = 31 * result + (presentationurl != null ? presentationurl.hashCode() : 0);
        return result;
    }
}
