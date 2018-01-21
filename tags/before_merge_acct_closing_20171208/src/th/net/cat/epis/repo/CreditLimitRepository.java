package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.CreditLimit;

@RepositoryRestResource(path = "CreditLimit")
public interface CreditLimitRepository extends PagingAndSortingRepository<CreditLimit, Long> {

}