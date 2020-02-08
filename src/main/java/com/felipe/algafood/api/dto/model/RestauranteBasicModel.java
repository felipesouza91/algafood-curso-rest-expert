package com.felipe.algafood.api.dto.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "restaurantes")
public class RestauranteBasicModel extends RepresentationModel<RestauranteModel>{

	private Long id;

	private String nome;
	
	private BigDecimal taxaFrete;
	
	private CozinhaModel cozinha;
}
