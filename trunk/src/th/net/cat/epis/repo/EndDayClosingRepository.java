package th.net.cat.epis.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import th.net.cat.epis.entity.EndDayClosing;

import java.util.Date;
import java.util.List;

/**
 * Created by nastanda on 5/9/2017 AD.
 */
@RepositoryRestResource(path = "end-day-closing")
public interface EndDayClosingRepository extends PagingAndSortingRepository<EndDayClosing, Long> {

    @RestResource(path = "empCode")
    List<EndDayClosing> findByEmpCodeAndDocStatusOrderByUpdateDttm(@Param("empCode") String empCode, @Param("docStatus") String docStatus);

    @RestResource(path = "userName")
    List<EndDayClosing> findByUserNameAndDocStatusOrderByUpdateDttm(@Param("userName") String userName, @Param("docStatus") String docStatus);
    
    //List<EndDayClosing> findByEmpCodeAndClosingDate(@Param("empCode") String empCode, @Param("closingDate") java.sql.Date closingDate);
    
    List<EndDayClosing> findByUserNameAndClosingDate(@Param("userName") String userName, @Param("closingDate") java.sql.Date closingDate);

    //List<EndDayClosing> findByEmpCodeAndReceiptDate(@Param("empCode") String empCode, @Param("receiptDate") java.sql.Date receiptDate);
    List<EndDayClosing> findByUserNameAndReceiptDate(@Param("userName") String userName, @Param("receiptDate") java.sql.Date receiptDate);

    //List<EndDayClosing> findByBranchCodeAndClosingDateAndBackDateStatus(@Param("branchCode") String branchCode, @Param("closingDate") Date closingDate, @Param("backDateStatus") String backDateStatus);
    List<EndDayClosing> findByUserNameAndClosingDateAndBackDateStatusOrderByIdAsc(@Param("userName") String userName, @Param("closingDate") java.sql.Date closingDate, @Param("backdateStatus") String backdateStatus);

    List<EndDayClosing> findByBranchCodeAndClosingDateAndBackDateStatus(@Param("branchCode") String branchCode, @Param("closingDate") Date closingDate, @Param("backDateStatus") String backDateStatus);

    List<EndDayClosing> findByBranchCodeAndClosingDateAndBackDateStatusAndShopClosing_IdIsNull(@Param("branchCode") String branchCode, @Param("closingDate") Date closingDate, @Param("backDateStatus") String backDateStatus);

    //List<EndDayClosing> findByBranchCodeAndDocStatusAndBackDateStatus(@Param("branchCode") String branchCode, @Param("docStatus") String docStatus, @Param("backDateStatus") String backDateStatus);

    @Query("select dc from EndDayClosing dc where dc.branchCode =  ?1 and dc.docStatus = ?2 and dc.backDateStatus = ?3 and dc.shopClosing.id is null order by dc.id asc ")
    List<EndDayClosing> findByBranchCodeAndDocStatusAndBackDateStatusAndShopClosing_IdIsNull(@Param("branchCode") String branchCode, @Param("docStatus") String docStatus, @Param("backDateStatus") String backDateStatus);
  
    EndDayClosing findById(@Param("id") Long closingId);

    List<EndDayClosing> findByEmpCodeAndMacNoAndClosingDate(@Param("empCode") String empCode, @Param("macNo") String macNo, @Param("closingDate") Date closingDate);

    List<EndDayClosing> findByEmpCodeAndMacNoAndBackDateStatusAndClosingDateAndReceiptDate(@Param("empCode") String empCode, @Param("macNo") String macNo, @Param("backDateStatus") String backDateStatus, @Param("closingDate") Date closingDate, @Param("receiptDate") Date receiptDate);

    EndDayClosing findByEmpCodeAndMacNoAndClosingDateAndDocStatusAndShopNoAndUserNameAndBackDateStatus(@Param("empCode") String empCode, @Param("macNo") String macNo, @Param("closingDate") Date closingDate, @Param("docStatus") String docStatus, @Param("shopNo") String shopNo, @Param("userName") String userName, @Param("backDateStatus") String backDateStatus );

    EndDayClosing findByEmpCodeAndMacNoAndBackDateStatusAndClosingDateAndReceiptDateAndDocStatusAndShopNoAndUserName(@Param("empCode") String empCode, @Param("macNo") String macNo, @Param("backDateStatus") String backDateStatus, @Param("closingDate") Date closingDate, @Param("receiptDate") Date receiptDate, @Param("docStatus") String docStatus, @Param("shopNo") String shopNo, @Param("userName") String userName );
}
