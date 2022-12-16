package br.com.seplag.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MicrosoftSecurityDetailVO implements Serializable {
	
	private static final long serialVersionUID = -8334183939733215117L;

	@JsonProperty(value = "@odata.context")
	private String context;

	@JsonProperty(value = "value")
	private List<ValueMicrosoftDetailVO> value;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public List<ValueMicrosoftDetailVO> getValue() {
		return value;
	}

	public void setValue(List<ValueMicrosoftDetailVO> value) {
		this.value = value;
	}

}
