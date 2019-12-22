package com.felipe.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.algafood.domain.exception.EntidadeEmUsoException;
import com.felipe.algafood.domain.exception.EstadoNaoEncontradaException;
import com.felipe.algafood.domain.model.Estado;
import com.felipe.algafood.domain.repository.EstadoRepository;

import lombok.Getter;

@Service
public class EstadoService {

	@Autowired
	@Getter
	private EstadoRepository estadoRepository;
	
	public Estado buscarPorId(Long id) {
		return this.estadoRepository.findById(id)
				.orElseThrow(() -> new EstadoNaoEncontradaException(id));
	}
	
	@Transactional
	public Estado salvar(Estado estado) {
		return this.estadoRepository.save(estado);
	}

	@Transactional
	public void excluir(Long id) {
		this.buscarPorId(id);
		try {
			
			this.estadoRepository.deleteById(id);
			this.estadoRepository.flush();
		} catch (DataIntegrityViolationException ex) {
			throw new EntidadeEmUsoException(String.format("Estado de codigo %d não pode ser removida, pois esta em uso", id));
		}	// TODO: handle exception
	}

	
}
