package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.epis.entity.BillingGroupIgnore;
@RepositoryRestResource(path = "billing-group-ignore")
public interface BillingGroupIgnoreRepository //
//extends PagingAndSortingRepository<BillingGroupIgnore, Long> {
//extends CrudRepository <BillingGroupIgnore, Long> {
	 extends JpaRepository<BillingGroupIgnore, Long> {
	// List<BillingGroupIgnore>
		// findCountBillingGroupIgnore(@Param("billingGroup") String billingGroup);
		@RestResource(path = "findCountBillingGroupIgnore")
		 @Query(
		  "select r from BillingGroupIgnore r where lower(r.billingGroup) like %:billingGroup% "
		  )
		List<BillingGroupIgnore> findCountBillingGroupIgnore(@Param("billingGroup") String billingGroup );

		
}
