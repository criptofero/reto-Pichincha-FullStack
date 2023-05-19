package co.com.banco.appservice.adapters.database.jpa.repositories;

import co.com.banco.appservice.adapters.database.jpa.entities.TransactionData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Date;
import java.util.List;

public interface JPATransactionDataRepository extends CrudRepository<TransactionData, Integer>, QueryByExampleExecutor<TransactionData> {
    List<TransactionData> findByFechaMovimientoBetween(Date inicio, Date fin);

    List<TransactionData> findByCuentaDataId(Integer id);
}