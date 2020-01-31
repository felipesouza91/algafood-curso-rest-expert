package com.felipe.algafood.api.dto.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonView;
import com.felipe.algafood.api.view.RestauranteView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Relation(collectionRelation = "cozinhas")
public class CozinhaModel  extends RepresentationModel<CozinhaModel>{

	@JsonView(RestauranteView.Resumo.class)
	@ApiModelProperty(value = "Codigo da cozinha", example = "10")
	private Long id;
	
	@JsonView(RestauranteView.Resumo.class)
	@ApiModelProperty(value = "Nome da cozinha", example = "Brasileira")
	private String nome;
	
}
