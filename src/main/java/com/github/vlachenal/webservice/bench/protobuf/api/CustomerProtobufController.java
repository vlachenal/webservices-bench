/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.protobuf.api;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.vlachenal.webservice.bench.AbstractBenchService;
import com.github.vlachenal.webservice.bench.business.CustomerBusiness;
import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.dto.CallDTO;
import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.dto.SearchRequestDTO;
import com.github.vlachenal.webservice.bench.mapping.manual.CustomerBridge;
import com.github.vlachenal.webservice.bench.mapping.mapstruct.MapStructMappers;
import com.github.vlachenal.webservice.bench.protobuf.ProtobufType;
import com.github.vlachenal.webservice.bench.rest.api.model.Mapper;


/**
 * Customer service REST endpoint
 *
 * @author Vincent Lachenal
 */
@RestController
@RequestMapping(path="/protobuf/customer")
public class CustomerProtobufController extends AbstractBenchService {

  // Attributes +
  /** Customer service */
  private final CustomerBusiness business;

  /** MapStruct mappers */
  private final MapStructMappers mapstruct;

  /** Dozer mapper */
  private final com.github.dozermapper.core.Mapper dozer;
  // Attributes -


  // Constructors +
  /**
   * {@link CustomerProtobufController} constructor
   *
   * @param stats the statistics cache to use
   * @param business the customer service to use
   */
  public CustomerProtobufController(final StatisticsCache stats,
                                    final CustomerBusiness business,
                                    final com.github.dozermapper.core.Mapper dozer,
                                    final MapStructMappers mapstruct) {
    super(stats);
    this.business = business;
    this.dozer = dozer;
    this.mapstruct = mapstruct;
  }
  // Constructors -


  // Methods +
  /**
   * Convert customer DTO to Thrift according to mapper
   *
   * @param dto the customer DTO
   * @param mapper the mapper
   *
   * @return the Thrift customer
   */
  private Customer toProtobuf(final CustomerDTO dto, final Mapper mapper) {
    Customer cust = null;
    switch(mapper) {
      case DOZER:
        cust = dozer.map(dto, Customer.class);
        break;
      case MAPSTRUCT:
        cust = mapstruct.protobuf().toProtobuf(dto);
        break;
      default:
        cust = CustomerBridge.toProtobuf(dto);
    }
    return cust;
  }

  /**
   * Convert Thrift customer to DTO according to mapper
   *
   * @param customer the Thrift customer
   * @param mapper the mapper
   *
   * @return the customer DTO
   */
  private CustomerDTO fromProtobuf(final Customer customer, final Mapper mapper) {
    CustomerDTO dto = null;
    switch(mapper) {
      case DOZER:
        dto = dozer.map(customer, CustomerDTO.class);
        break;
      case MAPSTRUCT:
        dto = mapstruct.protobuf().fromProtobuf(customer);
        break;
      default:
        dto = CustomerBridge.fromProtobuf(customer);
    }
    return dto;
  }

  /**
   * List all customers in database.<br>
   * Every filter is optional.
   *
   * @param requestSeq the request sequence header
   * @param mapper the mapper to use
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
  @RequestMapping(method=RequestMethod.GET,produces=ProtobufType.PROTOBUF_UTF8_VALUE)
  public ListAllResponse listCustomers(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq,
                                       @RequestHeader(name="mapper",required=false,defaultValue="MANUAL") final Mapper mapper,
                                       @RequestParam(name="first_name",required=false) final String firstName,
                                       @RequestParam(name="last_name",required=false) final String lastName,
                                       @RequestParam(name="email",required=false) @Email final String email,
                                       @RequestParam(name="birth_date",required=false) @DateTimeFormat(pattern="yyyy-MM-dd") final Date birthDate,
                                       @RequestParam(name="born_before",required=false) @DateTimeFormat(pattern="yyyy-MM-dd") final Date bornBefore,
                                       @RequestParam(name="born_after",required=false) @DateTimeFormat(pattern="yyyy-MM-dd") final Date bornAfter) {
    final CallDTO call = initializeCall(requestSeq, "list");
    ListAllResponse response;
    try {
      final SearchRequestDTO dto = new SearchRequestDTO();
      dto.setFirstName(firstName);
      dto.setLastName(lastName);
      dto.setEmail(email);
      dto.setBirthDate(birthDate);
      dto.setBornAfter(bornAfter);
      dto.setBornBefore(bornBefore);
      final List<Customer> customers = map(business.search(dto), mapper, this::toProtobuf);
      final ListAllResponse.Builder builder = ListAllResponse.newBuilder();
      if(customers != null) {
        builder.addAllCustomers(customers);
      }
      response = builder.build();
    } finally {
      registerCall(call);
    }
    return response;
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
  @RequestMapping(path="/{id}",method=RequestMethod.GET,produces=ProtobufType.PROTOBUF_UTF8_VALUE)
  public Customer get(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq,
                      @RequestHeader(name="mapper",required=false,defaultValue="MANUAL") final Mapper mapper,
                      @PathVariable("id") final String id) {
    final CallDTO call = initializeCall(requestSeq, "get");
    Customer customer;
    try {
      customer = map(business.getDetails(id), mapper, this::toProtobuf);
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
  @RequestMapping(method=RequestMethod.POST,consumes={ProtobufType.PROTOBUF_UTF8_VALUE},produces=MediaType.TEXT_PLAIN_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public String create(@RequestHeader(name="request_seq",required=false,defaultValue="-1") final int requestSeq,
                       @RequestHeader(name="mapper",required=false,defaultValue="MANUAL") final Mapper mapper,
                       @RequestBody final Customer customer) {
    final CallDTO call = initializeCall(requestSeq, "create");
    String uuid = null;
    try {
      uuid = business.create(map(customer, mapper, this::fromProtobuf));
    } finally {
      registerCall(call);
    }
    return uuid;
  }

  /**
   * Delete all customers
   */
  @RequestMapping(method=RequestMethod.DELETE)
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
    return "protobuf";
  }
  // Methods -

}
