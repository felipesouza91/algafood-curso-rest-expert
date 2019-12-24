package com.felipe.algafood.domain.exception;

public class GrupoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public GrupoNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public GrupoNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro de Grupo com o codigo %d", id));
	}
}
