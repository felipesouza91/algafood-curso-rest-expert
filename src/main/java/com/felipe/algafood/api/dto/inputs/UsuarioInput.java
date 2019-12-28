package com.felipe.algafood.api.dto.inputs;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String senha;
	
	@Email
	private String email;
}
