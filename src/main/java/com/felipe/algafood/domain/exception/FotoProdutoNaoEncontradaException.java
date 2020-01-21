package com.felipe.algafood.domain.exception;

public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public FotoProdutoNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public FotoProdutoNaoEncontradaException(Long idProd, Long idRest) {
		this(String.format("Não existe um cadastro de foto do produto com codigo %d para o resturante de código %d", idProd, idRest));
	}
}
