package th.net.cat.crm.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.core.config.Projection;

import th.net.cat.crm.entity.BillProfile;
import th.net.cat.crm.entity.CustomerProfile;

@RepositoryRestResource(path = "bill-profiles", excerptProjection = BillProfileProjection.class)
public interface BillProfileRepository extends PagingAndSortingRepository<BillProfile, String>, JpaSpecificationExecutor<BillProfile> {

	@RestResource(path = "id")
	Page<BillProfile> findById(@Param("id") String id, Pageable p);

	@RestResource(path = "no")
	BillProfile findByNo(@Param("no") String no);
	


	//by NSD 25-01-2017
	@RestResource(path = "customer")
	BillProfile findByCustomer(@Param("customer") CustomerProfile customer);

	@RestResource(path = "no-list")
	Page<BillProfile> findByNoStartingWith(@Param("no") String no, Pageable p);

	@RestResource(path = "tax")
	Page<BillProfile> findByTaxRegisterNoStartingWith(@Param("tax") String tax, Pageable p);

	@RestResource(path = "name")
	@Query("select b from BillProfile b where b.billFirstName like %:firstName% or b.billLastName like %:lastName%")
	Page<BillProfile> findByFirstNameOrLastName(@Param("firstName") String firstName, @Param("lastName") String lastName, Pageable p);

	@RestResource(path = "org")
	Page<BillProfile> findByBillCompNameStartingWith(@Param("org") String org, Pageable p);

	@RestResource(path = "custId")
	Page<BillProfile> findByCustomer_Id(@Param("custId") String custId, Pageable p);

	@RestResource(path = "custNo")
	Page<BillProfile> findByCustomer_NoStartingWith(@Param("custNo") String custNo, Pageable p);

	@RestResource(path = "cris")
	@Query("select b from BillProfile b where b.no like ?1% or b.taxRegisterNo like ?2% or b.billFirstName like ?3% or b.billLastName like ?4%")
	Page<BillProfile> findByCris(@Param("no") String no, @Param("tax") String tax, @Param("firstName") String firstName, @Param("lastName") String lastName, Pageable p);

}
@Projection(types = { BillProfile.class })
interface BillProfileProjection {
	String getId();
	String getNo();
	String getTaxRegisterNo();
	String getBranchId();
	String getBillFirstName();
	String getBillLastName();
	String getBillCompName();
	String getBillGroup();
	String getBillGroupFull();
	String getCollectionUnit();
	String getCustomerAccountName();
	String getType();
	String getVatType();
	String getCurrency();
	CustomerProfile getCustomer();
}