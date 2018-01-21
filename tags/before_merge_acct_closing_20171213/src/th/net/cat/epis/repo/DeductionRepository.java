package th.net.cat.epis.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.data.rest.core.annotation.RestResource;
import th.net.cat.epis.entity.Deduct;
import th.net.cat.epis.entity.Receipt;

@RepositoryRestResource(path = "deducts")
public interface DeductionRepository extends PagingAndSortingRepository<Deduct, Long> {

	@Query(value="SELECT DEDUCTIONTYPE, SUM(AMOUNT) AS AMOUNT, COUNT(*) AS COUNT FROM TRSDEDUCTION GROUP BY DEDUCTIONTYPE", nativeQuery=true)
	List<Object[]> findSumDeductByOfficerId();

	@RestResource(path = "paymentId")
	List<Deduct> findByPaymentId(@Param("paymentId") Long paymentId);

}