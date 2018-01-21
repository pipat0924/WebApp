package th.net.cat.epis.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import th.net.cat.epis.entity.Attachment;

public interface AttachmentRepository extends PagingAndSortingRepository<Attachment, Long>{

}
