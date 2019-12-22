package com.felipe.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.algafood.domain.exception.EntidadeEmUsoException;
import com.felipe.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.felipe.algafood.domain.model.FormaPagamento;
import com.felipe.algafood.domain.repository.FormaPagamentoRepository;

import lombok.Getter;

@Service
public class FormaPagamentoService {

	@Autowired
	@Getter
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Transactional
	public List<FormaPagamento> findAll(){
		return this.formaPagamentoRepository.findAll();
	}
	
	@Transactional
	public FormaPagamento buscarById(Long id) {
		return this.formaPagamentoRepository.findById(id)
				.orElseThrow(() -> new FormaPagamentoNaoEncontradaException(id));
	}
	
	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return this.formaPagamentoRepository.save(formaPagamento);
	}
	
	@Transactional
	public FormaPagamento atualizar(Long id, FormaPagamento formaPagamento) {
		this.buscarById(id);
		return this.salvar(formaPagamento);
	}
	
	@Transactional
	public void excluir(Long id) {
		this.buscarById(id);
		try {
			this.formaPagamentoRepository.deleteById(id);
			this.formaPagamentoRepository.flush();
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Forma de Pagamento de codigo %d não pode ser removida, pois esta em uso", id));
		}
	}
	
}
