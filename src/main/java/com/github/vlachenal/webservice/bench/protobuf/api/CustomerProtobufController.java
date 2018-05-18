/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.protobuf.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.github.vlachenal.webservice.bench.AbstractBenchService;
import com.github.vlachenal.webservice.bench.business.CustomerBusiness;
import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.dto.CallDTO;
import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.mapping.manual.CustomerBridge;
import com.github.vlachenal.webservice.bench.protobuf.ProtobufType;


/**
 * Customer service REST endpoint
 *
 * @author Vincent Lachenal
 */
@RestController
@RequestMapping(path="/protobuf/customer")
public class CustomerProtobufController extends AbstractBenchService {

  // Attributes +
  /** Customer service */
  private final CustomerBusiness business;
  // Attributes -


  // Constructors +
  /**
   * {@link CustomerProtobufController} constructor
   *
   * @param stats the statistics cache to use
   * @param business the customer service to use
   */
  public CustomerProtobufController(final StatisticsCache stats, final CustomerBusiness business) {
    super(stats);
    this.business = business;
  }
  // Constructors -


  // Methods +
  /**
   * Convert customer DTO to Thrift according to mapper
   *
   * @param dto the customer DTO
   * @param mapper the mapper
   *
   * @return the Thrift customer
   */
  private Customer toProtobuf(final CustomerDTO dto, final Mapper mapper) {
    Customer cust = null;
    switch(mapper) {
      case DOZER:
        throw new HttpClientErrorException(HttpStatus.NOT_IMPLEMENTED, "Dozer is not supported for now");
      case MAPSTRUCT:
        throw new HttpClientErrorException(HttpStatus.NOT_IMPLEMENTED, "MapStruct is not supported for now");
      default:
        cust = CustomerBridge.toProtobuf(dto);
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
  private CustomerDTO fromProtobuf(final Customer customer, final Mapper mapper) {
    CustomerDTO dto = null;
    switch(mapper) {
      case DOZER:
        throw new HttpClientErrorException(HttpStatus.NOT_IMPLEMENTED, "Dozer is not supported for now");
      case MAPSTRUCT:
        throw new HttpClientErrorException(HttpStatus.NOT_IMPLEMENTED, "MapStruct is not supported for now");
      default:
        dto = CustomerBridge.fromProtobuf(customer);
    }
    return dto;
  }

  /**
   * List all customers in database
   *
   * @param requestSeq the request sequence header
   * @param mapper the mapper to use
   *
   * @return customers
   */
  @RequestMapping(method=RequestMethod.GET,produces={ProtobufType.PROTOBUF_UTF8_VALUE})
  public ListAllResponse listCustomers(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq,
                                       @RequestHeader(name="mapper",required=false,defaultValue="MANUAL") final Mapper mapper) {
    final CallDTO call = initializeCall(requestSeq, "list");
    ListAllResponse response;
    try {
      final List<Customer> customers = map(business.listAll(), mapper, this::toProtobuf);
      final ListAllResponse.Builder builder = ListAllResponse.newBuilder();
      if(customers != null) {
        builder.addAllCustomers(customers);
      }
      response = builder.build();
    } finally {
      registerCall(call);
    }
    return response;
  }

  /**
   * Retrieve customer details
   *
   * @param requestSeq the request sequence header
   * @param mapper the mapper to use
   * @param id the customer identifier
   *
   * @return the customer details
   */
  @RequestMapping(path = "/{id}",
      method = RequestMethod.GET,
      produces = { ProtobufType.PROTOBUF_UTF8_VALUE }
      )
  public Customer get(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq,
                      @RequestHeader(name="mapper",required=false,defaultValue="MANUAL") final Mapper mapper,
                      @PathVariable("id") final String id) {
    final CallDTO call = initializeCall(requestSeq, "get");
    Customer customer;
    try {
      customer = map(business.getDetails(id), mapper, this::toProtobuf);
    } finally {
      registerCall(call);
    }
    return customer;
  }

  /**
   * Create customer
   *
   * @param requestSeq the request sequence header
   * @param mapper the mapper to use
   * @param customer the customer to create
   *
   * @return the new customer's identifier
   */
  @RequestMapping(method=RequestMethod.POST,consumes={ProtobufType.PROTOBUF_UTF8_VALUE},produces=MediaType.TEXT_PLAIN_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public String create(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq,
                       @RequestHeader(name="mapper",required=false,defaultValue="MANUAL") final Mapper mapper,
                       @RequestBody final Customer customer) {
    final CallDTO call = initializeCall(requestSeq, "create");
    String uuid = null;
    try {
      uuid = business.create(map(customer, mapper, this::fromProtobuf));
    } finally {
      registerCall(call);
    }
    return uuid;
  }

  /**
   * Delete all customers
   */
  @RequestMapping(method=RequestMethod.DELETE)
  public void deleteAll() {
    business.deleteAll();
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.AbstractBenchService#getProtocol()
   */
  @Override
  public String getProtocol() {
    return "protobuf";
  }
  // Methods -

}
