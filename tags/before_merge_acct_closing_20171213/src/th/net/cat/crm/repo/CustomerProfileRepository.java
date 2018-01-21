package th.net.cat.crm.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.crm.entity.BillProfile;
import th.net.cat.crm.entity.CustomerProfile;
import java.util.List;

@RepositoryRestResource(path = "cust-profiles")
public interface CustomerProfileRepository extends PagingAndSortingRepository<CustomerProfile, String>, JpaSpecificationExecutor<BillProfile> {

	@RestResource(path = "id")
	Page<CustomerProfile> findById(@Param("id") String id, Pageable p);

	@RestResource(path = "no")
	Page<CustomerProfile> findByNoStartingWith(@Param("no") String no, Pageable p);

	@RestResource(path = "billingNo")
	List<CustomerProfile> findByNo(@Param("billingNo") String billingNo);

}