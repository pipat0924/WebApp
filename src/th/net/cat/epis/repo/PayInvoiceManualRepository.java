package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import th.net.cat.epis.entity.PayInvoiceManualEntity;

public interface PayInvoiceManualRepository extends PagingAndSortingRepository<PayInvoiceManualEntity, Long> {
	@Query("select p from PayInvoiceManualEntity p where p.manual_id = ?1")
	List<PayInvoiceManualEntity> findByManualID(Long manual_id);
}
