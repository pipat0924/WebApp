package th.net.cat.epis.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.epis.entity.MapGLServiceTpye;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 25/5/2560.
 */
@RepositoryRestResource(path = "mapGlServiceType")
public interface MapGLServiceTypeRepository extends PagingAndSortingRepository<MapGLServiceTpye,Long> {

    List<MapGLServiceTpye> findByServId(@Param("servId") Long ServId);

    @Query("select r from MapGLServiceTpye r")
    List<MapGLServiceTpye> loadServiceNameList();

    List<MapGLServiceTpye> findByRevenueTypeCodeOrProductCode(@Param("revenueTypeCode") String revenueTypeCode, @Param("productCode") String productCode);

    @Query("select r from MapGLServiceTpye r where serviceCode = ?1 ")
    List<MapGLServiceTpye> MapGLServiceTpyeByServiceType(String serviceType);
    
    @Query("select gl from MapGLServiceTpye gl where SERVICE_CODE = ?1 ")
    List<MapGLServiceTpye> findByServiceCode(String serviceCode);

    @Query("select distinct(gl) from MapGLServiceTpye gl where SOURCE = ?1 and gl.status = '1' ORDER BY SOURCE")
    List<MapGLServiceTpye> findBySource(String source);
    
    @RestResource(path = "revenueTypeCode")
    @Query("select r from MapGLServiceTpye r where r.revenueTypeCode = ?1 and r.status = '1'  ")
    List<MapGLServiceTpye> findServiceList(@Param("revenueTypeCode") String revenueTypeCode);

}
