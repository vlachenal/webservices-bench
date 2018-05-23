/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.hateoas.api;

import java.util.List;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.vlachenal.webservice.bench.business.CustomerBusiness;
import com.github.vlachenal.webservice.bench.hateoas.CustomerResourceAssembler;
import com.github.vlachenal.webservice.bench.hateoas.api.resource.CustomerResource;
import com.github.vlachenal.webservice.bench.mapping.mapstruct.MapStructMappers;
import com.github.vlachenal.webservice.bench.rest.api.model.Customer;

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
@ExposesResourceFor(CustomerResource.class)
@RequestMapping(path="/hateoas/customer")
@Api("RESTful API to manage customers")
public class CustomerHATEOASController {

  // Attributes +
  /** Customer business */
  private final CustomerBusiness business;

  /** MapStruct mappers */
  private final MapStructMappers mapstruct;

  /** Cusomer resource assembler */
  private final CustomerResourceAssembler resAssembler;
  // Attributes -


  // Constructors +
  /**
   * {@link CustomerHATEOASController} constructor
   *
   * @param dao the customer DAO to use
   * @param mapstruct the MapStruct mapper to use
   * @param resAssembler the resource assembler to use
   */
  public CustomerHATEOASController(final CustomerBusiness business,
                                   final MapStructMappers mapstruct,
                                   final CustomerResourceAssembler resAssembler) {
    this.business = business;
    this.mapstruct = mapstruct;
    this.resAssembler = resAssembler;
  }
  // Constructors -


  /**
   * List all customers in database
   *
   * @return customers
   */
  @RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ApiOperation("List all customers stored in database")
  @ApiResponses(value= {
    @ApiResponse(code=200,message="Customers have been successfully retrieved")
  })
  public HttpEntity<List<CustomerResource>> listCustomers() {
    final List<Customer> customers = mapstruct.customer().toRestList(business.listAll());
    return new ResponseEntity<>(resAssembler.toResources(customers), HttpStatus.OK);
  }

  /**
   * Retrieve customer details
   *
   * @param id the customer identifier
   *
   * @return the customer details
   */
  @RequestMapping(path="/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ApiOperation("Retrieve customer details")
  @ApiResponses(value={
    @ApiResponse(code=200,message="Customer has been successfully retrieved"),
    @ApiResponse(code=400,message="Invalid customer identifier format (should be UUID)"),
    @ApiResponse(code=404,message="Customer has not been found in database")
  })
  public HttpEntity<CustomerResource> get(@PathVariable("id") final String id) {
    final CustomerResource res = resAssembler.toResource(mapstruct.customer().toRest(business.getDetails(id)));
    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  /**
   * Create customer
   *
   * @param customer the customer to create
   *
   * @return the new customer's identifier
   */
  @RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces= {MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value="Create new customer",notes="Success will produce " + MediaType.TEXT_PLAIN_VALUE + " ; errors will be produce " + MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ApiResponses(value= {
    @ApiResponse(code=201,message="Customer has been successfully created"),
    @ApiResponse(code=400,message="Missing or invalid field")
  })
  public String create(@RequestBody final Customer customer) {
    return business.create(mapstruct.customer().fromRest(customer));
  }

  /**
   * Delete all customers
   */
  @RequestMapping(method=RequestMethod.DELETE)
  @ApiOperation("Delete all customers stored in database")
  public void deleteAll() {
    business.deleteAll();
  }
  // Methods -

}
