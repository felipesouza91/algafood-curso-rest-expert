package com.felipe.algafood.api.dto.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.AlgaLinks;
import com.felipe.algafood.api.controller.FormaPagamentoController;
import com.felipe.algafood.api.dto.inputs.FormaPagamentoInput;
import com.felipe.algafood.api.dto.model.FormaPagamentoModel;
import com.felipe.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDtoManager extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public FormaPagamentoDtoManager() {
		super(FormaPagamentoController.class, FormaPagamentoModel.class);
	}

	@Override
	public FormaPagamentoModel toModel(FormaPagamento entity) {
		FormaPagamentoModel model = createModelWithId(entity.getId(), entity);
		modelMapper.map(entity, model);
		model.add(algaLinks.linkToFormasPagamentos("formas-pagamentos"));
		return model;
	}
	
	@Override
	public CollectionModel<FormaPagamentoModel> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
		return super.toCollectionModel(entities).add(algaLinks.linkToFormasPagamentos());
	}

	public FormaPagamento converterToDomainObject(FormaPagamentoInput formaPagamentoInput) {
		return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
	}

	public void copyToDomainObject(FormaPagamentoInput objectInput, FormaPagamento object) {
		modelMapper.map(objectInput, object);
	}

	

}
