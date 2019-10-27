/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;


/**
 * Open API specification configuration
 *
 * @author Vincent Lachenal
 */
@OpenAPIDefinition(info = @Info(
                                title = "Customer REST API",
                                description = "Provides RESTful API to manage customers",
                                version = "1.0",
                                license = @License(
                                                   name = "WTFPL 2",
                                                   url = "http://www.wtfpl.net/about/"
                                    ),
                                contact = @Contact(
                                                   name = "Vincent Lachenal",
                                                   url = "https://github.com/vlachenal",
                                                   email = "vincent.lachenal@yopmail.com"
                                    )
    )
)
public class OpenAPIConfig {
  // Nothing to do
}
