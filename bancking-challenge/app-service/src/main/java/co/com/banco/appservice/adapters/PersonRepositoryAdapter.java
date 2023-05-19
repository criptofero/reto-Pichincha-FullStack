package co.com.banco.appservice.adapters;

import co.com.banco.appservice.adapters.database.jpa.entities.PersonData;
import co.com.banco.appservice.adapters.database.jpa.repositories.JPAPersonDataRepository;
import co.com.banco.appservice.entrypoint.dto.PersonDTO;
import co.com.banco.appservice.mapper.PersonMapper;
import co.com.banco.gateway.PersonRepository;
import co.com.banco.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class PersonRepositoryAdapter implements PersonRepository {
    private final JPAPersonDataRepository jpaPersonDataRepository;

    private final PersonMapper mapper;


    @Override
    public Mono<Person> findByNumberId(String typeId, String numberId) {
        return null;
    }

    @Override
    public Flux<Person> findAll() {
        return null;
    }

    @Override
    public Mono<Person> save(Person person) {
        return null;
    }

    @Override
    public Mono<Person> update(Person person, Integer id) {
        return null;
    }

    @Override
    public Mono<Person> findById(Integer id) {
        return null;
    }

    @Override
    public Mono<Void> deleteByNumberDocument(String numberDocument) {
        return null;
    }

    /*
    @Override
    public Flux<Person> findAll() {
        return Flux.fromIterable(jpaPersonDataRepository.findAll())
                .map(this.mapper::toPersonData);
    }

    @Override
    public Mono<Person> save(Person person) {
        //Mono.just(jpaPersonDataRepository.savePerson(mapper.toEnti(person)));
        return Mono.just(jpaPersonDataRepository.save(mapper.toEnti(person)))
                .map(this.mapper::toPersonData);

    }


    @Override
    public Mono<Person> update(Person person, Integer id) {
        Mono.just(jpaPersonDataRepository.findById(id).get())
                .map(personData -> {
                    personData.setNumberDocument(person.getNumberDocument());
                    personData.setTypeDocument(person.getTypeDocument());
                    personData.setName(person.getName());
                    personData.setLastName(person.getLastName());
                    personData.setGender(person.getGender());
                    personData.setAddress(person.getAddress());
                    personData.setNumberPhone(person.getNumberPhone());
                    Mono.just(jpaPersonDataRepository.save(personData));

                    return personData;
                });//.doOnNext(jpaPersonDataRepository.save());
        return null;
    }



    @Override
    public Mono<Person> findById(Integer id) {
        //jpaPersonDataRepository.findById(id);
        log.info("DISPARA CASO DE USO");
        return Mono.just(jpaPersonDataRepository.findById(id).get())
                .map(this.mapper::toPersonData);
    }

    @Override
    public Mono<Void> deleteByNumberDocument(String numberDocument) {
        jpaPersonDataRepository.delete(jpaPersonDataRepository.findByNumberDocument(numberDocument));
        log.info("Usuario borrado Correctamente");
        return Mono.empty();
    }

     */

}
