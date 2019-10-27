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

import com.github.vlachenal.webservice.bench.rest.api.model.Phone;


/**
 * Phone resource assembler
 *
 * @author Vincent Lachenal
 */
@Component
public class PhoneResourceAssembler implements SimpleRepresentationModelAssembler<Phone> {

  // Attributes +
  /** Entity links */
  private final EntityLinks entityLinks;
  // Attributes -


  // Constructors +
  /**
   * {@link PhoneResourceAssembler} default constructor
   *
   * @param entityLinks the {@link EntityLinks} to use
   */
  public PhoneResourceAssembler(final EntityLinks entityLinks) {
//    super(PhoneHATEOASController.class, PhoneResource.class);
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
//  public PhoneResource toModel(final Phone entity) {
//    final PhoneResource res = new PhoneResource(entity);
//    if(entity != null) {
//      res.add(entityLinks.linkFor(PhoneResource.class, entity.getCustomerId()).slash(entity.getId()).withSelfRel());
//    }
//    return res;
//  }

  @Override
  public void addLinks(final EntityModel<Phone> resource) {
    if(resource.getContent() != null) {
      resource.add(entityLinks.linkFor(Phone.class, resource.getContent().getCustomerId())
                   .slash(resource.getContent().getId()).withSelfRel());
    }
  }

  @Override
  public void addLinks(final CollectionModel<EntityModel<Phone>> resources) {
    resources.forEach(this::addLinks);
  }
  // Methods -

}
