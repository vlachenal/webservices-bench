/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger configuration
 *
 * @author Vincent Lachenal
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  // Methods +
  /**
   * Provides customer REST API documentation
   *
   * @return the customer REST API documentation
   */
  @Bean
  public Docket providesCustomerAPI() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.github.vlachenal.webservice.bench"))
        .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
        .build()
        .apiInfo(metaData());
  }

  /**
   * Get API informations
   *
   * @return the informations
   */
  private ApiInfo metaData() {
    final ApiInfo apiInfo = new ApiInfo("Customer REST API",
                                        "Provides RESTful API to manage customers",
                                        "1.0",
                                        "Do What The Fuck You Want to",
                                        new Contact("Vincent Lachenal",
                                                    "https://github.com/vlachenal",
                                            "vincent.lachenal@yopmail.com"),
                                        "DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE",
                                        "http://www.wtfpl.net/about/",
                                        new ArrayList<>());
    return apiInfo;
  }
  // Methods -

}
