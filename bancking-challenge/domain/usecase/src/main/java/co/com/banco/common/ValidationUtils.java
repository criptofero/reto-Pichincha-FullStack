package co.com.banco.common;

import co.com.banco.model.Account;
import co.com.banco.model.Customer;
import co.com.banco.common.ex.BusinessException;
//import co.com.banco.model.Cuenta;
import co.com.banco.model.Transaction;
import co.com.banco.model.Person;

import java.util.Objects;

import static co.com.banco.common.StringUtils.isEmpty;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public final class ValidationUtils {


    private ValidationUtils() {
    }

    public static void verify(BusinessException.Type error) {
        if (nonNull(error)) {
            throw new BusinessException(error);
        }
    }

    public static BusinessException.Type required(BusinessException.Type[] exceptions, Object[] data) {
        BusinessException.Type required = null;
        for (int i = 0; i < data.length && isNull(required); i++) {
            boolean isRequired = (data[i] instanceof String && isEmpty((String) data[i])) || isNull(data[i]);

            if (isRequired) {
                required = exceptions[i];
            }
        }
        return required;
    }

    public static void validarPathConId (Integer idPath, Integer idObjeto){
        validarIdNulo(idObjeto);
        validarIdNulo(idPath);
        if (idPath != idObjeto) {
            throw new BusinessException(BusinessException.Type.PATH_NO_IGUAL_ID);
        }
    }

    public static void validarIdNulo(Integer id) {
        if (id == null || id < 0) {
            throw new BusinessException(BusinessException.Type.ID_NULL);
        }
    }

    public static void validarCamposPersona(Person person) {
        if (StringUtils.isEmpty(person.getNumberDocument(), person.getName(), person.getLastName(),
                person.getAddress(), person.getNumberPhone())) {
            throw new BusinessException(BusinessException.Type.ERROR_CAMPO_NULL_PERSONA);
        }
    }

    public static void validarCamposCliente(Customer cliente) {
        if (Objects.isNull(cliente.getPerson()) ||
                StringUtils.isEmpty(cliente.getPassword(),  cliente.getEstado())) {
            throw new BusinessException(BusinessException.Type.ERROR_CAMPO_NULL_CLIENTE);
        }
        validarCamposPersona(cliente.getPerson());

    }

    public static void validarCamposCuenta(Account cuenta) {
        if (StringUtils.isEmpty(cuenta.getNumeroCuenta(), cuenta.getTipoCuenta(), cuenta.getEstado())
                || cuenta.getSaldoInicial() == null || Objects.isNull(cuenta.getCliente())){
            throw new BusinessException(BusinessException.Type.ERROR_CAMPO_NULL_CUENTA);
        }
        validarCamposCliente(cuenta.getCliente());
    }

    public static void validarCamposMovimiento(Transaction movimiento) {
        if (
                movimiento.getFechaMovimiento() == null || movimiento.getValorMovimiento() == null
                        || movimiento.getTipoMovimiento() == null || movimiento.getSaldo() == null
                        || Objects.isNull(movimiento.getCuenta())
        ) {
            throw new BusinessException(BusinessException.Type.ERROR_CAMPO_NULL_MOVIMIENTO);
        }
        validarCamposCuenta(movimiento.getCuenta());
    }


}
