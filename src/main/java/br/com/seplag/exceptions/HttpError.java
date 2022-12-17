package br.com.seplag.exceptions;

/**
 * Classe responsável por tratar error relacionados com requisições HTTP
 * 
 * @author Artur
 * @since 17/12/2022 12:36
 */
public class HttpError extends Exception {

	private static final long serialVersionUID = 1L;

	public HttpError(String errorMessage, Throwable err) {

		super(errorMessage, err);

	}

}
