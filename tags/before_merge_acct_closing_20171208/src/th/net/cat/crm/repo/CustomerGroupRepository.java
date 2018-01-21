package th.net.cat.crm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import th.net.cat.crm.entity.BillProfile;
import th.net.cat.crm.entity.CustomerGroup;

public interface CustomerGroupRepository extends CrudRepository<CustomerGroup, String>, JpaSpecificationExecutor<BillProfile> {

	@Query("SELECT g FROM CustomerGroup g ORDER BY g.order ASC")
	List<CustomerGroup> findAllOrderByOrderAsc();

}