package as.riv.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import as.riv.model.APIKey;

public interface APIKeyRepository extends CrudRepository<APIKey, UUID> {

}
