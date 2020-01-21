package com.felipe.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {
	
	public FotoRecuperada recuperar(String nomeArquivo);

	public void armazenar(NovaFoto foto);
	
	public void removerArquivo(String nomeArquivo);
	
	default void substituir(String nomeArquivo, NovaFoto foto) {
		this.armazenar(foto);
		if (nomeArquivo != null ) {
			this.removerArquivo(nomeArquivo);
		}
	}

	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID() +"_"+ nomeOriginal;
	}
	
	@Getter
	@Builder
	class NovaFoto {
		private String nomeArquivo;
		private String contentType;
		private InputStream inputStream;
	}
	
	@Getter
	@Builder
	class FotoRecuperada {
		private InputStream inputStream;
		private String url;
		
		public boolean temUrl() {
			return url != null;
		}
		
		public boolean temInputStream() {
			return inputStream != null;
		}
	}
	
}
