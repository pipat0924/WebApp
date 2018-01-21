package th.net.cat.epis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import th.net.cat.epis.entity.BillingGroupCreditLimit;
import th.net.cat.epis.entity.CreditLimitTransEntity;
import th.net.cat.epis.entity.CreditLimitTransEntityPK;

/**
 * Created by imake on 01/03/2017
 */

@RepositoryRestResource(path = "credit-limit-trans")
public interface CreditLimitTransRepository extends JpaRepository<CreditLimitTransEntity, CreditLimitTransEntityPK> {
}
