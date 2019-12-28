package com.felipe.algafood.api.dto.inputs;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	@PositiveOrZero
	@NotNull
	private BigDecimal preco;
	
	@NotNull
	private Boolean ativo;
}