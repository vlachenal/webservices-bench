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
    final YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
    final List<PropertySource<?>> sources = sourceLoader.load(name, resource.getResource());
    return sources == null ? null : sources.get(0);
  }

}
