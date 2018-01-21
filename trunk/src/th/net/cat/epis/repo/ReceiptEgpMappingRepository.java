package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.epis.entity.ReceiptEgpMappingEntity;

/**
 * Created by imake on 08/02/2017.
 */
@RepositoryRestResource(path = "EgpMaps")
public interface ReceiptEgpMappingRepository extends JpaRepository<ReceiptEgpMappingEntity, Long> {
	@RestResource(path = "receiptNo")
	List<ReceiptEgpMappingEntity> findByReceiptNo(@Param("receiptNo") String receiptNo);

	@RestResource(path = "receiptId")
	List<ReceiptEgpMappingEntity> findByReceiptId(@Param("receiptId") Long receiptId);
}
