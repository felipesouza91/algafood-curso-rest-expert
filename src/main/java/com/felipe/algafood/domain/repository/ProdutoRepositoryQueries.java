package com.felipe.algafood.domain.repository;

import com.felipe.algafood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

	public FotoProduto saveFotoProduto(FotoProduto foto);
	
	public void deleteFotoProduto(FotoProduto foto);

}
