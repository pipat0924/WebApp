package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.repository.query.Param;
import th.net.cat.epis.entity.Invoice;

public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long> {

	List<Invoice> findByReceiptId(Long id);
	Invoice findByReceiptIdAndNo(@Param("receiptId") Long receiptId, @Param("no") String no);
	
}