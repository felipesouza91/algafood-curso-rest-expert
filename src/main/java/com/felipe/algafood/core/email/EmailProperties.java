package com.felipe.algafood.core.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {

	private String remetente;
	
	private TipoEmail impl;
	
	private SandBox sandBox = new SandBox();	
	
	
	public enum TipoEmail {
		FAKE,
		SANDBOX,
		SMTP;
	}
	
	@Getter
	@Setter
	public class SandBox {
		
		private String destinatario;
	}
}
	