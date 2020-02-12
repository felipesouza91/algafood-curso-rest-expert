package com.felipe.algafood.infrastructure.service.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.felipe.algafood.core.storage.StorageProperties;
import com.felipe.algafood.domain.exception.StorageException;
import com.felipe.algafood.domain.service.FotoStorageService;

public class S3FotoStorageService implements FotoStorageService {
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
		URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), caminhoArquivo);
		return FotoRecuperada.builder().url(url.toString()).build();
	}

	@Override
	public void armazenar(NovaFoto foto) {
		try {
			String caminhoArquivo = getCaminhoArquivo(foto.getNomeArquivo());
			var objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(foto.getContentType());
			var putObjectRequest = new PutObjectRequest(storageProperties.getS3().getBucket(),
															caminhoArquivo,
																foto.getInputStream(),
																	objectMetadata ).withCannedAcl(CannedAccessControlList.PublicRead);
			
			amazonS3.putObject(putObjectRequest);
		}catch (Exception e) {
			throw new StorageException("Não foi possivel enviar arquivo para Amazon S3", e);
		}
		
	}

	@Override
	public void removerArquivo(String nomeArquivo) {
		try {
			var deleteObjectRequest = new DeleteObjectRequest(storageProperties.getS3().getBucket(), getCaminhoArquivo(nomeArquivo));
			amazonS3.deleteObject(deleteObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Não foi possivel excluir arquivo para Amazon S3", e);
		}
	}
	
	private String getCaminhoArquivo(String nomeArquivo) {
		// TODO Auto-generated method stub
		return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(),nomeArquivo);
	}


	
}
