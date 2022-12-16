package br.com.seplag.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValueMicrosoftDetailVO {

	@JsonProperty("ID")
	private String id;

	@JsonProperty("Alias")
	private String alias;

	@JsonProperty("DocumentTitle")
	private String documentTitle;

	@JsonProperty("Severity")
	private String severity;

	@JsonProperty("InitialReleaseDate")
	private Date initialReleaseDate;

	@JsonProperty("CurrentReleaseDate")
	private Date currentReleaseDate;

	@JsonProperty("CvrfUrl")
	private String cvrfUrl;
	
	@JsonProperty("Contexto")
	private String contexto;
}
