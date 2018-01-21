package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.SapOutsideTBOSS;

@RepositoryRestResource(path = "otboss")
public interface SapOTBOSSRepository extends PagingAndSortingRepository<SapOutsideTBOSS, Long> {

}