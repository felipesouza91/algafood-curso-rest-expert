package com.felipe.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.algafood.domain.exception.EntidadeEmUsoException;
import com.felipe.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.felipe.algafood.domain.model.Produto;
import com.felipe.algafood.domain.repository.ProdutoRepository;

import lombok.Getter;

@Service
public class ProdutoService {

	@Autowired
	@Getter
	private ProdutoRepository produtoRepository; 
	
	@Transactional
	public List<Produto> buscarTodos() {
		return this.produtoRepository.findAll();
	}
	
	@Transactional
	public Produto buscarPorId(Long id) {
		return this.produtoRepository.findById(id)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(id));
	}
	
	public Produto buscarPorIdRestauranteEProduto(Long idRestaurante, Long idProduto) {
		return this.produtoRepository.findByIdRestAndIdProduto(idRestaurante, idProduto)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(
						String.format("Não existe um cadastro de produto com o codigo %d para o restaurante de codigo %d",
								idProduto, idRestaurante)));
	}
	
	@Transactional
	public Produto salvar(Produto produto) {
		return this.produtoRepository.save(produto);
	}
	
	@Transactional
	public void excluir(Long id) {
		this.buscarPorId(id);
		try {
			this.produtoRepository.deleteById(id);
			this.produtoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Produto de codigo %d não pode ser removida, pois esta em uso", id));
		}
	}
}
