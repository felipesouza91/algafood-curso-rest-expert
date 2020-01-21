package com.felipe.algafood.infrastructure.service.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.felipe.algafood.core.storage.StorageProperties;
import com.felipe.algafood.domain.exception.StorageException;
import com.felipe.algafood.domain.service.FotoStorageService;

public class LocalFotoStorageService implements FotoStorageService {
	
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void armazenar(NovaFoto foto) {
		try {
			Path arquivoPath = getArquivoPath(foto.getNomeArquivo());
			FileCopyUtils.copy(foto.getInputStream(), Files.newOutputStream(arquivoPath));
		} catch (Exception e) {
			throw new StorageException("Não foi possivel armazenar arquivo", e.getCause());
		}
	}

	@Override
	public void removerArquivo(String nomeArquivo) {
		try {
			Path arquivoPath = getArquivoPath(nomeArquivo);
			Files.deleteIfExists(arquivoPath);
		} catch (IOException e) {
			throw new StorageException("Não foi excluir arquivo", e.getCause());
		}
	}
	
	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		try {
			Path arquivoPath = getArquivoPath(nomeArquivo);
			return FotoRecuperada.builder().inputStream(Files.newInputStream(arquivoPath)).build(); 
		} catch (IOException e) {
			throw new StorageException("Não foi excluir arquivo", e.getCause());
		}
	}
	
	private Path getArquivoPath(String nomeArquivo) {
		return storageProperties.getLocal().getFotoPath().resolve(Path.of(nomeArquivo));
	}


	
}
