package th.net.cat.epis.repo;
        import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.epis.entity.Enum;
import th.net.cat.epis.entity.MapGLServiceTpye;
import th.net.cat.epis.entity.MasterData;

public interface MasterDataRepository extends CrudRepository<MasterData, Long>{
    @RestResource(path = "search")
    @Query("select o from MasterData o where o.groupKey like %?1% and o.key like %?2% and o.value like %?3% order by o.groupKey, o.key, o.value asc")
    List<MasterData> findByCriteria(@Param("groupKey") String groupKey, @Param("key") String key, @Param("value") String value);
    List<MasterData> findByGroupKeyContainingIgnoreCaseAndKeyContainingIgnoreCaseAndValueContainingIgnoreCase(@Param("groupKey") String groupKey, @Param("key") String key, @Param("value") String value);
    
    List<MasterData> findByGroupKeyContainingIgnoreCaseAndValueContainingIgnoreCaseOrderByGroupKeyAsc(String groupkey,String value);
	List<MasterData> findByKeyIgnoreCaseAndGroupKeyContainingIgnoreCaseAndValueContainingIgnoreCaseOrderByGroupKeyAsc(String key,String groupkey,String value);
	
	List<MasterData> findByGroupKey(@Param("groupKey") String groupKey);
	List<MasterData> findByGroupKeyAndProperty1(@Param("groupKey") String groupKey,@Param("property1") String property1);

	List<MasterData> findByKey(String key);
	@Query(value="select masterdata0_.* from MASTER_DATA masterdata0_ WHERE upper(masterdata0_.GROUP_KEY) IN :roleList ORDER BY masterdata0_.GROUP_KEY ASC", nativeQuery=true)
	//@Query(value="SELECT m.* FROM MENU m WHERE m.ID IN :menuId ", nativeQuery=true)
	List<MasterData> findKeyByGroupList(@Param("roleList") Set<String> roleList);
	
	
//	@Query(value="SELECT DISTINCT PROPERTY_1  FROM MASTER_DATA m WHERE Group_key = :groupKey order by PROPERTY_1 asc" , nativeQuery=true)
	
//	List<String> findKeyByGroupListBP(@Param("groupKey") String groupKey);
	List<MasterData> findDistinctByGroupKeyNotIn(Set<String> roleList);
	
	List<MasterData> findByGroupKeyIgnoreCase(String groupKey);
	List<MasterData> findByGroupKeyAndProperty1IgnoreCase(String groupKey,String property1);
	List<MasterData> findByGroupKeyContainingIgnoreCase(@Param("groupKey") String groupKey);
	List<MasterData> findById(@Param("id") Long id);
	List<MasterData> findByKeyInAndGroupKey(Set<String> key,String groupKey);

	@Query("SELECT m FROM MasterData m WHERE m.groupKey = ?1 and substr(m.key,1,5) = ?2 ")
	List<MasterData> findByGroupKeyAndKey(@Param("groupKey") String groupKey, @Param("key") String key);
	
	
}
