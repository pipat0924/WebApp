package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.data.rest.core.annotation.RestResource;
import th.net.cat.epis.entity.Payment;
import th.net.cat.epis.entity.Transaction;


@RepositoryRestResource(path = "transactions")
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {

	@Query("select t from Transaction t where t.payment = ?1")
	List<Transaction> findByPayment(Payment p);

    @RestResource(path = "paymentAndServiceId")
	@Query("select t from Transaction t where t.payment.id = ?1 and t.serviceId = ?2")
	List<Transaction> findByPaymentAndServiceId(@Param("payment") Long payment, @Param("serviceId") Long serviceId);

	@RestResource(path = "serviceId")
	List<Transaction> findByServiceId(@Param("serviceId") Long serviceId);

}