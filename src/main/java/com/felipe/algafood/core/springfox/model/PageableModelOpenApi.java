package com.felipe.algafood.core.springfox.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Pageable")
@Setter
@Getter
public class PageableModelOpenApi {

	@ApiModelProperty(example = "0", value = "Numero da pagina( começa em 0)")
	private int page;
	
	@ApiModelProperty(example = "10", value = "Quantidade de elementos por pagina")
	private int size;

	@ApiModelProperty(example = "nome,asc", value = "Nome da propriedade ordenação")
	private List<String> sort;
}
