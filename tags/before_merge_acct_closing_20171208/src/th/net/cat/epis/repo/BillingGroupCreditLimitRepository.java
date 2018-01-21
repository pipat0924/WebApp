package th.net.cat.epis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import th.net.cat.epis.entity.BillingGroupCreditLimit;

import java.util.List;

/**
 * Created by T'Tee Puthy on 2/6/2017.
 */
@RepositoryRestResource(path = "billing-group-credit-limit")
public interface BillingGroupCreditLimitRepository extends JpaRepository<BillingGroupCreditLimit, Long> {
        @RestResource(path = "findCountBillingGroupCreditLimit")
        @Query("select r from BillingGroupCreditLimit r where lower(r.billingGroupCode) like %:billingGroupCode% ")
        List<BillingGroupCreditLimit> findCountBillingGroupCreditLimit(@Param("billingGroupCode") String billingGroupCode );

    }
