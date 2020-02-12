package com.felipe.algafood.api.v1.dto.inputs;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.felipe.algafood.api.v1.dto.inputs.ids.CidadeIdInput;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

	@NotBlank
	@ApiModelProperty(value = "Cep do endereço", example = "20222-123", required = true)
	private String cep;

	@NotBlank
	@ApiModelProperty(value = "Nome da rua/lagradouro", example = "Rua das silvas", required = true)
	private String logradouro;

	@NotBlank
	@ApiModelProperty(value = "Numero da residencia", example = "2000", required = true)
	private String numero;
	
	@ApiModelProperty(value = "Complemento", example = "Proximo a equina")
	private String complemento;
	
	@NotBlank
	@ApiModelProperty(value = "Nome do bairro", example = "Santos dos milagres", required = true)
	private String bairro;
	
	@Valid
	@NotNull
	@ApiModelProperty(value = "Representação da cidade", required = true, example = "")
	private CidadeIdInput cidade;
}
