/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.soap.api;

import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;

import com.github.vlachenal.webservice.bench.AbstractBenchService;
import com.github.vlachenal.webservice.bench.business.CustomerBusiness;
import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.dto.CallDTO;
import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.dto.SearchRequestDTO;
import com.github.vlachenal.webservice.bench.mapping.manual.CustomerBridge;
import com.github.vlachenal.webservice.bench.mapping.mapstruct.MapStructMappers;


/**
 * Customer SOAP service endpoint
 *
 * @author Vincent Lachenal
 */
@Endpoint
public class CustomerEndpoint extends AbstractBenchService {

  // Attributes +
  /** Namespace URI */
  private static final String NAMESPACE_URI = "http://github.com/vlachenal/webservices-bench";

  /** Request header */
  private static final String REQ_HEADER = "{" + NAMESPACE_URI + "}request-header";

  /** Dozer mapper */
  private final com.github.dozermapper.core.Mapper dozer;

  /** MapStruct mappers */
  private final MapStructMappers mapstruct;

  /** Customer service */
  private final CustomerBusiness business;
  // Attributes -


  // Constructors +
  /**
   * {@link CustomerEndpoint} constructor
   *
   * @param stats the statistics cache to use
   * @param business the customer DAO to use
   * @param dozer the Dozer mapper to use
   * @param mapstruct the MapStruct mappers to use
   */
  public CustomerEndpoint(final StatisticsCache stats,
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
   * Parse header request
   *
   * @param headerElt the SOAP header element to parse
   *
   * @return the request header if found, {@code null} otherwise
   */
  private RequestHeader getHeader(final SoapHeaderElement headerElt) {
    RequestHeader header = null;
    if(headerElt != null) {
      try {
        final JAXBContext context = JAXBContext.newInstance(RequestHeader.class);
        final Unmarshaller unmarshaller = context.createUnmarshaller();
        header = (RequestHeader)unmarshaller.unmarshal(headerElt.getSource());
      } catch(final JAXBException e) {
        throw new RuntimeException("Unable to unmarshall error: " + e.getMessage(), e);
      }
    }
    if(header == null) {
      header = new RequestHeader();
      header.setRequestSeq(-1);
      header.setMapper(Mapper.MANUAL);
    } else {
      header.setRequestSeq(Optional.ofNullable(header.getRequestSeq()).orElse(-1));
      header.setMapper(Optional.ofNullable(header.getMapper()).orElse(Mapper.MANUAL));
    }
    return header;
  }

  /**
   * Convert customer DTO to SOAP according to mapper
   *
   * @param dto the customer DTO
   * @param mapper the mapper
   *
   * @return the SOAP customer
   */
  private Customer toSoap(final CustomerDTO dto, final Mapper mapper) {
    Customer customer = null;
    switch(mapper) {
      case DOZER:
        customer = dozer.map(dto, Customer.class);
        break;
      case MAPSTRUCT:
        customer = mapstruct.customer().toSoap(dto);
        break;
      default:
        customer = CustomerBridge.toSoap(dto);
    }
    return customer;
  }

  /**
   * Convert customer DTO to SOAP according to mapper
   *
   * @param dto the customer DTO
   * @param mapper the mapper
   *
   * @return the SOAP customer
   */
  private CustomerDTO fromSoap(final Customer customer, final Mapper mapper) {
    CustomerDTO dto = null;
    switch(mapper) {
      case DOZER:
        dto = dozer.map(customer, CustomerDTO.class);
        break;
      case MAPSTRUCT:
        dto = mapstruct.customer().fromSoap(customer);
        break;
      default:
        dto = CustomerBridge.fromSoap(customer);
    }
    return dto;
  }

  /**
   * List all customers in database
   *
   * @param header the request sequence header
   * @param request the (empty) request
   *
   * @return customers
   */
  @PayloadRoot(namespace=NAMESPACE_URI, localPart="listCustomersRequest")
  @ResponsePayload
  public ListCustomersResponse listCustomers(@SoapHeader(value=REQ_HEADER) final SoapHeaderElement header, @RequestPayload final ListCustomersRequest request) {
    final RequestHeader reqHeader = getHeader(header);
    final CallDTO call = initializeCall(reqHeader.getRequestSeq(), "list");
    final ListCustomersResponse res = new ListCustomersResponse();
    try {
      res.getCustomer().addAll(map(business.search(new SearchRequestDTO()), reqHeader.getMapper(), this::toSoap));
    } finally {
      registerCall(call);
    }
    return res;
  }

  /**
   * Retrieve customer details
   *
   * @param header the request sequence header
   * @param request the request
   *
   * @return the customer details
   */
  @PayloadRoot(namespace=NAMESPACE_URI, localPart="getDetailsRequest")
  @ResponsePayload
  public GetDetailsResponse get(@SoapHeader(value=REQ_HEADER) final SoapHeaderElement header, @RequestPayload final GetDetailsRequest request) {
    final RequestHeader reqHeader = getHeader(header);
    final CallDTO call = initializeCall(reqHeader.getRequestSeq(), "get");
    final GetDetailsResponse res = new GetDetailsResponse();
    try {
      res.setCustomer(map(business.getDetails(request.getId()), reqHeader.getMapper(), this::toSoap));
    } finally {
      registerCall(call);
    }
    return res;
  }

  /**
   * Create customer
   *
   * @param header the request sequence header
   * @param request the customer to create
   *
   * @return the new customer's identifier
   */
  @PayloadRoot(namespace=NAMESPACE_URI, localPart="createRequest")
  @ResponsePayload
  public CreateResponse create(@SoapHeader(value=REQ_HEADER) final SoapHeaderElement header, @RequestPayload final CreateRequest request) {
    final RequestHeader reqHeader = getHeader(header);
    final CallDTO call = initializeCall(reqHeader.getRequestSeq(), "create");
    final CreateResponse res = new CreateResponse();
    try {
      res.setId(business.create(map(request.getCustomer(), reqHeader.getMapper(), this::fromSoap)));
    } finally {
      registerCall(call);
    }
    return res;
  }

  /**
   * Delete all customers
   *
   * @param header the request sequence header
   * @param request the (empty) request
   *
   * @return the (empty) response
   */
  @PayloadRoot(namespace=NAMESPACE_URI, localPart="deleteAllRequest")
  @ResponsePayload
  public DeleteAllResponse deleteAll(@SoapHeader(value=REQ_HEADER) final SoapHeaderElement header, @RequestPayload final DeleteAllRequest request) {
    business.deleteAll();
    return new DeleteAllResponse();
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.AbstractBenchService#getProtocol()
   */
  @Override
  public String getProtocol() {
    return "soap";
  }
  // Methods -

}
