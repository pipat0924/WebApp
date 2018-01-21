package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.epis.entity.Enum;

@RepositoryRestResource(path = "enums")
public interface EnumRepository extends PagingAndSortingRepository<Enum, Long> {

	List<Enum> findByCategory(@Param("category") String category);
	
	List<Enum> findByCategoryAndCode(@Param("category") String category, @Param("code") String code);

	List<Enum> findByCategoryAndCodeStartingWith(@Param("category") String category, @Param("code") String code);

	List<Enum> findByCategoryAndDescFullThStartingWith(@Param("category") String category, @Param("descFullTh") String descFullTh);

	List<Enum> findByCategoryIsOrCodeIsOrDescFullThStartingWith(@Param("category") String category, @Param("code") String code, @Param("descFullTh") String descFullTh);

	List<Enum> findByCategoryAndIsPositive(@Param("category") String category, @Param("isPositive") Integer isPositive);

	@RestResource(path = "category")
	Page<Enum> findByCategory(@Param("category") String category, Pageable p);

	@RestResource(path = "category-code")
	Page<Enum> findByCategoryAndCode(@Param("category") String category, @Param("code") String code, Pageable p);

	@RestResource(path = "category-mapcode1")
	Page<Enum> findByCategoryAndMapCode1(@Param("category") String category, @Param("mapcode1") String mapcode1, Pageable p);

	@RestResource(path = "category-mapcode2")
	Page<Enum> findByCategoryAndMapCode2(@Param("category") String category, @Param("mapcode2") String mapcode2, Pageable p);

	@RestResource(path = "category-mapcode3")
	Page<Enum> findByCategoryAndMapCode3(@Param("category") String category, @Param("mapcode3") String mapcode3, Pageable p);

	@RestResource(path = "category-mapcode4")
	Page<Enum> findByCategoryAndMapCode4(@Param("category") String category, @Param("mapcode4") String mapcode4, Pageable p);
	
	List<Enum> findByCategoryAndCodeContainingIgnoreCase(@Param("category") String category, @Param("code") String code);
	
	@Query("select o.code from Enum o where o.category = ?1 group by o.category, o.code order by o.code")
	List<String> findCategoryGroup(String category);
	
	List<Enum> findByCategoryAndCodeContainingIgnoreCaseAndMapCode1ContainingIgnoreCaseAndMapCode2ContainingIgnoreCase(@Param("category") String category, @Param("code") String code, @Param("mapCode1") String mapCode1, @Param("mapCode2") String mapCode2);
	
	@Query("select o.category from Enum o group by o.category order by o.category")
	List<String> findAllCategoryGroup();
	
	@Query("select o from Enum o where o.category = :category and o.code like %:code% and o.mapCode1 like %:mapCode1% and o.mapCode2 like %:mapCode2%  and o.mapCode3 like %:mapCode3% and o.mapCode4 like %:mapCode4% and o.descFullEn like %:descFullEn% and o.descFullTh like %:descFullTh% and o.descAbvrEn like %:descAbvrEn% and o.descAbvrTh like %:descAbvrTh% order by o.category")
//	@Query(nativeQuery=true, value="select o.* from ArcEnums o where o.category = :category and o.messagecode like %:code% and o.mapCode1 like %:mapCode1% and o.mapCode2 like %:mapCode2%  and o.mapCode3 like %:mapCode3% and o.mapCode4 like %:mapCode4% and o.descFullEn like %:descFullEn% and o.descFullTh like %:descFullTh% and o.descAbvrEn like %:descAbvrEn% and o.descAbvrTh like %:descAbvrTh% order by o.category")
	List<Enum> findBySearchCriteria(
			@Param("category") String category
			,@Param("code") String code
			,@Param("mapCode1") String mapCode1
			,@Param("mapCode2") String mapCode2
			,@Param("mapCode3") String mapCode3
			,@Param("mapCode4") String mapCode4
			,@Param("descFullEn") String descFullEn
			,@Param("descFullTh") String descFullTh
			,@Param("descAbvrEn") String descAbvrEn
			,@Param("descAbvrTh") String descAbvrTh
	);
	
	List<Enum> findByCategoryAndCodeContainingIgnoreCaseAndMapCode1ContainingIgnoreCaseAndMapCode2ContainingIgnoreCaseAndMapCode3ContainingIgnoreCaseAndMapCode4ContainingIgnoreCaseAndDescFullEnContainingIgnoreCaseAndDescFullThContainingIgnoreCaseAndDescAbvrEnContainingIgnoreCaseAndDescAbvrThContainingIgnoreCase
	(
			@Param("category") String category
			,@Param("code") String code
			,@Param("mapCode1") String mapCode1
			,@Param("mapCode2") String mapCode2
			,@Param("mapCode3") String mapCode3
			,@Param("mapCode4") String mapCode4
			,@Param("descFullEn") String descFullEn
			,@Param("descFullTh") String descFullTh
			,@Param("descAbvrEn") String descAbvrEn
			,@Param("descAbvrTh") String descAbvrTh
	);

	//by NSD 17-01-2017
	@Query("select o from Enum o where o.category = :category and o.descFullTh like %:descFullTh% ")
//	@Query(nativeQuery=true, value="select o.* from ArcEnums o where o.category = :category and o.messagecode like %:code% and o.mapCode1 like %:mapCode1% and o.mapCode2 like %:mapCode2%  and o.mapCode3 like %:mapCode3% and o.mapCode4 like %:mapCode4% and o.descFullEn like %:descFullEn% and o.descFullTh like %:descFullTh% and o.descAbvrEn like %:descAbvrEn% and o.descAbvrTh like %:descAbvrTh% order by o.category")
	List<Enum> findBySearchCriteria2(
			@Param("category") String category
			, @Param("descFullTh") String descFullTh
	);
    @Query("select o from Enum o where o.category = :category1 or o.category = :category2")
    List<Enum> findByBranchCentralAndBusinessPlace(@Param("category1") String category1, @Param("category2") String category2);

    List<Enum> findByCodeOrDescFullTh(@Param("code") String code, @Param("descFullTh") String descFullTh);

	@RestResource(path = "id")
	Page<Enum> findById(@Param("category") String category, @Param("id") Long id, Pageable p);

}