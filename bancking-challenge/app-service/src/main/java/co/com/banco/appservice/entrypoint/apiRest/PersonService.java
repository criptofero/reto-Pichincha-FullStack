package co.com.banco.appservice.entrypoint.apiRest;


import co.com.banco.appservice.entrypoint.dto.PersonDTO;
import co.com.banco.appservice.mapper.PersonMapper;
import co.com.banco.model.Person;
import co.com.banco.usecase.person.PersonUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@CrossOrigin(
        origins = {"http://localhost:4200"},
        methods = {RequestMethod.GET, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonService {

    private final PersonUseCase personUseCase;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Person> findAllPerson() {
        return personUseCase.findAll();
    }

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Person> createPerson(@Valid @RequestBody Person person){
        return personUseCase.save(person);
    }

    @Transactional
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Person> PersonById(@PathVariable Integer id){
        return personUseCase.findById(id);
    }

    @Transactional
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Person> updatePerson(@Valid @RequestBody Person person, @PathVariable Integer id){
        return personUseCase.update(person,id);
    }
    /*
    @Transactional
    @DeleteMapping("/{numberDocument}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<Void> deletePerson(@PathVariable String numberDocument){
        return personUseCase.deleteByNumberDocument(numberDocument);
    }

     */

}
