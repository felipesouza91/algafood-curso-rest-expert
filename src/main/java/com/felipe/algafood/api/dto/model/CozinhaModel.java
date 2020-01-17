package com.felipe.algafood.api.dto.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.felipe.algafood.api.view.RestauranteView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaModel {

	@JsonView(RestauranteView.Resumo.class)
	private Long id;
	
	@JsonView(RestauranteView.Resumo.class)
	private String nome;
	
}
