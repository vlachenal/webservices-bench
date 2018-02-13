/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.github.vlachenal.webservice.bench.dao.bean.AddressBean;


/**
 * Address mapper
 *
 * @author Vincent Lachenal
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
    )
public interface AddressMapper {

  /**
   * Convert SOAP address to bean
   *
   * @param address the SOAP address
   *
   * @return the bean
   */
  AddressBean soapToBean(com.github.vlachenal.webservice.bench.soap.api.Address address);

  /**
   * Convert bean address to SOAP
   *
   * @param address the bean address
   *
   * @return the SOAP address
   */
  com.github.vlachenal.webservice.bench.soap.api.Address beanToSoap(AddressBean address);

  /**
   * Convert Thrift address to bean
   *
   * @param address the Thrift address
   *
   * @return the bean
   */
  AddressBean thriftToBean(com.github.vlachenal.webservice.bench.thrift.api.Address address);

  /**
   * Convert bean address to Thrift
   *
   * @param address the bean address
   *
   * @return the Thrift address
   */
  com.github.vlachenal.webservice.bench.thrift.api.Address beanToThrift(AddressBean address);

  /**
   * Convert REST address to bean
   *
   * @param address the REST address
   *
   * @return the bean
   */
  AddressBean restToBean(com.github.vlachenal.webservice.bench.rest.api.bean.Address address);

  /**
   * Convert bean address to REST
   *
   * @param address the bean address
   *
   * @return the REST address
   */
  com.github.vlachenal.webservice.bench.rest.api.bean.Address beanToRest(AddressBean address);

}
