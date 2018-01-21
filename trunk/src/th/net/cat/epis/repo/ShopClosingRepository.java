package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import th.net.cat.epis.entity.Shop;
import th.net.cat.epis.entity.ShopClosing;

import java.sql.Date;

/**
 * Created by nastanda on 5/22/2017 AD.
 */
@RepositoryRestResource(path = "shop-closing")
public interface ShopClosingRepository extends PagingAndSortingRepository<ShopClosing, Long> {
    ShopClosing findByBranchCodeAndClosingDateAndBackdateStatus(@Param("branchCode") String branchCode, @Param("closingDate") Date closingDate, @Param("backdateStatus") String backdateStatus);
    ShopClosing findById(@Param("id") Long id);
}
