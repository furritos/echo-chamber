package as.riv.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import as.riv.model.APIKey;

public interface APIKeyRepository extends CrudRepository<APIKey, UUID> {

	@Transactional
	List<APIKey> findByExpiresBefore(LocalDateTime expiryDate);

	@Transactional
	void deleteByExpiresBefore(LocalDateTime expiryDate);

}
