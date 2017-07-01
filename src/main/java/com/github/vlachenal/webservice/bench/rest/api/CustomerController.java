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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vlachenal.webservice.bench.bridge.CustomerBridge;
import com.github.vlachenal.webservice.bench.dao.CustomerDAO;
import com.github.vlachenal.webservice.bench.rest.api.bean.Address;
import com.github.vlachenal.webservice.bench.rest.api.bean.Customer;


/**
 * Customer service REST endpoint
 *
 * @author Vincent Lachenal
 */
@RestController
public class CustomerController {

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
  @RequestMapping(path="/rest/customer",method=RequestMethod.GET,produces="application/json")
  public List<Customer> listCustomers(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq) {
    return CustomerBridge.toRest(dao.listAll());
  }

  /**
   * Retrieve customer details
   *
   * @param requestSeq the request sequence header
   * @param id the customer identifier
   *
   * @return the customer details
   */
  @RequestMapping(path="/rest/customer/{id}",method=RequestMethod.GET,produces="application/json")
  public Customer get(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq, @PathVariable("id") final String id) {
    UUID custId = null;
    try {
      custId = UUID.fromString(id);
    } catch(final IllegalArgumentException e) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, id + " is not an UUID");
    }
    final Customer customer = CustomerBridge.toRest(dao.getDetails(custId));
    if(customer == null) {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND, id + " does not exist");
    }
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
  @RequestMapping(path="/rest/customer",method=RequestMethod.POST,consumes="application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public String create(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq, final Customer customer) {
    // Customer structure checks +
    if(customer == null) {
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
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Address lines[0], zip_code, city and country has to be set: " + input);
    }
    // Address structure checks -
    return dao.create(CustomerBridge.toBean(customer));
  }

  /**
   * Delete all customers
   *
   * @param requestSeq the request sequence header
   */
  @RequestMapping(path="/rest/customer",method=RequestMethod.DELETE)
  public void deleteAll(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq) {
    dao.deleteAll();
  }
  // Methods -

}
