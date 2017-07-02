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

import com.github.vlachenal.webservice.bench.AbstractBenchService;
import com.github.vlachenal.webservice.bench.RequestSequence;
import com.github.vlachenal.webservice.bench.bridge.CustomerBridge;
import com.github.vlachenal.webservice.bench.dao.CustomerDAO;
import com.github.vlachenal.webservice.bench.dao.bean.CallBean;


/**
 * Customer service Thrift endpoint
 *
 * @author Vincent Lachenal
 */
@Component
public class CustomerServiceHandler extends AbstractBenchService implements CustomerService.Iface {

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
    final CallBean call = initializeCall(RequestSequence.getRequestSequence(), "list");
    final List<Customer> customers = CustomerBridge.toThrift(dao.listAll());
    regitsterCall(call);
    return customers;
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.thrift.api.CustomerService.Iface#get(java.lang.String)
   */
  @Override
  public Customer get(final String id) throws CustomerException, TException {
    final CallBean call = initializeCall(RequestSequence.getRequestSequence(), "get");
    UUID custId = null;
    try {
      custId = UUID.fromString(id);
    } catch(final Exception e) {
      regitsterCall(call);
      throw new CustomerException(ErrorCode.PARAMETER, "Invalid UUID: " + id);
    }
    final Customer cust = CustomerBridge.toThrift(dao.getDetails(custId));
    if(cust == null) {
      regitsterCall(call);
      throw new CustomerException(ErrorCode.NOT_FOUND, "Customer " + id + " has not been found");
    }
    regitsterCall(call);
    return cust;
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.thrift.api.CustomerService.Iface#create(com.github.vlachenal.webservice.bench.thrift.api.Customer)
   */
  @Override
  public String create(final Customer customer) throws CustomerException, TException {
    final CallBean call = initializeCall(RequestSequence.getRequestSequence(), "create");
    // Customer structure checks +
    if(customer == null) {
      regitsterCall(call);
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Customer is null");
    }
    if(customer.getFirstName() == null || customer.getLastName() == null) {
      regitsterCall(call);
      throw new CustomerException(ErrorCode.PARAMETER, "Customer first_name, last_name and brith_date has to be set: " + customer);
    }
    // Customer structure checks -
    // Address structure checks +
    final Address addr = customer.getAddress();
    if(addr != null
        && (addr.getLines() == null || addr.getLines().isEmpty()
        || addr.getZipCode() == null || addr.getCity() == null || addr.getCountry() == null)) {
      regitsterCall(call);
      throw new CustomerException(ErrorCode.PARAMETER, "Address lines[0], zip_code, city and country has to be set: " + customer.getAddress());
    }
    // Address structure checks -
    final String uuid = dao.create(CustomerBridge.toBean(customer));
    regitsterCall(call);
    return uuid;
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.thrift.api.CustomerService.Iface#deleteAll()
   */
  @Override
  public void deleteAll() throws CustomerException, TException {
    final CallBean call = initializeCall(RequestSequence.getRequestSequence(), "delete-all");
    dao.deleteAll();
    regitsterCall(call);
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.AbstractBenchService#getProtocol()
   */
  @Override
  public String getProtocol() {
    return "thrift";
  }
  // Methods -

}
