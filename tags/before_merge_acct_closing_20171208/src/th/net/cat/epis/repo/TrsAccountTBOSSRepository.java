package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.epis.entity.TrsAccountTBOSS;

@RepositoryRestResource(path = "tboss-transaction")
public interface TrsAccountTBOSSRepository extends PagingAndSortingRepository<TrsAccountTBOSS, Long> {

	@RestResource(path = "invId")
	List<TrsAccountTBOSS> findByAccountTbossId(@Param("id") Long id);
	
	List<TrsAccountTBOSS> findByAccountTbossIdAndPaymentId(@Param("id") Long accountTbossId, @Param("payid") Long paymentId);
}