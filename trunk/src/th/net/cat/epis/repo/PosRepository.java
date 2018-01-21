package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.Pos;

@RepositoryRestResource(path = "Pos")
public interface PosRepository extends PagingAndSortingRepository<Pos, Long> {

//	List<Pos> findByNoIsOrNameIsOrShop_No(@Param("no") String no, @Param("name") String name, @Param("shop") String shop);
	List<Pos> findByNoContainingIgnoreCaseAndNameContainingIgnoreCase(@Param("no") String no, @Param("name") String name);
	List<Pos> findByNoContainingIgnoreCaseAndNameContainingIgnoreCaseAndShop_No(@Param("no") String no, @Param("name") String name, @Param("shop") String shop);

	
	List<Pos> findByShop_Id(@Param("shop") Long shop);
}