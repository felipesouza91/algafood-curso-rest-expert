package com.felipe.algafood.api.dto.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel {

	@ApiModelProperty(value = "Codigo do produto" , example = "2")
	private Long produtoId;
	
	@ApiModelProperty(value = "Nome do produto" , example = "Arroz")
	private String produtoNome;
	
	@ApiModelProperty(value = "Quantidade do produto" , example = "10")
	private Long quantidade;
	
	@ApiModelProperty(value = "Preço unitario do produto" , example = "15.00")
	private BigDecimal precoUnitario;

	@ApiModelProperty(value = "Preco total do Item" , example = "300.0")
	private BigDecimal precoTotal;
	
	@ApiModelProperty(value = "Observação" , example = "Frio")
	private String observacao;
}
