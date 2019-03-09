package as.riv.model;

import java.util.Date;

import com.fasterxml.jackson.databind.JsonNode;

public class Payload {

	public Date timestamp;

	public JsonNode payload;

	public Payload(JsonNode payload) {
		this.payload = payload;
		this.timestamp = new Date();
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
