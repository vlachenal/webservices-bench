/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.hateoas.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.vlachenal.webservice.bench.business.PhoneBusiness;
import com.github.vlachenal.webservice.bench.hateoas.PhoneResourceAssembler;
import com.github.vlachenal.webservice.bench.mapping.mapstruct.MapStructMappers;
import com.github.vlachenal.webservice.bench.rest.api.model.Phone;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * Phone REST resource
 *
 * @author Vincent Lachenal
 */
@RestController
@ExposesResourceFor(Phone.class)
@RequestMapping(path="/hateoas/customer/{customerId}/phone")
@Tag(name="HATEOAS - Phones",description="RESTful API to manage customers' phones")
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
  @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
  @Operation(description="List all phone numbers for a customer")
  @ApiResponse(responseCode="200",description="Phones have been successfully retrieved")
  @ApiResponse(responseCode="400",description="Customer identifier is not an UUID")
  public HttpEntity<CollectionModel<EntityModel<Phone>>> listPhones(@PathVariable("customerId") final String customerId) {
    final List<Phone> phones = business.listAll(customerId).stream().map(p -> {
      final Phone phone = mapstruct.phone().toRest(p);
      phone.setCustomerId(customerId);
      return phone;
    }).collect(Collectors.toList());
    return new ResponseEntity<>(resAssembler.toCollectionModel(phones), HttpStatus.OK);
  }

  /**
   * Create phone
   *
   * @param customerId the customers identifiers
   * @param phone the new phone
   *
   * @return the phones
   */
  @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE,produces= {MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(description="Register a new phone for a customer")
  @ApiResponse(responseCode="201",description="Phone has been successfully registered")
  @ApiResponse(responseCode="400",description="Invalid parameters")
  @ApiResponse(responseCode="404",description="Customer does not exist")
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
  @GetMapping(path="/{phoneId}",produces=MediaType.APPLICATION_JSON_VALUE)
  @Operation(description="Get phone")
  @ApiResponse(responseCode="200",description="Phones have been successfully retrieved")
  @ApiResponse(responseCode="400",description="Invalid UUID")
  @ApiResponse(responseCode="404",description="Phone does not exist")
  public HttpEntity<EntityModel<Phone>> get(@PathVariable("customerId") final String customerId, @PathVariable("phoneId") final String phoneId) {
    final Phone phone = mapstruct.phone().toRest(business.getPhone(customerId, phoneId));
    phone.setCustomerId(customerId);
    return new ResponseEntity<>(resAssembler.toModel(phone), HttpStatus.OK);
  }

  /**
   * Delete phone
   *
   * @param customerId the customer identifier
   * @param phoneId the phone identifier
   *
   * @return the phone
   */
  @DeleteMapping(path="/{phoneId}",produces=MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(description="Delete phone")
  @ApiResponse(responseCode="204",description="Phone have been successfully deleted")
  @ApiResponse(responseCode="400",description="Invalid UUID")
  @ApiResponse(responseCode="404",description="Phone does not exist")
  public void delete(@PathVariable("customerId") final String customerId, @PathVariable("phoneId") final String phoneId) {
    business.deletePhone(customerId, phoneId);
  }
  // Methods -

}
