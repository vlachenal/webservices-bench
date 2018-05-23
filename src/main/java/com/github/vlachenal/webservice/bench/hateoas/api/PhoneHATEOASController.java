/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.hateoas.api;

import java.util.List;
import java.util.stream.Collectors;

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

import com.github.vlachenal.webservice.bench.business.PhoneBusiness;
import com.github.vlachenal.webservice.bench.hateoas.PhoneResourceAssembler;
import com.github.vlachenal.webservice.bench.hateoas.api.resource.PhoneResource;
import com.github.vlachenal.webservice.bench.mapping.mapstruct.MapStructMappers;
import com.github.vlachenal.webservice.bench.rest.api.model.Phone;

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
@ExposesResourceFor(PhoneResource.class)
@RequestMapping(path="/hateoas/customer/{customerId}/phone")
@Api("RESTful API to manage customers' phones")
public class PhoneHATEOASController {

  // Attributes +
  /** Phone business */
  private final PhoneBusiness business;

  /** MapStruct mappers */
  private final MapStructMappers mapstruct;

  /** Phone resource assembler */
  private final PhoneResourceAssembler resAssembler;
  // Attributes -


  // Constructors +
  /**
   * {@link PhoneHATEOASController} constructor
   *
   * @param business the business
   * @param mapstruct the MapStruct mapper to use
   * @param resAssembler the resource assembler to use
   */
  public PhoneHATEOASController(final PhoneBusiness business, final MapStructMappers mapstruct, final PhoneResourceAssembler resAssembler) {
    this.business = business;
    this.mapstruct = mapstruct;
    this.resAssembler = resAssembler;
  }
  // Constructors -


  // Methods +
  /**
   * List customer's phones
   *
   * @param customerId the customers identifiers
   *
   * @return the phones
   */
  @RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ApiOperation("List all phone numbers for a customer")
  @ApiResponses(value= {
    @ApiResponse(code=200,message="Phones have been successfully retrieved"),
    @ApiResponse(code=400,message="Customer identifier is not an UUID")
  })
  public HttpEntity<List<PhoneResource>> listPhones(@PathVariable("customerId") final String customerId) {
    final List<Phone> phones = business.listAll(customerId).stream().map(p -> {
      final Phone phone = mapstruct.phone().toRest(p);
      phone.setCustomerId(customerId);
      return phone;
    }).collect(Collectors.toList());
    return new ResponseEntity<>(resAssembler.toResources(phones), HttpStatus.OK);
  }

  /**
   * Create phone
   *
   * @param customerId the customers identifiers
   * @param phone the new phone
   *
   * @return the phones
   */
  @RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces= {MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation("Register a new phone for a customer")
  @ApiResponses(value={
    @ApiResponse(code=201,message="Phone has been successfully registered"),
    @ApiResponse(code=400,message="Invalid parameters"),
    @ApiResponse(code=404,message="Customer does not exist")
  })
  public String addPhone(@PathVariable("customerId") final String customerId, @RequestBody final Phone phone) {
    return business.registerPhone(customerId, mapstruct.phone().fromRest(phone));
  }

  /**
   * Get phone
   *
   * @param customerId the customer identifier
   * @param phoneId the phone identifier
   *
   * @return the phone
   */
  @RequestMapping(path="/{phoneId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ApiOperation("Get phone")
  @ApiResponses(value={
    @ApiResponse(code=200,message="Phones have been successfully retrieved"),
    @ApiResponse(code=400,message="Invalid UUID"),
    @ApiResponse(code=404,message="Phone does not exist")
  })
  public HttpEntity<PhoneResource> get(@PathVariable("customerId") final String customerId, @PathVariable("phoneId") final String phoneId) {
    final Phone phone = mapstruct.phone().toRest(business.getPhone(customerId, phoneId));
    phone.setCustomerId(customerId);
    return new ResponseEntity<>(resAssembler.toResource(phone), HttpStatus.OK);
  }

  /**
   * Delete phone
   *
   * @param customerId the customer identifier
   * @param phoneId the phone identifier
   *
   * @return the phone
   */
  @RequestMapping(path="/{phoneId}",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ApiOperation("Delete phone")
  @ApiResponses(value={
    @ApiResponse(code=200,message="Phones have been successfully retrieved"),
    @ApiResponse(code=400,message="Invalid UUID"),
    @ApiResponse(code=404,message="Phone does not exist")
  })
  public void delete(@PathVariable("customerId") final String customerId, @PathVariable("phoneId") final String phoneId) {
    business.deletePhone(customerId, phoneId);
  }
  // Methods -

}
