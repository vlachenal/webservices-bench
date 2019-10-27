/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.hateoas;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.rest.api.model.Address;


/**
 * Address resource assembler
 *
 * @author Vincent Lachenal
 */
@Component
public class AddressResourceAssembler implements SimpleRepresentationModelAssembler<Address> {

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
    this.entityLinks = entityLinks;
  }
  // Constructors -


  // Methods +
//  /**
//   * {@inheritDoc}
//   *
//   * @see org.springframework.hateoas.server.RepresentationModelAssembler#toModel(java.lang.Object)
//   */
//  @Override
//  public AddressResource toModel(final Address entity) {
//    final AddressResource res = new AddressResource(entity);
//    if(entity != null) {
//      res.add(entityLinks.linkFor(AddressResource.class, entity.getCustomerId()).withSelfRel());
//    }
//    return res;
//  }


  @Override
  public void addLinks(final EntityModel<Address> resource) {
    if(resource.getContent() != null) {
      resource.add(entityLinks.linkFor(Address.class, resource.getContent().getCustomerId()).withSelfRel());
    }
  }


  @Override
  public void addLinks(final CollectionModel<EntityModel<Address>> resources) {
    resources.forEach(this::addLinks);
  }
  // Methods -

}
