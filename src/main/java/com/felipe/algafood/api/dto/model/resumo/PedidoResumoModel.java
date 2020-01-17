package com.felipe.algafood.api.dto.model.resumo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.felipe.algafood.api.dto.model.UsuarioModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoModel {

	private String codigo;

	private BigDecimal subTotal;

	private BigDecimal taxaFrete;

	private BigDecimal valorTotal;

	private OffsetDateTime dataCriacao;

	private String status;

	private RestauranteResumoModel restaurante;

	private UsuarioModel cliente;
	
}
