package as.riv.model;

import java.util.Date;

import org.json.JSONObject;

public class Payload {

	public Date timestamp;

	public JSONObject payload;

	public Payload(String payload) {
		this.payload = new JSONObject(payload);
		this.timestamp = new Date();
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public JSONObject getPayload() {
		return payload;
	}

	public void setPayload(JSONObject payload) {
		this.payload = payload;
	}

}
