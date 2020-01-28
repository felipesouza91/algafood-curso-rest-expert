package com.felipe.algafood.api.dto.inputs;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputNoPassword {

	@NotBlank
	@ApiModelProperty(value = "Nome do usuario" , example = "Fulano", required = true)
	private String nome;
	
	@Email
	@ApiModelProperty(value = "Email do usuario" , example = "fulano@email.com", required = true)
	private String email;
}
