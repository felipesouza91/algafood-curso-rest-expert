package com.felipe.algafood.api.dto.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoModel {

	@ApiModelProperty(value = "Codigo Permissao" , example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome permisao" , example = "CAD_USER")
	private String nome;
	
	@ApiModelProperty(value = "Descrição permisao" , example = "Permite o cadastramento de um usuario")
	private String descricao;
}
