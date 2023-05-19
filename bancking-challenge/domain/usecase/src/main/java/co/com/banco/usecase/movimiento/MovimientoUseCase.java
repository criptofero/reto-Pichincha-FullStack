package co.com.banco.usecase.movimiento;

import co.com.banco.gateway.TransactionRepository;
import co.com.banco.common.ex.BusinessException;
//import co.com.banco.model.Cuenta;
import co.com.banco.model.Account;
import co.com.banco.model.Transaction;
import co.com.banco.usecase.cliente.ClienteUseCase;
import co.com.banco.usecase.cuenta.CuentaUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static co.com.banco.common.ValidationUtils.*;


@RequiredArgsConstructor
public class MovimientoUseCase {

    public static final String DEBITO = "DEBITO";
    public static final String CREDITO = "CREDITO";

    private final MovimientoDebitoUseCase movimientoDebitoUseCase;
    private final MovimientoCreditoUseCase movimientoCreditoUseCase;
    private final TransactionRepository transactionRepository;
    private final CuentaUseCase cuentaUseCase;
    private final ClienteUseCase clienteUseCase;


    public Flux<Transaction> obtenerMovimientos() {
        return transactionRepository.verMovimientos();
    }

    public Mono<Transaction> encontrarPorId(Integer id) {
        validarIdNulo(id);
        return transactionRepository.encontrarPorId(id).switchIfEmpty(Mono.defer(() -> {
            throw new BusinessException(BusinessException.Type.MOVIMIENTO_NO_ENCONTRADO);
        }));
    }


    public Mono<Transaction> guardarMovimiento(Transaction movimiento) {
        validarCamposMovimiento(movimiento);
        validarEstructuraMovimiento(movimiento);
        if (movimiento.getValorMovimiento() <= 0L) {
            throw new BusinessException(BusinessException.Type.MOVIMIENTO_NO_PERMITIDO);
        }
        return aplicarMovimiento(movimiento).flatMap(movimientoAplicado -> {
            return guardarNuevoSaldoEnCuenta(movimientoAplicado.getCuenta())
                    .flatMap(cuentaConNuevoSaldo -> {
                        movimientoAplicado.setCuenta(cuentaConNuevoSaldo);
                        return transactionRepository.crearMovimiento(movimientoAplicado);
                    });
        });
    }



    public Mono<Void> eliminarMovimiento(Integer id) {
        encontrarPorId(id);
        return transactionRepository.deleteById(id);
    }

    public Mono<Transaction> actualizarMovimiento(Integer id, Transaction movimiento) {
        validarPathConId(id, movimiento.getId());
        validarSiEsUltimoMovimiento(movimiento);
        return encontrarPorId(id).flatMap(movimientoEnBaseDatos -> {
            movimientoEnBaseDatos.setFechaMovimiento(movimiento.getFechaMovimiento());
            movimientoEnBaseDatos.setTipoMovimiento(movimiento.getTipoMovimiento());
            movimientoEnBaseDatos.setValorMovimiento(movimiento.getValorMovimiento());
            movimientoEnBaseDatos.setSaldo(movimiento.getSaldoAnterior());
            return guardarMovimiento(movimientoEnBaseDatos);
        });

    }

    private Mono<Transaction> aplicarMovimiento(Transaction movimiento) {
        switch (movimiento.getTipoMovimiento().toUpperCase()) {

            case DEBITO:
                return movimientoDebitoUseCase.construirMovimientoDebito(movimiento);

            case CREDITO:
                return movimientoCreditoUseCase.construirMovimientoCredito(movimiento);

            default:
                throw new BusinessException(BusinessException.Type.TIPO_MOVIMIENTO_NO_VALIDO);
        }
    }

    private Mono<Account> guardarNuevoSaldoEnCuenta(Account cuenta) {
        return cuentaUseCase.guardar(cuenta);
    }


    private Mono<Void> validarEstructuraMovimiento(Transaction movimiento) {

        return clienteUseCase.obtenerPor(movimiento.getCuenta().getCliente().getId())
                .filter(cliente -> Objects.nonNull(cliente.getPerson()))
                .switchIfEmpty(Mono.defer(() -> {
                    throw new BusinessException(BusinessException.Type.ERROR_CLIENTE_NO_REGISTRADO);
                }))
                .filter(cliente -> !cliente.getCuentaDataList().isEmpty())  .switchIfEmpty(Mono.defer(() -> {
            throw new BusinessException(BusinessException.Type.ERROR_CUENTA_NO_REGISTRADO);
        })).then();


    }



    private Mono<Void> validarSiEsUltimoMovimiento(Transaction movimiento) {
                return transactionRepository.encontrarMovimientosPorCuentaAsociada(movimiento.getCuenta().getId())
                        .collectList()
                        .map(movimientosCuenta -> {
                    movimientosCuenta.forEach(
                            movimientoActual -> {
                                if (movimientoActual.getId() > movimiento.getId()) {
                                    throw new BusinessException(BusinessException.Type.MOVIMIENTO_NO_ES_ULTIMO);
                                }
                            }
                    );
                    return movimientosCuenta;
                }).then();



    }


}
