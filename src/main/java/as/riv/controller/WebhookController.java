package as.riv.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class WebhookController {

	private JSONArray payloads = new JSONArray();
	private int MAX_PAYLOADS = 10;

	@PostMapping(path = "/webhook", consumes = "application/json")
	public void postWebHook(@RequestBody String payload) {
		JSONObject json = new JSONObject(payload);
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

}
