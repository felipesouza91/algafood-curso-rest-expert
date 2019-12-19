package com.felipe.algafood.domain.repository;

import java.util.List;

import com.felipe.algafood.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {

	List<Restaurante> findComFreteGratis(String nome);
}
