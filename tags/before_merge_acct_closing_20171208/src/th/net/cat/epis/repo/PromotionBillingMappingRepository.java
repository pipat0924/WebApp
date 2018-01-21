package th.net.cat.epis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.PromotionReceiptMappingEntity;


@RepositoryRestResource(path = "promotion-billing-mapping")
public interface PromotionBillingMappingRepository extends JpaRepository<PromotionReceiptMappingEntity, Long> {
	  @Query("select p from PromotionReceiptMappingEntity p where p.receiptid = :receiptid ")
	  PromotionReceiptMappingEntity findPromotionReceiptMappingByReceiptid(@Param("receiptid") Long receiptid);

}
