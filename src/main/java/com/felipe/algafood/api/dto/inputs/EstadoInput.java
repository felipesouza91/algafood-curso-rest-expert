package com.felipe.algafood.api.dto.inputs;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInput {

	@NotBlank
	@ApiModelProperty(value = "Nome do estado", example = "Rio de janeiro", required = true)
	private String nome;
}
