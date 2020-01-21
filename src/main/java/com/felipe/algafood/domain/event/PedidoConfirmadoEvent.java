package com.felipe.algafood.domain.event;

import com.felipe.algafood.domain.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoConfirmadoEvent  {

	private Pedido pedido;
}
