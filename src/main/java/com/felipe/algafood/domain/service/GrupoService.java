package com.felipe.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.algafood.domain.exception.EntidadeEmUsoException;
import com.felipe.algafood.domain.exception.GrupoNaoEncontradaException;
import com.felipe.algafood.domain.model.Grupo;
import com.felipe.algafood.domain.repository.GrupoRepository;

import lombok.Getter;

@Service
public class GrupoService {

	@Getter
	@Autowired
	private GrupoRepository grupoRepository;

	@Transactional
	public List<Grupo> buscarTodos() {
		return this.grupoRepository.findAll();
	}

	@Transactional
	public Grupo buscarPorId(Long id) {
		return this.grupoRepository.findById(id).orElseThrow(() -> new GrupoNaoEncontradaException(id));
	}

	@Transactional
	public Grupo salvar(Grupo grupo) {
		return this.grupoRepository.save(grupo);
	}

	@Transactional
	public void excluir(Long id) {
		this.buscarPorId(id);
		try {
			this.grupoRepository.deleteById(id);
			this.grupoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Grupo de codigo %d não pode ser removida, pois esta em uso", id));
		}
	}

}
