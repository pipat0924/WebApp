package th.net.cat.epis.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.epis.entity.Payment;

public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long> {

	@RestResource(path = "officerId")
	List<Payment> findByOfficerId(Long officerId);

	@RestResource(path = "officerIdAndupdateDttm")
	List<Payment> findByOfficerIdAndUpdateDttmBetween(Long officerId, Date updateDttm1, Date updateDttm2);

	@RestResource(path = "updateUser")
	List<Payment> findByUpdateUser(String updateUser);

}