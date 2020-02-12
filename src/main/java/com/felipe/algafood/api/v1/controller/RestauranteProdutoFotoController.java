package com.felipe.algafood.api.v1.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.felipe.algafood.api.v1.docs.RestauranteFotoProdutoControllerOpenApi;
import com.felipe.algafood.api.v1.dto.converters.FotoProdutoDtoManager;
import com.felipe.algafood.api.v1.dto.inputs.FotoProdutoInput;
import com.felipe.algafood.api.v1.dto.model.FotoProdutoModel;
import com.felipe.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.felipe.algafood.domain.model.FotoProduto;
import com.felipe.algafood.domain.service.FotoStorageService;
import com.felipe.algafood.domain.service.FotoStorageService.FotoRecuperada;
import com.felipe.algafood.domain.service.ProdutoFotoService;
import com.felipe.algafood.domain.service.ProdutoService;

@RestController
@RequestMapping(path ="/v1/restaurantes/{restId}/produtos/{prodId}/foto", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoFotoController implements RestauranteFotoProdutoControllerOpenApi{
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private FotoProdutoDtoManager dtoManager;
	
	@Autowired
	private ProdutoFotoService fotoService;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public FotoProdutoModel buscarFoto(@PathVariable Long restId,@PathVariable Long prodId) {
		return dtoManager.toModel(fotoService.buscarFotoProtudo(restId, prodId));
	}
	
	@GetMapping(produces = MediaType.ALL_VALUE)
	public ResponseEntity<?> servirFoto(@PathVariable Long restId,@PathVariable Long prodId,
		 	@RequestHeader(name="accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
		try {
			FotoProduto fotoProduto= fotoService.buscarFotoProtudo(restId, prodId);
			MediaType mediaType = MediaType.parseMediaType(fotoProduto.getContentType());
			List<MediaType> mediaTypeAcceita = MediaType.parseMediaTypes(acceptHeader);
			this.verificarCompatibilidadeMediaType(mediaType, mediaTypeAcceita);
			FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(fotoProduto.getNomeArquivo());
			
			if (fotoRecuperada.temUrl()) {
				return ResponseEntity.status(HttpStatus.FOUND)
							.header(HttpHeaders.LOCATION, fotoRecuperada.getUrl()).build();
			} else {
				return ResponseEntity.ok()
						.contentType(mediaType)
						.body(new InputStreamResource(fotoRecuperada.getInputStream()));	
			}
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	} 

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizaFoto(@PathVariable Long restId, @PathVariable Long prodId,@Valid FotoProdutoInput produtoInput, 
			@RequestPart(required = true)MultipartFile arquivo) throws IOException {
			var prod = produtoService.buscarPorIdRestauranteEProduto(restId, prodId);
			var foto = new FotoProduto();
			foto.setProduto(prod);
			foto.setContentType(arquivo.getContentType());
			foto.setDescricao(produtoInput.getDescricao());
			foto.setNomeArquivo(arquivo.getOriginalFilename());
			foto.setTamanho(arquivo.getSize());
			return dtoManager.toModel(fotoService.salvar(foto, arquivo.getInputStream()));
		
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long restId, @PathVariable Long prodId) {
		FotoProduto fotoProduto= fotoService.buscarFotoProtudo(restId, prodId);
		this.fotoService.excluir(fotoProduto);
	}
	
	private void verificarCompatibilidadeMediaType(MediaType mediaType, List<MediaType> mediaTypeAcceita) throws HttpMediaTypeNotAcceptableException {
		// TODO Auto-generated method stub
		boolean compativel = mediaTypeAcceita.stream().anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaType));
		if(!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypeAcceita);
		}
	}
}
