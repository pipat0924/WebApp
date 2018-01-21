package th.net.cat.epis.repo;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.epis.entity.Session;

@RepositoryRestResource(path = "sessions")
public interface SessionRepository extends CrudRepository<Session, Long> {

	@RestResource(path = "userName")
	Session findByUserName(@Param("userName") String userName);

	@RestResource(path = "firstTime")
	Session findByFirstTime(@Param("firstTime") Date firstTime);
}