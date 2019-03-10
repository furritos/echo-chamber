package as.riv.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public class APIKey {

	@Id
	private UUID apikey;
	private LocalDateTime expires;
	private String message;
	private List<Payload> payloads = new ArrayList<Payload>();

	public UUID getApikey() {
		return apikey;
	}

	public void setApikey(UUID apikey) {
		this.apikey = apikey;
	}

	public LocalDateTime getExpires() {
		return expires;
	}

	public void setExpires(LocalDateTime expires) {
		this.expires = expires;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Payload> getPayloads() {
		if (payloads == null) {
			payloads = new ArrayList<Payload>();
		}
		return payloads;
	}

	public void setPayloads(List<Payload> payloads) {
		this.payloads = payloads;
	}

}
