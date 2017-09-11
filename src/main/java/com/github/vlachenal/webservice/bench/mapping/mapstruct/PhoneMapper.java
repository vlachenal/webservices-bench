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

import com.github.vlachenal.webservice.bench.dao.bean.PhoneBean;


/**
 * Phone mapper
 *
 * @author Vincent Lachenal
 */
@Mapper(componentModel="spring",unmappedTargetPolicy=ReportingPolicy.IGNORE)
public interface PhoneMapper {

  /**
   * Convert SOAP phone to bean
   *
   * @param phone the SOAP phone
   *
   * @return the bean
   */
  @Mapping(source="phoneType",target="type")
  PhoneBean soapToBean(com.github.vlachenal.webservice.bench.soap.api.Phone phone);

  /**
   * Convert bean phone to SOAP
   *
   * @param phone the bean phone
   *
   * @return the SOAP phone
   */
  @Mapping(source="type",target="phoneType")
  com.github.vlachenal.webservice.bench.soap.api.Phone beanToSoap(PhoneBean phone);

  /**
   * Convert Thrift phone to bean
   *
   * @param phone the Thrift phone
   *
   * @return the bean
   */
  PhoneBean thriftToBean(com.github.vlachenal.webservice.bench.thrift.api.Phone phone);

  /**
   * Convert bean phone to Thrift
   *
   * @param phone the bean phone
   *
   * @return the Thrift phone
   */
  com.github.vlachenal.webservice.bench.thrift.api.Phone beanToThrift(PhoneBean phone);

  /**
   * Convert REST phone to bean
   *
   * @param phone the REST phone
   *
   * @return the bean
   */
  PhoneBean restToBean(com.github.vlachenal.webservice.bench.rest.api.bean.Phone phone);

  /**
   * Convert bean phone to REST
   *
   * @param phone the bean phone
   *
   * @return the REST phone
   */
  com.github.vlachenal.webservice.bench.rest.api.bean.Phone beanToRest(PhoneBean phone);

}
