package as.riv.task;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import as.riv.repository.APIKeyRepository;

@Component
public class ScheduledTask {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

	@Autowired
	private APIKeyRepository repository;

	@Scheduled(fixedRateString = "${fixedRate.in.milliseconds}", initialDelay = 15000)
	public void removeExpiredAPIKeys() {
		log.debug("Count before delete call: " + repository.count());
		repository.deleteByExpiresBefore(LocalDateTime.now());
		log.debug("Count after delete call: " + repository.count());
	}

}