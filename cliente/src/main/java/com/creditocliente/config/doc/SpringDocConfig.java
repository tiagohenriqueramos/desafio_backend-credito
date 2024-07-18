package com.creditocliente.config.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfig {

	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()				
				.info(new Info().title("Credito Express API").description(
						"API Rest da aplicação Credito Express, contendo as funcionalidades de CRUD de cliente e taxa, alem da funcionalidade de simulação")
						.contact(new Contact().name("Time Backend").email("backend@credito.com"))
						.license(new License().name("License 1.0").url("https://www.apache.org/licenses/LICENSE-2.0")));
	}

}
