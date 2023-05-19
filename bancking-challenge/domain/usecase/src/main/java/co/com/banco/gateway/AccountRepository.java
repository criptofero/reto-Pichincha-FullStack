package co.com.banco.gateway;

import co.com.banco.model.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepository {
    Flux<Account> encontrarCuentas(String estado);

    Mono<Account> encontrarCuentaPorId(Integer id);

    Mono<Account> guardarCuenta(Account cuenta);

    Mono<Account> buscarPorNumeroCuenta(String numeroCuenta);
}
