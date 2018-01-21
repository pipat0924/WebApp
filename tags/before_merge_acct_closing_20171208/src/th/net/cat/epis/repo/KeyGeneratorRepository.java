package th.net.cat.epis.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import th.net.cat.epis.entity.KeyGenerator;

/**
 * Created by nastanda on 12/1/2016 AD.
 */
public interface KeyGeneratorRepository extends CrudRepository<KeyGenerator, Long> {
    KeyGenerator findByDocCodeAndBranchCode(@Param("docCode") String docCode, @Param("branchCode") String branchCode);
}
