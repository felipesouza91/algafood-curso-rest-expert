package com.felipe.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.algafood.domain.exception.CidadeNaoEncontradaException;
import com.felipe.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.felipe.algafood.domain.exception.EntidadeEmUsoException;
import com.felipe.algafood.domain.model.Cozinha;
import com.felipe.algafood.domain.repository.CozinhaRepository;

import lombok.Getter;

@Service
public class CozinhaService {

	@Autowired
	@Getter
	private CozinhaRepository cozinhaRepository;
	
	
	/**
	 * @param Instancia do objeto que sera persistida
	 * @return {@link Cozinha} do objeto persistida
	 * */
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	/**
	 * @param id identificado do objeto a ser excluido
	 * @return nao possui retorno no metodo
	 * @throws CidadeNaoEncontradaException caso o objeto a ser excluido nao seja encontrado.
	 * @throws EntidadeEmUsoException Caso o objeto a ser excluido esteja em uso.
	 * */
	@Transactional
	public void excluir(Long id) {
		this.buscarPorId(id);
		try {
			this.cozinhaRepository.deleteById(id);
		} catch (DataIntegrityViolationException ex) {
			throw new EntidadeEmUsoException(String.format("Cozinha de codigo %d não pode ser removida, pois esta em uso", id));
		}
	}
	
	/**
	 * @param id identificado do objeto a ser buscado
	 * @return @linkCozinha o objeto que foi encontrado
	 * @throws CidadeNaoEncontradaException caso o objeto nao seja encontrado.
	 * */
	public Cozinha buscarPorId(Long id) {
		return this.cozinhaRepository.findById(id)
					.orElseThrow(() -> new CozinhaNaoEncontradaException(id));
	}
	
	
}
