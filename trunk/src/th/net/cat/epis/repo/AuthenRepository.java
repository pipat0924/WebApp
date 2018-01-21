package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.Authen;

@RepositoryRestResource(path = "authens")
public interface AuthenRepository extends CrudRepository<Authen, Long> {
	
	List<Authen> findByOfficerId(Long officerId);

}