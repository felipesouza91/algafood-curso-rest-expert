package com.felipe.algafood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.algafood.domain.exception.CidadeNaoEncontradaException;
import com.felipe.algafood.domain.exception.EntidadeEmUsoException;
import com.felipe.algafood.domain.exception.EstadoNaoEncontradaException;
import com.felipe.algafood.domain.exception.NegocioException;
import com.felipe.algafood.domain.model.Cidade;
import com.felipe.algafood.domain.repository.CidadeRepository;

import lombok.Getter;

@Service
public class CidadeService {

	@Autowired
	@Getter
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	public Cidade buscarPorId(Long id) {
		return this.cidadeRepository.findById(id)
				.orElseThrow(() -> new CidadeNaoEncontradaException(id));
	}
	
	@Transactional
	public Cidade salvar (Cidade cidade) {
		Long codigo = cidade.getEstado().getId();
		try {
			this.estadoService.buscarPorId(codigo);
		}catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		return this.cidadeRepository.save(cidade);
	}

	@Transactional
	public Cidade atualizar(Long id, Cidade cidade) {
		try {
			estadoService.buscarPorId(cidade.getEstado().getId());
		}catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		Cidade cidadeSalva = this.buscarPorId(id);
		BeanUtils.copyProperties(cidade, cidadeSalva, "id");
		return this.cidadeRepository.save(cidadeSalva);
	}

	@Transactional
	public void excluir(Long id) {
		this.buscarPorId(id);
		try {
			this.cidadeRepository.deleteById(id);
			this.cidadeRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cidade de codigo %d não pode ser removida, pois esta em uso", id));
		}
	}

}
