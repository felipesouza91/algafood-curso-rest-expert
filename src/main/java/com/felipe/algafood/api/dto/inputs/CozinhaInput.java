package com.felipe.algafood.api.dto.inputs;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInput {
	
	@NotBlank
	@ApiModelProperty(value = "Nome da cozinha", example = "Brasileira", required = true)
	private String nome;

}
