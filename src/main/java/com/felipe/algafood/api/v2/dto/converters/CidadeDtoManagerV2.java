package com.felipe.algafood.api.v2.dto.converters;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.v2.AlgaLinksV2;
import com.felipe.algafood.api.v2.controller.CidadeControllerV2;
import com.felipe.algafood.api.v2.dto.inputs.CidadeInputV2;
import com.felipe.algafood.api.v2.dto.model.CidadeModelV2;
import com.felipe.algafood.domain.model.Cidade;

@Component
public class CidadeDtoManagerV2 extends RepresentationModelAssemblerSupport<Cidade, CidadeModelV2> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinksV2 algaLinks;
	
	public CidadeDtoManagerV2() {
		super(CidadeControllerV2.class, CidadeModelV2.class);
	}

	@Override
	public CidadeModelV2 toModel(Cidade cidade) {
		CidadeModelV2 cidadeModel = createModelWithId(cidade.getId(), cidade);
		modelMapper.map(cidade, cidadeModel);
		cidadeModel.add(algaLinks.LinkToCidades("cidades"));
		
		return cidadeModel;
	}
	
	@Override
	public CollectionModel<CidadeModelV2> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities).add(algaLinks.LinkToCidades());
	}
	
	public Cidade converterToDomainObject(CidadeInputV2 cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}

}
