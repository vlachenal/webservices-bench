/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.thrift.api;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.AbstractBenchService;
import com.github.vlachenal.webservice.bench.dao.CustomerDAO;
import com.github.vlachenal.webservice.bench.dto.CallBean;
import com.github.vlachenal.webservice.bench.dto.CustomerBean;
import com.github.vlachenal.webservice.bench.mapping.manual.CustomerBridge;
import com.github.vlachenal.webservice.bench.mapping.mapstruct.MapStructMappers;


/**
 * Customer service Thrift endpoint
 *
 * @author Vincent Lachenal
 */
@Component
public class CustomerServiceHandler extends AbstractBenchService implements CustomerService.Iface {

  // Attributes +
  /** Dozer mapper */
  @Autowired
  private org.dozer.Mapper dozer;

  /** MapStruct mappers */
  @Autowired
  private MapStructMappers mapstruct;

  @Autowired
  private CustomerDAO dao;
  // Attributes -


  // Methods +
  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.thrift.api.CustomerService.Iface#listCustomers(com.github.vlachenal.webservice.bench.thrift.api.ListAllRequest)
   */
  @Override
  public List<Customer> listCustomers(final ListAllRequest request) throws CustomerException, TException {
    int reqSeq = -1;
    Mapper mapper = Mapper.MANUAL;
    if(request != null && request.isSetHeader()) {
      if(request.getHeader().isSetRequestSeq()) {
        reqSeq = request.getHeader().getRequestSeq();
      }
      if(request.getHeader().isSetMapper()) {
        mapper = request.getHeader().getMapper();
      }
    }
    final CallBean call = initializeCall(reqSeq, "list");
    final List<CustomerBean> res = dao.listAll();
    List<Customer> customers = null;
    switch(mapper) {
      case DOZER:
        customers = res.stream().map(from -> dozer.map(from, Customer.class)).collect(Collectors.toList());
        break;
      case MAPSTRUCT:
        customers = mapstruct.customer().toThriftList(res);
        break;
      default:
        customers = CustomerBridge.toThrift(res);
    }
    registerCall(call);
    return customers;
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.thrift.api.CustomerService.Iface#get(com.github.vlachenal.webservice.bench.thrift.api.GetRequest)
   */
  @Override
  public Customer get(final GetRequest request) throws CustomerException, TException {
    int reqSeq = -1;
    Mapper mapper = Mapper.MANUAL;
    if(request != null && request.isSetHeader()) {
      if(request.getHeader().isSetRequestSeq()) {
        reqSeq = request.getHeader().getRequestSeq();
      }
      if(request.getHeader().isSetMapper()) {
        mapper = request.getHeader().getMapper();
      }
    }
    final CallBean call = initializeCall(reqSeq, "get");
    if(request == null) {
      registerCall(call);
      throw new CustomerException(ErrorCode.PARAMETER, "Request is null");
    }
    if(!request.isSetId()) {
      registerCall(call);
      throw new CustomerException(ErrorCode.PARAMETER, "Customer identifier is not set");
    }
    UUID custId = null;
    try {
      custId = UUID.fromString(request.getId());
    } catch(final Exception e) {
      registerCall(call);
      throw new CustomerException(ErrorCode.PARAMETER, "Invalid UUID: " + request.getId());
    }
    final CustomerBean customer = dao.getDetails(custId);
    Customer cust = null;
    switch(mapper) {
      case DOZER:
        cust = dozer.map(customer, Customer.class);
        break;
      case MAPSTRUCT:
        cust = mapstruct.customer().toThrift(customer);
        break;
      default:
        cust = CustomerBridge.toThrift(customer);
    }
    if(cust == null) {
      registerCall(call);
      throw new CustomerException(ErrorCode.NOT_FOUND, "Customer " + request.getId() + " has not been found");
    }
    registerCall(call);
    return cust;
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.thrift.api.CustomerService.Iface#create(com.github.vlachenal.webservice.bench.thrift.api.CreateRequest)
   */
  @Override
  public String create(final CreateRequest request) throws CustomerException, TException {
    int reqSeq = -1;
    Mapper mapper = Mapper.MANUAL;
    if(request != null && request.isSetHeader()) {
      if(request.getHeader().isSetRequestSeq()) {
        reqSeq = request.getHeader().getRequestSeq();
      }
      if(request.getHeader().isSetMapper()) {
        mapper = request.getHeader().getMapper();
      }
    }
    final CallBean call = initializeCall(reqSeq, "create");
    if(request == null) {
      registerCall(call);
      throw new CustomerException(ErrorCode.PARAMETER, "Request is null");
    }
    final Customer customer = request.getCustomer();
    // Customer structure checks +
    if(customer == null) {
      registerCall(call);
      throw new CustomerException(ErrorCode.PARAMETER, "Customer is null");
    }
    if(customer.getFirstName() == null || customer.getLastName() == null) {
      registerCall(call);
      throw new CustomerException(ErrorCode.PARAMETER, "Customer first_name, last_name and brith_date has to be set: " + customer);
    }
    // Customer structure checks -
    // Address structure checks +
    final Address addr = customer.getAddress();
    if(addr != null
        && (addr.getLines() == null || addr.getLines().isEmpty()
        || addr.getZipCode() == null || addr.getCity() == null || addr.getCountry() == null)) {
      registerCall(call);
      throw new CustomerException(ErrorCode.PARAMETER, "Address lines[0], zip_code, city and country has to be set: " + customer.getAddress());
    }
    // Address structure checks -
    CustomerBean bean = null;
    switch(mapper) {
      case DOZER:
        bean = dozer.map(customer, CustomerBean.class);
        break;
      case MAPSTRUCT:
        bean = mapstruct.customer().fromThrift(customer);
        break;
      default:
        bean = CustomerBridge.fromThrift(customer);
    }
    final String uuid = dao.create(bean);
    registerCall(call);
    return uuid;
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
