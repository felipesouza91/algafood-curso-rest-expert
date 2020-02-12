package com.felipe.algafood.core.springfox;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.felipe.algafood.api.exceptionhandler.Problem;
import com.felipe.algafood.api.v1.dto.model.CidadeModel;
import com.felipe.algafood.api.v1.dto.model.CozinhaModel;
import com.felipe.algafood.api.v1.dto.model.EstadoModel;
import com.felipe.algafood.api.v1.dto.model.FormaPagamentoModel;
import com.felipe.algafood.api.v1.dto.model.GrupoModel;
import com.felipe.algafood.api.v1.dto.model.PermissaoModel;
import com.felipe.algafood.api.v1.dto.model.ProdutoModel;
import com.felipe.algafood.api.v1.dto.model.RestauranteBasicModel;
import com.felipe.algafood.api.v1.dto.model.UsuarioModel;
import com.felipe.algafood.api.v1.dto.model.resumo.PedidoResumoModel;
import com.felipe.algafood.api.v2.dto.model.CidadeModelV2;
import com.felipe.algafood.core.springfox.model.CidadesModelOpenApi;
import com.felipe.algafood.core.springfox.model.CidadesModelV2OpenApi;
import com.felipe.algafood.core.springfox.model.CozinhasModelOpenApi;
import com.felipe.algafood.core.springfox.model.EstadosModelOpenApi;
import com.felipe.algafood.core.springfox.model.FormasPagamentosModelOpenApi;
import com.felipe.algafood.core.springfox.model.GruposModelOpenApi;
import com.felipe.algafood.core.springfox.model.LinksModelOpenApi;
import com.felipe.algafood.core.springfox.model.PageableModelOpenApi;
import com.felipe.algafood.core.springfox.model.PedidosResumoModelOpenApi;
import com.felipe.algafood.core.springfox.model.PermissoesModelOpenApi;
import com.felipe.algafood.core.springfox.model.ProdutosModelOpenApi;
import com.felipe.algafood.core.springfox.model.RestaurantesModelOpenApi;
import com.felipe.algafood.core.springfox.model.UsuariosModelOpenApi;

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
	
	private TypeResolver typeResolver = new TypeResolver();
	
	@Bean
	public Docket apiDocketV1() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("v1")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.felipe.algafood.api"))
				.paths(PathSelectors.ant("/v1/**"))
					.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, this.globalGetResponseMethos() )
				.globalResponseMessage(RequestMethod.POST, this.globalPostResponseMethos())
				.globalResponseMessage(RequestMethod.PUT, this.globalPutResponseMethos())
				.globalResponseMessage(RequestMethod.DELETE, this.globalDeleteResponseMethos())
				/*
				 * Para fins de estudo
				 * .globalOperationParameters(Arrays.asList(
						new ParameterBuilder().name("campos")
						.description("Nomes das propriedas para filtrar na resposta separados por virgula")
						.parameterType("query")
						.modelRef(new ModelRef("String")).build()
						))*/ 
				.additionalModels(typeResolver.resolve(Problem.class))
				.ignoredParameterTypes(ServletWebRequest.class, URI.class, URL.class,URLStreamHandler.class, Resource.class, File.class,
						InputStream.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class,LinksModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(PagedModel.class, CozinhaModel.class), CozinhasModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(PagedModel.class, PedidoResumoModel.class), PedidosResumoModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, CidadeModel.class), CidadesModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, EstadoModel.class), EstadosModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, FormaPagamentoModel.class), FormasPagamentosModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, PermissaoModel.class), PermissoesModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, ProdutoModel.class), ProdutosModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, RestauranteBasicModel.class), RestaurantesModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, UsuarioModel.class), UsuariosModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, GrupoModel.class), GruposModelOpenApi.class))
				.apiInfo(apiInfoV1()) 
				.tags(new Tag("Cidades", "Gerencia a cidade"),
						new Tag("Grupos", "Gerencia de grupos"),
						new Tag("Cozinhas", "Gerencia de cozinhas"),
						new Tag("Formas Pagamentos", "Gerencia de formas pagamentos"),
						new Tag("Pedidos", "Gerencia de pedidos"),
						new Tag("Restaurantes", "Gerencia de restaurante"),
						new Tag("Estados", "Gerencia de estados"),
						new Tag("Produtos", "Gerencia de produtos"),
						new Tag("Usuarios", "Gerencia de usuarios"),
						new Tag("Estatisticas", "Gerencia de estatisticas"),
						new Tag("Permissoes", "Listar Permissoes"),
						new Tag("Root", "Lista os links da api"));
	}
	
	@Bean
	public Docket apiDocketV2() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("v2")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.felipe.algafood.api"))
				.paths(PathSelectors.ant("/v2/**"))
					.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, this.globalGetResponseMethos() )
				.globalResponseMessage(RequestMethod.POST, this.globalPostResponseMethos())
				.globalResponseMessage(RequestMethod.PUT, this.globalPutResponseMethos())
				.globalResponseMessage(RequestMethod.DELETE, this.globalDeleteResponseMethos())
				/*
				 * Para fins de estudo
				 * .globalOperationParameters(Arrays.asList(
						new ParameterBuilder().name("campos")
						.description("Nomes das propriedas para filtrar na resposta separados por virgula")
						.parameterType("query")
						.modelRef(new ModelRef("String")).build()
						))*/ 
				.additionalModels(typeResolver.resolve(Problem.class))
				.ignoredParameterTypes(ServletWebRequest.class, URI.class, URL.class,URLStreamHandler.class, Resource.class, File.class,
						InputStream.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class,LinksModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, CidadeModelV2.class), CidadesModelV2OpenApi.class))
				.apiInfo(apiInfoV2()) 
				.tags(new Tag("Cidades", "Gerencia a cidade")
						);
	}
	
	private ApiInfo apiInfoV1() {
		return new ApiInfoBuilder().title("AlgaFood Api").version("v1")
				.description("Api aberta apara clientes e restaurantes")
				.contact(new Contact("Felipe Souza", "felipedb91@hotmail.com", "felipedb91@hotmail.com"))
				.build();
	}
	
	private ApiInfo apiInfoV2() {
		return new ApiInfoBuilder().title("AlgaFood Api").version("v2")
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
