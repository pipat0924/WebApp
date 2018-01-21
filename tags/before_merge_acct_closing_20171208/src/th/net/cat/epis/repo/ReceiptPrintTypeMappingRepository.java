package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.ReceiptPrinttypeMapping;
import th.net.cat.epis.entity.ReceiptPrinttypeMappingPK;
@RepositoryRestResource(path = "ReceiptPrintTypeMapping")
public interface ReceiptPrintTypeMappingRepository extends PagingAndSortingRepository<ReceiptPrinttypeMapping, ReceiptPrinttypeMappingPK> {
	List<ReceiptPrinttypeMapping> findByReceiptid(@Param("receiptid") Long receiptid);
}

