package co.com.banco.appservice.adapters.database.jpa.repositories;

import co.com.banco.appservice.adapters.database.jpa.entities.CustomerData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface JPACustomerDataRepository extends CrudRepository<CustomerData, Integer>, QueryByExampleExecutor<CustomerData> {
    List<CustomerData> findByEstado(String estado);
}