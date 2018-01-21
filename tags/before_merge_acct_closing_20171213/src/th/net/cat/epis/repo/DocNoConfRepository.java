package th.net.cat.epis.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.DocNoConf;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 23/11/2560.
 */
@RepositoryRestResource(path = "DocNoConf")
public interface DocNoConfRepository extends PagingAndSortingRepository<DocNoConf,Long> {

    List<DocNoConf> findById(@Param("id") Long ServId);

    @Query("select r from DocNoConf r")
    List<DocNoConf> loadDocNoConfList();

    @Query("select r from DocNoConf r where systemCode = ?1 order by systemCode asc")
    List<DocNoConf> findBySystemCode(String systemCode);
    
    @Query("select r from DocNoConf r where systemType = ?1 order by systemCode asc ")
    List<DocNoConf> findBySystemType(String systemType);

    @Query("select r from DocNoConf r where docType = ?1  ")
    List<DocNoConf> findByDocType(String docType);
    
    @Query("select  r from DocNoConf r where id = ?1 order by id asc ")
    List<DocNoConf> findById(long id);
    
}
