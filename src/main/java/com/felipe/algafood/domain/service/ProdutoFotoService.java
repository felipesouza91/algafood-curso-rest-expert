package com.felipe.algafood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.algafood.domain.exception.FotoProdutoNaoEncontradaException;
import com.felipe.algafood.domain.model.FotoProduto;
import com.felipe.algafood.domain.repository.ProdutoRepository;
import com.felipe.algafood.domain.service.FotoStorageService.NovaFoto;

import lombok.Getter;

@Service
public class ProdutoFotoService {
	
	@Getter
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	public FotoProduto buscarFotoProtudo(Long restId, Long prodId) {
		return this.produtoRepository.findFotoById(restId, prodId).orElseThrow(()-> new FotoProdutoNaoEncontradaException(prodId, restId));
	}

	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream inputStream) {
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();
		
		String novoNomeArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
		
		Optional<FotoProduto> fotoProdutoOptinal = produtoRepository.findFotoById(restauranteId, produtoId);
		
		String nomeArquivo = null;
		
		if (fotoProdutoOptinal.isPresent()) {
			nomeArquivo = fotoProdutoOptinal.get().getNomeArquivo();
			produtoRepository.deleteFotoProduto(fotoProdutoOptinal.get());
			
		}
		
		foto.setNomeArquivo(novoNomeArquivo);
		
		foto = produtoRepository.saveFotoProduto(foto);
		produtoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeArquivo(foto.getNomeArquivo())
				.inputStream(inputStream)
				.contentType(foto.getContentType())
				.build();
		
		fotoStorage.substituir(nomeArquivo, novaFoto);
		
		return foto;
	}
	
	@Transactional
	public void excluir(FotoProduto foto) {
			produtoRepository.deleteFotoProduto(foto);
			produtoRepository.flush();
			fotoStorage.removerArquivo(foto.getNomeArquivo());
	}
	
}
