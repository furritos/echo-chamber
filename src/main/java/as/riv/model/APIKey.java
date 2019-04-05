package as.riv.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class APIKey {

	@Id
	private UUID apikey = UUID.randomUUID();

	private LocalDateTime expires;

	private String message;

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

}
