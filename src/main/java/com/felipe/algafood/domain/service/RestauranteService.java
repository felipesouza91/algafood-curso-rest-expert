package com.felipe.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.algafood.domain.exception.CidadeNaoEncontradaException;
import com.felipe.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.felipe.algafood.domain.exception.NegocioException;
import com.felipe.algafood.domain.exception.RestauranteNaoEncontradaException;
import com.felipe.algafood.domain.model.Cidade;
import com.felipe.algafood.domain.model.Cozinha;
import com.felipe.algafood.domain.model.Restaurante;
import com.felipe.algafood.domain.repository.RestauranteRepository;

import lombok.Getter;

@Service
public class RestauranteService {

	@Autowired
	@Getter
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CidadeService cidadeService;

	/**
	 * Retornar o objeto do banco de dados
	 * 
	 * @param id identificado do objeto a ser buscado
	 * @return {@link Restaurante} objeto que foi encontrado
	 * @throws RestauranteNaoEncontradaException caso o objeto nao seja encontrado.
	 * */
	public Restaurante buscarPorId(Long id) {
		return this.restauranteRepository.findById(id)
				.orElseThrow(() -> new RestauranteNaoEncontradaException(id));
	}
	
	/**
	 * @param restaurante Objeto que sera persistido
	 * @return {@link Restaurante} objeto apos persistido no banco de dados
	 * @throws NegocioException caso a Cozinha da instancia não esteja cadastrado
	 * */
	public Restaurante salvar(Restaurante restaurante) {
		Cozinha cozinha = findCozinhaById(restaurante.getCozinha().getId());
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}

	
	/**
	 * @param id Identificado da entidade que sera atualizada
	 * @param restaurante Objeto a instancia da entidade da ser atualizada
	 * @return {@link Restaurante} objeto apos persistido no banco de dados
	 * @throws RestauranteNaoEncontradaException caso o objeto nao seja encontrado.
	 * @throws NegocioException caso a Cozinha da instancia não esteja cadastrado
	 * */
	public Restaurante atualizar(Long id, Restaurante restaurante) {
		Cozinha cozinha = this.findCozinhaById(restaurante.getCozinha().getId());
		Cidade cidade = this.findCidadeByid(restaurante.getEndereco().getCidade().getId());
		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);
		this.buscarPorId(id);
		return this.restauranteRepository.save(restaurante);
	}
	
	@Transactional
	public void ativar(Long id) {
		Restaurante restaurante = this.buscarPorId(id);
		restaurante.ativar();
	}
	
	@Transactional
	public void inativar(Long id) {
		Restaurante restaurante = this.buscarPorId(id);
		restaurante.inativar();
	}
	
	/**
	 * @param id identificado do objeto a ser buscado
	 * @return {@link Cozinha } Cozinha o objeto que foi encontrado
	 * @throws NegocioException caso o objeto nao seja encontrado.
	 * */
	private Cozinha findCozinhaById(Long id) {
		Cozinha cozinha = null;
		try {
			cozinha  = this.cozinhaService.buscarPorId(id);
		}catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		return cozinha;
	}
	
	private Cidade findCidadeByid(Long id ) {
		Cidade cidade = null;
		try {
			cidade = this.cidadeService.buscarPorId(id);
		} catch (CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		return cidade;
	}
}
