package as.riv.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import as.riv.model.APIKey;
import as.riv.model.Payload;
import as.riv.repository.APIKeyRepository;

@Service
public class APIKeyService {

	@Autowired
	private APIKeyRepository repository;

	private final static int MAX_HOURS = 24;

	public APIKey register() {
		APIKey apikey = new APIKey();
		submit(apikey);
		return apikey;
	}

	public void populate(String uuid, String message) {
		Optional<APIKey> instance = repository.findById(UUID.fromString(uuid));
		APIKey apikey = instance.get();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(message);
			Payload payload = new Payload(node);
			apikey.getPayloads().add(payload);
		} catch (IOException e) {
			System.out.println("Something went wrong in populating " + uuid);
		}
	}

	public APIKey get(String uuid) {
		Optional<APIKey> instance = repository.findById(UUID.fromString(uuid));
		APIKey apikey = instance.get();
		return apikey;
	}

	private void submit(APIKey apikey) {
		populate(apikey);
		setStatusMessage(apikey);
		repository.save(apikey);
	}

	private void populate(APIKey apikey) {
		setUUID(apikey);
		setExpirationDate(apikey);
	}

	private void setUUID(APIKey apikey) {
		apikey.setApikey(UUID.randomUUID());
	}

	private void setExpirationDate(APIKey apikey) {
		apikey.setExpires(LocalDateTime.now().plusHours(MAX_HOURS));
	}

	private void setStatusMessage(APIKey apikey) {
		if (apikey.getExpires() != null && apikey.getApikey() != null) {
			apikey.setMessage("SUCCESS | OK");
		} else {
			apikey.setMessage("FAILURE | Try again later");
		}
	}

}
