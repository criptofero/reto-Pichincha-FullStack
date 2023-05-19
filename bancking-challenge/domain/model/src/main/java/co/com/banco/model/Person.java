package co.com.banco.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@Builder(toBuilder = true)
public class Person implements Serializable {
    private Integer id;
    private String numberDocument;
    private String typeDocument;
    private String name;
    private String lastName;
    private String gender;
    private String address;
    private String numberPhone;

}
