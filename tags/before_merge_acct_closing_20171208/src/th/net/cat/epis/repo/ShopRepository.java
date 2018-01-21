package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.Shop;

@RepositoryRestResource(path = "shop")
public interface ShopRepository extends PagingAndSortingRepository<Shop, Long> {

	//List<Shop> findByBusinessPlaceIsOrBusinessPlaceIsOrNameStartingWith(@Param("businessPlace") String businessPlace, @Param("businessArea") String businessArea, @Param("name") String name);
	//List<Shop> findByBusinessPlaceContainingIgnoreCaseAndBusinessAreaContainingIgnoreCaseAndNameContainingIgnoreCase(@Param("businessPlace") String businessPlace, @Param("businessArea") String businessArea, @Param("name") String name);
	List<Shop> findByBusinessPlaceIsOrBusinessAreaIsOrName(@Param("businessPlace") String businessPlace, @Param("businessArea") String businessArea, @Param("name") String name);
	Shop findById(@Param("id") Long id);

}