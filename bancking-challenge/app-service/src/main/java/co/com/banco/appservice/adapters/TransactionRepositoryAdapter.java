package co.com.banco.appservice.adapters;

import co.com.banco.appservice.adapters.database.jpa.repositories.JPATransactionDataRepository;
import co.com.banco.gateway.TransactionRepository;
import co.com.banco.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RequiredArgsConstructor
@Component

public class TransactionRepositoryAdapter implements TransactionRepository {
    private final JPATransactionDataRepository jpaTransactionDataRepository;
    @Override
    public Flux<Transaction> findAll() {
        return null;
    }

    @Override
    public Flux<Transaction> verMovimientos() {
        return null;
    }

    @Override
    public Mono<Transaction> encontrarPorId(Integer id) {
        return null;
    }

    @Override
    public Mono<Transaction> crearMovimiento(Transaction movimiento) {
        return null;
    }

    @Override
    public Flux<Transaction> generarReporteEntreFechas(Date inicio, Date fin) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        return null;
    }

    @Override
    public Flux<Transaction> encontrarMovimientosPorCuentaAsociada(Integer id) {
        return null;
    }
}
