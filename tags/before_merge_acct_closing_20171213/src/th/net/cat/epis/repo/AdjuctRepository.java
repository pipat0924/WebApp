package th.net.cat.epis.repo;




import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.Receipt;

@RepositoryRestResource(path = "adjuctRepository")
public interface AdjuctRepository extends PagingAndSortingRepository<Receipt, Long> {

	@Query("select r from Receipt r  inner join r.invoices inv where (r.no = ?2 or inv.no like ?1% ) order by r.no desc")
	List<Receipt> findByinvoiceandepCode(String inv, String epCode);
}
