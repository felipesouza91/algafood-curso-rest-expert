package com.felipe.algafood.core.springfox.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.felipe.algafood.api.v1.dto.model.FormaPagamentoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FormasPagamentosModel")
public class FormasPagamentosModelOpenApi {

	private EmbeddedModelApi _embedded;
	private Links _links;
	
	@Data
	@ApiModel("FormasPagamentosEmbeddedModel")
	public class EmbeddedModelApi {
		private List<FormaPagamentoModel> formasPagamentos;
	}
}
