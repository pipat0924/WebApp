package th.net.cat.crm.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.core.config.Projection;

import th.net.cat.crm.entity.BillProfile;
import th.net.cat.crm.entity.BillService;

@RepositoryRestResource(path = "bill-svcs", excerptProjection = BillServiceInlineProfileProjection.class)
public interface BillServiceRepository extends PagingAndSortingRepository<BillService, String> {

	@RestResource(path = "id")
	Page<BillService> findByProfile_Id(@Param("id") String id, Pageable p);

	@RestResource(path = "no")
	Page<BillService> findByProfile_NoStartingWith(@Param("no") String no, Pageable p);

	@RestResource(path = "one")
	Page<BillService> findByPropLabelAndPropOneStartingWith(@Param("label") String label, @Param("one") String one, Pageable p);

	@RestResource(path = "two")
	Page<BillService> findByPropLabelAndPropTwoStartingWith(@Param("label") String label, @Param("two") String two, Pageable p);

	@RestResource(path = "both")
	Page<BillService> findByPropLabelAndPropOneStartingWithOrPropTwoStartingWith(@Param("label") String label, @Param("one") String one, @Param("two") String two, Pageable p);

}
@Projection(types = { BillService.class })
interface BillServiceInlineProfileProjection {
	String getId();
	String getPropOne();
	String getPropTwo();
	String getPropLabel();
	String getPropValue();
	BillProfile getProfile();
}