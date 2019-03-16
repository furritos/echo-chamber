package as.riv.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import as.riv.model.APIKey;
import as.riv.service.APIKeyService;

@RestController
@RequestMapping(path = "/api")
public class WebhookController {

	@Autowired
	private APIKeyService service;

	@GetMapping(path = "/uuid", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody APIKey get() {
		return service.provision();
	}

	@GetMapping(path = "/webhook/{uuid}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody APIKey get(@PathVariable String uuid) {
		return service.retrieve(uuid);
	}

	@PostMapping(path = "/webhook/{uuid}", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void post(@RequestBody String message, @PathVariable String uuid) throws IOException {
		service.record(uuid, message);
	}

}
