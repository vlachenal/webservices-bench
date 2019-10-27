/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.hateoas.api;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;

import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.vlachenal.webservice.bench.business.CustomerBusiness;
import com.github.vlachenal.webservice.bench.dto.SearchRequestDTO;
import com.github.vlachenal.webservice.bench.hateoas.CustomerResourceAssembler;
import com.github.vlachenal.webservice.bench.mapping.mapstruct.MapStructMappers;
import com.github.vlachenal.webservice.bench.rest.api.model.Customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * Customer service REST endpoint
 *
 * @author Vincent Lachenal
 */
@RestController
@ExposesResourceFor(Customer.class)
@RequestMapping(path="/hateoas/customer")
@Tag(name="HATEOAS - Customer",description="RESTful API to manage customers")
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


  // Methods +
  /**
   * Search customers in database.<br>
   * Every filter is optional.
   *
   * @param firstName the first name to search. If the name contains '%', it will return any
   *                  customer which first name matches the value.
   * @param lastName the last name to search. If the name contains '%', it will return any
   *                  customer which last name matches the value.
   * @param email the email to search
   * @param birthDate the birth date to search
   * @param bornBefore the maximum birth date
   * @param bornAfter the minimum birth date
   *
   * @return customers
   */
  @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
  @Operation(description="List all customers stored in database")
  @ApiResponse(responseCode="200",description="Customers have been successfully retrieved")
  @ApiResponse(responseCode="400",description="Invalid filter format (date or email)")
  public HttpEntity<CollectionModel<EntityModel<Customer>>> listCustomers(@RequestParam(name="first_name",required=false) final String firstName,
                                                                          @RequestParam(name="last_name",required=false) final String lastName,
                                                                          @RequestParam(name="email",required=false) @Email final String email,
                                                                          @RequestParam(name="birth_date",required=false) @DateTimeFormat(pattern="yyyy-MM-dd") final Date birthDate,
                                                                          @RequestParam(name="born_before",required=false) @DateTimeFormat(pattern="yyyy-MM-dd") final Date bornBefore,
                                                                          @RequestParam(name="born_after",required=false) @DateTimeFormat(pattern="yyyy-MM-dd") final Date bornAfter) {
    final SearchRequestDTO dto = new SearchRequestDTO();
    dto.setFirstName(firstName);
    dto.setLastName(lastName);
    dto.setEmail(email);
    dto.setBirthDate(birthDate);
    dto.setBornAfter(bornAfter);
    dto.setBornBefore(bornBefore);
    final List<Customer> customers = mapstruct.customer().toRestList(business.search(dto));
    return new ResponseEntity<>(resAssembler.toCollectionModel(customers), HttpStatus.OK);
  }

  /**
   * Retrieve customer details
   *
   * @param id the customer identifier
   *
   * @return the customer details
   */
  @GetMapping(path="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
  @Operation(description="Retrieve customer details")
  @ApiResponse(responseCode="200",description="Customer has been successfully retrieved")
  @ApiResponse(responseCode="400",description="Invalid customer identifier format (should be UUID)")
  @ApiResponse(responseCode="404",description="Customer has not been found in database")
  public HttpEntity<EntityModel<Customer>> get(@PathVariable("id") final String id) {
    final EntityModel<Customer> res = resAssembler.toModel(mapstruct.customer().toRest(business.getDetails(id)));
    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  /**
   * Create customer
   *
   * @param customer the customer to create
   *
   * @return the new customer's identifier
   */
  @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE,produces= {MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(description="Create new customer. Success will produce " + MediaType.TEXT_PLAIN_VALUE + " ; errors will be produce " + MediaType.APPLICATION_JSON_VALUE)
  @ApiResponse(responseCode="201",description="Customer has been successfully created")
  @ApiResponse(responseCode="400",description="Missing or invalid field")
  public String create(@RequestBody final Customer customer) {
    return business.create(mapstruct.customer().fromRest(customer));
  }

  /**
   * Delete all customers
   */
  @DeleteMapping
  @Operation(description="Delete all customers stored in database")
  public void deleteAll() {
    business.deleteAll();
  }
  // Methods -

}
