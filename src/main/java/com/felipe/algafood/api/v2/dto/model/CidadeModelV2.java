package com.felipe.algafood.api.v2.dto.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "cidades")
@ApiModel("CidadeModel")
public class CidadeModelV2 extends RepresentationModel<CidadeModelV2>{

	@ApiModelProperty(value = "Codigo da cidade" , example = "1")
	private Long idCidade;
	
	@ApiModelProperty(value = "Nome da cidade" , example = "Rio de janeiro")
	private String nomeCidade;
	
	@ApiModelProperty(value = "Codigo do estado" , example = "1")
	private Long idEstado;
	
	@ApiModelProperty(value = "Nome da estado" , example = "Rio de janeiro")
	private String nomeEstado;
	
	//@ApiModelProperty(value = "Representação de estado")
	//private EstadoModel estado;
	
	
}
