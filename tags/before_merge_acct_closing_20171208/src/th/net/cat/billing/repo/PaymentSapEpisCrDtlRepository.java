package th.net.cat.billing.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.billing.entity.PaymentSapEpisCrDtl;

@RepositoryRestResource(path = "PaymentSapEpisCrDtl")
public interface PaymentSapEpisCrDtlRepository extends PagingAndSortingRepository<PaymentSapEpisCrDtl, Long> {

}
