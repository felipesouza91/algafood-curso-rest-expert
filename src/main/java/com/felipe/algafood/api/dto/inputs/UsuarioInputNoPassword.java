package com.felipe.algafood.api.dto.inputs;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputNoPassword {

	@NotBlank
	private String nome;
	
	@Email
	private String email;
}
