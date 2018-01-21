package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.TrsMethodManualEntity;


@RepositoryRestResource(path = "trsMethodManualEntity")
public interface TrsMethodManualRepository extends PagingAndSortingRepository<TrsMethodManualEntity, Long>{

	List<TrsMethodManualEntity> findByManualId(Long manualID);
}
