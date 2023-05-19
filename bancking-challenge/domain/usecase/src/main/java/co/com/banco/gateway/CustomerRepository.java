package co.com.banco.gateway;

import co.com.banco.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CustomerRepository {
    List<Customer> encontrarClientes(String estado);
    Mono<Customer> encontrarPorId(Integer id);

    Mono<Customer> guardarCliente(Customer cliente);
}
