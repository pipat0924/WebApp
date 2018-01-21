package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.MethodDiscount;

@RepositoryRestResource(path = "discounts")
public interface DiscountRepository extends PagingAndSortingRepository<MethodDiscount, Long> {

}