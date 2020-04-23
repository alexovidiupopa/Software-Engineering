package ro.ubb.project.web.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class PersonDto implements Serializable {
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

}
