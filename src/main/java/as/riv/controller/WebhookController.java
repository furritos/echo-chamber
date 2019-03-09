package as.riv.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import as.riv.model.Payload;
import as.riv.repository.PayloadRepository;

@RestController
@RequestMapping(path = "/api")
public class WebhookController {

	@Autowired
	private PayloadRepository repository;

	@PostMapping(path = "/webhook", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void newPostWebHook(@RequestBody String message) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(message);
		Payload payload = new Payload(node);
		repository.addPayload(payload);
	}

	@GetMapping(path = "/webhook", produces = "application/json")
	public @ResponseBody List<Payload> newGetWebHook() {
		return repository.getAllPayloads().getPayloadList();
	}

}
