package com.felipe.algafood.api.v1.dto.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.v1.AlgaLinks;
import com.felipe.algafood.api.v1.controller.RestauranteController;
import com.felipe.algafood.api.v1.dto.inputs.RestauranteInput;
import com.felipe.algafood.api.v1.dto.model.RestauranteModel;
import com.felipe.algafood.domain.model.Cidade;
import com.felipe.algafood.domain.model.Cozinha;
import com.felipe.algafood.domain.model.Restaurante;

@Component
public class RestauranteDtoManager extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {
	
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;	
	
	public RestauranteDtoManager() {
		super(RestauranteController.class, RestauranteModel.class);
		// TODO Auto-generated constructor stub
	}


	@Override
	public RestauranteModel toModel(Restaurante object) {
		RestauranteModel model = createModelWithId(object.getId(), object);
		modelMapper.map(object, model);
		
		
		if (object.ativacaoPermitida()) {
			model.add(algaLinks.linkToRestauranteAtivo(model.getId(), "ativar"));
		}

		if (object.inativacaoPermitida()) {
			model.add(algaLinks.linkToRestauranteInativo(model.getId(), "invativar"));
		}

		if (object.aberturaPermitida()) {
			model.add(algaLinks.linkToRestauranteAbrir(model.getId(), "abrir"));
		}

		if (object.fechamentoPermitido()) {
			model.add(algaLinks.linkToRestauranteFechar(model.getId(), "fechar"));
		}
		model.add(algaLinks.linkToRestaurantes("restaurantes"));
		model.getCozinha().add(algaLinks.linkToCozinha(model.getCozinha().getId()));
		if(model.getEndereco() != null && model.getEndereco().getCidade() != null) {
			model.getEndereco().getCidade().add(algaLinks.linkToCidade(model.getEndereco().getCidade().getId()));	
		}
		model.add(algaLinks.linkToRestaurantesFormasPagamentos(model.getId(),"formas-pagamentos"));
		model.add(algaLinks.linkToRestauranteUsuarios(model.getId(),"responsaveis"));
		model.add(algaLinks.linkToProdutos(model.getId(),"produtos"));
		return model;
	}
	
	@Override
	public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities);
	}
	
	public Restaurante converterToDomainObject(RestauranteInput objectInput) {
		return modelMapper.map(objectInput, Restaurante.class);
	}
	
	public void copyToDomainObject(RestauranteInput objectInput, Restaurante object) {
		object.setCozinha(new Cozinha());
		if( object.getEndereco() != null ) {
			object.getEndereco().setCidade(new Cidade());	
		}
		modelMapper.map(objectInput, object);
	}
}
