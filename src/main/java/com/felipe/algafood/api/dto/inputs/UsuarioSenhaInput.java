package com.felipe.algafood.api.dto.inputs;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioSenhaInput {

	@NotBlank
	private String senhaAtual;
	
	@NotBlank
	private String novaSenha;
}
