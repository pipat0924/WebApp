package th.net.cat.epis.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import th.net.cat.epis.entity.MasAgent;

import java.util.List;

/**
 * Created by puthy on 5/28/17.
 */
@RepositoryRestResource(path = "agent")
public interface AgentRepository extends PagingAndSortingRepository<MasAgent, Long>{

    List<MasAgent> findById(@Param("id") Long id);

    List<MasAgent> findByCode(@Param("code") String code);

    List<MasAgent> findByCodeStartingWithIgnoreCase(@Param("code") String code);

    List<MasAgent> findByNameStartingWithIgnoreCase(@Param("name") String name);

    List<MasAgent> findByIsPositive(@Param("isPositive") Long isPositive);

    @Query("select o from MasAgent o where o.code like :code% and o.name like %:name% order by o.code asc")
    //@Query("select o.* from MAS_AGENT o where UPPER(o.code) like UPPER(:code%) and UPPER(o.AGENT_NAME) like UPPER(%:name%) order by o.code ASC")
    List<MasAgent> findAgentByCodeOrName(@Param("code") String code, @Param("name") String name);

    @Query("SELECT ma FROM MasAgent ma ORDER BY ma.id ASC")
    List<MasAgent> findAllOrderByIdAsc();

    @Query("SELECT ma FROM MasAgent ma ORDER BY ma.name ASC")
    List<MasAgent> findAllOrderByNameAsc();
    
    List<MasAgent> findByTaxId(@Param("taxId") String taxId);

}
