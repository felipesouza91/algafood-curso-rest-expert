package com.felipe.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.felipe.algafood.domain.model.Restaurante;
import com.felipe.algafood.domain.repository.RestauranteRepository;
import com.felipe.algafood.domain.repository.RestauranteRepositoryQueries;
import com.felipe.algafood.infrastructure.repository.spec.RestauranteSpecs;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired @Lazy
	private RestauranteRepository restauranteRepository;

	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		return restauranteRepository.findAll(RestauranteSpecs.comFresteGratis().and(RestauranteSpecs.comNomeSemelhante(nome)));
	}
}
