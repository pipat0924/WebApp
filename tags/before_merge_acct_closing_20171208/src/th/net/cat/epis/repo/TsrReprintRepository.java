package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.Trsreprint;

@RepositoryRestResource(path = "reprint-transaction")
public interface TsrReprintRepository extends PagingAndSortingRepository<Trsreprint, Long> { 

}
