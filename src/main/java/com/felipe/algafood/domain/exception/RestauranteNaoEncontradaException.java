package com.felipe.algafood.domain.exception;

public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public RestauranteNaoEncontradaException(Long id) {
		this(String.format("Não existe um Restaurante  com o codigo %d", id));
	}
}
