package com.felipe.algafood.api.dto.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "estados")
public class EstadoModel extends RepresentationModel<EstadoModel>{

	@ApiModelProperty(value = "Codigo do estado" , example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome do estado" , example = "Rio de janeiro")
	private String nome;
}
