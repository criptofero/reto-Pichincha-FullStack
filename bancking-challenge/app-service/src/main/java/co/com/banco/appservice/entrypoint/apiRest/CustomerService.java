package co.com.banco.appservice.entrypoint.apiRest;

import co.com.banco.model.Customer;
import co.com.banco.usecase.cliente.ClienteUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@CrossOrigin(
        origins = {"http://localhost:4200"},
        methods = {RequestMethod.GET, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
@Slf4j
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class CustomerService {

    private final ClienteUseCase clienteUseCase;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Customer> showAll() {
        log.info("ENTRE AL CONTROLADOR");

        return clienteUseCase.findAll();
    }

}