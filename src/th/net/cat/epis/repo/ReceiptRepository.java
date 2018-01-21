package th.net.cat.epis.repo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.epis.entity.Receipt;

@RepositoryRestResource(path = "receipts")
public interface ReceiptRepository extends PagingAndSortingRepository<Receipt, Long> {

	
	
	@RestResource(path = "no")
	@Query("select i from Receipt i where i.no like %?1% order by i.no asc")
	Page<Receipt> findByNoStartingWith(@Param("no") String no, Pageable p);

	@Query("select i from Receipt i where i.no like %?1% and  i.updateUser in ?2 order by i.no desc")
	Page<Receipt> findByNoStartingWith(@Param("no") String no, @Param("updateUserList") List<String> updateUser, Pageable p);

	@RestResource(path = "no-payType")
	Page<Receipt> findByNoStartingWithAndPayment_TypeOrderByNoDesc(@Param("no") String no, @Param("payType") String payType, Pageable p);
	
	@RestResource(path = "cusNo")
	List<Receipt> findByAccountNoOrderByUpdateDttmDesc(@Param("cusNo") String cusNo);

	@RestResource(path = "custName")
	@Query("select r from Receipt r where r.name LIKE %?1% ")
	Page<Receipt> findByNameOrderByNoDesc(@Param("custName") String custName, Pageable p);

	@RestResource(path = "cusActSeg")
	@Query("select r from Receipt r where r.customer.acctCatLkp = ?1 or r.customer.catCustomerSegment = ?1 ")
	Page<Receipt> findByCustomer(@Param("cusActSeg") String cusActSeg, Pageable p);

	@RestResource(path = "cusNo-payType")
	Page<Receipt> findByAccountNoStartingWithAndPayment_TypeOrderByNoDesc(@Param("cusNo") String cusNo, @Param("payType") String payType, Pageable p);

	@RestResource(path = "cusNo-payType2")
	List<Receipt> findByAccountNoStartingWithAndPayment_TypeOrderByNoDesc(@Param("cusNo") String cusNo, @Param("payType") String payType);

	@RestResource(path = "no-payType2")
	List<Receipt> findByNoStartingWithAndPayment_TypeOrderByDocDttmDesc(@Param("no") String no, @Param("payType") String payType);

	@RestResource(path = "taxNo")
	Page<Receipt> findByTaxNoStartingWith(@Param("taxNo") String taxNo,Pageable p);
	
	@Query("select i from Receipt i where i.taxNo like %?1% and  i.updateUser in ?2 order by i.no desc")
	Page<Receipt> findByTaxNoStartingWith(@Param("taxNo") String taxNo, @Param("updateUserList") List<String> updateUser,  Pageable p);

	@RestResource(path = "payment")
	@Query("select r from Receipt r where r.payment.updateDttm between ?1 and ?2")
	Page<Receipt> findByPayment(@Param("from") Date fromDate, @Param("to") Date toDate, Pageable p);
	
	@Query("select r from Receipt r where r.payment.updateDttm between ?1 and ?2 and  r.updateUser in ?3 order by r.no desc")
	Page<Receipt> findByPaymentDate(@Param("from") Date fromDate, @Param("to") Date toDate,  @Param("updateUserList") List<String> updateUser, Pageable p);

//	@RestResource(path = "issueDt")
	@Query("select r from Receipt r inner join r.invoices inv where (inv.issueDt between ?1 and ?2) and r.updateUser in ?3 order by r.no desc")
	Page<Receipt> findByInvoices1(@Param("from") Date fromDate, @Param("to") Date toDate, @Param("updateUserList") List<String> updateUser,Pageable p);
	
	@RestResource(path = "issueDt")
	@Query("select r from Receipt r inner join r.invoices inv where inv.issueDt between ?1 and ?2")
	Page<Receipt> findByInvoices(@Param("from") Date fromDate, @Param("to") Date toDate, Pageable p);

	@RestResource(path = "invSubNo2")
	@Query("select r from Receipt r inner join r.invoices inv where inv.subNo like %?1% or r.accountSubNo like %?1% ")
	Page<Receipt> findByInvoices(@Param("invSubNo") String invSubNo, Pageable p);
	
