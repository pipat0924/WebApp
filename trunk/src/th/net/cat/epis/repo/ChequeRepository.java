package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import th.net.cat.epis.entity.MethodCheque;
import th.net.cat.epis.entity.MethodCreditCard;
import th.net.cat.epis.entity.MethodMoneyTransfer;
import th.net.cat.epis.entity.Transaction;

public interface ChequeRepository extends PagingAndSortingRepository<MethodCheque, Long> {

	@Query("select m from MethodCheque m where m.methodId = ?1")
	List<MethodCheque> findByPaymentId(Long t);


}