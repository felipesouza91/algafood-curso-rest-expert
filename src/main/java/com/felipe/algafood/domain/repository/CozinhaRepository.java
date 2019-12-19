package com.felipe.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.felipe.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

}
