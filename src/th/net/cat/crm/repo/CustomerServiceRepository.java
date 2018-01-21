package th.net.cat.crm.repo;

        import java.util.List;

        import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

        import th.net.cat.crm.entity.CustomerService;

//@RepositoryRestResource(path = "cust-service")
public interface CustomerServiceRepository  extends PagingAndSortingRepository<CustomerService, String>{

    @Query("select o from CustomerService o where (o.billingGroup in ?1) and (o.propertyOne like ?2 or o.propertyTwo like ?2) ")//and o.catBillAcctNo is not null ")
    List<CustomerService> findByBillingGroupAndPropertyOneAndPropertyTwo(List<String> billingGroups, String serviceNo);

    @Query("select o from CustomerService o where o.billingGroup in ?1 and o.propertyOne like ?2  ")//and o.catBillAcctNo is not null")
    List<CustomerService> findByBillingGroupAndPropertyOne(List<String> billingGroups, String serviceNo);

    @Query("select o from CustomerService o where o.billingGroup in ?1 and o.propertyTwo like ?2  ")//and o.catBillAcctNo is not null")
    List<CustomerService> findByBillingGroupAndPropertyTwo(List<String> billingGroups, String serviceNo);

    @Query(value ="select o.* from CRMDATA.V_CATCRM_SERVICE o where o.PROPERTY_ONE like :serviceNo or o.PROPERTY_TWO like :serviceNo ",nativeQuery = true)//and o.catBillAcctNo is not null")
    List<CustomerService> findByPropertyOneOrPropertyTwo(@Param("serviceNo") String serviceNo);

    @Query("select o from CustomerService o where o.propertyOne like ?1  ")//and o.catBillAcctNo is not null")
    List<CustomerService> findByPropertyOne(String serviceNo);

    @Query("select o from CustomerService o where o.propertyTwo like ?1  ")//and o.catBillAcctNo is not null")
    List<CustomerService> findByPropertyTwo(String serviceNo);
}
