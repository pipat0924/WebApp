package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import th.net.cat.epis.entity.RevertPaymentInvoiceDtl;

/**
 * Created by nastanda on 11/30/2016 AD.
 */
public interface RevertPaymentInvoiceDtlRepository extends PagingAndSortingRepository<RevertPaymentInvoiceDtl, Long> {
    RevertPaymentInvoiceDtl findByInvoiceNoAndRevertReqNoAndReceiptNo(@Param("invoiceNo") String invoiceNo, @Param("revertReqNo") String revertReqNo, @Param("receiptNo") String receiptNo);

    RevertPaymentInvoiceDtl findByRevertInvDtlId(@Param("revertInvDtlId") Long revertInvDtlId);
}
