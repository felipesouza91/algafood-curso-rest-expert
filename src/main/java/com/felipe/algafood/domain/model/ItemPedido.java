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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "itens_pedido")
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@NotNull
	@Column(nullable =  false)
	private Integer quantidade;
	
	@NotNull
	@Column(name = "preco_unitario", nullable = false	)
	private BigDecimal precoUnitario;
	
	@NotNull
	@Column(name = "preco_total")
	private BigDecimal precoTotal;
	
	private String observacao;
	
	@Valid
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	@Valid
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	public void calcularPrecoTotal() {
		BigDecimal precoUnitario = this.getPrecoUnitario();
		Integer quantidade = this.getQuantidade();

		if (precoUnitario == null) {
			precoUnitario = BigDecimal.ZERO;
		}

		if (quantidade == null) {
			quantidade = 0;
		}

		this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
	}
	
}
