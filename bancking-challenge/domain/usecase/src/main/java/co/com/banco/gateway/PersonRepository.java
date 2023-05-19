package co.com.banco.gateway;


import co.com.banco.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository {


    Mono<Person> findByNumberId(String typeId, String numberId);

    Flux<Person> findAll();

    Mono<Person> save(Person person);

    Mono<Person> update(Person person, Integer id);

    Mono<Person> findById(Integer id);

    Mono<Void> deleteByNumberDocument(String numberDocument);
}
