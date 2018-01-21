package th.net.cat.crm.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import th.net.cat.crm.entity.BillProfile;
import th.net.cat.crm.entity.CustomerAddress;

import java.util.List;

/**
 * Created by nastanda on 4/21/2017 AD.
 */
@RepositoryRestResource(path = "cust-addresses")
public interface CustomerAddressRepository extends PagingAndSortingRepository<CustomerAddress, String>, JpaSpecificationExecutor<BillProfile> {
    @RestResource(path = "customerId")
    List<CustomerAddress> findByCustomerId(@Param("customerId") String customerId);
    @RestResource(path = "customerNo")
    List<CustomerAddress> findByCustomerNo(@Param("customerNo") String customerNo);
}
