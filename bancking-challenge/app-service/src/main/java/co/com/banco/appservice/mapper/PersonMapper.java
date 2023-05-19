package co.com.banco.appservice.mapper;

import co.com.banco.appservice.adapters.database.jpa.entities.PersonData;
import co.com.banco.appservice.entrypoint.dto.PersonDTO;
import co.com.banco.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    /*
    public Person toPerson(PersonDTO personDTO){
        return Person
                .builder()
                .id(personDTO.getId())
                .numberDocument(personDTO.getNumberDocument())
                .typeDocument(personDTO.getTypeDocument())
                .name(personDTO.getName())
                .lastName(personDTO.getLastName())
                .gender(personDTO.getGender())
                .address(personDTO.getAddress())
                .numberPhone(personDTO.getNumberPhone())
                .build();
    }

    public PersonDTO toData(Person person){
        return PersonDTO
                .builder()
                .id(person.getId())
                .numberDocument(person.getNumberDocument())
                .typeDocument(person.getTypeDocument())
                .name(person.getName())
                .lastName(person.getLastName())
                .gender(person.getGender())
                .address(person.getAddress())
                .numberPhone(person.getNumberPhone())
                .build();
    }

    public PersonData toEnti(Person person){
        return PersonData
                .builder()
                .id(person.getId())
                .numberDocument(person.getNumberDocument())
                .typeDocument(person.getTypeDocument())
                .name(person.getName())
                .lastName(person.getLastName())
                .gender(person.getGender())
                .address(person.getAddress())
                .numberPhone(person.getNumberPhone())
                .build();
    }

    public Person toPersonData(PersonData personData){
        return Person
                .builder()
                .id(personData.getId())
                .numberDocument(personData.getNumberDocument())
                .typeDocument(personData.getTypeDocument())
                .name(personData.getName())
                .lastName(personData.getLastName())
                .gender(personData.getGender())
                .address(personData.getAddress())
                .numberPhone(personData.getNumberPhone())
                .build();
    }

     */
}
