package com.felipe.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {

	void armazenar(NovaFoto foto);
	
	@Getter
	@Builder
	class NovaFoto {
		private String nomeArquivo;
		
		private InputStream inputStream;
	}
	
	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID() +"_"+ nomeOriginal;
	}
}
