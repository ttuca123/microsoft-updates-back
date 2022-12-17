package br.com.seplag.services.rest;

import br.com.seplag.vo.MicrosoftSecurityDetailVO;

/**
 * Classe responsável por Obter as informações da API da microsoft
 * @author Artur
 *
 */
public interface MicrosoftUpdateServiceRest {
	
	public void getUpdates() throws Exception;
	public MicrosoftSecurityDetailVO getMicrosoftVO(String endpoint);
}
