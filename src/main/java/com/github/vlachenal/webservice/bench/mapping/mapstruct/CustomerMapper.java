/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.mapstruct;

import org.mapstruct.Mapper;

import com.github.vlachenal.webservice.bench.dao.bean.CustomerBean;


/**
 * Customer mapper
 *
 * @author Vincent Lachenal
 */
@Mapper(componentModel="spring", uses= {PhoneMapper.class,AddressMapper.class,LongDateMapper.class})
public interface CustomerMapper {

  /**
   * Convert SOAP customer to bean
   *
   * @param customer the SOAP customer
   *
   * @return the bean
   */
  CustomerBean soapToBean(com.github.vlachenal.webservice.bench.soap.api.Customer customer);

  /**
   * Convert bean customer to SOAP
   *
   * @param customer the bean customer
   *
   * @return the SOAP customer
   */
  com.github.vlachenal.webservice.bench.soap.api.Customer beanToSoap(CustomerBean customer);

  /**
   * Convert Thrift customer to bean
   *
   * @param customer the Thrift customer
   *
   * @return the bean
   */
  CustomerBean thriftToBean(com.github.vlachenal.webservice.bench.thrift.api.Customer customer);

  /**
   * Convert bean customer to Thrift
   *
   * @param customer the bean customer
   *
   * @return the Thrift customer
   */
  com.github.vlachenal.webservice.bench.thrift.api.Customer beanToThrift(CustomerBean customer);

  /**
   * Convert REST customer to bean
   *
   * @param customer the REST customer
   *
   * @return the bean
   */
  CustomerBean restToBean(com.github.vlachenal.webservice.bench.rest.api.bean.Customer customer);

  /**
   * Convert bean customer to REST
   *
   * @param customer the bean customer
   *
   * @return the REST customer
   */
  com.github.vlachenal.webservice.bench.rest.api.bean.Customer beanToRest(CustomerBean customer);

}
