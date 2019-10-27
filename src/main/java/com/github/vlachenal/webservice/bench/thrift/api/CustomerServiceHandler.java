/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.thrift.api;

import java.util.List;

import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
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
  private final com.github.dozermapper.core.Mapper dozer;

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
                                final com.github.dozermapper.core.Mapper dozer,
                                final MapStructMappers mapstruct) {
    super(stats);
    this.business = business;
    this.dozer = dozer;
    this.mapstruct = mapstruct;
  }
  // Constructors -


  // Methods +
  /**
   * Check that Thrift request is not null
   *
   * @param request the request
   *
   * @throws CustomerException request is {@code null}
   */
  private void checkRequest(final TBase<?,? extends TFieldIdEnum> request) throws CustomerException {
    if(request == null) {
      throw new CustomerException(ErrorCode.PARAMETER, "Request is null");
    }
  }

  /**
   * Compute request header according to request and default values
   *
   * @param reqHeader the request header
   *
   * @return the usable header
   */
  private Header getHeader(final Header reqHeader) {
    final Header header = new Header();
    header.setRequestSeq(-1);
    header.setMapper(Mapper.MANUAL);
    if(reqHeader != null) {
      if(reqHeader.isSetRequestSeq()) {
        header.setRequestSeq(reqHeader.getRequestSeq());
      }
      if(reqHeader.isSetMapper()) {
        header.setMapper(reqHeader.getMapper());
      }
    }
    return header;
  }

  /**
   * Convert customer DTO to Thrift according to mapper
   *
   * @param dto the customer DTO
   * @param mapper the mapper
   *
   * @return the Thrift customer
   */
  private Customer toThrift(final CustomerDTO dto, final Mapper mapper) {
    Customer cust = null;
    switch(mapper) {
      case DOZER:
        cust = dozer.map(dto, Customer.class);
        break;
      case MAPSTRUCT:
        cust = mapstruct.customer().toThrift(dto);
        break;
      default:
        cust = CustomerBridge.toThrift(dto);
    }
    return cust;
  }

  /**
   * Convert Thrift customer to DTO according to mapper
   *
   * @param customer the Thrift customer
   * @param mapper the mapper
   *
   * @return the customer DTO
   */
  private CustomerDTO fromThrift(final Customer customer, final Mapper mapper) {
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
    return dto;
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.thrift.api.CustomerService.Iface#listCustomers(com.github.vlachenal.webservice.bench.thrift.api.ListAllRequest)
   */
  @Override
  public List<Customer> listCustomers(final ListAllRequest request) throws CustomerException, TException {
    checkRequest(request);
    final Header header = getHeader(request.getHeader());
    final CallDTO call = initializeCall(header.getRequestSeq(), "list");
    final List<Customer> customers = map(business.search(mapstruct.search().fromThrift(request)), header.getMapper(), this::toThrift);
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
    checkRequest(request);
    final Header header = getHeader(request.getHeader());
    final CallDTO call = initializeCall(header.getRequestSeq(), "get");
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
    final Customer cust = map(customer, header.getMapper(), this::toThrift);
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
    checkRequest(request);
    final Header header = getHeader(request.getHeader());
    final CallDTO call = initializeCall(header.getRequestSeq(), "create");
    String uuid = null;
    try {
      uuid = business.create(map(request.getCustomer(), header.getMapper(), this::fromThrift));
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
