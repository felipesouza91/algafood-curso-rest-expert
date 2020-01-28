package com.felipe.algafood.api.dto.model.resumo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResumoModel {

	@ApiModelProperty(value = "Id do restaurante", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome do restaurante", example = "Brasileiros")
	private String nome;
}
