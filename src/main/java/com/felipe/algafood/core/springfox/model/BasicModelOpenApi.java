package com.felipe.algafood.core.springfox.model;

import java.util.List;

import org.springframework.hateoas.Links;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicModelOpenApi<T> {
	
	private EmbeddedModelApi<T> _embedded;
	private Links links;
	
	@SuppressWarnings("hiding")
	@Data
	@ApiModel("Embedded")
	public class EmbeddedModelApi<T> {
		
		private List<T> values;
		
		private Links links;
	}
}
