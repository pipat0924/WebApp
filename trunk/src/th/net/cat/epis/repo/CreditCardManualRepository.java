package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.repository.query.Param;

import th.net.cat.epis.entity.MethodChequeManual;
import th.net.cat.epis.entity.MethodCreditCard;
import th.net.cat.epis.entity.MethodCreditCardManual;
import th.net.cat.epis.entity.Transaction;

public interface CreditCardManualRepository extends PagingAndSortingRepository<MethodCreditCardManual, Long> {

	
	//@Query("select m from MethodCreditCard m where m.paymentId = ?1")
	List<MethodCreditCardManual> findByMethodManualId(@Param("methodManualId") Long methodId);
}