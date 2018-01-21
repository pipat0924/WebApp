package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import th.net.cat.epis.entity.InvoiceVatDetail;

import java.util.List;

/**
 * Created by nastanda on 4/10/2017 AD.
 */
public interface InvoiceVatDetailRepository extends PagingAndSortingRepository<InvoiceVatDetail, Long>{
    List<InvoiceVatDetail> findByInvoiceId(Long invoiceId);

}
