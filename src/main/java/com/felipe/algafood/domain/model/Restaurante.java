package com.felipe.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.felipe.algafood.core.Groups.CozinhaId;
import com.felipe.algafood.core.Groups.RestauranteId;
import com.felipe.algafood.core.ValorZeroIncluiDescricao;

import lombok.Data;
import lombok.EqualsAndHashCode;

@ValorZeroIncluiDescricao(
		valorField = "taxaFrete",
		descricaoField = "nome",
		descricaoObrigatorio="Frete Gratis"
		)
@Entity
@Table(name="restaurante")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

	@Id
	@NotNull(groups = RestauranteId.class)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@NotNull
	@PositiveOrZero
	@Column(name="taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = CozinhaId.class)
	@ManyToOne
	@JoinColumn(name= "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	@Embedded
	private Endereco endereco;
	
	@CreationTimestamp
	@Column(nullable =  false, updatable = false)
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(nullable =  false)
	private OffsetDateTime dataAtualizacao;
	
	
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
					joinColumns = @JoinColumn(name="restaurante_id"),
					inverseJoinColumns = @JoinColumn(name="forma_pagamento_id"))
	private List<FormaPagamento> formasPagamentos = new ArrayList<>();
	
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos;
}
