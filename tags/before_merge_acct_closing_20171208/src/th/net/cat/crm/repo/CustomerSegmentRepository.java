package th.net.cat.crm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import th.net.cat.crm.entity.BillProfile;
import th.net.cat.crm.entity.CustomerSegment;

public interface CustomerSegmentRepository extends CrudRepository<CustomerSegment, String>, JpaSpecificationExecutor<BillProfile> {

	@Query("SELECT s FROM CustomerSegment s ORDER BY s.order ASC")
	List<CustomerSegment> findAllOrderByOrderAsc();

}