	@Query("select r from Receipt r inner join r.invoices inv where inv.subNo like %?1% or r.accountSubNo like %?1%  and r.updateUser in ?2 order by r.no desc")
	Page<Receipt> findByInvoices(@Param("invSubNo") String invSubNo,@Param("updateUserList") List<String> updateUser, Pageable p);

	@RestResource(path = "custNo")
	Page<Receipt> findByCustomer_NoStartingWith(@Param("custNo") String custNo, Pageable p);

//	@RestResource(path = "invNo")
	@Query("select r from Receipt r inner join r.invoices inv where inv.no like ?1%  and  r.updateUser in ?2 order by r.no desc")
	Page<Receipt> findByInvoices_NoStartingWith(@Param("invNo") String invNo, @Param("updateUserList") List<String> updateUser, Pageable p);
	
	@RestResource(path = "invNo")
	Page<Receipt> findByInvoices_NoStartingWith(@Param("invNo") String invNo, Pageable p);
	
	@RestResource(path = "invSubNo")
	Page<Receipt> findByInvoices_SubNoStartingWith(@Param("invSubNo") String invSubNo, Pageable p);

//	@RestResource(path = "invNo-payType")
	Page<Receipt> findByInvoices_NoStartingWithAndPayment_TypeStartingWithOrderByNoDesc(@Param("invNo") String invNo, @Param("payType") String payType,  Pageable p);

	@Query("select r from Receipt r inner join r.invoices inv where (inv.no like ?1% or r.no like ?2% ) and r.payment.type like ?3% and r.updateUser in ?4 order by r.no desc")
	Page<Receipt> findByInvoiceReceipt(@Param("invNo") String invNo,@Param("no") String no, @Param("payType") String payType, @Param("updateUserList") List<String> updateUser,  Pageable p);
	
	@Query("select r from Receipt r inner join r.invoices inv where (inv.no like ?1% or r.no like ?2% ) and r.payment.type like ?3% and r.updateUser in ?4 and r.docDttm between ?5 and ?6 order by r.no desc")
	Page<Receipt> findByInvoiceReceipt(@Param("invNo") String invNo,@Param("no") String no, @Param("payType") String payType, @Param("updateUserList") List<String> updateUser, @Param("docDttm") Date date,@Param("docDttm") Date date2,  Pageable p);
	
	
	@Query("select r from Receipt r inner join r.invoices inv where (inv.no like ?1% or r.no like ?2% ) and r.payment.type like ?3% and r.updateUser in ?4 and r.docDttm < ?5 order by r.no desc")
	Page<Receipt> findByInvoiceReceiptBefore( String invNo, String no, String payType, List<String> updateUser, Date date,  Pageable p);
	
//	@RestResource(path = "userNameCode")
//	Page<Receipt> findBy(@Param("invNo") String invNo);

	@RestResource(path = "svcNo")
	Page<Receipt> findByServices_ServiceNoStartingWith(@Param("svcNo") String svcNo, Pageable p);

	@RestResource(path = "svcNo-payType")
	Page<Receipt> findByServices_ServiceNoStartingWithAndPayment_TypeOrderByNoDesc(@Param("svcNo") String svcNo, @Param("payType") String payType, Pageable p);

	@RestResource(path = "svcNo-payType2")
	List<Receipt> findByServices_ServiceNoStartingWithAndPayment_TypeOrderByDocDttmDesc(@Param("svcNo") String svcNo, @Param("payType") String payType);

	@RestResource(path = "mdn-payType")
	Page<Receipt> findByServices_MdnStartingWithAndPayment_TypeOrderByNoDesc(@Param("mdn") String svcNo, @Param("payType") String payType, Pageable p);

	
	@Query("select r from Receipt r where r.payment.officerId = ?1 and r.payment.updateDttm between ?2 and ?3 and r.type = ?4")
	List<Receipt> findByPayment(Long officerId, Date fromDate, Date toDate, String receiptType);

