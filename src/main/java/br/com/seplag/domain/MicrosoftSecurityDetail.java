package br.com.seplag.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "microsoft_security_detail")
@Getter
@Setter
public class MicrosoftSecurityDetail {

	@Id
	private String id;

	private String alias;

	private String documentTitle;

	private String severity;

	private Date initialReleaseDate;

	private Date currentReleaseDate;

	private String cvrfUrl;

	private String contexto;

}
