package th.net.cat.crm.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.crm.entity.BillAddress;

@RepositoryRestResource(path = "bill-addrs")
public interface BillAddressRepository extends PagingAndSortingRepository<BillAddress, String> {

	@RestResource(path = "id")
	Page<BillAddress> findById(@Param("id") String id, Pageable p);

	@RestResource(path = "no")
	Page<BillAddress> findByNo(@Param("no") String no, Pageable p);

}