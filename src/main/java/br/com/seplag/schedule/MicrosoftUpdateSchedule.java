package br.com.seplag.schedule;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.seplag.services.rest.MicrosoftUpdateServiceRest;

@Component
public class MicrosoftUpdateSchedule {

	@Autowired
	public MicrosoftUpdateServiceRest microsoftService;

	private static final Logger log = LoggerFactory.getLogger(MicrosoftUpdateSchedule.class);

	@Scheduled(fixedRate = 300000)
	public void verificarPagamentosExpirados() {

		log.info("time: " + new Date());

		microsoftService.getUpdates();
	}

}
