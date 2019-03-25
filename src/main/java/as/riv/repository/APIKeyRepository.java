package as.riv.repository;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import as.riv.model.APIKey;

public interface APIKeyRepository extends CrudRepository<APIKey, UUID> {

	@Transactional
	void deleteByExpiresBefore(Date expiryDate);

}
