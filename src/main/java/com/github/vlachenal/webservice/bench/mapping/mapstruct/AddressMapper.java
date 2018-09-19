/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.github.vlachenal.webservice.bench.dto.AddressDTO;


/**
 * Address mapper
 *
 * @author Vincent Lachenal
 */
@Mapper(componentModel = "spring")
public interface AddressMapper {

  /**
   * Convert SOAP address to DTO
   *
   * @param address the SOAP address
   *
   * @return the DTO
   */
  AddressDTO fromSoap(com.github.vlachenal.webservice.bench.soap.api.Address address);

  /**
   * Convert DTO address to SOAP
   *
   * @param address the DTO address
   *
   * @return the SOAP address
   */
  com.github.vlachenal.webservice.bench.soap.api.Address toSoap(AddressDTO address);

  /**
   * Convert Thrift address to DTO
   *
   * @param address the Thrift address
   *
   * @return the DTO
   */
  AddressDTO fromThrift(com.github.vlachenal.webservice.bench.thrift.api.Address address);

  /**
   * Convert DTO address to Thrift
   *
   * @param address the DTO address
   *
   * @return the Thrift address
   */
  @Mappings({
    @Mapping(target = "cityIsSet", ignore = true), @Mapping(target = "countryIsSet", ignore = true),
    @Mapping(target = "linesIsSet", ignore = true), @Mapping(target = "zipCodeIsSet", ignore = true)
  })
  com.github.vlachenal.webservice.bench.thrift.api.Address toThrift(AddressDTO address);

  /**
   * Convert REST address to DTO
   *
   * @param address the REST address
   *
   * @return the DTO
   */
  AddressDTO fromRest(com.github.vlachenal.webservice.bench.rest.api.model.Address address);

  /**
   * Convert DTO address to REST
   *
   * @param address the DTO address
   *
   * @return the REST address
   */
  @Mappings({
    @Mapping(target = "customerId", ignore = true)
  })
  com.github.vlachenal.webservice.bench.rest.api.model.Address toRest(AddressDTO address);

}
