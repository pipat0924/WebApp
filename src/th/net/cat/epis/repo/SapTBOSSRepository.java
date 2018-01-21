package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.SapTBOSS;

@RepositoryRestResource(path = "tboss")
public interface SapTBOSSRepository extends PagingAndSortingRepository<SapTBOSS, Long> {

}