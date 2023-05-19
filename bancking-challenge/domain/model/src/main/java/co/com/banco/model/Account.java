package co.com.banco.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Account{
    private Integer id;
    private String numeroCuenta;
    private String tipoCuenta;
    private Long saldoInicial;
    private String estado;
    private List<Transaction> listaMovimientos;
    private Customer cliente;
}
