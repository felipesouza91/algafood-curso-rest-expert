package com.felipe.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.felipe.algafood.core.Groups.CidadeId;

import lombok.Data;

@Data
@Embeddable
public class Endereco {

	@Column(name="endereco_cep")
	private String cep;
	
	@Column(name="endereco_logradouro")
	private String logradouro;
	
	@Column(name="endereco_numero")
	private String numero;
	
	@Column(name="endereco_complemento")
	private String complemento;
	
	@Column(name="endereco_bairro")
	private String bairro;
	
	@Valid
	@ConvertGroup(from = Default.class, to = CidadeId.class )
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_cidade_id")
	private Cidade cidade;
}
