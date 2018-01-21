package th.net.cat.epis.repo;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import th.net.cat.epis.entity.EpisShop;

import javax.persistence.TemporalType;

public interface EpisShopRepository extends PagingAndSortingRepository<EpisShop, Long> {

	@Query("select s from EpisShop s where s.branchArea = ?1 and s.shopOpenDttm between ?2 and ?3")
	EpisShop findByBranchAreaAndShopOpenDttm(String branchArea, Date fromDate, Date toDate);

}
