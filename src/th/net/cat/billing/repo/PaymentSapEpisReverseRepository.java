package th.net.cat.billing.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.billing.entity.PaymentSapEpisReverse;

@RepositoryRestResource(path = "PaymentSapEpisReverse")
public interface PaymentSapEpisReverseRepository extends PagingAndSortingRepository<PaymentSapEpisReverse,Long>{

}
