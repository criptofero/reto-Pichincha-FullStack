package co.com.banco.appservice.mapper;

import co.com.banco.appservice.adapters.database.jpa.entities.AccountData;
import co.com.banco.appservice.adapters.database.jpa.entities.CustomerData;
import co.com.banco.appservice.adapters.database.jpa.entities.PersonData;
import co.com.banco.appservice.adapters.database.jpa.entities.TransactionData;
import co.com.banco.model.Account;
import co.com.banco.model.Customer;
import co.com.banco.model.Person;
import co.com.banco.model.Transaction;

import java.util.ArrayList;
import java.util.List;


public class DataMapper {

    private DataMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Transaction convertirMovimientoDataAMovimiento(TransactionData movimientoData) {
        return Transaction.builder()
                .id(movimientoData.getId())
                .fechaMovimiento(movimientoData.getFechaMovimiento())
                .tipoMovimiento(movimientoData.getTipoMovimiento())
                .valorMovimiento(movimientoData.getValorMovimiento())
                .saldo(movimientoData.getSaldo())
                .saldoAnterior(movimientoData.getSaldoAnterior())
                .cuenta(convertirCuentaDataACuenta(movimientoData.getAccountData()))
                .build();
    }

    public static TransactionData convertirMovimientoAMovimientoData(Transaction movimiento) {
        return TransactionData.builder()
                .id(movimiento.getId())
                .fechaMovimiento(movimiento.getFechaMovimiento())
                .tipoMovimiento(movimiento.getTipoMovimiento())
                .valorMovimiento(movimiento.getValorMovimiento())
                .saldo(movimiento.getSaldo())
                .saldoAnterior(movimiento.getSaldoAnterior())
                .accountData(convertirCuentaACuentaData(movimiento.getCuenta()))
                .build();
    }

    public static Account convertirCuentaDataACuenta(AccountData cuentaData) {
        return Account.builder()
                .id(cuentaData.getId())
                .numeroCuenta(cuentaData.getNumeroCuenta())
                .tipoCuenta(cuentaData.getTipoCuenta())
                .saldoInicial(cuentaData.getSaldoInicial())
                .estado(cuentaData.getEstado())
                .cliente(convertirClienteDataACliente(cuentaData.getClienteData()))
                .build();
    }

    public static AccountData convertirCuentaACuentaData(Account cuenta) {
        return AccountData.builder()
                .id(cuenta.getId())
                .numeroCuenta(cuenta.getNumeroCuenta())
                .tipoCuenta(cuenta.getTipoCuenta())
                .saldoInicial(cuenta.getSaldoInicial())
                .estado(cuenta.getEstado())
                .clienteData(convertirClienteAClienteData(cuenta.getCliente()))
                .build();
    }

    public static Customer convertirClienteDataACliente(CustomerData clienteData) {
        return Customer.builder()
                .id(clienteData.getId())
                .password(clienteData.getPassword())
                .password(clienteData.getPassword())
                .estado(clienteData.getEstado())
                .person(convertirPersonaDataAPersona(clienteData.getPersonData()))
                .build();
    }

    public static CustomerData convertirClienteAClienteData(Customer cliente) {
        return CustomerData.builder()
                .id(cliente.getId())
                .password(cliente.getPassword())
                .estado(cliente.getEstado())
                .personData(convertirPersonaAPersonaData(cliente.getPerson()))
                .build();
    }

    public static Person convertirPersonaDataAPersona(PersonData personaData) {

        Person person = new Person(personaData.getId(),
                personaData.getNumberDocument(),
                personaData.getTypeDocument(),
                personaData.getName(),
                personaData.getLastName(),
                personaData.getGender(),
                personaData.getAddress(),
                personaData.getNumberPhone());

        return person;
    }

    public static PersonData convertirPersonaAPersonaData(Person persona) {
        return PersonData.builder()
                .id(persona.getId())
                .numberDocument(persona.getNumberDocument())
                .typeDocument(persona.getTypeDocument())
                .name(persona.getName())
                .lastName(persona.getLastName())
                .gender(persona.getGender())
                .address(persona.getAddress())
                .numberPhone(persona.getNumberPhone())
                .build();
    }

    public static List<Transaction> converitirListaMovimientosDataAListaMovimiento(Iterable<TransactionData> movimientoDataIterable) {
        List<Transaction> listaMovimientos = new ArrayList<>();
        movimientoDataIterable.forEach(
                movimientoData -> listaMovimientos.add(
                        convertirMovimientoDataAMovimiento(movimientoData)
                )
        );
        return listaMovimientos;
    }

    public static List<Customer> convertirlistClienteDataAListCliente(Iterable<CustomerData> clienteDataIterable) {
        List<Customer> listaClientes = new ArrayList<>();
        clienteDataIterable.forEach(
                clienteData -> listaClientes.add(
                        convertirClienteDataACliente(clienteData)
                )
        );
        return listaClientes;
    }


    public static List<Account> converitirListaCuentasDataAListaCuentas(Iterable<AccountData> cuentasDataIterable) {
        List<Account> listaCuentas = new ArrayList<>();
        cuentasDataIterable.forEach(
                cuentaData -> listaCuentas.add(
                        convertirCuentaDataACuenta(cuentaData)
                )
        );
        return listaCuentas;
    }


}
