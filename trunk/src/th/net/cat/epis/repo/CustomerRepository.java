package th.net.cat.epis.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.epis.entity.Customer;

@RepositoryRestResource(path = "customers")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

	@RestResource(path = "no")
	List<Customer> findByNo(@Param("no") String no);

	@RestResource(path = "payment")
	@Query("select r from Customer r where r.payment.id = ?1 ")
	List<Customer> findPayment(@Param("id") Long id);

}