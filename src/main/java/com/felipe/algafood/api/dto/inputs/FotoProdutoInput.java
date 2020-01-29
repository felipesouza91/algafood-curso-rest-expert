package com.felipe.algafood.api.dto.inputs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.felipe.algafood.core.validation.FileContentType;
import com.felipe.algafood.core.validation.FileSize;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInput {
	
	@NotNull
	@FileSize(max = "500KB")
	@FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	@ApiModelProperty(hidden = true)
	private MultipartFile arquivo;
	
	@NotBlank
	@ApiModelProperty(value = "Descriçã da foto", required = true, example = "Teste")
	private String descricao;

}
