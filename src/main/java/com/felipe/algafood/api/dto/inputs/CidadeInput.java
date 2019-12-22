package com.felipe.algafood.api.dto.inputs;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.felipe.algafood.api.dto.inputs.ids.EstadoIdInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {
	
	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private EstadoIdInput estado;

}
