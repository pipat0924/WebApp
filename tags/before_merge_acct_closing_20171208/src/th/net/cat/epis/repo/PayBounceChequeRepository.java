package th.net.cat.epis.repo;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.BounceCheque;

@RepositoryRestResource(path = "payBounceCheque")
public interface PayBounceChequeRepository extends PagingAndSortingRepository<BounceCheque, Long>{

	@Query("select bch from BounceCheque bch  where (bch.arAccountCode = ?1 or bch.docHead = ?2 ) and bch.recordStatus = 'A' order by bch.updateD desc")
	List<BounceCheque> searchPaySAP(@Param("arCode") String arCode, @Param("docHead") String docHead);
	
	@Query("select bch from BounceCheque bch  where (bch.arAccountCode = ?1 or bch.arName = ?2 or bch.regionKey1 = ?3 ) order by bch.arAccountCode desc")
	List<BounceCheque> searchPayAdvTab(@Param("accountNo") String accountNo, @Param("cusName") String cusName, @Param("avdRegionKey1") String avdRegionKey1);
	
//	BounceCheque findSubProduct(@Param("docHead") String docHead);
}
