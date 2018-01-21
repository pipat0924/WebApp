package th.net.cat.epis.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import th.net.cat.epis.entity.Document;

public interface DocumentRepository extends CrudRepository<Document, Long> {

	Document findByTypeAndBranchAreaAndDateAndHeader(@Param("type") String type, @Param("branchArea") String branchArea, @Param("date") String date, @Param("header") String header);

}