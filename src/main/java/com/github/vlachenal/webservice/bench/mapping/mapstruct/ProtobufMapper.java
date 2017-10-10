/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.github.vlachenal.webservice.bench.dao.bean.AddressBean;
import com.github.vlachenal.webservice.bench.dao.bean.CustomerBean;
import com.github.vlachenal.webservice.bench.dao.bean.PhoneBean;


/**
 * Protocol Buffer mappers.<br>
 * Declare Protocol Buffer specific mapping interface to activate null check on every fields.
 *
 * @author Vincent Lachenal
 */
@Mapper(
        componentModel="spring",
        uses={
          ProtobufMessageFactory.class,
          MapStructCustomMapper.class,
          LongDateMapper.class
        },
        unmappedTargetPolicy=ReportingPolicy.IGNORE,
        nullValueCheckStrategy=NullValueCheckStrategy.ALWAYS
    )
public interface ProtobufMapper {

  /**
   * Convert Protocol buffer phone to bean
   *
   * @param phone the Protocol buffer phone
   *
   * @return the bean
   */
  PhoneBean protobufToBean(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone phone);

  /**
   * Convert bean address to Protocol buffer
   *
   * @param address the bean address
   *
   * @return the Protocol buffer address
   */
  default com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone beanToProtobuf(final PhoneBean phone) {
    return beanToProtobufForBuilder(phone).build();
  }

  /**
   * Convert bean phone to Protocol buffer
   *
   * @param phone the bean phone
   *
   * @return the Protocol buffer phone
   */
  com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.Builder beanToProtobufForBuilder(PhoneBean phone);

  /**
   * Convert Protocol buffer address to bean
   *
   * @param address the Protocol buffer address
   *
   * @return the bean
   */
  AddressBean protobufToBean(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address address);

  /**
   * Bean to protocol buffer for builder
   *
   * @param customer the bean address
   *
   * @return the builder
   */
  com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address.Builder beanToProtobufForBuilder(AddressBean address);

  /**
   * Convert bean address to Protocol buffer
   *
   * @param address the bean address
   *
   * @return the Protocol buffer address
   */
  default com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address beanToProtobuf(final AddressBean address) {
    return beanToProtobufForBuilder(address).build();
  }

  /**
   * Convert SOAP customer to bean
   *
   * @param customer the SOAP customer
   *
   * @return the bean
   */
  CustomerBean protobufToBean(com.github.vlachenal.webservice.bench.protobuf.api.Customer customer);

  /**
   * Bean to protocol buffer for builder
   *
   * @param customer the bean customer
   *
   * @return the builder
   */
  com.github.vlachenal.webservice.bench.protobuf.api.Customer.Builder beanToProtobufForBuilder(CustomerBean customer);

  /**
   * Convert bean customer to Protocol buffer
   *
   * @param customer the bean customer
   *
   * @return the Protocol buffer customer
   */
  default com.github.vlachenal.webservice.bench.protobuf.api.Customer beanToProtobuf(final CustomerBean customer) {
    return beanToProtobufForBuilder(customer).build();
  }

  /**
   * Convert bean customer to Protocol buffer
   *
   * @param customer the bean customer
   *
   * @return the Protocol buffer customer
   */
  List<com.github.vlachenal.webservice.bench.protobuf.api.Customer> beanListToProtobuf(List<CustomerBean> customer);

}