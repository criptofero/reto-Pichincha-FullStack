package co.com.banco.appservice.adapters.database.jpa.repositories;

import co.com.banco.appservice.adapters.database.jpa.entities.AccountData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface JPAAccountDataRepository extends CrudRepository<AccountData, Integer>, QueryByExampleExecutor<AccountData> {
    AccountData findByNumeroCuenta(String numeroCuenta);
    List<AccountData> findByEstado(String estado);
}