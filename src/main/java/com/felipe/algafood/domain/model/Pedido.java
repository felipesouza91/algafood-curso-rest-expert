package com.felipe.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;

import com.felipe.algafood.core.Groups.ClienteId;
import com.felipe.algafood.core.Groups.FormaPagamentoId;
import com.felipe.algafood.core.Groups.PedidoId;
import com.felipe.algafood.core.Groups.RestauranteId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@NotNull(groups = PedidoId.class)
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotNull
	@Column(name = "sub_total")
	private BigDecimal subTotal;

	@NotNull
	@Column(name= "taxa_frete")
	private BigDecimal taxaFrete;
	
	@NotNull
	@Column(name = "valor_total")
	private BigDecimal valoTotal;
	
	@CreationTimestamp
	@Column(name= "data_criacao", updatable = false)
	private OffsetDateTime dataCriacao;
	
	@Column(name = "data_confirmacao")
	private OffsetDateTime dataConfirmacao;
	
	@Column(name = "data_cancelamento")
	private LocalDateTime dataCancelamento;
	
	@Column(name = "data_entrega")
	private LocalDateTime dataEntrega;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	@NotNull
	@Embedded
	private Endereco enderecoEntrega;
	
	@Valid
	@ConvertGroup(from = Default.class, to= FormaPagamentoId.class)
	@ManyToOne
	@JoinColumn(name = "forma_pagamento_id", nullable = false)
	private FormaPagamento formaPagamento;
	
	@Valid
	@ConvertGroup(from = Default.class, to = RestauranteId.class)
	@ManyToOne
	@JoinColumn(name = "restaurante_id", nullable = false)
	private Restaurante restaurante;
	
	
	@Valid
	@ConvertGroup(from = Default.class, to = ClienteId.class)
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Usuario cliente;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItensPedido> listItensPedido = new ArrayList<>();
}
