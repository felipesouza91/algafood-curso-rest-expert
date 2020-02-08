package com.felipe.algafood.api.dto.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.AlgaLinks;
import com.felipe.algafood.api.controller.RestauranteController;
import com.felipe.algafood.api.dto.model.resumo.RestauranteApenasNomeModel;
import com.felipe.algafood.domain.model.Restaurante;

@Component
public class RestauranteApenasNomeDtoManager extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeModel>{
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AlgaLinks algaLinks;

	public RestauranteApenasNomeDtoManager() {
		super(RestauranteController.class, RestauranteApenasNomeModel.class);
	}

	@Override
	public RestauranteApenasNomeModel toModel(Restaurante entity) {
		RestauranteApenasNomeModel model = createModelWithId(entity.getId(), entity);
		mapper.map(entity, model);
		model.add(algaLinks.linkToRestaurantes("restaurantes"));
		return model;
	}

	@Override
	public CollectionModel<RestauranteApenasNomeModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities).add(algaLinks.linkToRestaurantes());
	}
}
