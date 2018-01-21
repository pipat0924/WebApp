package th.net.cat.epis.repo;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import th.net.cat.epis.entity.MasChangeRate;

@RepositoryRestResource(path = "xchng")
public interface ExchangeRateRepository extends PagingAndSortingRepository<MasChangeRate, Long> {

	MasChangeRate findById(Long id); 
	
	@RestResource(path = "dt-code")
	Page<MasChangeRate> findByDateUsedBetweenAndRateCode(@Param("dts") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateUsedStart, 
														 @Param("dte") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateUsedEnd, 
														 @Param("code") String rateCode, Pageable p);
	
//	@RestResource(path = "dt-code")
//	Page<MasChangeRate> findByDateUsedAndRateCode(@Param("dt") Date dateUsed, @Param("code") String rateCode, Pageable p);
	
}