	@Query(value="SELECT COUNT(*) FROM CORRECEIPT WHERE ATTRIBUTES LIKE %?1%", nativeQuery=true)
	List<BigDecimal> findSumReceiptWithTaxInvoiceByOfficerIdAndAttributes(String attributes);

	@Query(value="SELECT COUNT(*) FROM CORRECEIPT WHERE ATTRIBUTES LIKE %?1%", nativeQuery=true)
	List<BigDecimal> findSumReceiptWithoutTaxInvoiceByOfficerIdAndAttributes(String attributes);
	
	@RestResource(path = "attributes")
	List<Receipt> findByAttributes(@Param("attributes") String attributes);
	
	List<Receipt> findByUpdateDttmAndUpdateUser(@Param("updateDttm") Date updateDttm, @Param("updateUserList") String updateUser);

	@RestResource(path = "cusAllCri")
	@Query("select r from Receipt r where (?2 is null or r.accountNo = ?2) and r.name LIKE %?1% and (?3 is null or r.taxNo = ?3) and (?4 is null or (r.customer.acctCatLkp = ?4 or r.customer.catCustomerSegment = ?4))  and  r.updateUser in ?5 order by r.no desc")
	Page<Receipt> findByReceiptOrderByNoDesc(@Param("custName") String custName, @Param("custNo") String custNo,@Param("taxNo") String taxNo, @Param("cusActSeg") String cusActSeg , @Param("updateUserList") List<String> updateUser, Pageable p);

	@Query("select r from Receipt r where r.type=?1 and r.branchArea = ?2 and (?3 is null or r.flgHeader = ?3) and r.docDttm >= ?4 and r.docDttm< ?5")
	List<Receipt> findByTypeAndBranchAreaAndFlgHeaderAndDocDttmOrderByNoDesc(String receiptType, String branchArea, String flgHeader, Date fromDate, Date toDate);

	@Query("select r from Receipt r where r.type=?1 and r.branchArea = ?2 and (?3 is null or r.flgHeader = ?3) and r.docDttm >= ?4 and r.docDttm< ?5 order by r.docDttm desc ")
	List<Receipt> findBackDateReceiptList(String receiptType, String branchArea, String flgHeader, java.sql.Date fromDate, java.sql.Date toDate);


	List<Receipt> findByNoOrName(@Param("no") String no, @Param("name") String name);

    List<Receipt> findByNoStartingWithAndPayment_TypeOrderByUpdateDttmDescIdAsc(@Param("no") String no, @Param("payType") String payType);

    List<Receipt> findByTaxNoAgentStartingWithAndPayment_TypeOrderByUpdateDttmDescIdAsc(@Param("taxNoAgent") String taxNoAgent, @Param("payType") String payType);

    List<Receipt> findByNoStartingWithAndPayment_TypeAndTaxNoAgentOrderByUpdateDttmDescIdAsc( @Param("no") String no, @Param("payType") String payType , @Param("taxNo") String taxNo);

    @Query("select r from Receipt r where (r.taxNoAgent in ?1) and r.payment.type = ?2 order by r.updateDttm desc, r.id asc ")
    List<Receipt> findByTaxNoAgentAndPayment_Type (List<String> taxNoAgent, String payType);

    @Query("select r from Receipt r where r.ref1 = ?1 and r.taxNoAgent = ?2 and r.payment.type = ?3 order by r.updateDttm desc, r.id asc ")
    List<Receipt> findByRef1AndTaxNoAgentAndPayment_TypeOrderByUpdateDttmDescIdAsc (String codeAgent, String taxNoAgent, String payType);

    @Query("select r from Receipt r where r.ref1 = ?1 and r.payment.type = ?2 order by r.updateDttm desc, r.id asc ")
    List<Receipt> findByRef1AndPayment_TypeOrderByUpdateDttmDescIdAsc (String codeAgent, String payType);

    List<Receipt> findByPayment_TypeOrderByUpdateDttmDescIdAsc (String payType);
    
    
    @Query("select r from Receipt r where r.no like %?1% and r.payment.type like ?2% and r.updateUser in ?3 and r.docDttm between ?4 and ?5 order by r.no asc")
	Page<Receipt> findByNoPenaltyExtend(@Param("no") String no,@Param("payType") String payType,@Param("updateUserList") List<String> updateUser, @Param("docDttm") Date date, @Param("docDttm") Date date2, Pageable p);

