package com.felipe.algafood.domain.service;

import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream inputStream) {
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();
		String novoNomeArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
		Optional<FotoProduto> fotoProdutoOptinal = produtoRepository.findFotoById(restauranteId, produtoId);
		if (fotoProdutoOptinal.isPresent()) {
			produtoRepository.deleteFotoProduto(fotoProdutoOptinal.get());
		}
		foto.setNomeArquivo(novoNomeArquivo);
		foto = produtoRepository.saveFotoProduto(foto);
		produtoRepository.flush();
		NovaFoto novaFoto = NovaFoto.builder().nomeArquivo(foto.getNomeArquivo()).inputStream(inputStream).build();
		fotoStorage.armazenar(novaFoto);
		return foto;
	}
	
}
