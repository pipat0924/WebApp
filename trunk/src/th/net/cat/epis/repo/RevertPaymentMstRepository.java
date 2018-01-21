package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import th.net.cat.epis.entity.RevertPaymentMst;
import th.net.cat.epis.entity.RevertPaymentPK;

/**
 * Created by nastanda on 11/29/2016 AD.
 */

public interface RevertPaymentMstRepository extends PagingAndSortingRepository<RevertPaymentMst, Long> {
    RevertPaymentMst findByRevertPaymentPK(@Param("revertPaymentPK") RevertPaymentPK revertPaymentPK);
}
