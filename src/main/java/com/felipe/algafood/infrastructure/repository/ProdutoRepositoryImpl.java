package com.felipe.algafood.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.algafood.domain.model.FotoProduto;
import com.felipe.algafood.domain.repository.ProdutoRepositoryQueries;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public FotoProduto saveFotoProduto(FotoProduto foto) {
		return manager.merge(foto);
	}

	@Transactional
	@Override
	public void deleteFotoProduto(FotoProduto foto) {
		manager.remove(foto);
	}

}
