package com.felipe.algafood.api.v1.dto.inputs;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {

	@NotNull
	@ApiModelProperty(value = "Codigo do produto", required = true, example = "1")
	private Long produtoId;
	
	@NotNull
	@Positive(message = "Quantidade do iten deve ser mairo que zero")
	@ApiModelProperty(value = "Quantidade do produto", required = true, example = "10")
	private Integer quantidade;
	
	@ApiModelProperty(value = "Observação")
	private String observacao;
	
}
