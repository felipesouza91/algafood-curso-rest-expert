package com.felipe.algafood.api.v1.dto.model.resumo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.felipe.algafood.api.v1.dto.model.UsuarioModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Relation(collectionRelation = "pedidos")
public class PedidoResumoModel extends RepresentationModel<PedidoResumoModel> {

	@ApiModelProperty(value = "Codigo de um pedido", example = "55s8d48-55d45d8-5d5d4d")
	private String codigo;

	@ApiModelProperty(value = "Sub-total de um epdido", example = "1.000,00")
	private BigDecimal subTotal;

	@ApiModelProperty(value = "Taxa de frete de um pedido", example = "10,00")
	private BigDecimal taxaFrete;

	@ApiModelProperty(value = "Valor total de um pedido", example = "900,00")
	private BigDecimal valorTotal;

	@ApiModelProperty(value = "Data de criação de um pedido", example = "2020-01-28T01:03:11Z")
	private OffsetDateTime dataCriacao;

	@ApiModelProperty(value = "Status de um pedido", example = "Criado")
	private String status;

	@ApiModelProperty(value = "Restaurante do pedido")
	private RestauranteApenasNomeModel restaurante;

	@ApiModelProperty(value = "Cliente do pedido")
	private UsuarioModel cliente;
	
}
