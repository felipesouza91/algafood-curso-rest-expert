package com.felipe.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.felipe.algafood.domain.model.Grupo;

@Repository
public interface GrupoRepository extends CustomJpaRepository<Grupo, Long>{

}
