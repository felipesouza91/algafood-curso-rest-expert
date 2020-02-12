package com.felipe.algafood.core.springfox.model;

import java.math.BigDecimal;

import com.felipe.algafood.api.v1.dto.model.CozinhaModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("RestauranteBasicoModel")
@Getter
@Setter
public class RestauranteBasicModelOpenApi {

	@ApiModelProperty(value = "Codigo do restaurante", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome do restaurante", example = "Brasileiros")
	private String nome;
	
	@ApiModelProperty(value = "Taxa frete do restaurante", example = "15,00")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(value = "Representação da cozinha")
	private CozinhaModel cozinha;
}