    @Query("select r from Receipt r where (r.no = ?3 or r.accountNo = ?1 or r.id in (select s.receiptId from Receipt r inner join r.services s where s.mdn = ?2 )and r.payment.type like ?4% and r.updateUser in ?5) and r.docDttm between ?6 and ?7 order by r.no desc")
	Page<Receipt> findByInvoiceMNP(@Param("oderID") String oderID,@Param("mdn") String mdn,@Param("no") String no, @Param("payType") String payType, @Param("updateUserList") List<String> updateUser, @Param("docDttm") Date date, @Param("docDttm") Date date2, Pageable p);
     
    @Query("select r from Receipt r inner join r.services s where ( r.no like ?1% or s.serviceNo like ?2%) and r.payment.type like ?3% and  r.updateUser in ?4 order by r.no desc")
	Page<Receipt> findByInvoiceTopup(@Param("serviceNo") String serviceNo,@Param("receiptNo") String receiptNo, @Param("payType") String payType, @Param("updateUserList") List<String> updateUser,  Pageable p);
    
    @Query("select r from Receipt r where ( r.accountNo like ?1% or r.no like ?2% ) and r.payment.type like ?3% and r.updateUser in ?4 and r.updateDttm between ?5 and ?6 order by r.no desc")
	Page<Receipt> findByInvoiceCancelOther(@Param("invNo") String invNo,@Param("receiptNo") String ReceiptNo, @Param("payType") String payType, @Param("updateUserList") List<String> updateUser, @Param("docDttm") Date date, @Param("docDttm") Date date2, Pageable p);
   
    @Query("select r from Receipt r inner join r.services s inner join r.invoices inv where ( inv.no like ?1% or r.accountNo like ?2% or r.no like ?3%) and r.payment.type like ?4% and r.updateUser in ?5 order by r.no desc")
	Page<Receipt> findByInvoiceTBOSS(@Param("invNo") String invNo,@Param("ReceiptNo") String ReceiptNo,@Param("ContractNo") String ContractNo, @Param("payType") String payType, @Param("updateUserList") List<String> updateUser,  Pageable p);
    
    @Query("select r from Receipt r  where ( r.no like ?1% ) and r.payment.type like ?2% and r.updateUser in ?3 and r.docDttm between ?4 and ?5 order by r.no desc")
	Page<Receipt> findByInvoiceAGENT(@Param("receiptNo") String receiptNo, @Param("payType") String payType, @Param("updateUserList") List<String> updateUser, @Param("docDttm") Date date,@Param("docDttm") Date date2, Pageable p);
 
    @Query("select r from Receipt r inner join r.invoices inv where (inv.no like ?1% and r.no like ?2% and inv.source in ?3) and r.payment.type in ?4 and  r.updateUser in ?5 and r.docDttm between ?6 and ?7 order by r.no desc")
    Page<Receipt> findByInvoiceCancelOTTBOSSByInvNoAndReceiptNo(@Param("invNo") String invNo,@Param("no") String no,@Param("source") List<String> source,@Param("payType") List<String> payType, @Param("updateUserList") List<String> updateUser, @Param("docDttm") Date date,@Param("docDttm") Date date2, Pageable p);

    @Query("select r from Receipt r inner join r.invoices inv where (inv.no like ?1% and inv.source in ?2) and r.payment.type in ?3 and  (r.updateUser in ?4) and r.docDttm between ?5 and ?6 order by r.no desc")
    Page<Receipt> findByInvoiceCancelOTTBOSSByInvNo(@Param("invNo") String invNo,@Param("source") List<String> source,@Param("payType") List<String> payType, @Param("updateUserList") List<String> updateUser, @Param("docDttm") Date date,@Param("docDttm") Date date2, Pageable p);

