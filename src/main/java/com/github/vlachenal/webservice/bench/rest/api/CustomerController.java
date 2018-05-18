/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.rest.api;

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

import com.github.vlachenal.webservice.bench.AbstractBenchService;
import com.github.vlachenal.webservice.bench.business.CustomerBusiness;
import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.dto.CallDTO;
import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.mapping.manual.CustomerBridge;
import com.github.vlachenal.webservice.bench.mapping.mapstruct.MapStructMappers;
import com.github.vlachenal.webservice.bench.rest.api.model.Customer;
import com.github.vlachenal.webservice.bench.rest.api.model.Mapper;

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
@RequestMapping(path="/rest/customer")
@Api("RESTful API to manage customers")
public class CustomerController extends AbstractBenchService {

  // Attributes +
  /** Customer business */
  private final CustomerBusiness business;

  /** Dozer mapper */
  private final org.dozer.Mapper dozer;

  /** MapStruct mappers */
  private final MapStructMappers mapstruct;
  // Attributes -


  // Constructors +
  /**
   * {@link CustomerController} constructor
   *
   * @param stats the statistics cache to use
   * @param dao the customer DAO to use
   * @param dozer the Dozer mapper to use
   * @param mapstruct the MapStrrct mapper to use
   */
  public CustomerController(final StatisticsCache stats,
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
   * Convert customer DTO to REST according to mapper
   *
   * @param dto the customer DTO
   * @param mapper the mapper
   *
   * @return the REST customer
   */
  private Customer toRest(final CustomerDTO dto, final Mapper mapper) {
    Customer customer = null;
    switch(mapper) {
      case DOZER:
        customer = dozer.map(dto, Customer.class);
        break;
      case MAPSTRUCT:
        customer = mapstruct.customer().toRest(dto);
        break;
      default:
        customer = CustomerBridge.toRest(dto);
    }
    return customer;
  }

  /**
   * Convert REST customer to DTO according to mapper
   *
   * @param customer the REST customer
   * @param mapper the mapper
   *
   * @return the customer DTO
   */
  private CustomerDTO fromRest(final Customer customer, final Mapper mapper) {
    CustomerDTO dto = null;
    switch(mapper) {
      case DOZER:
        dto = dozer.map(customer, CustomerDTO.class);
        break;
      case MAPSTRUCT:
        dto = mapstruct.customer().fromRest(customer);
        break;
      default:
        dto = CustomerBridge.fromRest(customer);
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
  @RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ApiOperation("List all customers stored in database")
  @ApiResponses(value= {
    @ApiResponse(code=200,message="Customers hasve been successfully retrieved")
  })
  public List<Customer> listCustomers(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq,
                                      @RequestHeader(name="mapper",required=false,defaultValue="MANUAL") final Mapper mapper) {
    final CallDTO call = initializeCall(requestSeq, "list");
    List<Customer> customers;
    try {
      customers = map(business.listAll(), mapper, this::toRest);
    } finally {
      registerCall(call);
    }
    return customers;
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
  @RequestMapping(path="/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    Customer customer;
    try {
      customer = map(business.getDetails(id), mapper, this::toRest);
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
  @RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces= {MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value="Create new customer",notes="Succes will produce " + MediaType.TEXT_PLAIN_VALUE + " ; errors will be produce " + MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ApiResponses(value= {
    @ApiResponse(code=201,message="Customer has been successfully created"),
    @ApiResponse(code=400,message="Missing or invalid field")
  })
  public String create(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq,
                       @RequestHeader(name="mapper",required=false,defaultValue="MANUAL") final Mapper mapper,
                       @RequestBody final Customer customer) {
    final CallDTO call = initializeCall(requestSeq, "create");
    String uuid = null;
    try {
      uuid = business.create(map(customer, mapper, this::fromRest));
    } finally {
      registerCall(call);
    }
    return uuid;
  }

  /**
   * Delete all customers
   */
  @RequestMapping(method=RequestMethod.DELETE)
  @ApiOperation("Delete all customers stored in database")
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
    return "rest";
  }
  // Methods -

}
