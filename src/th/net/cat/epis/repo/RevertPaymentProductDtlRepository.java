package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import th.net.cat.epis.entity.RevertPaymentProductDtl;

/**
 * Created by nastanda on 11/30/2016 AD.
 */
public interface RevertPaymentProductDtlRepository extends PagingAndSortingRepository<RevertPaymentProductDtl, Long> {
    RevertPaymentProductDtl findByRevertProDtlId(@Param("revertProDtlId") Long revertProDtlId);
}
