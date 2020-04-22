package ro.ubb.project.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@Setter
@EqualsAndHashCode
public class Person {

    @Id
    private int uid;

    private String username;
    private String password;
    private String website;
    private String affiliation;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String academicRank;

    @Override
    public String toString() {
        return "Person{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", website='" + website + '\'' +
                ", affiliation='" + affiliation + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", academicRank='" + academicRank + '\'' +
                '}';
    }
}
