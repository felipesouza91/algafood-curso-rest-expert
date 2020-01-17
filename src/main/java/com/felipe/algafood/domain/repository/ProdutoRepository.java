package com.felipe.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.felipe.algafood.domain.model.FotoProduto;
import com.felipe.algafood.domain.model.Produto;
import com.felipe.algafood.domain.model.Restaurante;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>, ProdutoRepositoryQueries{
	
	@Query("from Produto where restaurante.id=:restaurante and id=:produto")
	public Optional<Produto> findByIdRestAndIdProduto(@Param("restaurante") Long idRestaurante, @Param("produto") Long idProduto);

	public List<Produto> findByRestaurante(Restaurante restaurante);
	
	@Query("from Produto p where p.ativo=true and p.restaurante = :restaurante")
	public List<Produto> findAtivoByRestaurante(@Param("restaurante") Restaurante restaurante);
	
	@Query("select f from FotoProduto f join f.produto p where p.restaurante.id = :restauranteId and f.produto.id = :produtoId")
	public Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId);
	
}
