/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;


/**
 * YAML property source factory
 *
 * @author Vincent Lachenal
 */
public class YamlSingleDocPropertySourceFactory implements PropertySourceFactory {

  /**
   * {@inheritDoc}
   *
   * @see org.springframework.core.io.support.PropertySourceFactory#createPropertySource(java.lang.String, org.springframework.core.io.support.EncodedResource)
   */
  @Override
  public PropertySource<?> createPropertySource(final String name, final EncodedResource resource) throws IOException {
    final YamlPropertySourceLoader srcLoader = new YamlPropertySourceLoader();
    final List<PropertySource<?>> sources = srcLoader.load(name, resource.getResource());
    return sources == null ? null : sources.get(0);
  }

}
