package com.felipe.algafood.api.v1.dto.inputs;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@NotBlank
	@ApiModelProperty(value = "Nome do usuario" , example = "Fulano", required = true)
	private String nome;
	
	@NotBlank
	@ApiModelProperty(value = "Senha do usuario" , example = "sebgas55", required = true)
	private String senha;
	
	@Email
	@ApiModelProperty(value = "Email do usuario" , example = "fulano@email.com", required = true)
	private String email;
}
