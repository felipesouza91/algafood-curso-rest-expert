package com.felipe.algafood.api.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.dto.converters.FotoProdutoDtoManager;
import com.felipe.algafood.api.dto.inputs.FotoProdutoInput;
import com.felipe.algafood.api.dto.model.FotoProdutoModel;
import com.felipe.algafood.domain.model.FotoProduto;
import com.felipe.algafood.domain.service.ProdutoFotoService;
import com.felipe.algafood.domain.service.ProdutoService;

@RestController
@RequestMapping("/restaurantes/{restId}/produtos/{prodId}/foto")
public class RestauranteProdutoFotoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private FotoProdutoDtoManager dtoManager;
	
	@Autowired
	private ProdutoFotoService fotoService;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizaFoto(@PathVariable Long restId, @PathVariable Long prodId,@Valid FotoProdutoInput produtoInput) throws IOException {
			var prod = produtoService.buscarPorIdRestauranteEProduto(restId, prodId);
			var foto = new FotoProduto();
			foto.setProduto(prod);
			foto.setContentType(produtoInput.getArquivo().getContentType());
			foto.setDescricao(produtoInput.getDescricao());
			foto.setNomeArquivo(produtoInput.getArquivo().getOriginalFilename());
			foto.setTamanho(produtoInput.getArquivo().getSize());
			return dtoManager.conveterToDtoModel(fotoService.salvar(foto, produtoInput.getArquivo().getInputStream()));
		
	}
}
