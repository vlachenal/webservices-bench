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

import com.github.vlachenal.webservice.bench.hateoas.api.PhoneHATEOASController;
import com.github.vlachenal.webservice.bench.hateoas.api.resource.PhoneResource;
import com.github.vlachenal.webservice.bench.rest.api.model.Phone;


/**
 * Phone resource assembler
 *
 * @author Vincent Lachenal
 */
@Component
public class PhoneResourceAssembler extends ResourceAssemblerSupport<Phone, PhoneResource> {

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
    super(PhoneHATEOASController.class, PhoneResource.class);
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
  public PhoneResource toResource(final Phone entity) {
    final PhoneResource res = new PhoneResource(entity);
    if(entity != null) {
      res.add(entityLinks.linkFor(PhoneResource.class, entity.getCustomerId()).slash(entity.getId()).withSelfRel());
    }
    return res;
  }
  // Methods -

}
