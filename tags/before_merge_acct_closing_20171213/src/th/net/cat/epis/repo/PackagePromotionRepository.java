package th.net.cat.epis.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.epis.entity.PackagePromotion;
@RepositoryRestResource(path = "package-promotion")
public interface PackagePromotionRepository extends JpaRepository<PackagePromotion, Long> {
	@RestResource(path = "findCountPackagePromotion")
	 @Query(
	  "select r from PackagePromotion r where lower(r.packageId) like %:packageId% "
	  )
	List<PackagePromotion> findCountPackagePromotion(@Param("packageId") String packageId );
}
