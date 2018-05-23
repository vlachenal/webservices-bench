/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.hateoas;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;


/**
 * HATEOAS configuration
 *
 * @author Vincent Lachenal
 */
@Configuration
@EnableHypermediaSupport(type = { HypermediaType.HAL })
public class HATEOASConfig {

  // Nothing more for now ...

}
