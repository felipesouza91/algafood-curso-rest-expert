package com.felipe.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.algafood.domain.exception.CidadeNaoEncontradaException;
import com.felipe.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.felipe.algafood.domain.exception.NegocioException;
import com.felipe.algafood.domain.exception.RestauranteNaoEncontradaException;
import com.felipe.algafood.domain.model.Cidade;
import com.felipe.algafood.domain.model.Cozinha;
import com.felipe.algafood.domain.model.FormaPagamento;
import com.felipe.algafood.domain.model.Restaurante;
import com.felipe.algafood.domain.model.Usuario;
import com.felipe.algafood.domain.repository.RestauranteRepository;

import lombok.Getter;

@Service
public class RestauranteService {

	@Autowired
	@Getter
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private UsuarioService usuarioService;

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
	public void fechar(Long id) {
		Restaurante restaurante = this.buscarPorId(id);
		restaurante.fechar();
	}

	@Transactional
	public void abrir(Long id) {
		Restaurante restaurante = this.buscarPorId(id);
		restaurante.abrir();
	}
	
	@Transactional
	public void inativar(Long id) {
		Restaurante restaurante = this.buscarPorId(id);
		restaurante.inativar();
	}
	
	@Transactional
	public void desassociarFormaPagamento(Long idRestaurante, Long idFormaPagamento) {
		Restaurante restaurante = this.buscarPorId(idRestaurante);
		FormaPagamento formaPagamento = this.formaPagamentoService.buscarById(idFormaPagamento);
		restaurante.removerFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void associarFormaPagamento(Long idRestaurante, Long idFormaPagamento) {
		Restaurante restaurante = this.buscarPorId(idRestaurante);
		FormaPagamento formaPagamento = this.formaPagamentoService.buscarById(idFormaPagamento);
		restaurante.adicionarFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void associarResponavel(Long id, Long idUsuario) {
		Restaurante restaurante = this.buscarPorId(id);
		Usuario usuario = this.usuarioService.buscarPorId(idUsuario);
		restaurante.adicionarResponavel(usuario);
	}

	@Transactional
	public void desassociarResponavel(Long id, Long idUsuario) {
		Restaurante restaurante = this.buscarPorId(id);
		Usuario usuario = this.usuarioService.buscarPorId(idUsuario);
		restaurante.removerResponavel(usuario);
	}
	
	@Transactional
	public void ativar(List<Long> restaurantesIds) {
		try {
			restaurantesIds.forEach(this::ativar);	
		} catch (RestauranteNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		
	}
	
	@Transactional
	public void inativar(List<Long> restaurantesIds) {
		try {
			restaurantesIds.forEach(this::inativar);
		} catch (RestauranteNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
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
