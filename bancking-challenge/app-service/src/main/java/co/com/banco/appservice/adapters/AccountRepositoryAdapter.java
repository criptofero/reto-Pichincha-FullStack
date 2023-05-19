package co.com.banco.appservice.adapters;

import co.com.banco.appservice.adapters.database.jpa.repositories.JPAAccountDataRepository;
import co.com.banco.gateway.AccountRepository;
//import co.com.banco.model.Cuenta;
import co.com.banco.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor

@Component
public class AccountRepositoryAdapter implements AccountRepository {
    private final JPAAccountDataRepository jpaAccountDataRepository;

    @Override
    public Flux<Account> encontrarCuentas(String estado) {
        return null;
    }

    @Override
    public Mono<Account> encontrarCuentaPorId(Integer id) {
        return null;
    }

    @Override
    public Mono<Account> guardarCuenta(Account cuenta) {
        return null;
    }

    @Override
    public Mono<Account> buscarPorNumeroCuenta(String numeroCuenta) {
        return null;
    }
}
