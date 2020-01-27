package com.felipe.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.felipe.algafood.domain.model.Cidade;

@Repository
public interface CidadeRepository extends CustomJpaRepository<Cidade, Long>{

	@Query("from Cidade c join fetch c.estado")
	public List<Cidade> findAll();
}
