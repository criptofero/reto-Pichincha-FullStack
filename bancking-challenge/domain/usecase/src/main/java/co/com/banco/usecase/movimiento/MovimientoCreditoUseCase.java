package co.com.banco.usecase.movimiento;

import co.com.banco.model.Transaction;
import co.com.banco.gateway.AccountRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class MovimientoCreditoUseCase {

    private final AccountRepository accountRepository;

    public Mono<Transaction> construirMovimientoCredito(Transaction movimiento) {
        if (movimiento.getId() == null) {
            return construirNuevoMovimiento(movimiento);
        }
        return construirReversionMovimiento(movimiento);
    }

    private Mono<Transaction> construirReversionMovimiento(Transaction movimiento) {
        return Mono.just(movimiento).map(movimiento1 -> {
            movimiento1.setSaldo(movimiento.getSaldoAnterior() + movimiento.getValorMovimiento());
            movimiento1.getCuenta().setSaldoInicial(movimiento.getSaldo());
            return movimiento1;
        });

    }

    private Mono<Transaction> construirNuevoMovimiento(Transaction movimiento) {
        return accountRepository.encontrarCuentaPorId(movimiento.getCuenta().getId()).map(cuentaConsultada -> {
            movimiento.setSaldo(cuentaConsultada.getSaldoInicial() + movimiento.getValorMovimiento());
            movimiento.setSaldoAnterior(cuentaConsultada.getSaldoInicial());
            movimiento.getCuenta().setSaldoInicial(movimiento.getSaldo());
            return movimiento;
        });

    }
}
