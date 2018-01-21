package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.epis.entity.ContractTBOSS;

@RepositoryRestResource(path = "tboss-contract")
public interface ContractTBOSSRepository extends PagingAndSortingRepository<ContractTBOSS, Long> {

	@RestResource(path = "no")
	List<ContractTBOSS> findByContractNo(@Param("no") String contractNo);
}