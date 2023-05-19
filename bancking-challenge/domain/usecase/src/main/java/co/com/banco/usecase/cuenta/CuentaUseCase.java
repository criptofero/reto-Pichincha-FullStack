package co.com.banco.usecase.cuenta;

import co.com.banco.common.ex.BusinessException;
import co.com.banco.gateway.AccountRepository;
import co.com.banco.model.Account;
import co.com.banco.usecase.cliente.ClienteUseCase;
import co.com.banco.usecase.person.PersonUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static co.com.banco.common.ValidationUtils.validarCamposCuenta;
import static co.com.banco.common.ValidationUtils.validarIdNulo;

@RequiredArgsConstructor
public class CuentaUseCase {

    public static final String INACTIVO = "INACTIVO";
    public static final String ACTIVO = "ACTIVO";
    private final AccountRepository accountRepository;
    private final ClienteUseCase clienteUseCase;
    private final PersonUseCase personUseCase;

    public Flux<Account> obtenerCuentas() {
        return accountRepository.encontrarCuentas(ACTIVO);
    }

    public Mono<Account> obtenerCuentaPor(Integer id) {
        validarIdNulo(id);
        return accountRepository.encontrarCuentaPorId(id)
                .filter(cuentaEncontrada -> !cuentaEncontrada.getEstado().equalsIgnoreCase(ACTIVO))
                .switchIfEmpty(Mono.defer(() ->{
            throw new BusinessException(BusinessException.Type.CUENTA_NO_ENCONTRADA);
        }));
    }


    public Mono<Account> guardar(Account cuenta) {
        validarCamposCuenta(cuenta);
        validarTipoCuenta(cuenta.getTipoCuenta());
        //validarSiExisteClienteYPersona(cuenta);
        validarSiExisteCuenta(cuenta);
        return accountRepository.guardarCuenta(cuenta);
    }


    public Mono<Void> eliminar(Integer id) {
        return obtenerCuentaPor(id).flatMap(cuentaEncontrada -> {
            cuentaEncontrada.setEstado(INACTIVO);
           return accountRepository.guardarCuenta(cuentaEncontrada);
        }).then();
    }

    public Mono<Account> actualizar(Integer id, Account cuenta) {
        validarIdNulo(id);
        validarCamposCuenta(cuenta);
        validarTipoCuenta(cuenta.getTipoCuenta());
        return accountRepository.buscarPorNumeroCuenta(cuenta.getNumeroCuenta()).filter(cuentaEnBaseDeDatos -> {
            return !cuentaEnBaseDeDatos.getEstado().equalsIgnoreCase(ACTIVO);
        }) .switchIfEmpty(Mono.defer(() ->{
            throw new BusinessException(BusinessException.Type.CUENTA_NO_ENCONTRADA);
        })).flatMap(cuentaEnBaseDeDatos -> {
            cuentaEnBaseDeDatos.setTipoCuenta(cuenta.getTipoCuenta());
            cuentaEnBaseDeDatos.setEstado(cuenta.getEstado());
            cuentaEnBaseDeDatos.setSaldoInicial(cuenta.getSaldoInicial());
            return accountRepository.guardarCuenta(cuentaEnBaseDeDatos);
        });
    }

    private void validarTipoCuenta(String tipoCuenta) {
        if (!tipoCuenta.equalsIgnoreCase("AHORRO") && !tipoCuenta.equalsIgnoreCase("CORRIENTE")) {
            throw new BusinessException(BusinessException.Type.TIPO_CUENTA_NO_VALIDO);
        }
    }

    private Mono<Void> validarSiExisteCuenta(Account cuenta) {
        return accountRepository.buscarPorNumeroCuenta(cuenta.getNumeroCuenta()).switchIfEmpty(Mono.defer(() -> {
            throw new BusinessException(BusinessException.Type.CUENTA_YA_EXISTE);
        })).then();
    }

    /*
    private Mono<Void> validarSiExisteClienteYPersona(Cuenta cuenta) {
        return Flux.just(
                clienteUseCase.validarSiExisteCliente(cuenta),
                personUseCase.validarSiExistePersona(cuenta)
        ).then();
    }

     */


}
