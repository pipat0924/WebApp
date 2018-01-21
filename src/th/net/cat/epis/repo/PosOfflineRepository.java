package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.Pos;
import th.net.cat.epis.entity.PosOffline;

@RepositoryRestResource(path = "PosOffline")
public interface PosOfflineRepository extends PagingAndSortingRepository<PosOffline, Long> {

	List<PosOffline> findByNoIsOrNameIsOrShop_No(@Param("no") String no, @Param("name") String name, @Param("shop") String shop);

}