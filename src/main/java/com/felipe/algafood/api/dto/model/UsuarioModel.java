package com.felipe.algafood.api.dto.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {
	
	@ApiModelProperty(value = "Id do Cliente", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome do Cliente", example = "Fulano da Silva")
	private String nome;
	
	@ApiModelProperty(value = "Email do Cliente", example = "fulano@email.com")
	private String email;
}
