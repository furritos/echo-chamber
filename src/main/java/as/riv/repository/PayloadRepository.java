package as.riv.repository;

import org.springframework.stereotype.Repository;

import as.riv.model.Payload;
import as.riv.model.Payloads;

@Repository
public class PayloadRepository {

	private int MAX_PAYLOADS = 15;

	private static Payloads list = new Payloads();

	public Payloads getAllPayloads() {
		return list;
	}

	public void addPayload(Payload payload) {
		if (list.getPayloadList().size() < MAX_PAYLOADS) {
			list.getPayloadList().add(payload);
		} else {
			synchronized (list.getPayloadList()) {
				list.getPayloadList().remove(0);
				list.getPayloadList().add(payload);
			}
		}

	}

}
