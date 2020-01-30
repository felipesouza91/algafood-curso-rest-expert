package com.felipe.algafood.api.dto.model;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoModel extends RepresentationModel<EstadoModel>{

	@ApiModelProperty(value = "Codigo do estado" , example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome do estado" , example = "Rio de janeiro")
	private String nome;
}
