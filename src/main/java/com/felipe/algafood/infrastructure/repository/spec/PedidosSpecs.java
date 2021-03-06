package com.felipe.algafood.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.felipe.algafood.domain.filter.PedidoFilter;
import com.felipe.algafood.domain.model.Pedido;

public class PedidosSpecs {
	
	public static Specification<Pedido> findWithFilter(PedidoFilter filtro){
		return (root, query, criteriaBuilder ) -> {
			if(Pedido.class.equals(query.getResultType())) {
				root.fetch("restaurante").fetch("cozinha");
				root.fetch("cliente");
			}
			
			var predicates = new ArrayList<Predicate>();
			
			if(filtro.getClienteId() != null ) {
				predicates.add(criteriaBuilder.equal(root.get("cliente"), filtro.getClienteId() ));
			}
			if(filtro.getRestauranteId() != null ) {
				predicates.add(criteriaBuilder.equal(root.get("restaurante"), filtro.getRestauranteId()));
			}
			if(filtro.getDataCriacaoInicio() != null ) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
			}
			if(filtro.getDataCriacaoFim() != null ) {
				predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
