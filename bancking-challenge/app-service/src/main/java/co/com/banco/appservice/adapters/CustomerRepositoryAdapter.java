package co.com.banco.appservice.adapters;

import co.com.banco.appservice.adapters.database.jpa.entities.CustomerData;
import co.com.banco.appservice.adapters.database.jpa.repositories.JPACustomerDataRepository;
import co.com.banco.appservice.mapper.DataMapper;
import co.com.banco.gateway.CustomerRepository;
import co.com.banco.model.Customer;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;


@Repository
public class CustomerRepositoryAdapter extends AdapterOperations<Customer, CustomerData, Integer, JPACustomerDataRepository> implements CustomerRepository {


    public CustomerRepositoryAdapter(JPACustomerDataRepository repository, ObjectMapper mapper, Function<CustomerData, Customer> toEntityFn) {
        super(repository, mapper, toEntityFn);
    }


    @Override
    public List<Customer> encontrarClientes(String estado) {
        return null;
    }

    @Override
    public Mono<Customer> encontrarPorId(Integer id) {
        return null;
    }

    @Override
    public Mono<Customer> guardarCliente(Customer cliente) {
        return null;
    }


    /*
    private final JPACustomerDataRepository jpaCustomerDataRepository;

    @Override
    public Flux<Customer> findAll() {
        return Flux.fromIterable(jpaCustomerDataRepository.findAll())
                .map(this.mapper::toCustomerData);
    }

    @Override
    public Mono<Customer> encontrarPorId(Integer id) {
        return null;
    }

    @Override
    public Mono<Customer> guardarCliente(Customer cliente) {
        return null;
    }

     */
}
