package th.net.cat.epis.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import th.net.cat.epis.entity.PrincPermissionMapping;

public interface PrincpermissionmappingRepository extends CrudRepository<PrincPermissionMapping, Long> {

	
	
	
	
	 
	@Query(
			value="SELECT r.* FROM EPIS.PRINC_PERMISSION_MAPPING r WHERE r.PRINCIPAL_ID = (SELECT m.PRINCIPALID FROM MASOFFICER m WHERE m.USERNAME = ?1 ) ORDER BY r.PERMISSION_ID ASC", 
			nativeQuery=true
	)
    List<PrincPermissionMapping> findRolenameByUsernameOrderByPermissionIdAsc(String username);
			
	List<PrincPermissionMapping> findByPrincipalId(Long principalId);
	
	List<PrincPermissionMapping> findByPrincipalIdOrderByPermissionIdAsc(Long principalId);
	
	
	
	/*@Transactional

	@Query(value="DELETE FROM  EPIS.PRINC_PERMISSION_MAPPING WHERE PRINCIPAL_ID IN :principalId ", nativeQuery=true)
	Long deleateByPrincipalIdList(@Param("principalId") Set<Long> principalId);*/
	 
	 
	 @Transactional
	 Long deleteByPrincipalId(Long principalId);
}
