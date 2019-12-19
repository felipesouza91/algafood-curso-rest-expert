package com.felipe.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.felipe.algafood.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends CustomJpaRepository<FormaPagamento, Long> {

}
