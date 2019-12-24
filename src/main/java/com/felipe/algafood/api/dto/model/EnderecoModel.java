package com.felipe.algafood.api.dto.model;

import com.felipe.algafood.api.dto.model.resumo.CidadeResumoModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

	private String cep;
	
	private String logradouro;
	
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	private CidadeResumoModel cidade;
}
