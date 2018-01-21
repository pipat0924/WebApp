package th.net.cat.epis.repo;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import th.net.cat.epis.entity.AmountAdjustmentDetail;

@RepositoryRestResource(path = "amount-adjustment-detail")
public interface AmountAdjustmentDetailRepository extends PagingAndSortingRepository<AmountAdjustmentDetail, Long>{
	AmountAdjustmentDetail findById(@Param("id") Long id);
	List<AmountAdjustmentDetail> findByAdjustAmtId(@Param("adjustAmtId") Long adjustAmtId);
}
