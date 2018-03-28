/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.github.vlachenal.webservice.bench.dto.PhoneDTO;


/**
 * Phone mapper
 *
 * @author Vincent Lachenal
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
    )
public interface PhoneMapper {

  /**
   * Convert SOAP phone to DTO
   *
   * @param phone the SOAP phone
   *
   * @return the DTO
   */
  @Mapping(source="phoneType",target="type")
  PhoneDTO fromSoap(com.github.vlachenal.webservice.bench.soap.api.Phone phone);

  /**
   * Convert DTO phone to SOAP
   *
   * @param phone the DTO phone
   *
   * @return the SOAP phone
   */
  @Mapping(source="type",target="phoneType")
  com.github.vlachenal.webservice.bench.soap.api.Phone toSoap(PhoneDTO phone);

  /**
   * Convert Thrift phone to DTO
   *
   * @param phone the Thrift phone
   *
   * @return the DTO
   */
  PhoneDTO fromThrift(com.github.vlachenal.webservice.bench.thrift.api.Phone phone);

  /**
   * Convert DTO phone to Thrift
   *
   * @param phone the DTO phone
   *
   * @return the Thrift phone
   */
  com.github.vlachenal.webservice.bench.thrift.api.Phone toThrift(PhoneDTO phone);

  /**
   * Convert REST phone to DTO
   *
   * @param phone the REST phone
   *
   * @return the DTO
   */
  PhoneDTO fromRest(com.github.vlachenal.webservice.bench.rest.api.bean.Phone phone);

  /**
   * Convert DTO phone to REST
   *
   * @param phone the DTO phone
   *
   * @return the REST phone
   */
  com.github.vlachenal.webservice.bench.rest.api.bean.Phone toRest(PhoneDTO phone);

}
