package th.net.cat.epis.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import th.net.cat.epis.entity.SapDebtEntity;

@RepositoryRestResource(path = "resapDebt")
public interface SapDebtRepository extends PagingAndSortingRepository<SapDebtEntity, Serializable>{
	
	@Query("select sap from SapDebtEntity sap  where sap.docHeaderTextInv =? and sap.recordStatus = 'A' order by sap.updatedDtm desc")
	List<SapDebtEntity> searchSapDebt( @Param("docHeaderTextInv") String docHeaderTextInv);
	
	@Query("select sap from SapDebtEntity sap  where sap.customerName like %?1% and sap.accountNo = NVL(?2,sap.accountNo) and sap.ref1SapRegionCode = NVL(?3,sap.ref1SapRegionCode) order by sap.updatedDtm desc")
	List<SapDebtEntity> searchSapDebtList(@Param("customerName") String customerName,@Param("accountNo") String accountNo,@Param("ref1SapRegionCode") String ref1SapRegionCode ,Pageable p);
											
}
