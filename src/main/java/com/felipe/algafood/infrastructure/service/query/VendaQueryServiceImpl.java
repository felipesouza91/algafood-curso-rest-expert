package com.felipe.algafood.infrastructure.service.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.felipe.algafood.domain.filter.VendaDiariaFilter;
import com.felipe.algafood.domain.model.Pedido;
import com.felipe.algafood.domain.model.StatusPedido;
import com.felipe.algafood.domain.model.aggregation.VendaDiaria;
import com.felipe.algafood.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<VendaDiaria> consultarVendaDiaria(VendaDiariaFilter vendaDiariaFilter, String timeOffset) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(VendaDiaria.class);
		var root = query.from(Pedido.class);
		var predicates = new ArrayList<Predicate>();
		
		var functionConvertTzDataCriacao = builder.function("convert_tz", Date.class, root.get("dataCriacao"),
				builder.literal("+00:00"),builder.literal(timeOffset));
		
		var functionDataDataCriacao = builder.function("date", Date.class, functionConvertTzDataCriacao);
		
		predicates.add(root.get("status").in(StatusPedido.ENTREGUE, StatusPedido.CONFIRMADO));
		if(vendaDiariaFilter.getRestId() != null ) {
			predicates.add(builder.equal(root.get("restaurante"), vendaDiariaFilter.getRestId()));
		}
		if(vendaDiariaFilter.getDataCriacaoInicio() != null ) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), vendaDiariaFilter.getDataCriacaoInicio()));
		}
		if(vendaDiariaFilter.getDataCriacaoFim() != null ) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), vendaDiariaFilter.getDataCriacaoFim()));
		}
		var selection = builder.construct( VendaDiaria.class,
				functionDataDataCriacao, builder.count(root.get("id")), builder.sum(root.get("valorTotal")));
		
		query.select(selection);
		
		query.where(predicates.toArray(new Predicate[0]));
		
		query.groupBy(functionDataDataCriacao);
		
		return manager.createQuery(query).getResultList();
	}

}
