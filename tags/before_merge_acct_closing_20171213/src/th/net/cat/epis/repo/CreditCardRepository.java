package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.repository.query.Param;
import th.net.cat.epis.entity.MethodCreditCard;
import th.net.cat.epis.entity.Transaction;

public interface CreditCardRepository extends PagingAndSortingRepository<MethodCreditCard, Long> {

	@Query("select m from MethodCreditCard m where m.transaction = ?1")
	List<MethodCreditCard> findByTransaction(Transaction t);
	
	//@Query("select m from MethodCreditCard m where m.paymentId = ?1")
	List<MethodCreditCard> findByPaymentId(@Param("paymentId") Long paymentId);
}