    @Query("select r from Receipt r inner join r.invoices inv where (r.no like ?1% and inv.source in ?2) and r.payment.type in ?3 and  r.updateUser in ?4 and r.docDttm between ?5 and ?6 order by r.no desc")
    Page<Receipt> findByInvoiceCancelOTTBOSSByReciptNo(@Param("no") String no,@Param("source") List<String> source,@Param("payType") List<String> payType, @Param("updateUserList") List<String> updateUser, @Param("docDttm") Date date,@Param("docDttm") Date date2, Pageable p);
      //Added by T.Sayan : Start : 20170908 , receipt for adjust payment 
   @RestResource(path = "invNo-payType")
   Page<Receipt> findByInvoices_NoStartingWithAndPayment_TypeOrderByNoDesc(@Param("invNo") String invNo, @Param("payType") String payType, Pageable p);
   
   @RestResource(path = "invNo-payTypes")
   @Query("select i from Receipt i inner join i.invoices inv where inv.no = ?1 and i.payment.type in ('SERVICE_CHARGE','OTBOSS','TBOSS','TOPUP','OTHER') order by i.no desc")
   Page<Receipt> findByInvoicesNoAndPaymentTypesOrderByNoDesc(@Param("invNo") String invNo, Pageable p);
   
   @RestResource(path = "no-payTypes")
   @Query("select i from Receipt i where i.no = ?1 and i.payment.type in ('SERVICE_CHARGE','OTBOSS','TBOSS','TOPUP','OTHER') order by i.no desc")
   Page<Receipt> findByNoAndPaymentTypesOrderByNoDesc(@Param("no") String no, Pageable p);
   //Added by T.Sayan : End
   
   @Query("select rc from Receipt rc inner join rc.services sv where (sv.serviceNo = ?1 or rc.no = ?2) and rc.payment.type = ?3  AND  sv.serviceCode IN ?4  order by rc.updateDttm desc")   
   List<Receipt> findByServiceNoORReciptNoAndServiceCode_Topup(@Param("serviceNo") String serviceNo,@Param("no") String no, @Param("payType") String payType,@Param("serviceCode")List<String> serviceCode,Pageable p);

   @Query("select rc from Receipt rc inner join rc.services sv where (sv.serviceNo = ?1 AND rc.no = ?2) and rc.payment.type = ?3  AND  sv.serviceCode IN ?4  order by rc.updateDttm desc")   
   List<Receipt> findByServiceNoANDReciptNoAndServiceCode_Topup(@Param("serviceNo") String serviceNo,@Param("no") String no, @Param("payType") String payType,@Param("serviceCode")List<String> serviceCode,Pageable p);

   @Query("select rc from Receipt rc inner join rc.services sv where rc.payment.type = ?1  AND  sv.serviceCode IN ?2  order by rc.updateDttm desc")   
   List<Receipt> findInvoicesByServiceCode_Topup(@Param("payType") String payType,@Param("serviceCode")List<String> serviceCode,Pageable p);
   
   @Query("select r from Receipt r inner join r.services s where ( s.serviceNo like ?1% or r.no like ?2%) and r.payment.type like ?3% and s.serviceCode IN ?4 and r.updateUser in ?5 and r.docDttm between ?6 and ?7 order by r.no desc")
   Page<Receipt> findByInvoiceOrServiceNoTopup(String serviceNo,String receiptNo,String payType,List<String> serviceCode,List<String> updateUser, Date date, Date date2, Pageable p);
   
   @Query("select r from Receipt r inner join r.services s where ( s.serviceNo like ?1% AND r.no like ?2%) and r.payment.type like ?3% and s.serviceCode IN ?4 and r.updateUser in ?5 and r.docDttm between ?6 and ?7 order by r.no desc")
   Page<Receipt> findByInvoiceAndServiceNoTopup(String serviceNo,String receiptNo,String payType,List<String> serviceCode,List<String> updateUser, Date date, Date date2, Pageable p);
  
   List<Receipt> findById(@Param("id") Long id);
   
   List<Receipt> findByAccountNoStartingWithAndPayment_TypeOrderByUpdateDttmDescDocDttmDesc(@Param("cusNo") String cusNo, @Param("payType") String payType);
   
   List<Receipt> findByNo(@Param("no") String no);
}