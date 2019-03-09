package as.riv.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import as.riv.model.Payload;
import as.riv.model.Payloads;
import as.riv.repository.PayloadRepository;

@RestController
@RequestMapping(path = "/api")
public class WebhookController {

	private JSONArray payloads = new JSONArray();
	private int MAX_PAYLOADS = 10;

	@Autowired
	private PayloadRepository repository;

	@PostMapping(path = "/webhook", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void postWebHook(@RequestBody String payload) {
		Payload pl = new Payload(payload);
		pl.setPayload(new JSONObject(payload));
		JSONObject json = new JSONObject(pl);
		if (payloads.length() < MAX_PAYLOADS) {
			payloads.put(json);
		} else {
			synchronized (payloads) {
				payloads.remove(0);
				payloads.put(json);
			}
		}
	}

	@GetMapping(path = "/webhook", produces = "application/json")
	public @ResponseBody String getWebHook() {
		return payloads.toString(4);
	}

	@PostMapping(path = "/webhook1", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void newPostWebHook(@RequestBody String message) {
		Payload payload = new Payload(message);
		repository.addPayload(payload);
	}

	@GetMapping(path = "/webhook1", produces = "application/json")
	public @ResponseBody Payloads newGetWebHook() {
		return repository.getAllPayloads();
	}

}
