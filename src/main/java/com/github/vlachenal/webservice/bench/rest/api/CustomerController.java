/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.rest.api;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.github.vlachenal.webservice.bench.bridge.CustomerBridge;
import com.github.vlachenal.webservice.bench.dao.CustomerDAO;
import com.github.vlachenal.webservice.bench.dao.bean.CallBean;
import com.github.vlachenal.webservice.bench.rest.api.bean.Address;
import com.github.vlachenal.webservice.bench.rest.api.bean.Customer;

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
  /** Customer DAO */
  @Autowired
  private CustomerDAO dao;
  // Attributes -


  // Methods +
  /**
   * List all customers in database
   *
   * @param requestSeq the request sequence header
   *
   * @return customers
   */
  @RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ApiOperation("List all customers stored in database")
  @ApiResponses(value= {
    @ApiResponse(code=200,message="Customers hasve been successfully retrieved")
  })
  public List<Customer> listCustomers(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq) {
    final CallBean call = initializeCall(requestSeq, "list");
    final List<Customer> customers = CustomerBridge.toRest(dao.listAll());
    registerCall(call);
    return customers;
  }

  /**
   * Retrieve customer details
   *
   * @param requestSeq the request sequence header
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
  public Customer get(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq, @PathVariable("id") final String id) {
    final CallBean call = initializeCall(requestSeq, "get");
    UUID custId = null;
    try {
      custId = UUID.fromString(id);
    } catch(final IllegalArgumentException e) {
      registerCall(call);
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, id + " is not an UUID");
    }
    final Customer customer = CustomerBridge.toRest(dao.getDetails(custId));
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
   * @param customer the customer to create
   *
   * @return the new customer's identifier
   */
  @RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.TEXT_PLAIN_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation("Create new customer")
  @ApiResponses(value= {
    @ApiResponse(code=201,message="Customer has been successfully created"),
    @ApiResponse(code=400,message="Missing or invalid field")
  })
  public String create(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq, @RequestBody final Customer customer) {
    final CallBean call = initializeCall(requestSeq, "create");
    // Customer structure checks +
    if(customer == null) {
      registerCall(call);
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Customer is null");
    }
    if(customer.getFirstName() == null || customer.getLastName() == null || customer.getBirthDate() == null) {
      String input = null;
      final ObjectMapper mapper = new ObjectMapper();
      try {
        input = new String(mapper.writeValueAsBytes(customer));
      } catch(final Exception e) {
        // Nothing to do
      }
      registerCall(call);
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Customer first_name, last_name and brith_date has to be set: " + input);
    }
    // Customer structure checks -
    // Address structure checks +
    final Address addr = customer.getAddress();
    if(addr != null
        && (addr.getLines() == null || addr.getLines().isEmpty()
        || addr.getZipCode() == null || addr.getCity() == null || addr.getCountry() == null)) {
      String input = null;
      final ObjectMapper mapper = new ObjectMapper();
      try {
        input = new String(mapper.writeValueAsBytes(customer));
      } catch(final Exception e) {
        // Nothing to do
      }
      registerCall(call);
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Address lines[0], zip_code, city and country has to be set: " + input);
    }
    // Address structure checks -
    final String uuid = dao.create(CustomerBridge.toBean(customer));
    registerCall(call);
    return uuid;
  }

  /**
   * Delete all customers
   *
   * @param requestSeq the request sequence header
   */
  @RequestMapping(method=RequestMethod.DELETE)
  @ApiOperation("Delete all customers stored in database")
  public void deleteAll(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq) {
    final CallBean call = initializeCall(requestSeq, "delete-all");
    dao.deleteAll();
    registerCall(call);
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
