package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.data.rest.core.annotation.RestResource;
import th.net.cat.epis.entity.Deduct;
import th.net.cat.epis.entity.Method;

@RepositoryRestResource(path = "methods")
public interface PayMethodRepository extends PagingAndSortingRepository<Method, Long> {

	@Query(value="SELECT CODE, NAME, SUM(AMOUNT) AS AMOUNT, COUNT(*) AS COUNT FROM TRSMETHOD GROUP BY CODE, NAME", nativeQuery=true)
	List<Object[]> findSumMethodByOfficerId();

	@RestResource(path = "paymentId")
	List<Method> findByPaymentId(@Param("paymentId") Long paymentId);

}