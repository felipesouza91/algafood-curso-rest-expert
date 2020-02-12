package com.felipe.algafood.api.v2.dto.inputs;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CidadeInput")
public class CidadeInputV2 {
	
	@NotBlank
	@ApiModelProperty(value = "Nome do estado",example = "Uberlandia", required = true)
	private String nome;
	
	@NotNull
	@ApiModelProperty(value = "Codigo do estado",example = "1", required = true)
	private Long idEstado;

}
