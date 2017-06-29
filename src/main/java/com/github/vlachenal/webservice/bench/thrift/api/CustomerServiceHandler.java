/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.thrift.api;

import java.util.List;
import java.util.UUID;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

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
    UUID custId = null;
    try {
      custId = UUID.fromString(id);
    } catch(final Exception e) {
      throw new CustomerException(ErrorCode.PARAMETER, "Invalid UUID: " + id);
    }
    final Customer cust = CustomerBridge.toThrift(dao.getDetails(custId));
    if(cust == null) {
      throw new CustomerException(ErrorCode.NOT_FOUND, "Customer " + id + " has not been found");
    }
    return cust;
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.thrift.api.CustomerService.Iface#create(com.github.vlachenal.webservice.bench.thrift.api.Customer)
   */
  @Override
  public String create(final Customer customer) throws CustomerException, TException {
    // Customer structure checks +
    if(customer == null) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Customer is null");
    }
    if(customer.getFirstName() == null || customer.getLastName() == null) {
      throw new CustomerException(ErrorCode.PARAMETER, "Customer first_name, last_name and brith_date has to be set: " + customer);
    }
    // Customer structure checks -
    // Address structure checks +
    final Address addr = customer.getAddress();
    if(addr != null
        && (addr.getLines() == null || addr.getLines().isEmpty()
        || addr.getZipCode() == null || addr.getCity() == null || addr.getCountry() == null)) {
      throw new CustomerException(ErrorCode.PARAMETER, "Address lines[0], zip_code, city and country has to be set: " + customer.getAddress());
    }
    // Address structure checks -
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
