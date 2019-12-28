package com.felipe.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.algafood.domain.exception.PermissaoNaoEncontradaException;
import com.felipe.algafood.domain.model.Permissao;
import com.felipe.algafood.domain.repository.PermissaoRepository;

import lombok.Getter;

@Service
public class PermissaoService {

	@Autowired
	@Getter
	private PermissaoRepository permissaoRepository;
	
	@Transactional
	public List<Permissao> buscarTodos() {
		return this.permissaoRepository.findAll();
	}
	
	@Transactional
	public Permissao buscarPorId( Long id ) {
		return this.permissaoRepository.findById(id)
					.orElseThrow(() -> new PermissaoNaoEncontradaException(id));
	}
	
}
