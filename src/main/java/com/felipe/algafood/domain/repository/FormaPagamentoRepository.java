package com.felipe.algafood.domain.repository;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.felipe.algafood.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends CustomJpaRepository<FormaPagamento, Long> {
	
	@Query("select max(dataAtualizacao) from FormaPagamento")
	public OffsetDateTime getDataUltimaAtualizacao();

	@Query("select max(dataAtualizacao) from FormaPagamento where id =:id")
	public OffsetDateTime getDataUltimaAtualizacaoById(Long id);
}
