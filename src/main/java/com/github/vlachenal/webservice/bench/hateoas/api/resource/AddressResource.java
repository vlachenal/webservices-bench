/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.hateoas.api.resource;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.github.vlachenal.webservice.bench.rest.api.model.Address;


/**
 * Address HATEOAS resource
 *
 * @author Vincent Lachenal
 */
public class AddressResource extends EntityModel<Address> {

  /**
   * {@link AddressResource} constructor
   *
   * @param content the content
   * @param links the links
   */
  public AddressResource(final Address content, final Iterable<Link> links) {
    super(content, links);
  }

  /**
   * {@link AddressResource} constructor
   *
   * @param content the content
   * @param links the links
   */
  public AddressResource(final Address content, final Link... links) {
    super(content, links);
  }

}
