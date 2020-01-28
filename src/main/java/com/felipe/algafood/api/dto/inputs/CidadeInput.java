package com.felipe.algafood.api.dto.inputs;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.felipe.algafood.api.dto.inputs.ids.EstadoIdInput;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {
	
	@NotBlank
	@ApiModelProperty(value = "Nome do estado",example = "Uberlandia", required = true)
	private String nome;
	
	@Valid
	@NotNull
	@ApiModelProperty(value = "Representação de estado", required = true ,example = "")
	private EstadoIdInput estado;

}
