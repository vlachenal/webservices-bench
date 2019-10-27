/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.hateoas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.filter.ForwardedHeaderFilter;


/**
 * HATEOAS configuration
 *
 * @author Vincent Lachenal
 */
@Configuration
@EnableHypermediaSupport(type = { HypermediaType.HAL })
public class HATEOASConfig {

  /**
   * Forwarded headers management filter (links will be computed according to caller URL when behind proxy)
   *
   * @return the filter
   */
  @Bean
  public ForwardedHeaderFilter forwardedHeaderFilter() {
    return new ForwardedHeaderFilter();
  }

}
