package com.felipe.algafood.api.dto.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "cidades")
public class CidadeModel extends RepresentationModel<CidadeModel>{

	@ApiModelProperty(value = "Codigo da cidade" , example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome da cidade" , example = "Rio de janeiro")
	private String nome;
	
	@ApiModelProperty(value = "Representação de estado")
	private EstadoModel estado;
	
	
}
