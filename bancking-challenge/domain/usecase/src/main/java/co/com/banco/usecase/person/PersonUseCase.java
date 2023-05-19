package co.com.banco.usecase.person;


import co.com.banco.gateway.PersonRepository;
import co.com.banco.common.ex.BusinessException;
import co.com.banco.model.Person;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class PersonUseCase {

    public static final String ESTADO_INACTIVO = "INACTIVO";
    public static final String ESTADO_ACTIVO = "ACTIVO";

    private final PersonRepository personRepository;

    public Mono<Person> findByNumberId(String tipoDocumento, String numeroDocumento) {
        return personRepository.findByNumberId(tipoDocumento, numeroDocumento)
                .switchIfEmpty(Mono.defer(() -> {
                    throw new BusinessException(BusinessException.Type.ERROR_PERSONA_NO_REGISTRADA);
                }));
    }

    public Mono<Person> save(Person person) {
        return personRepository.save(person);
    }

    public Mono<Person> update(Person person, Integer id) {
        return personRepository.update(person,id);
    }


    public Mono<Person> findById(Integer id) {
        return personRepository.findById(id);
    }

    public Flux<Person> findAll() {
        return personRepository.findAll();
    }

    public Mono<Void> deleteByNumberDocument(String numberDocument){
        return personRepository.deleteByNumberDocument(numberDocument);
    }

}
