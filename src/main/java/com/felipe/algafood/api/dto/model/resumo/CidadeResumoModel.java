package com.felipe.algafood.api.dto.model.resumo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResumoModel {

	@ApiModelProperty(value = "Codigo da Cidade" , example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome da cidade" , example = "Rio de janeiro")
	private String nome;
	
	@ApiModelProperty(value = "Nome do estado" , example = "Rio de janeiro")
	private String estado;
}
