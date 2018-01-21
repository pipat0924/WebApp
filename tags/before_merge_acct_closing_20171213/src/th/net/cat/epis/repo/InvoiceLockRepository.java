package th.net.cat.epis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.InvoiceLock;
@RepositoryRestResource(path = "invoiceLock")
public interface InvoiceLockRepository  extends JpaRepository<InvoiceLock, String> {

}
