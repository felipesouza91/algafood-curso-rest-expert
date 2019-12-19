package com.felipe.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.felipe.algafood.core.Groups.PedidoId;
import com.felipe.algafood.core.Groups.ProdutoId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "itens_pedido")
public class ItensPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@NotNull
	@Column(nullable =  false)
	private Long quantidade;
	
	@NotNull
	@Column(name = "preco_unitario", nullable = false	)
	private BigDecimal precoUnitario;
	
	@NotNull
	@Column(name = "preco_total")
	private BigDecimal precoTotal;
	
	private String observacao;
	
	@Valid
	@ConvertGroup(from = Default.class, to = ProdutoId.class)
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	@Valid
	@ConvertGroup(from = Default.class, to= PedidoId.class)
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
}
