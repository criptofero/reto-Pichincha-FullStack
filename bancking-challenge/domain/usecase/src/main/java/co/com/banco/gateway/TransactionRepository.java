package co.com.banco.gateway;

import co.com.banco.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface TransactionRepository {
    Flux<Transaction> findAll();

    Flux<Transaction> verMovimientos();

    Mono<Transaction> encontrarPorId(Integer id);

    Mono<Transaction> crearMovimiento(Transaction movimiento);

    Flux<Transaction> generarReporteEntreFechas(Date inicio, Date fin);

    Mono<Void> deleteById(Integer id);

    Flux<Transaction> encontrarMovimientosPorCuentaAsociada(Integer id);
}
