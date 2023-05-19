package co.com.banco.appservice.adapters.database.jpa.entities;

import co.com.banco.model.Customer;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "T_PERSONAS")
@MappedSuperclass
public class PersonData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 6, max = 10)
    @Column(name = "numero_documento", unique = true)
    private String numberDocument;

    @NotEmpty
    @Column(name = "tipo_documento", length = 20)
    private String typeDocument;

    @NotEmpty
    @Size(min = 3, max = 50)
    @Column(name = "nombre", length = 50)
    private String name;

    @NotEmpty
    @Size(min = 3, max = 50)
    @Column(name = "apellido", length = 50)
    private String lastName;

    @NotEmpty
    @Size(min = 3, max = 15)
    @Column(name = "genero")
    private String gender;

    @NotEmpty
    @Size(min = 5, max = 50)
    @Column(name = "direccion")
    private String address;

    @NotEmpty
    @Size(min = 9, max = 12)
    @Column(name = "telefono")
    private String numberPhone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personData")
    private List<CustomerData> customerDataList;

}