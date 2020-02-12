package com.felipe.algafood.api.v1.dto.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation =  "permissoes")
public class PermissaoModel extends RepresentationModel<PermissaoModel> {

	@ApiModelProperty(value = "Codigo Permissao" , example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome permisao" , example = "CAD_USER")
	private String nome;
	
	@ApiModelProperty(value = "Descrição permisao" , example = "Permite o cadastramento de um usuario")
	private String descricao;
}
