package com.felipe.algafood.core.openapi;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.felipe.algafood.api.dto.model.CozinhaModel;
import com.felipe.algafood.api.exceptionhandler.Problem;
import com.felipe.algafood.core.openapi.model.CozinhasModelOpenApi;
import com.felipe.algafood.core.openapi.model.PageableModelOpenApi;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer{
	
	private final ModelRef modelRef = new ModelRef("Problema");
	
	@Bean
	public Docket apiDocket() {
		TypeResolver typeResolver = new TypeResolver();
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.felipe.algafood.api"))
				.paths(PathSelectors.any())
					.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, this.globalGetResponseMethos() )
				.globalResponseMessage(RequestMethod.POST, this.globalPostResponseMethos())
				.globalResponseMessage(RequestMethod.PUT, this.globalPutResponseMethos())
				.globalResponseMessage(RequestMethod.DELETE, this.globalDeleteResponseMethos())
				.additionalModels(typeResolver.resolve(Problem.class))
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Page.class, CozinhaModel.class), CozinhasModelOpenApi.class))
				.apiInfo(apiInfo()) 
				.tags(new Tag("Cidades", "Gerencia a cidade"), new Tag("Grupos", "Gerencia de grupos"));
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("AlgaFood Api").version("v1")
				.description("Api aberta apara clientes e restaurantes")
				.contact(new Contact("Felipe Souza", "felipedb91@hotmail.com", "felipedb91@hotmail.com"))
				.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	
	private List<ResponseMessage> globalGetResponseMethos() {
		return Arrays.asList(
				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.responseModel(modelRef)
					.message("Error interno do servidor").build(),
				new ResponseMessageBuilder().code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso não possui representação que poderia ser aceito pelo consumidor").build()
				);
	}

	private List<ResponseMessage> globalPostResponseMethos() {
		return Arrays.asList(
				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.responseModel(modelRef)
					.message("Error interno do servidor").build(),
				new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
					.responseModel(modelRef)
					.message("A solicitação enviada possui erros").build(),
				new ResponseMessageBuilder().code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso não possui representação que poderia ser aceito pelo consumidor").build(),
				new ResponseMessageBuilder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
					.responseModel(modelRef)
					.message("O tipo de requisição enviado não e suportado").build()
			);
	}
	
	private List<ResponseMessage> globalPutResponseMethos() {
		return Arrays.asList(
				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.responseModel(modelRef)
					.message("Error interno do servidor").build(),
				new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
					.responseModel(modelRef)
					.message("A solicitação enviada possui erros").build(),
				new ResponseMessageBuilder().code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso não possui representação que poderia ser aceito pelo consumidor").build(),
				new ResponseMessageBuilder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
					.responseModel(modelRef)
					.message("O tipo de requisição enviado não e suportado").build()
			);
	}
	
	private List<ResponseMessage> globalDeleteResponseMethos() {
		return Arrays.asList(
				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.responseModel(modelRef)
					.message("Error interno do servidor").build(),
				new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
					.responseModel(modelRef)
					.message("A solicitação enviada possui erros").build()
			);
	}
}
