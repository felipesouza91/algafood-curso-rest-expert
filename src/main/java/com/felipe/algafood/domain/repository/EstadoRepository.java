package com.felipe.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.felipe.algafood.domain.model.Estado;

@Repository
public interface EstadoRepository extends CustomJpaRepository<Estado, Long>{

}
