package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import th.net.cat.epis.entity.Method;
import th.net.cat.epis.entity.MethodMoneyTransfer;
import th.net.cat.epis.entity.Transaction;

public interface MoneyTransferRepository extends PagingAndSortingRepository<MethodMoneyTransfer, Long> {

	@Query("select m from MethodMoneyTransfer m where m.transaction = ?1")
	List<MethodMoneyTransfer> findByTransaction(Transaction t);

	@RestResource(path = "id")
	@Query("select m from MethodMoneyTransfer m where m.transaction.payment.id = ?1")
	List<MethodMoneyTransfer> findByTransaction(@Param("id") Long id);
	
}