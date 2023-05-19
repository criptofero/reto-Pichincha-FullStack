package co.com.banco.usecase.movimiento;

import co.com.banco.common.ex.BusinessException;
import co.com.banco.model.Transaction;
import co.com.banco.usecase.cuenta.CuentaUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class MovimientoDebitoUseCase {
    private final CuentaUseCase cuentaUseCase;

    public Mono<Transaction> construirMovimientoDebito(Transaction movimiento) {
        movimiento.setValorMovimiento(movimiento.getValorMovimiento() * -1L);
        if (movimiento.getId() == null) {
            return construirNuevoMovimiento(movimiento);
        }
        return construirReversionMovimiento(movimiento);
    }

    private Mono<Transaction> construirNuevoMovimiento(Transaction movimiento) {
        return cuentaUseCase.obtenerCuentaPor(movimiento.getCuenta().getId()).map(cuentaConsultada -> {
            validarSaldoDisponibleEnCuenta(movimiento);
            movimiento.setSaldo(cuentaConsultada.getSaldoInicial() + movimiento.getValorMovimiento());
            movimiento.setSaldoAnterior(cuentaConsultada.getSaldoInicial());
            movimiento.getCuenta().setSaldoInicial(movimiento.getSaldo());
            return movimiento;
        });
    }

    private Mono<Transaction> construirReversionMovimiento(Transaction movimiento) {
        validarSaldoDisponibleEnCuentaAlRevertir(movimiento);
        movimiento.setSaldo(movimiento.getSaldoAnterior() + movimiento.getValorMovimiento());
        movimiento.getCuenta().setSaldoInicial(movimiento.getSaldo());
        return Mono.just(movimiento);
    }

    private void validarSaldoDisponibleEnCuenta(Transaction movimiento) {
        cuentaUseCase.obtenerCuentaPor(movimiento.getCuenta().getId()).filter(cuentaConsultada -> {
            return cuentaConsultada.getSaldoInicial() + movimiento.getValorMovimiento() < 0L;
        }).switchIfEmpty(Mono.defer(() -> {
            throw new BusinessException(BusinessException.Type.SALDO_INFERIOR_CERO);
        }));
    }

    private void validarSaldoDisponibleEnCuentaAlRevertir(Transaction movimiento) {
        if (movimiento.getSaldoAnterior() + movimiento.getValorMovimiento() < 0L) {
            throw new BusinessException(BusinessException.Type.SALDO_INFERIOR_CERO);
        }
    }
}
