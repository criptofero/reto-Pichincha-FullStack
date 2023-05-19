package co.com.banco.appservice.mapper;

import co.com.banco.appservice.adapters.database.jpa.entities.AccountData;
import co.com.banco.appservice.adapters.database.jpa.entities.CustomerData;
import co.com.banco.appservice.adapters.database.jpa.entities.PersonData;
import co.com.banco.model.Customer;
import co.com.banco.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public CustomerData toEnti(Customer customer){

        Person person = new Person(customer.getPerson().getId(),
                customer.getPerson().getNumberDocument(),
                customer.getPerson().getTypeDocument(),
                customer.getPerson().getName(),
                customer.getPerson().getLastName(),
                customer.getPerson().getGender(),
                customer.getPerson().getAddress(),
                customer.getPerson().getNumberPhone(),
                customer.getPerson().getCustomerDataList());


        PersonData personData = new PersonData(
                person.getId(),
                person.getNumberDocument(),
                person.getTypeDocument(),
                person.getName(),
                person.getLastName(),
                person.getGender(),
                person.getAddress(),
                person.getNumberPhone(),
                person.getCustomerDataList()
                        .stream()
                        .map(this::toEnti)
                        .collect(Collectors.toList())
        );

        AccountData accountData = AccountData
                .builder()

                .build();

        return null; //new CustomerData(customer.getId(), customer.getPassword(), customer.getEstado(), personData,customer.getPerson().getCustomerDataList());

    }

    public Customer toCustomerData(CustomerData customerData){

        Person person = new Person(
                customerData.getPersonData().getId(),
                customerData.getPersonData().getNumberDocument(),
                customerData.getPersonData().getTypeDocument(),
                customerData.getPersonData().getName(),
                customerData.getPersonData().getLastName(),
                customerData.getPersonData().getGender(),
                customerData.getPersonData().getAddress(),
                customerData.getPersonData().getNumberPhone(),
                customerData.getPersonData().getCustomerDataList().stream()
                        .map(this::toCustomerData)
                        .collect(Collectors.toList())
        );

        return Customer
                .builder()
                .id(customerData.getId())
                .password(customerData.getPassword())
                .estado(customerData.getEstado())
                .person(person)
                .build();
    }
}
