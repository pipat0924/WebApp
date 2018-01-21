package th.net.cat.epis.repo;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import th.net.cat.epis.entity.DW;

import java.util.List;

@RepositoryRestResource(path = "dw")
public interface DWRepository extends PagingAndSortingRepository<DW, Long> {
        @RestResource(path = "findDW")
       
    	@Query("select i from DW i where i.postDate like ?1% order by post_date desc ")
    	List<DW> findByPostDate(@Param("postDate") String postDate);

    }
