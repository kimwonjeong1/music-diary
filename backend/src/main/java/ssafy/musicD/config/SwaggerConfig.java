package ssafy.musicD.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	private ApiInfo apiInfo() {

		return new ApiInfoBuilder().title("musicD").description("APIs").build();
	}

	@Bean
	public Docket commonApi() {
		List global = new ArrayList();
		global.add(new ParameterBuilder().name("Authorization").description("Access Token").parameterType("header")
				.required(false).modelRef(new ModelRef("string")).build());

		return new Docket(DocumentationType.SWAGGER_2).groupName("musicD").apiInfo(this.apiInfo())
				.globalOperationParameters(global).select()
				.apis(RequestHandlerSelectors.basePackage("ssafy.musicD.controller"))
				.paths(PathSelectors.ant("/api/**")).build();
	}
}
