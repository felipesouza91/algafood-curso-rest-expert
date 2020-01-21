package com.felipe.algafood.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.felipe.algafood.core.email.EmailProperties.TipoEmail;
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
		
		if(TipoEmail.SMTP.equals(emailProperties.getImpl())) {
			return new SmtpEnvioEmailService();
		} else if( TipoEmail.SANDBOX.equals(emailProperties.getImpl())) {
			return new SandBoxEnvioEmailService();
		} else {
			return new FakeEnvioEmailService();
		}
	}
}
