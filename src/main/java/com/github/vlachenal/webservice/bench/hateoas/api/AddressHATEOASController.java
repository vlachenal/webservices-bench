/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.hateoas.api;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.vlachenal.webservice.bench.business.AddressBusiness;
import com.github.vlachenal.webservice.bench.hateoas.AddressResourceAssembler;
import com.github.vlachenal.webservice.bench.hateoas.api.resource.AddressResource;
import com.github.vlachenal.webservice.bench.mapping.mapstruct.MapStructMappers;
import com.github.vlachenal.webservice.bench.rest.api.model.Address;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * Phone REST resource
 *
 * @author Vincent Lachenal
 */
@RestController
@ExposesResourceFor(AddressResource.class)
@RequestMapping(path="/hateoas/customer/{customerId}/address")
@Api("RESTful API to manage customers' address")
public class AddressHATEOASController {

  // Attributes +
  /** Address business */
  private final AddressBusiness business;

  /** MapStruct mappers */
  private final MapStructMappers mapstruct;

  /** Phone resource assembler */
  private final AddressResourceAssembler resAssembler;
  // Attributes -


  // Constructors +
  /**
   * {@link AddressHATEOASController} constructor
   *
   * @param business the business
   * @param mapstruct the MapStruct mapper to use
   * @param resAssembler the resource assembler to use
   */
  public AddressHATEOASController(final AddressBusiness business, final MapStructMappers mapstruct, final AddressResourceAssembler resAssembler) {
    this.business = business;
    this.mapstruct = mapstruct;
    this.resAssembler = resAssembler;
  }
  // Constructors -


  // Methods +
  /**
   * Register address
   *
   * @param customerId the customers identifiers
   * @param address the new address
   *
   * @return the phones
   */
  @RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces= {MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation("Register a new address for a customer")
  @ApiResponses(value={
    @ApiResponse(code=201,message="Address has been successfully registered"),
    @ApiResponse(code=400,message="Invalid parameters"),
    @ApiResponse(code=404,message="Customer does not exist")
  })
  public void registerAddress(@PathVariable("customerId") final String customerId, @RequestBody final Address address) {
    business.registerAddress(customerId, mapstruct.address().fromRest(address));
  }

  /**
   * Get phone
   *
   * @param customerId the customer identifier
   *
   * @return the phone
   */
  @RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ApiOperation("Get address")
  @ApiResponses(value={
    @ApiResponse(code=200,message="Address has been successfully retrieved"),
    @ApiResponse(code=400,message="Invalid UUID"),
    @ApiResponse(code=404,message="Phone does not exist")
  })
  public HttpEntity<AddressResource> get(@PathVariable("customerId") final String customerId) {
    final Address address = mapstruct.address().toRest(business.getAddress(customerId));
    address.setCustomerId(customerId);
    return new ResponseEntity<>(resAssembler.toResource(address), HttpStatus.OK);
  }

  /**
   * Delete address
   *
   * @param customerId the customer identifier
   *
   * @return the address
   */
  @RequestMapping(method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ApiOperation("Delete address")
  @ApiResponses(value={
    @ApiResponse(code=200,message="Address has been successfully deleted"),
    @ApiResponse(code=400,message="Invalid UUID"),
    @ApiResponse(code=404,message="Phone does not exist")
  })
  public void delete(@PathVariable("customerId") final String customerId) {
    business.deleteAddress(customerId);
  }
  // Methods -

}
