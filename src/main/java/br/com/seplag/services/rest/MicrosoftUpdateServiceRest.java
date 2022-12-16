package br.com.seplag.services.rest;

/**
 * Classe responsável por Obter as informações da API da microsoft
 * @author Artur
 *
 */
public interface MicrosoftUpdateServiceRest {

	public void getCvrf(String id);
	public void getUpdates();
	public void getUpdates(String key);
}
