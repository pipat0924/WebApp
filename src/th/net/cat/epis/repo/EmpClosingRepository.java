package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import th.net.cat.epis.entity.EmpClosing;
import th.net.cat.epis.entity.EndDayClosing;

/**
 * Created by nastanda on 5/27/2017 AD.
 */
@RepositoryRestResource(path = "emp-closing")
public interface EmpClosingRepository extends PagingAndSortingRepository<EmpClosing, Long> {
    EmpClosing findById(@Param("id") Long empClosingId);
}
