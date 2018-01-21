package th.net.cat.epis.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import th.net.cat.epis.entity.Menu;
import th.net.cat.epis.entity.Permission;

public interface  MenuRepository extends CrudRepository<Menu, Long> {
	
	
	List<Menu> findAll();
	List<Menu> findByParrentId(Long parrentId);
	
	@Query(value="SELECT * FROM MENU WHERE ID IN :parrentId ORDER BY \"ORDERING\" ASC ", nativeQuery=true)
	List<Menu> findByIdList(@Param("parrentId") Set<Long> parrentId);
	
	@Transactional
	Long deleteByParrentId(Long parrentId);
	
	@Query(value="SELECT r.*  FROM EPIS.MENU r ORDER BY r.ID ASC ", nativeQuery=true )
	List<Menu> findAllOrderByIdAsc();

	
	@Query(value="SELECT p.* FROM MENU p ORDER BY p.ORDERING ASC", nativeQuery=true )
	List<Menu> findAllOrderByOrderingAsc();
	
	
	@Query(value=" SELECT m.* FROM MENU m WHERE m.ID IN ( "
		 +"	SELECT pe.REF_ID FROM PERMISSION pe WHERE pe.\"TYPE\" = ?1 AND  pe.ID IN( "
		 +"		SELECT p.PERMISSION_ID FROM PRINC_PERMISSION_MAPPING  p WHERE p.PRINCIPAL_ID = (SELECT m.PRINCIPALID FROM MASOFFICER m WHERE m.USERNAME = ?2 ) "
		 +"   ) "
		 +")ORDER BY m.ORDERING ASC" , nativeQuery=true )
	
	
	List<Menu> findAllByPrincipalUsername(String menuType,String username);
	
	@Query(value=" SELECT m.* FROM MENU m WHERE m.ID IN ( "
			 +"	SELECT pe.REF_ID FROM PERMISSION pe WHERE pe.\"TYPE\" = ?1 AND  pe.ID IN( "
			 +"		SELECT p.PERMISSION_ID FROM PRINC_PERMISSION_MAPPING  p WHERE p.PRINCIPAL_ID = ?2 "
			 +"   ) "
			 +")ORDER BY m.ORDERING ASC" , nativeQuery=true )
		
		
		List<Menu> findAllByPrincipalId(String menuType,int principalId);
	
	List<Menu> findByNameIgnoreCase(String name);
    
	//@Query(value="DELETE FROM  EPIS.PRINC_PERMISSION_MAPPING WHERE PRINCIPAL_ID IN :principalId ", nativeQuery=true)
	@Query(value="SELECT m.* FROM MENU m WHERE m.ID IN :menuId ", nativeQuery=true)
	List<Menu> findAllByIdIn(@Param("menuId") Set<Long> menuId);
	List<Menu> findById(Long menuId);
	
	//SELECT * FROM menu WHERE name LIKE '%%' AND "DESC" LIKE '%%' ORDER BY id DESC
	List<Menu> findByNameContainingIgnoreCaseAndDescContainingIgnoreCaseOrderByOrderingAsc(String name,String desc);
	
	
	/*SELECT *FROM MENU WHERE id = 172
			SELECT *FROM MENU WHERE parent_id = 172*/

}
