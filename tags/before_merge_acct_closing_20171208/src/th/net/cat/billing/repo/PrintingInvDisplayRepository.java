package th.net.cat.billing.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import th.net.cat.billing.entity.PrintingInvDisplay;

@RepositoryRestResource(path = "PrintingInvDisplay")
public interface PrintingInvDisplayRepository extends CrudRepository<PrintingInvDisplay, Long> {

}
