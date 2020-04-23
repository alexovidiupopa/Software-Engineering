package ro.ubb.project.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    private String username;
    private String password;
    private String website;
    private String affiliation;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String email;
    private String academicrank;

    @Override
    public String toString() {
        return "Person{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", website='" + website + '\'' +
                ", affiliation='" + affiliation + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", academicrank='" + academicrank + '\'' +
                '}';
    }
}
