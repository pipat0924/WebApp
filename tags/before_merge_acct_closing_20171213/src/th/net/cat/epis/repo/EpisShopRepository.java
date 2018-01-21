package th.net.cat.epis.repo;

import java.util.Date;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import th.net.cat.epis.entity.EpisShop;

public interface EpisShopRepository extends PagingAndSortingRepository<EpisShop, Long> {
	EpisShop findByBranchAreaAndShopOpenDttm(@Param("branchArea") String branchArea, @Param("shopOpenDttm") Date shopOpenDttm);
}
