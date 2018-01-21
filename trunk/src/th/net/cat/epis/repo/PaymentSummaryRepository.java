package th.net.cat.epis.repo;

import org.springframework.data.repository.CrudRepository;

import th.net.cat.epis.entity.PaymentSummary;

public interface PaymentSummaryRepository extends CrudRepository<PaymentSummary, Long> {

	PaymentSummary findBySessionId(Long sessionId);

}