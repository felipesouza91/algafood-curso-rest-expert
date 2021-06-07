package com.felipe.algafood.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.felipe.algafood.domain.service.EnvioEmailService;
import com.felipe.algafood.infrastructure.service.email.FakeEnvioEmailService;
import com.felipe.algafood.infrastructure.service.email.SandBoxEnvioEmailService;
import com.felipe.algafood.infrastructure.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {

	@Autowired
	private EmailProperties emailProperties;
	
	@Bean
	public EnvioEmailService envioEmailService() {
		switch (emailProperties.getImpl()) {
			case FAKE:
				return new FakeEnvioEmailService();
			case SMTP:
				return new SmtpEnvioEmailService();
			case SANDBOX:
				return new SandBoxEnvioEmailService();
			default:
				return null;
		}
	}
}
