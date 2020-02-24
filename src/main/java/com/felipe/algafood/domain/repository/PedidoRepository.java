package com.felipe.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.felipe.algafood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido>{

	@Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha	")
	public List<Pedido> findAll();
	
	public Optional<Pedido> findByCodigo(String codigo);
	
	public boolean gerenciarPedido(String codigoPedido, Long codigoReponsavel);
}
