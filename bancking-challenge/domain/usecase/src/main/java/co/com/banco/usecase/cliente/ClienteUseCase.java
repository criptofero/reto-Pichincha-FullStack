package co.com.banco.usecase.cliente;

import co.com.banco.gateway.CustomerRepository;
import co.com.banco.model.Customer;
import co.com.banco.common.ex.BusinessException;
import co.com.banco.usecase.person.PersonUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import static co.com.banco.common.ValidationUtils.validarCamposCliente;
import static co.com.banco.common.ValidationUtils.validarIdNulo;

@RequiredArgsConstructor
public class ClienteUseCase {

    public static final String ESTADO_INACTIVO = "INACTIVO";
    public static final String ESTADO_ACTIVO = "ACTIVO";
    private final CustomerRepository customerRepository;
    private final PersonUseCase personUseCase;

    public Flux<Customer> findAll() {

        return customerRepository.findAll();
    }

    public Mono<Customer> obtenerPor(Integer id) {
        validarIdNulo(id);
        return customerRepository.encontrarPorId(id)
                .filter(cliente -> !cliente.getEstado().equalsIgnoreCase(ESTADO_ACTIVO))
                .switchIfEmpty(Mono.defer(() -> {
                    throw new BusinessException(BusinessException.Type.ERROR_CLIENTE_NO_REGISTRADO);
                }));
    }


    public Mono<Customer> guardar(Customer cliente) {
        validarCamposCliente(cliente);
        return personUseCase.findByNumberId(
                cliente.getPerson().getTypeDocument(), cliente.getPerson().getNumberDocument()
        ).switchIfEmpty(Mono.defer(() -> {
            throw new BusinessException(BusinessException.Type.PERSONA_EXISTE);
        })).flatMap(persona -> personUseCase.save(cliente.getPerson()))
        .flatMap(personaCreada -> {
            cliente.setPerson(personaCreada);
            return customerRepository.guardarCliente(cliente);
        });
    }


    public Mono<Void> inactivarPor(Integer id) {
        return obtenerPor(id).flatMap(clienteEncontrado -> {
            clienteEncontrado.setEstado(ESTADO_INACTIVO);
            return customerRepository.guardarCliente(clienteEncontrado);
        }).then();

    }
    /*
    public Mono<Cliente> actualizar(Integer id, Cliente cliente) {
        validarCamposCliente(cliente);

        return personUseCase.findByNumberId(
                cliente.getPerson().getTypeDocument(), cliente.getPerson().getNumberDocument()
        ).switchIfEmpty(Mono.defer(() -> {
            throw new BusinessException(BusinessException.Type.ERROR_PERSONA_NO_REGISTRADA);
        })).flatMap(persona -> personUseCase.editarPersona( cliente, persona))
                .flatMap(personaActualizada -> obtenerPor(id).flatMap(clienteEnBaseDeDatos -> {
            clienteEnBaseDeDatos.setPassword(cliente.getPassword());
            clienteEnBaseDeDatos.setPerson(personaActualizada);
            return clienteRepository.guardarCliente(clienteEnBaseDeDatos);
        }));
    }


    public Mono<Void> validarSiExisteCliente(Cuenta cuenta) {
        return clienteRepository.encontrarPorId(cuenta.getCliente().getId()).switchIfEmpty(Mono.defer(()->{
            throw new BusinessException(BusinessException.Type.ERROR_PERSONA_NO_REGISTRADA);
        })).then();
    }

     */

}
