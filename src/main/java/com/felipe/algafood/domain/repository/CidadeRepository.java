package com.felipe.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.felipe.algafood.domain.model.Cidade;

@Repository
public interface CidadeRepository extends CustomJpaRepository<Cidade, Long>{

}
