package th.net.cat.billing.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.billing.entity.PaymentSapEpisOthoRev;

@RepositoryRestResource(path = "PaymentSapEpisOthoRev")
public interface PaymentSapEpisOthoRevRepository extends PagingAndSortingRepository<PaymentSapEpisOthoRev,Long>{

}
