package ro.ubb.project.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Paper implements Serializable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paper paper = (Paper) o;
        return pid == paper.pid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private int aid;
    private int session;
    private String title;
    private String accepted;
    @Column(name="abstracturl")
    private String abstractUrl;
    @Column(name="contenturl")
    private String contentUrl;
}
