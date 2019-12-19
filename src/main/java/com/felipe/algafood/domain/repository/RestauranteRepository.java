package com.felipe.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.felipe.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
		JpaSpecificationExecutor<Restaurante>, RestauranteRepositoryQueries{

	
	@Query("from Restaurante r join r.cozinha")
	List<Restaurante> findAll();
}
