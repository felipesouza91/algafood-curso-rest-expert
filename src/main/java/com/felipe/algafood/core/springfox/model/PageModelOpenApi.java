package com.felipe.algafood.core.springfox.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("PageModel")
public class PageModelOpenApi {
	
	@ApiModelProperty(example = "10", value = "Numero de registro por paginas")
	private int size;
	
	@ApiModelProperty(example = "55", value = "Total de registro por paginas")
	private int totalElements;
	
	@ApiModelProperty(example = "3", value = "Total de paginas disponiveis")
	private int totalPages;
	
	@ApiModelProperty(example = "0", value = "Numero da pagina (começa em 0)")
	private int number;
}
