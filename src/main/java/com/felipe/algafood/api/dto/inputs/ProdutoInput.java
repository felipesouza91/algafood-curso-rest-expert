package com.felipe.algafood.api.dto.inputs;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {

	@NotBlank
	@ApiModelProperty(value = "Nome do produto" , example = "Arroz", required = true)
	private String nome;
	
	@NotBlank
	@ApiModelProperty(value = "Descrição do produto" , example = "Descrição do arroz", required = true)
	private String descricao;
	
	@PositiveOrZero
	@NotNull
	@ApiModelProperty(value = "Preço do produto" , example = "15.00", required = true)
	private BigDecimal preco;
	
	@NotNull
	@ApiModelProperty(value = "Estado do produto" , example = "true", required = true)
	private Boolean ativo;
}
