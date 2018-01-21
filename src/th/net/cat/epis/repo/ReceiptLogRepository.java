package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.ReceiptLog;

@RepositoryRestResource(path = "receiptLogs")
public interface ReceiptLogRepository extends PagingAndSortingRepository<ReceiptLog, Long> {


}