package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import th.net.cat.epis.entity.Service;

public interface ServiceRepository extends PagingAndSortingRepository<Service, Long> {

	List<Service> findByReceiptId(Long id);
	
}