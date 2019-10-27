/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.hateoas.api;

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

import com.github.vlachenal.webservice.bench.business.AddressBusiness;
import com.github.vlachenal.webservice.bench.hateoas.AddressResourceAssembler;
import com.github.vlachenal.webservice.bench.mapping.mapstruct.MapStructMappers;
import com.github.vlachenal.webservice.bench.rest.api.model.Address;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * Phone REST resource
 *
 * @author Vincent Lachenal
 */
@RestController
@ExposesResourceFor(Address.class)
@RequestMapping(path="/hateoas/customer/{customerId}/address")
@Tag(name = "HATEOAS - Address", description = "RESTful API to manage customers' address")
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
  @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE,produces= {MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(description="Register a new address for a customer")
  @ApiResponse(responseCode="201",description="Address has been successfully registered")
  @ApiResponse(responseCode="400",description="Invalid parameters")
  @ApiResponse(responseCode="404",description="Customer does not exist")
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
  @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
  @Operation(description="Get address")
  @ApiResponse(responseCode="200",description="Address has been successfully retrieved")
  @ApiResponse(responseCode="400",description="Invalid UUID")
  @ApiResponse(responseCode="404",description="Phone does not exist")
  public HttpEntity<EntityModel<Address>> get(@PathVariable("customerId") final String customerId) {
    final Address address = mapstruct.address().toRest(business.getAddress(customerId));
    address.setCustomerId(customerId);
    return new ResponseEntity<>(resAssembler.toModel(address), HttpStatus.OK);
  }

  /**
   * Delete address
   *
   * @param customerId the customer identifier
   *
   * @return the address
   */
  @DeleteMapping(produces=MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(description="Delete address")
  @ApiResponse(responseCode="200",description="Address has been successfully deleted")
  @ApiResponse(responseCode="400",description="Invalid UUID")
  @ApiResponse(responseCode="404",description="Phone does not exist")
  public void delete(@PathVariable("customerId") final String customerId) {
    business.deleteAddress(customerId);
  }
  // Methods -

}
