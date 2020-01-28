package com.felipe.algafood.api.dto.model;

import com.felipe.algafood.api.dto.model.resumo.CidadeResumoModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

	@ApiModelProperty(value = "Cep do endereço" , example = "22222-222")
	private String cep;
	
	@ApiModelProperty(value = "Informação do lagradouro" , example = "Rua da entrada")
	private String logradouro;
	
	@ApiModelProperty(value = "Numero do endereço" , example = "1")
	private String numero;
	
	@ApiModelProperty(value = "Complemento do endereço" , example = "Entrada lado esquerdo")
	private String complemento;
	
	@ApiModelProperty(value = "Nome do bairro" , example = "Santo Cristo")
	private String bairro;
	
	@ApiModelProperty(value = "Representação da cidade")
	private CidadeResumoModel cidade;
}
