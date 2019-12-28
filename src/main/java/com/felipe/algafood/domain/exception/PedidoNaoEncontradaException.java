package com.felipe.algafood.domain.exception;

public class PedidoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public PedidoNaoEncontradaException(Long id) {
		this(String.format("Não existe um pedido com o codigo %d", id));
	}
}
