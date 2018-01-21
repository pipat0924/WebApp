package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.AdjustSapInsertTmpLog;

@RepositoryRestResource(path = "adjust-sap-insert-tmp-log")
public interface AdjustSapInsertTmpLogRepository extends PagingAndSortingRepository<AdjustSapInsertTmpLog, Long> {
	AdjustSapInsertTmpLog findById(@Param("id") Long id);
}
