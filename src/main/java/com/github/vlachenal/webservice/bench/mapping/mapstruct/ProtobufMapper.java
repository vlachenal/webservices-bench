/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.mapstruct;

import java.util.List;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

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
        componentModel = "spring",
        uses = {
          ProtobufMessageFactory.class,
          LongDateMapper.class
        },
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
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
   * Convert Protocol buffer phone type to bean
   *
   * @param type Protocol buffer phone type
   *
   * @return the bean
   */
  @ValueMappings({
    @ValueMapping(source="UNKNOWN",target=MappingConstants.NULL),
    @ValueMapping(source="UNRECOGNIZED",target=MappingConstants.NULL)
  })
  PhoneBean.Type toBean(final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.PhoneType type);

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
  @Mapping(source="lines",target="linesList",ignore=true)
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
   * Convert Protocol buffer customer to bean
   *
   * @param customer the Protocol buffer customer
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
  @Mapping(source="phones",target="phonesList")
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
