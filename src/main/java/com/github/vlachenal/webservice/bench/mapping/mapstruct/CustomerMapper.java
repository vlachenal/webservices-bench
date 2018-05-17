/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.github.vlachenal.webservice.bench.dto.CustomerDTO;


/**
 * Customer mapper
 *
 * @author Vincent Lachenal
 */
@Mapper(
        componentModel = "spring",
        uses = {
          PhoneMapper.class,
          AddressMapper.class,
          LongDateMapper.class
        },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
    )
public interface CustomerMapper {

  /**
   * Convert SOAP customer to DTO
   *
   * @param customer the SOAP customer
   *
   * @return the DTO
   */
  CustomerDTO fromSoap(com.github.vlachenal.webservice.bench.soap.api.Customer customer);

  /**
   * Convert DTO customer to SOAP
   *
   * @param customer the DTO customer
   *
   * @return the SOAP customer
   */
  com.github.vlachenal.webservice.bench.soap.api.Customer toSoap(CustomerDTO customer);

  /**
   * Convert DTO customer to SOAP
   *
   * @param customer the DTO customer
   *
   * @return the SOAP customer
   */
  List<com.github.vlachenal.webservice.bench.soap.api.Customer> toSoapList(List<CustomerDTO> customer);

  /**
   * Convert Thrift customer to DTO
   *
   * @param customer the Thrift customer
   *
   * @return the DTO
   */
  CustomerDTO fromThrift(com.github.vlachenal.webservice.bench.thrift.api.Customer customer);

  /**
   * Convert DTO customer to Thrift
   *
   * @param customer the DTO customer
   *
   * @return the Thrift customer
   */
  com.github.vlachenal.webservice.bench.thrift.api.Customer toThrift(CustomerDTO customer);

  /**
   * Convert DTO customer to Thrift
   *
   * @param customer the DTO customer
   *
   * @return the Thrift customer
   */
  List<com.github.vlachenal.webservice.bench.thrift.api.Customer> toThriftList(List<CustomerDTO> customer);

  /**
   * Convert REST customer to DTO
   *
   * @param customer the REST customer
   *
   * @return the DTO
   */
  CustomerDTO fromRest(com.github.vlachenal.webservice.bench.rest.api.model.Customer customer);

  /**
   * Convert DTO customer to REST
   *
   * @param customer the DTO customer
   *
   * @return the REST customer
   */
  com.github.vlachenal.webservice.bench.rest.api.model.Customer toRest(CustomerDTO customer);

  /**
   * Convert DTO customer to REST
   *
   * @param customer the DTO customer
   *
   * @return the REST customer
   */
  List<com.github.vlachenal.webservice.bench.rest.api.model.Customer> toRestList(List<CustomerDTO> customer);

}
