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

import com.github.vlachenal.webservice.bench.dto.AddressDTO;
import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.dto.PhoneDTO;


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
   * Convert Protocol buffer phone to DTO
   *
   * @param phone the Protocol buffer phone
   *
   * @return the DTO
   */
  PhoneDTO protobufToBean(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone phone);

  /**
   * Convert Protocol buffer phone type to DTO
   *
   * @param type Protocol buffer phone type
   *
   * @return the DTO
   */
  @ValueMappings({
    @ValueMapping(source="UNKNOWN",target=MappingConstants.NULL),
    @ValueMapping(source="UNRECOGNIZED",target=MappingConstants.NULL)
  })
  PhoneDTO.Type toBean(final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.PhoneType type);

  /**
   * Convert DTO address to Protocol buffer
   *
   * @param address the DTO address
   *
   * @return the Protocol buffer address
   */
  default com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone beanToProtobuf(final PhoneDTO phone) {
    return beanToProtobufForBuilder(phone).build();
  }

  /**
   * Convert DTO phone to Protocol buffer
   *
   * @param phone the DTO phone
   *
   * @return the Protocol buffer phone
   */
  com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.Builder beanToProtobufForBuilder(PhoneDTO phone);

  /**
   * Convert Protocol buffer address to DTO
   *
   * @param address the Protocol buffer address
   *
   * @return the DTO
   */
  AddressDTO protobufToBean(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address address);

  /**
   * DTO to protocol buffer for builder
   *
   * @param customer the DTO address
   *
   * @return the builder
   */
  @Mapping(source="lines",target="linesList",ignore=true)
  com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address.Builder beanToProtobufForBuilder(AddressDTO address);

  /**
   * Convert DTO address to Protocol buffer
   *
   * @param address the DTO address
   *
   * @return the Protocol buffer address
   */
  default com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address beanToProtobuf(final AddressDTO address) {
    return beanToProtobufForBuilder(address).build();
  }

  /**
   * Convert Protocol buffer customer to DTO
   *
   * @param customer the Protocol buffer customer
   *
   * @return the DTO
   */
  CustomerDTO protobufToBean(com.github.vlachenal.webservice.bench.protobuf.api.Customer customer);

  /**
   * DTO to protocol buffer for builder
   *
   * @param customer the DTO customer
   *
   * @return the builder
   */
  @Mapping(source="phones",target="phonesList")
  com.github.vlachenal.webservice.bench.protobuf.api.Customer.Builder beanToProtobufForBuilder(CustomerDTO customer);

  /**
   * Convert DTO customer to Protocol buffer
   *
   * @param customer the DTO customer
   *
   * @return the Protocol buffer customer
   */
  default com.github.vlachenal.webservice.bench.protobuf.api.Customer beanToProtobuf(final CustomerDTO customer) {
    return beanToProtobufForBuilder(customer).build();
  }

  /**
   * Convert DTO customer to Protocol buffer
   *
   * @param customer the DTO customer
   *
   * @return the Protocol buffer customer
   */
  List<com.github.vlachenal.webservice.bench.protobuf.api.Customer> beanListToProtobuf(List<CustomerDTO> customer);

}
