/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.vlachenal.webservice.bench.bridge.CustomerBridge;
import com.github.vlachenal.webservice.bench.dao.CustomerDAO;
import com.github.vlachenal.webservice.bench.rest.api.bean.Customer;


/**
 * Customer service REST endpoint
 *
 * @author Vincent Lachenal
 */
@RestController
public class CustomerController {

  // Attributes +
  @Autowired
  private CustomerDAO dao;
  // Attributes -


  // Methods +
  /**
   * List all customers in database
   *
   * @return customers
   */
  @RequestMapping(path="/rest/customer",method=RequestMethod.GET,produces="application/json")
  public List<Customer> listCustomers() {
    return CustomerBridge.toRest(dao.listAll());
  }

  /**
   * Retrieve customer details
   *
   * @param id the customer identifier
   *
   * @return the customer details
   */
  @RequestMapping(path="/rest/customer/{id}",method=RequestMethod.GET,produces="application/json")
  public Customer get(@PathVariable("id") final String id) {
    return CustomerBridge.toRest(dao.getDetails(id));
  }

  /**
   * Create customer
   *
   * @param customer the customer to create
   *
   * @return the new customer's identifier
   */
  @RequestMapping(path="/rest/customer",method=RequestMethod.POST,consumes="application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public String create(final Customer customer) {
    return dao.create(CustomerBridge.toBean(customer));
  }

  /**
   * Delete all customers
   */
  @RequestMapping(path="/rest/customer",method=RequestMethod.DELETE)
  public void deleteAll() {
    dao.deleteAll();
  }
  // Methods -

}
