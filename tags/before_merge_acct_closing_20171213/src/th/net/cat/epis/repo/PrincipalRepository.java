package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import th.net.cat.epis.entity.Principal;

@RepositoryRestResource(path = "Principal")
public interface PrincipalRepository extends PagingAndSortingRepository<Principal, Long> {
	public List<Principal> findAllByOrderByIdAsc(); // add by kao 25600707 1019

	
	public List<Principal> findByNameLikeIgnoreCaseAndDescLikeIgnoreCase(String name,String desc);// add by kao 25600707 1019
	
	public List<Principal> findByName(String name);
	public List<Principal> findByNameAndDesc(String name,String desc);
	public List<Principal> findByNameIgnoreCase(String name);
	public List<Principal> findById(Long id);
}