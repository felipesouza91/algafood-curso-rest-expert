package com.felipe.algafood.api.dto.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

import com.felipe.algafood.api.dto.model.resumo.RestauranteResumoModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel {
	
	private String codigo;
	
	private BigDecimal subTotal;
	
	private BigDecimal taxaFrete;
	
	private BigDecimal valoTotal;
	
	private OffsetDateTime dataCriacao;
	
	private OffsetDateTime dataConfirmacao;
	
	private LocalDateTime dataCancelamento;
	
	private LocalDateTime dataEntrega;
	
	private String status;
	
	private EnderecoModel enderecoEntrega;
	
	private FormaPagamentoModel formaPagamento;
	
	private RestauranteResumoModel restaurante;
	
	private UsuarioModel cliente;
	
	private List<ItemPedidoModel> itens ;
}
