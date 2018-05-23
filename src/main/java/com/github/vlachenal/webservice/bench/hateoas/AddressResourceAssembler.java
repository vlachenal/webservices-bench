/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.hateoas;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.hateoas.api.AddressHATEOASController;
import com.github.vlachenal.webservice.bench.hateoas.api.resource.AddressResource;
import com.github.vlachenal.webservice.bench.rest.api.model.Address;


/**
 * Address resource assembler
 *
 * @author Vincent Lachenal
 */
@Component
public class AddressResourceAssembler extends ResourceAssemblerSupport<Address, AddressResource> {

  // Attributes +
  /** Entity links */
  private final EntityLinks entityLinks;
  // Attributes -


  // Constructors +
  /**
   * {@link AddressResourceAssembler} default constructor
   *
   * @param entityLinks the {@link EntityLinks} to use
   */
  public AddressResourceAssembler(final EntityLinks entityLinks) {
    super(AddressHATEOASController.class, AddressResource.class);
    this.entityLinks = entityLinks;
  }
  // Constructors -


  // Methods +
  /**
   * {@inheritDoc}
   *
   * @see org.springframework.hateoas.ResourceAssembler#toResource(java.lang.Object)
   */
  @Override
  public AddressResource toResource(final Address entity) {
    final AddressResource res = new AddressResource(entity);
    if(entity != null) {
      res.add(entityLinks.linkFor(AddressResource.class, entity.getCustomerId()).withSelfRel());
    }
    return res;
  }
  // Methods -

}
