package th.net.cat.billing.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.billing.entity.AdjustSapInsertTmp;

@RepositoryRestResource(path = "adjust-sap-insert-tmp")
public interface AdjustSapInsertTmpRepository extends PagingAndSortingRepository<AdjustSapInsertTmp, Long> {
	AdjustSapInsertTmp findById(@Param("id") Long id);
}
