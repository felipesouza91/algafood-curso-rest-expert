package com.felipe.algafood.api.dto.inputs;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInput {
	
	@NotBlank
	private String nome;

}
