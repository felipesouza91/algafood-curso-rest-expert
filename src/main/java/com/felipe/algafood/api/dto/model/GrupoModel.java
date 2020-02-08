package com.felipe.algafood.api.dto.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "grupos")
public class GrupoModel extends RepresentationModel<GrupoModel> {

	@ApiModelProperty(value = "Codigo do grupo" , example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome do grupo" , example = "Administrador")
	private String nome;
}
