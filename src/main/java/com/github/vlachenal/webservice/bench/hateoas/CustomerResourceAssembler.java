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
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.rest.api.model.Address;
import com.github.vlachenal.webservice.bench.rest.api.model.Customer;
import com.github.vlachenal.webservice.bench.rest.api.model.Phone;


/**
 * Customer resource assembler
 *
 * @author Vincent Lachenal
 */
@Component
public class CustomerResourceAssembler implements SimpleRepresentationModelAssembler<Customer> {

  // Attributes +
  /** Entity links */
  private final EntityLinks entityLinks;
  // Attributes -


  // Constructors +
  /**
   * {@link CustomerResourceAssembler} default constructor
   *
   * @param entityLinks the {@link EntityLinks} to use
   */
  public CustomerResourceAssembler(final EntityLinks entityLinks) {
//    super(CustomerHATEOASController.class, CustomerResource.class);
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
//  public CustomerResource toModel(final Customer entity) {
//    final CustomerResource res = new CustomerResource(entity);
//    if(entity != null) {
//      res.add(entityLinks.linkFor(CustomerResource.class).slash(entity.getId()).withSelfRel());
//      if(entity.getAddress() != null) {
//        res.add(entityLinks.linkFor(AddressResource.class, entity.getId()).withRel("address"));
//        entity.setAddress(null);
//      }
//      if(entity.getPhones() != null && !entity.getPhones().isEmpty()) {
//        final LinkBuilder phoneLnkBuilder = entityLinks.linkFor(PhoneResource.class, entity.getId());
//        res.add(phoneLnkBuilder.withRel("phones"));
//        entity.getPhones().stream().forEach(phone -> {
//          res.add(phoneLnkBuilder.slash(phone.getId()).withRel("phone"));
//        });
//        entity.setPhones(null);
//      }
//    }
//    return res;
//  }

  @Override
  public void addLinks(final EntityModel<Customer> resource) {
    if(resource.getContent() != null) {
      final Customer entity = resource.getContent();
      resource.add(entityLinks.linkFor(Customer.class).slash(entity.getId()).withSelfRel());
      if(entity.getAddress() != null) {
        resource.add(entityLinks.linkFor(Address.class, entity.getId()).withRel("address"));
        entity.setAddress(null);
      }
      if(entity.getPhones() != null && !entity.getPhones().isEmpty()) {
        final LinkBuilder phoneLnkBuilder = entityLinks.linkFor(Phone.class, entity.getId());
        resource.add(phoneLnkBuilder.withRel("phones"));
        entity.getPhones().stream().forEach(phone -> {
          resource.add(phoneLnkBuilder.slash(phone.getId()).withRel("phone"));
        });
        entity.setPhones(null);
      }
    }
  }

  @Override
  public void addLinks(final CollectionModel<EntityModel<Customer>> resources) {
    resources.forEach(this::addLinks);
  }
  // Methods -

}
