package th.net.cat.epis.repo;

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

    List<EndDayClosing> findByEmpCodeAndClosingDate(@Param("empCode") String empCode, @Param("closingDate") java.sql.Date closingDate);

    List<EndDayClosing> findByEmpCodeAndReceiptDate(@Param("empCode") String empCode, @Param("receiptDate") java.sql.Date receiptDate);

    List<EndDayClosing> findByBranchCodeAndClosingDateAndBackDateStatus(@Param("branchCode") String branchCode, @Param("closingDate") Date closingDate, @Param("backDateStatus") String backDateStatus);

    List<EndDayClosing> findByBranchCodeAndDocStatusAndBackDateStatus(@Param("branchCode") String branchCode, @Param("docStatus") String docStatus, @Param("backDateStatus") String backDateStatus);

    EndDayClosing findById(@Param("id") Long closingId);

    EndDayClosing findByEmpCodeAndMacNoAndClosingDate(@Param("empCode") String empCode, @Param("macNo") String macNo, @Param("closingDate") Date closingDate);

    EndDayClosing findByEmpCodeAndMacNoAndBackDateStatusAndClosingDateAndReceiptDate(@Param("empCode") String empCode, @Param("macNo") String macNo, @Param("backDateStatus") String backDateStatus, @Param("closingDate") Date closingDate, @Param("receiptDate") Date receiptDate);

}
