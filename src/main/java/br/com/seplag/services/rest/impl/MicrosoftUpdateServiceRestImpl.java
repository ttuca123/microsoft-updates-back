package br.com.seplag.services.rest.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.seplag.services.MicrosoftUpdateService;
import br.com.seplag.services.rest.MicrosoftUpdateServiceRest;
import br.com.seplag.vo.MicrosoftSecurityDetailVO;

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

	private static final Logger log = LoggerFactory.getLogger(MicrosoftUpdateServiceRestImpl.class);

	@Autowired
	@Qualifier("microsoftUpdateServiceImpl")
	public MicrosoftUpdateService microsoftService;

	/**
	 * Obtém todas as atualizações de segurança microsoft
	 * 
	 * @author Artur Cavalcante
	 * @throws Exception
	 * @since 14/12/2022 14:55
	 */
	public void getUpdates() throws Exception {

		try {

			String endpoint = new String(urlHost + "/updates/");

			MicrosoftSecurityDetailVO microsoftDetailVO = getMicrosoftVO(endpoint);

			microsoftService.save(microsoftDetailVO.getValue(), microsoftDetailVO.getContext());
		} catch (HttpClientErrorException e) {

			log.error(e.getMessage());
			throw new Exception(e);
		}

	}

	@Override
	public MicrosoftSecurityDetailVO getMicrosoftVO(String endpoint) throws RestClientException {

		RestTemplate restTemplate = new RestTemplate();

		MicrosoftSecurityDetailVO microsoftDetailVO = restTemplate.getForObject(endpoint,
				MicrosoftSecurityDetailVO.class);

		return microsoftDetailVO;

	}

}
