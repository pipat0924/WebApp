package th.net.cat.erp.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.erp.entity.PayService;

@RepositoryRestResource(path = "erp-pay-services")
public interface PayServiceRepository extends PagingAndSortingRepository<PayService, String> {

	@RestResource(path = "branch")
	Page<PayService> findByBranch(@Param("branch") String branch, Pageable p);

	@RestResource(path = "account")
	Page<PayService> findByAccount(@Param("account") String account, Pageable p);

	@RestResource(path = "product")
	Page<PayService> findByProduct(@Param("product") String product, Pageable p);

	@RestResource(path = "subProduct")
	Page<PayService> findBySubProduct(@Param("subProduct") String subProduct, Pageable p);

	@RestResource(path = "last-record-branch")
	PayService findTopByBranchOrderByRecordSequenceDesc(@Param("branch") String branch);

	@RestResource(path = "last-record-seq")
	PayService findTopByRecordSequenceOrderByNumberOfLineItem1Desc(@Param("seq") String seq);

	@RestResource(path = "date-branch-acct")
	PayService findTopByDocumentDateAndBranchAndAccount(@Param("date") String date, @Param("branch") String branch, @Param("account") String account);

	@RestResource(path = "date-branch-acct-prod")
	PayService findTopByDocumentDateAndBranchAndAccountAndProductEndingWith(@Param("date") String date, @Param("branch") String branch, @Param("account") String account, @Param("product") String product);

	@RestResource(path = "date-branch-acct-ref2")
	PayService findTopByDocumentDateAndBranchAndAccountAndRef2EndingWith(@Param("date") String date, @Param("branch") String branch, @Param("account") String account, @Param("ref2") String ref2);

}