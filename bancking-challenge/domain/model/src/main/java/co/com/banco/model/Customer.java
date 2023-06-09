package co.com.banco.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Customer extends Person {
    private Integer id;
    private String password;
    private String estado;
    private Person person;
    private List<Account> cuentaDataList;

}
