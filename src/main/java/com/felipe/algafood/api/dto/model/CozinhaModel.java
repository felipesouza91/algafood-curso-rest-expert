package com.felipe.algafood.api.dto.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.felipe.algafood.api.view.RestauranteView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaModel {

	@JsonView(RestauranteView.Resumo.class)
	@ApiModelProperty(value = "Codigo da cozinha", example = "10")
	private Long id;
	
	@JsonView(RestauranteView.Resumo.class)
	@ApiModelProperty(value = "Nome da cozinha", example = "Brasileira")
	private String nome;
	
}
