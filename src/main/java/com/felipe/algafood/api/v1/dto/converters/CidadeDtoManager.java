package com.felipe.algafood.api.v1.dto.converters;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.v1.AlgaLinks;
import com.felipe.algafood.api.v1.controller.CidadeController;
import com.felipe.algafood.api.v1.dto.inputs.CidadeInput;
import com.felipe.algafood.api.v1.dto.model.CidadeModel;
import com.felipe.algafood.domain.model.Cidade;
import com.felipe.algafood.domain.model.Estado;

@Component
public class CidadeDtoManager extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public CidadeDtoManager() {
		super(CidadeController.class, CidadeModel.class);
	}

	@Override
	public CidadeModel toModel(Cidade cidade) {
		CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);
		modelMapper.map(cidade, cidadeModel);
		cidadeModel.add(algaLinks.LinkToCidades("cidades"));
		
		cidadeModel.getEstado().add(algaLinks.linkToEstado(cidadeModel.getEstado().getId()));
		
		return cidadeModel;
	}
	
	@Override
	public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities).add(algaLinks.LinkToCidades());
	}
	
	public Cidade converterToDomainObject(CidadeInput cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeInput objectInput, Cidade object) {
		object.setEstado(new Estado());
		modelMapper.map(objectInput, object);
	}

}
