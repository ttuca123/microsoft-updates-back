package br.com.seplag.services.rest.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.seplag.services.MicrosoftUpdateService;
import br.com.seplag.services.rest.MicrosoftUpdateServiceRest;
import br.com.seplag.vo.MicrosoftSecurityDetailVO;
import br.com.seplag.vo.ValueMicrosoftDetailVO;

/**
 * 
 * @author Artur
 * @since 13/12/2022
 * 
 *        Serviços responsáveis por consumir API de microsoft security
 */
@Service
public class MicrosoftUpdateServiceRestImpl implements MicrosoftUpdateServiceRest {

	@Value("${spring.url.base}")
	private String urlHost;

	public RestTemplate restTemplate;

	@Autowired
	@Qualifier("microsoftUpdateServiceImpl")
	public MicrosoftUpdateService microsoftService;

	/**
	 * Obtém Common Vulnerability Reporting Framework pelo ID
	 * 
	 * @param id
	 */
	public void getCvrf(String id) {		

		String endpoint = new String(urlHost + "/cvrf/{id}");
		restTemplate = new RestTemplate();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);

		ValueMicrosoftDetailVO valor = restTemplate.getForObject(endpoint, ValueMicrosoftDetailVO.class, params);		

	}

	/**
	 * Obtém todas as atualizações de segurança microsoft
	 * 
	 * @author Artur Cavalcante
	 * @since 14/12/2022 14:55
	 */
	public void getUpdates() {

		restTemplate = new RestTemplate();
		String endpoint = new String(urlHost + "/updates/");

		MicrosoftSecurityDetailVO microsoftDetailVO = restTemplate.getForObject(endpoint,
				MicrosoftSecurityDetailVO.class);

		microsoftService.save(microsoftDetailVO.getValue(), microsoftDetailVO.getContext());

	}

	/**
	 * Obtém as atualizações de segurança pela key
	 * 
	 * @param key
	 */
	public void getUpdates(String key) {
		
		// TODO Obter Updates by key
	}

}
