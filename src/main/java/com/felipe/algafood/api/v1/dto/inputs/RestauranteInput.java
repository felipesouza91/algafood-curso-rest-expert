package com.felipe.algafood.api.v1.dto.inputs;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.felipe.algafood.api.v1.dto.inputs.ids.CozinhaIdInput;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteInput {

	@NotBlank
	@ApiModelProperty(value = "Nome do restaurante", required = true, example = "Restaurante Brasil")
	private String nome;
	
	@NotNull
	@PositiveOrZero
	@ApiModelProperty(value = "Taxa frete do restaurante", required = true, example = "12.00")
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	@ApiModelProperty(value = "Representação da cozinha", required = true)
	private CozinhaIdInput cozinha;
	
	@Valid
	@ApiModelProperty(value = "Representação do endereço do restaurante", required = true)
	private EnderecoInput endereco;
}
