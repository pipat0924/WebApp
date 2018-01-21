package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import th.net.cat.epis.entity.Office;

import java.util.List;

/**
 * Created by T'Tee Puthy on 1/31/2017.
 */
@RepositoryRestResource(path = "offices")
public interface OfficeRepository extends PagingAndSortingRepository<Office, Long>{

    List<Office> findByBuPlaceCodeOrBuAreaCodeOrBuAreaName(@Param("buPlaceCode") String buPlaceCode, @Param("buAreaCode") String buAreaCode, @Param("buAreaName") String buAreaName);
}
