package th.net.cat.billing.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.billing.entity.PaymentSapEpisOthoCrDtl;

@RepositoryRestResource(path = "PaymentSapEpisOthoCrDtl")
public interface PaymentSapEpisOthoCrDtlRepository extends PagingAndSortingRepository<PaymentSapEpisOthoCrDtl,Long> {

}
