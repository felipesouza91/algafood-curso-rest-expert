package com.felipe.algafood.api.dto.converters;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.controller.CidadeController;
import com.felipe.algafood.api.controller.EstadoController;
import com.felipe.algafood.api.dto.inputs.CidadeInput;
import com.felipe.algafood.api.dto.model.CidadeModel;
import com.felipe.algafood.domain.model.Cidade;
import com.felipe.algafood.domain.model.Estado;

@Component
public class CidadeDtoManager extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {
	
	

	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeDtoManager() {
		super(CidadeController.class, CidadeModel.class);
	}

	@Override
	public CidadeModel toModel(Cidade cidade) {
		CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);
		modelMapper.map(cidade, cidadeModel);
		cidadeModel.add(linkTo(methodOn(CidadeController.class).buscar()).withRel("cidades"));
		
		cidadeModel.getEstado().add(linkTo(methodOn(EstadoController.class).buscarPorId(cidadeModel.getEstado().getId())).withSelfRel());
		
		return cidadeModel;
	}
	
	@Override
	public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities).add(linkTo(CidadeController.class).withSelfRel());
	}
	
	public Cidade converterToDomainObject(CidadeInput cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeInput objectInput, Cidade object) {
		object.setEstado(new Estado());
		modelMapper.map(objectInput, object);
	}

}
