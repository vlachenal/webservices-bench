/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.mapstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * MapStruct configuration
 *
 * @author Vincent Lachenal
 */
@Component
public class MapStructMappers {

  // Attributes +
  /** Customer mapper */
  @Autowired
  private CustomerMapper customer;

  /** Address mapper */
  @Autowired
  private AddressMapper address;

  /** Phone mapper */
  @Autowired
  private PhoneMapper phone;
  // Attributes -


  // Methods +
  /**
   * Customer mapper getter
   *
   * @return the mapper
   */
  public CustomerMapper customer() {
    return customer;
  }

  /**
   * Address mapper getter
   *
   * @return the mapper
   */
  public AddressMapper address() {
    return address;
  }

  /**
   * Phone mapper getter
   *
   * @return the mapper
   */
  public PhoneMapper phone() {
    return phone;
  }
  // Methods -

}
