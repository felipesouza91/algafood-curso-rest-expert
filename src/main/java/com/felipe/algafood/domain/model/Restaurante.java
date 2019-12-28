package com.felipe.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	
	@Column(name="taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
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
	private Set<FormaPagamento> formasPagamentos = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "restaurante_usuario_responsavel",
					joinColumns = @JoinColumn(name="restaurante_id"),
					inverseJoinColumns = @JoinColumn(name="usuario_id"))
	private Set<Usuario> responsaveis = new HashSet<>();
	
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos;
	
	private Boolean ativo = Boolean.TRUE;
	
	private Boolean aberto = Boolean.TRUE;
	
	public void ativar() {
		setAtivo(true);
	}
	
	public void inativar() {
		setAtivo(false);
	}
	
	public void fechar() {
		setAberto(false);
	}
	
	public void abrir() {
		setAberto(true);
	}
	
	public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
		return this.getFormasPagamentos().add(formaPagamento);
	}

	public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
		return this.getFormasPagamentos().remove(formaPagamento);
	}

	public boolean adicionarResponavel(Usuario usuario) {
		return this.getResponsaveis().add(usuario);
	}

	public boolean removerResponavel(Usuario usuario) {
		return this.getResponsaveis().remove(usuario);
	}

	public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
		return !this.getFormasPagamentos().contains(formaPagamento);
	}
}
