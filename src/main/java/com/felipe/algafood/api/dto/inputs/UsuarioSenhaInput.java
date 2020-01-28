package com.felipe.algafood.api.dto.inputs;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioSenhaInput {

	@NotBlank
	@ApiModelProperty(value = "Senha atual do usuario" , example = "Senha$3Atual", required = true)
	private String senhaAtual;
	
	@NotBlank
	@ApiModelProperty(value = "Nova senha do usuario" , example = "Nova@1senha", required = true)
	private String novaSenha;
}
