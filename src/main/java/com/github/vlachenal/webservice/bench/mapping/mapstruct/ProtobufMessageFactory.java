/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.mapstruct;

import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.protobuf.api.Customer;


/**
 * Protocol buffer message factory
 *
 * @author Vincent Lachenal
 */
@Component
public class ProtobufMessageFactory {

  // Methods +
  /**
   * Create new {@link Customer.Builder}
   *
   * @return the {@link Customer.Builder}
   */
  public Customer.Builder protocolCustomerBuilder() {
    return Customer.newBuilder();
  }

  /**
   * Create new {@link Address.Builder}
   *
   * @return the {@link Address.Builder}
   */
  public Customer.Address.Builder protocolAddressBuilder() {
    return Customer.Address.newBuilder();
  }

  /**
   * Create new {@link Phone.Builder}
   *
   * @return the {@link Phone.Builder}
   */
  public Customer.Phone.Builder protocolPhoneBuilder() {
    return Customer.Phone.newBuilder();
  }
  // Methods -

}
