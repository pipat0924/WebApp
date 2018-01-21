package th.net.cat.epis.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import th.net.cat.epis.entity.Permission;

public interface PermissionRepository extends CrudRepository<Permission, Long> {

	
	 List<Permission> findById(Long id);
	 List<Permission> findAll();
	 
	 
	
	 @Query(
				value="SELECT  p.* FROM PERMISSION p WHERE p.ID IN("
			  +" SELECT  r.PERMISSION_ID "
			  +" FROM EPIS.PRINC_PERMISSION_MAPPING r WHERE r.PRINCIPAL_ID = (SELECT m.PRINCIPALID FROM MASOFFICER m WHERE m.USERNAME = ?1 ) "
			  +" ) ORDER BY REF_ID ASC  ", 
			 
			 nativeQuery=true
		)
	    List<Permission> findPermissionByUsername(String username);
	 
	 
	 @Query(
				value="SELECT  p.* FROM PERMISSION p WHERE p.ID IN("
			 +" SELECT  r.PERMISSION_ID"
			 +" FROM EPIS.PRINC_PERMISSION_MAPPING r WHERE r.PRINCIPAL_ID = ?1 "
			 +" ) ORDER BY ID ASC ", 
			 
			 nativeQuery=true
		)
	    List<Permission> findMenuByPrincipalIdOrderByIdAsc(int principalId);
	 
	 List<Permission> findByTypeOrderByIdAsc(String type);
	 
	 @Query(value="SELECT  p.* FROM PERMISSION p ORDER BY p.ID ASC ", nativeQuery=true)
	    List<Permission> findAllOrderByidAsc();
	 
	 @Query(value="SELECT p.* FROM PERMISSION p ORDER BY p.ORDERING ASC ", nativeQuery=true)
	    List<Permission> findAllOrderByOrderingAsc();
	 
	 
	 
	/* SELECT * FROM PERMISSION WHERE NAME LIKE '%%' AND "DESC" LIKE '%ยกเลิกชำระค่าบริการอื่นๆ%' AND "TYPE" = ''
			 
			 */
	 List<Permission> findByNameLikeIgnoreCaseAndDescLikeIgnoreCaseAndType(String name,String desc,String type);
	 List<Permission> findByNameLikeIgnoreCaseAndDescLikeIgnoreCase(String name,String desc);
	 
	 				//findByNameLikeIgnoreCaseOrDescLikeIgnoreCase

	 
	 
	 //@Query("SELECT l1 FROM Location l1 WHERE l1.node.id IN :refid")
	/* @Query(value="SELECT p.* FROM PERMISSION p WHERE p.\"TYPE\"= :menu AND p.REF_ID IN :refid ", nativeQuery=true)
	 List<Permission> findAllByRefId(@Param("menu")  String menu,@Param("refid") Set<Integer> refid);*/
}
