/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.thrift.api;

import java.util.List;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.bridge.CustomerBridge;
import com.github.vlachenal.webservice.bench.dao.CustomerDAO;


/**
 * Customer service Thrift endpoint
 *
 * @author Vincent Lachenal
 */
@Component
public class CustomerServiceHandler implements CustomerService.Iface {

  // Attributes +
  @Autowired
  private CustomerDAO dao;
  // Attributes -


  // Methods +
  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.thrift.api.CustomerService.Iface#listCustomers()
   */
  @Override
  public List<Customer> listCustomers() throws CustomerException, TException {
    return CustomerBridge.toThrift(dao.listAll());
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.thrift.api.CustomerService.Iface#get(java.lang.String)
   */
  @Override
  public Customer get(final String id) throws CustomerException, TException {
    return CustomerBridge.toThrift(dao.getDetails(id));
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.thrift.api.CustomerService.Iface#create(com.github.vlachenal.webservice.bench.thrift.api.Customer)
   */
  @Override
  public String create(final Customer customer) throws CustomerException, TException {
    return dao.create(CustomerBridge.toBean(customer));
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.thrift.api.CustomerService.Iface#deleteAll()
   */
  @Override
  public void deleteAll() throws CustomerException, TException {
    dao.deleteAll();
  }
  // Methods -

}
