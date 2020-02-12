package com.felipe.algafood.api.v1.dto.model.resumo;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResumoModel extends RepresentationModel<CidadeResumoModel> {

	@ApiModelProperty(value = "Codigo da Cidade" , example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome da cidade" , example = "Rio de janeiro")
	private String nome;
	
	@ApiModelProperty(value = "Nome do estado" , example = "Rio de janeiro")
	private String estado;
}
