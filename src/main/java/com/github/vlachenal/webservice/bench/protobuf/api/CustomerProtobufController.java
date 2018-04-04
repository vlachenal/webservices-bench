/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.protobuf.api;

import java.util.List;
import java.util.UUID;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vlachenal.webservice.bench.AbstractBenchService;
import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.dao.CustomerDAO;
import com.github.vlachenal.webservice.bench.dto.CallDTO;
import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.mapping.manual.CustomerBridge;
import com.github.vlachenal.webservice.bench.protobuf.ProtobufType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * Customer service REST endpoint
 *
 * @author Vincent Lachenal
 */
@RestController
@RequestMapping(path="/protobuf/customer")
@Api("RESTful API to manage customers through Protocol buffer")
public class CustomerProtobufController extends AbstractBenchService {

  // Attributes +
  /** Customer DAO */
  private final CustomerDAO dao;
  // Attributes -


  // Constructors +
  /**
   * {@link CustomerProtobufController} constructor
   *
   * @param stats the statistics cache to use
   * @param dao the customer DAO to use
   */
  public CustomerProtobufController(final StatisticsCache stats, final CustomerDAO dao) {
    super(stats);
    this.dao = dao;
  }
  // Constructors -


  // Methods +
  /**
   * List all customers in database
   *
   * @param requestSeq the request sequence header
   * @param mapper the mapper to use
   *
   * @return customers
   */
  @RequestMapping(method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_UTF8_VALUE,ProtobufType.PROTOBUF_UTF8_VALUE})
  @ApiOperation("List all customers stored in database")
  @ApiResponses(value= {
    @ApiResponse(code=200,message="Customers hasve been successfully retrieved")
  })
  public ListAllResponse listCustomers(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq,
                                       @RequestHeader(name="mapper",required=false,defaultValue="MANUAL") final Mapper mapper) {
    final CallDTO call = initializeCall(requestSeq, "list");
    final List<CustomerDTO> res = dao.listAll();
    List<Customer> customers = null;
    switch(mapper) {
      case MAPSTRUCT:
        registerCall(call);
        throw new HttpClientErrorException(HttpStatus.NOT_IMPLEMENTED, "MapStruct is not supported for now");
      case DOZER:
        registerCall(call);
        throw new HttpClientErrorException(HttpStatus.NOT_IMPLEMENTED, "Dozer is not supported for now");
      default:
        customers = CustomerBridge.toProtobuf(res);
    }
    final ListAllResponse.Builder builder = ListAllResponse.newBuilder();
    if(customers != null) {
      builder.addAllCustomers(customers);
    }
    final ListAllResponse response = builder.build();
    registerCall(call);
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
      produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, ProtobufType.PROTOBUF_UTF8_VALUE }
      )
  @ApiOperation("Retrieve customer details")
  @ApiResponses(value= {
    @ApiResponse(code=200,message="Customer has been successfully retrieved"),
    @ApiResponse(code=400,message="Invalid customer identifier format (should be UUID)"),
    @ApiResponse(code=404,message="Customer has not been found in database")
  })
  public Customer get(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq,
                      @RequestHeader(name="mapper",required=false,defaultValue="MANUAL") final Mapper mapper,
                      @PathVariable("id") final String id) {
    final CallDTO call = initializeCall(requestSeq, "get");
    UUID custId = null;
    try {
      custId = UUID.fromString(id);
    } catch(final IllegalArgumentException e) {
      registerCall(call);
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, id + " is not an UUID");
    }
    final CustomerDTO res = dao.getDetails(custId);
    Customer customer = null;
    switch(mapper) {
      case MAPSTRUCT:
        registerCall(call);
        throw new HttpClientErrorException(HttpStatus.NOT_IMPLEMENTED, "MapStruct is not supported for now");
      case DOZER:
        registerCall(call);
        throw new HttpClientErrorException(HttpStatus.NOT_IMPLEMENTED, "Dozer is not supported for now");
      default:
        customer = CustomerBridge.toProtobuf(res);
    }
    if(customer == null) {
      registerCall(call);
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND, id + " does not exist");
    }
    registerCall(call);
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
  @RequestMapping(method=RequestMethod.POST,consumes={MediaType.APPLICATION_JSON_UTF8_VALUE,ProtobufType.PROTOBUF_UTF8_VALUE},produces=MediaType.TEXT_PLAIN_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation("Create new customer")
  @ApiResponses(value= {
    @ApiResponse(code=201,message="Customer has been successfully created"),
    @ApiResponse(code=400,message="Missing or invalid field")
  })
  public String create(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq,
                       @RequestHeader(name="mapper",required=false,defaultValue="MANUAL") final Mapper mapper,
                       @RequestBody final Customer customer) {
    final CallDTO call = initializeCall(requestSeq, "create");
    // Customer structure checks +
    if(customer == null) {
      registerCall(call);
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Customer is null");
    }
    if(customer.getFirstName() == null || customer.getLastName() == null || customer.getBirthDate() == 0) {
      String input = null;
      final ObjectMapper jsonMapper = new ObjectMapper();
      try {
        input = new String(jsonMapper.writeValueAsBytes(customer));
      } catch(final Exception e) {
        // Nothing to do
      }
      registerCall(call);
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Customer first_name, last_name and brith_date has to be set: " + input);
    }
    // Customer structure checks -
    // Address structure checks +
    final Customer.Address addr = customer.getAddress();
    if(addr != null
        && (addr.getLinesList() == null || addr.getLinesList().isEmpty()
        || addr.getZipCode() == null || addr.getCity() == null || addr.getCountry() == null)) {
      String input = null;
      final ObjectMapper jsonMapper = new ObjectMapper();
      try {
        input = new String(jsonMapper.writeValueAsBytes(customer));
      } catch(final Exception e) {
        // Nothing to do
      }
      registerCall(call);
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Address lines[0], zip_code, city and country has to be set: " + input);
    }
    // Address structure checks -
    CustomerDTO dto = null;
    switch(mapper) {
      case MAPSTRUCT:
        registerCall(call);
        throw new HttpClientErrorException(HttpStatus.NOT_IMPLEMENTED, "MapStruct is not supported for now");
      case DOZER:
        registerCall(call);
        throw new HttpClientErrorException(HttpStatus.NOT_IMPLEMENTED, "Dozer is not supported for now");
      default:
        dto = CustomerBridge.fromProtobuf(customer);
    }
    final String uuid = dao.create(dto);
    registerCall(call);
    return uuid;
  }

  /**
   * Delete all customers
   */
  @RequestMapping(method=RequestMethod.DELETE)
  @ApiOperation("Delete all customers stored in database")
  public void deleteAll() {
    dao.deleteAll();
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
