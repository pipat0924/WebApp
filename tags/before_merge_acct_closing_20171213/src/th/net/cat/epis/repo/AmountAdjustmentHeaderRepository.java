package th.net.cat.epis.repo;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import th.net.cat.epis.entity.AmountAdjustmentHeader;

@RepositoryRestResource(path = "amount-adjustment")
public interface AmountAdjustmentHeaderRepository extends PagingAndSortingRepository<AmountAdjustmentHeader, Long>{
	AmountAdjustmentHeader findById(@Param("id") Long id);
}
