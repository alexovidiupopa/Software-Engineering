package ro.ubb.project.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.project.core.model.Person;
import ro.ubb.project.web.dto.PersonDto;

@Component
public class PersonConverter extends AbstractConverter<Person, PersonDto> implements Converter<Person, PersonDto> {

    @Override
    public Person dtoToModel(PersonDto personDto) {
        return Person.builder()
                .uid(personDto.getUid())
                .username(personDto.getUsername())
                .password(personDto.getPassword())
                .affiliation(personDto.getAffiliation())
                .firstname(personDto.getFirstname())
                .lastname(personDto.getLastname())
                .phonenumber(personDto.getPhonenumber())
                .email(personDto.getEmail())
                .website(personDto.getWebsite())
                .academicrank(personDto.getAcademicrank())
                .build();
    }

    @Override
    public PersonDto modelToDto(Person Person) {
        return PersonDto.builder()
                .uid(Person.getUid())
                .username(Person.getUsername())
                .password(Person.getPassword())
                .affiliation(Person.getAffiliation())
                .firstname(Person.getFirstname())
                .lastname(Person.getLastname())
                .phonenumber(Person.getPhonenumber())
                .email(Person.getEmail())
                .website(Person.getWebsite())
                .academicrank(Person.getAcademicrank())
                .build();
    }
}
