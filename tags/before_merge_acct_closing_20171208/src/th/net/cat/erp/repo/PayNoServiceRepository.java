package th.net.cat.erp.repo;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.erp.entity.PayNoService;

@RepositoryRestResource(path = "erp-pay-no-services")
public interface PayNoServiceRepository extends PagingAndSortingRepository<PayNoService, String> {

	@RestResource(path = "updateDttm")
	Page<PayNoService> findByUpdateDttm(@Param("updateDttm") Date updateDttm, Pageable p);

	@RestResource(path = "branchCode")
	Page<PayNoService> findByBranchCode(@Param("branchCode") String branchCode, Pageable p);

	@RestResource(path = "product")
	Page<PayNoService> findByProduct(@Param("product") String product, Pageable p);

	@RestResource(path = "subProduct")
	Page<PayNoService> findBySubProduct(@Param("subProduct") String subProduct, Pageable p);

	@RestResource(path = "dateAndBranch")
	Page<PayNoService> findByUpdateDttmAndBranchCode(@Param("updateDttm") Date updateDttm, @Param("branchCode") String branchCode, Pageable p);

}