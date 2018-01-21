package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.PromotionMappingEntity;
import th.net.cat.epis.entity.PromotionMappingEntityPK;



@RepositoryRestResource(path = "promotion-mapping")
public interface PromotionMappingRepository  extends JpaRepository<PromotionMappingEntity, PromotionMappingEntityPK> {
	  @Query("select p from PromotionMappingEntity p where p.baId = :baId and p.status='A' ")
	  List<PromotionMappingEntity> findActivePromotionMapping(@Param("baId") String baId);
	  
	  @Query("select p from PromotionMappingEntity p where p.baId = :baId and p.status='S' ")
	  List<PromotionMappingEntity> findInActivePromotionMapping(@Param("baId") String baId);
	  
	  @Query("select p from PromotionMappingEntity p where p.baId in :baIds and p.status='A' ") 
	  List<PromotionMappingEntity> findInActivePromotionMappingNotInAccount(@Param("baIds") List<String> baIds);
}
