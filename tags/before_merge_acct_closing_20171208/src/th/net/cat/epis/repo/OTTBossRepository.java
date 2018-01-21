package th.net.cat.epis.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import th.net.cat.epis.entity.OTTBossEntity;

public interface OTTBossRepository extends PagingAndSortingRepository<OTTBossEntity , Integer> {
	
	@Modifying
	@Query("UPDATE OTTBossEntity u SET u.record_status = ?1 , u.update_by = ?2 , u.update_date = ?3 WHERE u.id = ?4" )
	public void update(String record_status,String updateBy , Timestamp updateDate, int id);
	

    @Query("select r from OTTBossEntity r where ( r.account_no like ?1% ) and ( ?2 is null or r.create_by in ?2 ) order by r.account_no desc")
	Page<OTTBossEntity> findByInvoiceOTTBoss(@Param("invNo") String invNo, @Param("updateUser") List<String> updateUser,  Pageable p);
	
	@Query("select o from OTTBossEntity o where (o.ar_ref = ?1 and o.account_no = ?2)and o.source = ?3 and o.record_status = 'A' " )
	Page<OTTBossEntity> findByARREFAndAccountNo(String ar_REF ,String accountNo, String sourec ,Pageable p);
}
