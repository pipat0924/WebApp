package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.epis.entity.AccountTBOSS;

@RepositoryRestResource(path = "tboss-invoice")
public interface AccountTBOSSRepository extends PagingAndSortingRepository<AccountTBOSS, Long> {

	@RestResource(path = "no")
	List<AccountTBOSS> findByInvoiceNo(@Param("no") Integer invoiceNo);
	
	@RestResource(path = "contractNo")
	List<AccountTBOSS> findByContractNo(@Param("no") String contractNo);
}