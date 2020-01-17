package com.felipe.algafood.infrastructure.service;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.felipe.algafood.domain.exception.StorageException;
import com.felipe.algafood.domain.service.FotoStorageService;

@Service
public class LocalFotoStorageService implements FotoStorageService{
	
	@Value("${algafood.storage.local.foto-path}")
	private Path diretorioPath;

	@Override
	public void armazenar(NovaFoto foto) {
		try {
			Path arquivoPath = getArquivoPath(foto.getNomeArquivo());
			FileCopyUtils.copy(foto.getInputStream(), Files.newOutputStream(arquivoPath));
		} catch (Exception e) {
			throw new StorageException("Não foi possivel armazenar arquivo", e.getCause());
		}
	}
	
	
	private Path getArquivoPath(String nomeArquivo) {
		return diretorioPath.resolve(Path.of(nomeArquivo));
	}


}
