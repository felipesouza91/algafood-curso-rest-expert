package com.felipe.algafood.api.dto.converters;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.AlgaLinks;
import com.felipe.algafood.api.controller.EstadoController;
import com.felipe.algafood.api.dto.inputs.EstadoInput;
import com.felipe.algafood.api.dto.model.EstadoModel;
import com.felipe.algafood.domain.model.Estado;

@Component
public class EstadoDtoManager extends RepresentationModelAssemblerSupport<Estado, EstadoModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public EstadoDtoManager() {
		super(EstadoController.class, EstadoModel.class);
	}

	@Override
	public EstadoModel toModel(Estado estado) {
		EstadoModel estadoModel = createModelWithId(estado.getId(), estado);
		modelMapper.map(estado, estadoModel);
		estadoModel.add(algaLinks.linkToEstados("estados"));
		return estadoModel;
	}

	@Override
	public CollectionModel<EstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {
		return super.toCollectionModel(entities).add(linkTo(EstadoController.class).withSelfRel());
	}

	public Estado converterToDomainObject(EstadoInput objectInput) {
		return modelMapper.map(objectInput, Estado.class);
	}

	public void copyToDomainObject(EstadoInput objectInput, Estado object) {
		modelMapper.map(objectInput, object);
		
	}
}
