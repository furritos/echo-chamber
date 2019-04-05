package as.riv.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.JsonNode;

@Entity
public class Payload {

	@Id
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private APIKey apikey;

	private Date timestamp;

	private JsonNode payload;

	public Payload(JsonNode payload) {
		this.payload = payload;
		this.timestamp = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public APIKey getApikey() {
		return apikey;
	}

	public void setApikey(APIKey apikey) {
		this.apikey = apikey;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public JsonNode getPayload() {
		return payload;
	}

	public void setPayload(JsonNode payload) {
		this.payload = payload;
	}

}
