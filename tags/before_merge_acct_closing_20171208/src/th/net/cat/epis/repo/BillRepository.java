package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.BillInvoice;

@RepositoryRestResource(path = "bills")
public interface BillRepository extends PagingAndSortingRepository<BillInvoice, Long> {

}