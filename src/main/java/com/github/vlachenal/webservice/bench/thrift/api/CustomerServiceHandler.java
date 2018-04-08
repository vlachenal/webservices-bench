/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.thrift.api;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.AbstractBenchService;
import com.github.vlachenal.webservice.bench.business.CustomerBusiness;
import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.dto.CallDTO;
import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.errors.InvalidParametersException;
import com.github.vlachenal.webservice.bench.errors.NotFoundException;
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
  private final org.dozer.Mapper dozer;

  /** MapStruct mappers */
  private final MapStructMappers mapstruct;

  /** Customer service */
  private final CustomerBusiness business;
  // Attributes -


  // Constructors +
  /**
   * {@link CustomerServiceHandler} constructor
   *
   * @param stats the statistics cache to use
   * @param business the customer service to use
   * @param dozer the Dozer mapper to use
   * @param mapstruct the MapStruct mappers to use
   */
  public CustomerServiceHandler(final StatisticsCache stats,
                                final CustomerBusiness business,
                                final org.dozer.Mapper dozer,
                                final MapStructMappers mapstruct) {
    super(stats);
    this.business = business;
    this.dozer = dozer;
    this.mapstruct = mapstruct;
  }
  // Constructors -


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
    final CallDTO call = initializeCall(reqSeq, "list");
    final List<CustomerDTO> res = business.listAll();
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
    final CallDTO call = initializeCall(reqSeq, "get");
    if(request == null) {
      registerCall(call);
      throw new CustomerException(ErrorCode.PARAMETER, "Request is null");
    }
    if(!request.isSetId()) {
      registerCall(call);
      throw new CustomerException(ErrorCode.PARAMETER, "Customer identifier is not set");
    }
    CustomerDTO customer = null;
    try {
      customer = business.getDetails(request.getId());
    } catch(final InvalidParametersException e) {
      registerCall(call);
      throw new CustomerException(ErrorCode.PARAMETER, e.getMessage());
    } catch(final NotFoundException e) {
      registerCall(call);
      throw new CustomerException(ErrorCode.NOT_FOUND, e.getMessage());
    }
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
    final CallDTO call = initializeCall(reqSeq, "create");
    if(request == null) {
      registerCall(call);
      throw new CustomerException(ErrorCode.PARAMETER, "Request is null");
    }
    final Customer customer = request.getCustomer();
    CustomerDTO dto = null;
    switch(mapper) {
      case DOZER:
        dto = dozer.map(customer, CustomerDTO.class);
        break;
      case MAPSTRUCT:
        dto = mapstruct.customer().fromThrift(customer);
        break;
      default:
        dto = CustomerBridge.fromThrift(customer);
    }
    String uuid = null;
    try {
      uuid = business.create(dto);
    } catch(final InvalidParametersException e) {
      registerCall(call);
      throw new CustomerException(ErrorCode.PARAMETER, e.getMessage());
    }
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
    business.deleteAll();
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
