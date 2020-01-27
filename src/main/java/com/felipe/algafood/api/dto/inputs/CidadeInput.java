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
	@ApiModelProperty(example = "Uberlandia")
	private String nome;
	
	@Valid
	@NotNull
	private EstadoIdInput estado;

}
