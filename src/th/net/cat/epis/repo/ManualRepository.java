package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import th.net.cat.epis.entity.ManualEntity;

@RepositoryRestResource(path = "manualrepo")
public interface ManualRepository extends PagingAndSortingRepository<ManualEntity , Long> {
	
	@Query("select i from ManualEntity i where (i.invoiceNo like %?1% or  i.receiptNoManual like %?2% )and i.clearing IN ?3 and i.clearingSap IN ?4 and i.recordStatus = 'A' " )
	List<ManualEntity> findByInvoiceNoOrReceipt(@Param("invNo") String invNo, @Param("receipt") String receipt ,@Param("status") List<String> status,@Param("statusSAP")List<String> statusSAP );

	@Query("select i from ManualEntity i where (i.invoiceNo like %?1% And  i.receiptNoManual like %?2% )and i.clearing IN ?3 and i.clearingSap IN ?4 and i.recordStatus = 'A' " )
	List<ManualEntity> findByInvoiceNoAndReceipt(@Param("invNo") String invNo, @Param("receipt") String receipt ,@Param("status") List<String> status,@Param("statusSAP")List<String> statusSAP );

	@Query("select i from ManualEntity i where  i.clearing IN ?1 and i.clearingSap IN ?2 and i.recordStatus = 'A' " )
	List<ManualEntity> findfindByRecordStatusAndClearing(@Param("status") List<String> status,@Param("statusSAP")List<String> statusSAP );

	
	@Query("select i from ManualEntity i where i.receiptNoManual like %?1% and i.clearing = 'N'")
	List<ManualEntity> findByReceiptNonClearing(@Param("receipt") String receipt);
	
	@Query("select i from ManualEntity i where i.receiptNoManual = ?1 ")
	List<ManualEntity> findByReceipt(@Param("receipt") String receipt);

	
	@Query("select i from ManualEntity i where i.clearing = 'N' and i.source = ?1 ")
	List<ManualEntity> findAllManualBySource(@Param("source") String source);

	
	List<ManualEntity> findByRecordStatus(String status);
	ManualEntity findByManualId(Long manualId);
	ManualEntity findByPaymenId(Long paymentId);
	List<ManualEntity> findByInvoiceNoOrReceiptNoManualAndRecordStatus(String invNo,String receipt,String statusRecord);

}
