package th.net.cat.epis.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("commonRepository")
@Transactional
public class CommonRepository <T> {
	private static Logger logger = Logger.getLogger(CommonRepository.class);
	@Autowired
	@PersistenceContext(unitName = "episEntityManagerFactory")
	private EntityManager entityManager;
	
	public void save(T transientInstance)
            throws DataAccessException {
        entityManager.persist(transientInstance);
    }
	public void update(T transientInstance)
            throws DataAccessException {
        entityManager.merge(transientInstance);
    }
	public void delete(T transientInstance)
            throws DataAccessException {
        entityManager.remove(transientInstance);
    }
}
