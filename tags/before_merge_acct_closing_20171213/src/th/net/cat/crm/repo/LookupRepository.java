package th.net.cat.crm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import th.net.cat.crm.entity.Lookup;

public interface LookupRepository  extends CrudRepository<Lookup, String>{

//	@Query("SELECT o FROM Lookup o ORDER BY o.listorder ASC")
	List<Lookup> findByLookupCategoryNameUpOrderByListorderAsc(@Param("categoryNameUp") String categoryNameUp);
}
