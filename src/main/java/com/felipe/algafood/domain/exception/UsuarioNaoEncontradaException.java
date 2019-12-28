package com.felipe.algafood.domain.exception;

public class UsuarioNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public UsuarioNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro de Usuário com o codigo %d", id));
	}
}
