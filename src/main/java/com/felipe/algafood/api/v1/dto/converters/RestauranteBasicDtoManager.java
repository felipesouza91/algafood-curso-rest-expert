package com.felipe.algafood.api.v1.dto.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.v1.AlgaLinks;
import com.felipe.algafood.api.v1.controller.RestauranteController;
import com.felipe.algafood.api.v1.dto.model.RestauranteBasicModel;
import com.felipe.algafood.domain.model.Restaurante;

@Component
public class RestauranteBasicDtoManager extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicModel>{
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AlgaLinks algaLinks;

	public RestauranteBasicDtoManager() {
		super(RestauranteController.class, RestauranteBasicModel.class);

	}

	@Override
	public RestauranteBasicModel toModel(Restaurante entity) {
		RestauranteBasicModel model = createModelWithId(entity.getId(), entity);
		mapper.map(entity, model);
		model.getCozinha().add(algaLinks.linkToCozinha(model.getCozinha().getId()));
		model.add(algaLinks.linkToRestaurantes("restaurantes"));
		return model;
	}
	
	@Override
	public CollectionModel<RestauranteBasicModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		// TODO Auto-generated method stub
		return super.toCollectionModel(entities).add(algaLinks.linkToRestaurantes());
	}

}
