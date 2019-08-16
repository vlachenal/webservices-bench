/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.mapstruct;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ValueMapping;

import com.github.vlachenal.webservice.bench.dto.AddressDTO;
import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.dto.PhoneDTO;
import com.github.vlachenal.webservice.bench.protobuf.api.Customer;


/**
 * Protocol Buffer mappers.<br>
 * Declare Protocol Buffer specific mapping interface to activate null check on every fields.
 *
 * @author Vincent Lachenal
 */
@Mapper(componentModel = "spring",
        uses = { LongDateMapper.class },
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface ProtobufMapper {

  /**
   * Convert Protocol buffer phone to DTO
   *
   * @param phone the Protocol buffer phone
   *
   * @return the DTO
   */
  @Mapping(target = "id", ignore = true)
  PhoneDTO fromProtobuf(Customer.Phone phone);

  /**
   * Convert Protocol buffer phone type to DTO
   *
   * @param type Protocol buffer phone type
   *
   * @return the DTO
   */
  @ValueMapping(source = "UNKNOWN", target = MappingConstants.NULL)
  @ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.NULL)
  PhoneDTO.Type fromProtobuf(Customer.Phone.PhoneType type);

  /**
   * Convert DTO phone to Protocol buffer
   *
   * @param phone the DTO phone
   *
   * @return the Protocol buffer phone
   */
  @Mapping(target = "mergeFrom", ignore = true)
  @Mapping(target = "clearField", ignore = true)
  @Mapping(target = "clearOneof", ignore = true)
  @Mapping(target = "mergeUnknownFields", ignore = true)
  @Mapping(target = "unknownFields", ignore = true)
  @Mapping(target = "allFields", ignore = true)
  @Mapping(target = "numberBytes", ignore = true)
  @Mapping(target = "typeValue", ignore = true)
  Customer.Phone toProtobuf(PhoneDTO phone);

  /**
   * Convert Protocol buffer address to DTO
   *
   * @param address the Protocol buffer address
   *
   * @return the DTO
   */
  @Mapping(target = "lines", source = "linesList")
  AddressDTO fromProtobuf(Customer.Address address);

  /**
   * DTO to protocol buffer for builder
   *
   * @param customer the DTO address
   *
   * @return the builder
   */
  @Mapping(source="lines",target="linesList")
  @Mapping(target = "mergeFrom", ignore = true)
  @Mapping(target = "clearField", ignore = true)
  @Mapping(target = "clearOneof", ignore = true)
  @Mapping(target = "mergeUnknownFields", ignore = true)
  @Mapping(target = "unknownFields", ignore = true)
  @Mapping(target = "allFields", ignore = true)
  @Mapping(target = "cityBytes", ignore = true)
  @Mapping(target = "countryBytes", ignore = true)
  @Mapping(target = "zipCodeBytes", ignore = true)
  Customer.Address toProtobuf(AddressDTO address);

  /**
   * Convert Protocol buffer customer to DTO
   *
   * @param customer the Protocol buffer customer
   *
   * @return the DTO
   */
  @Mapping(target = "phones", source = "phonesList")
  CustomerDTO fromProtobuf(Customer customer);

  /**
   * DTO to protocol buffer for builder
   *
   * @param customer the DTO customer
   *
   * @return the builder
   */
  @Mapping(target = "phonesList", source = "phones")
  @Mapping(target = "mergeFrom", ignore = true)
  @Mapping(target = "clearField", ignore = true)
  @Mapping(target = "clearOneof", ignore = true)
  @Mapping(target = "mergeUnknownFields", ignore = true)
  @Mapping(target = "unknownFields", ignore = true)
  @Mapping(target = "allFields", ignore = true)
  @Mapping(target = "mergeAddress", ignore = true)
  @Mapping(target = "removePhones", ignore = true)
  @Mapping(target = "emailBytes", ignore = true)
  @Mapping(target = "firstNameBytes", ignore = true)
  @Mapping(target = "lastNameBytes", ignore = true)
  @Mapping(target = "idBytes", ignore = true)
  @Mapping(target = "phonesBuilderList", ignore = true)
  @Mapping(target = "phonesOrBuilderList", ignore = true)
  Customer toProtobuf(CustomerDTO customer);

}
