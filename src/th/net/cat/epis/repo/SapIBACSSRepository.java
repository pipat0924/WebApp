package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.SapIBACSS;

@RepositoryRestResource(path = "ibacss")
public interface SapIBACSSRepository extends PagingAndSortingRepository<SapIBACSS, Long> {

}