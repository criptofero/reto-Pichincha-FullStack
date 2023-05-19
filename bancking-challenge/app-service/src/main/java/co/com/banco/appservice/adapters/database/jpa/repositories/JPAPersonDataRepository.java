package co.com.banco.appservice.adapters.database.jpa.repositories;

import co.com.banco.appservice.adapters.database.jpa.entities.PersonData;
import co.com.banco.appservice.entrypoint.dto.PersonDTO;
import co.com.banco.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import reactor.core.publisher.Mono;

public interface JPAPersonDataRepository extends CrudRepository<PersonData, Integer>, QueryByExampleExecutor<PersonData> {
    PersonData findByNumberDocument(String numberDocument);



}