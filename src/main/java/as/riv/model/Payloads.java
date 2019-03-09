package as.riv.model;

import java.util.ArrayList;
import java.util.List;

public class Payloads {

	private List<Payload> payloads;

	public List<Payload> getPayloadList() {
		if (payloads == null) {
			payloads = new ArrayList<Payload>();
		}
		return payloads;
	}

	public void setPayloadList(List<Payload> payloads) {
		this.payloads = payloads;
	}

}
