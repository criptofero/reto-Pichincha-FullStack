package co.com.banco.appservice.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO implements Serializable {
    private Integer id;
    private String numberDocument;
    private String typeDocument;
    private String name;
    private String lastName;
    private String gender;
    private String address;
    private String numberPhone;
    private static final long serialVersionUID = 1L;
}
