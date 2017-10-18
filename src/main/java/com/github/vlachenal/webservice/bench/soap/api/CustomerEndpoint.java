/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.soap.api;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vlachenal.webservice.bench.AbstractBenchService;
import com.github.vlachenal.webservice.bench.dao.CustomerDAO;
import com.github.vlachenal.webservice.bench.dao.bean.CallBean;
import com.github.vlachenal.webservice.bench.dao.bean.CustomerBean;
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
  @Autowired
  private org.dozer.Mapper dozer;

  /** MapStruct mappers */
  @Autowired
  private MapStructMappers mapstruct;

  /** Customer DAO */
  @Autowired
  private CustomerDAO dao;
  // Attributes -


  // Methods +
  /**
   * Parse header request
   *
   * @param headerElt the SOAP header element to parse
   *
   * @return the request header if found, <code>null</code> otherwise
   */
  private RequestHeader getRequestHeader(final SoapHeaderElement headerElt) {
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
    return header;
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
    final RequestHeader reqHeader = getRequestHeader(header);
    int reqSeq = -1;
    Mapper mapper = Mapper.MANUAL;
    if(reqHeader != null) {
      if(reqHeader.getRequestSeq() != null) {
        reqSeq = reqHeader.getRequestSeq();
      }
      if(reqHeader.getMapper() != null) {
        mapper = reqHeader.getMapper();
      }
    }
    final CallBean call = initializeCall(reqSeq, "list");
    final List<CustomerBean> custs = dao.listAll();
    List<Customer> customers = null;
    switch(mapper) {
      case DOZER:
        customers = custs.stream().map(from -> dozer.map(from, Customer.class)).collect(Collectors.toList());
        break;
      case MAPSTRUCT:
        customers = mapstruct.customer().beanListToSoap(custs);
        break;
      default:
        customers = CustomerBridge.toSoap(custs);
    }
    final ListCustomersResponse res = new ListCustomersResponse();
    res.getCustomer().addAll(customers);
    registerCall(call);
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
    final RequestHeader reqHeader = getRequestHeader(header);
    int reqSeq = -1;
    Mapper mapper = Mapper.MANUAL;
    if(reqHeader != null) {
      if(reqHeader.getRequestSeq() != null) {
        reqSeq = reqHeader.getRequestSeq();
      }
      if(reqHeader.getMapper() != null) {
        mapper = reqHeader.getMapper();
      }
    }
    final CallBean call = initializeCall(reqSeq, "get");
    UUID custId = null;
    try {
      custId = UUID.fromString(request.getId());
    } catch(final IllegalArgumentException e) {
      registerCall(call);
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, request.getId() + " is not an UUID");
    }
    final GetDetailsResponse res = new GetDetailsResponse();
    final CustomerBean cust = dao.getDetails(custId);
    Customer customer = null;
    switch(mapper) {
      case DOZER:
        customer = dozer.map(cust, Customer.class);
        break;
      case MAPSTRUCT:
        customer = mapstruct.customer().beanToSoap(cust);
        break;
      default:
        customer = CustomerBridge.toSoap(cust);
    }
    if(customer == null) {
      registerCall(call);
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND, request.getId() + " does not exist");
    }
    res.setCustomer(customer);
    registerCall(call);
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
    final RequestHeader reqHeader = getRequestHeader(header);
    int reqSeq = -1;
    Mapper mapper = Mapper.MANUAL;
    if(reqHeader != null) {
      if(reqHeader.getRequestSeq() != null) {
        reqSeq = reqHeader.getRequestSeq();
      }
      if(reqHeader.getMapper() != null) {
        mapper = reqHeader.getMapper();
      }
    }
    final CallBean call = initializeCall(reqSeq, "create");
    final Customer customer = request.getCustomer();
    // Customer structure checks +
    if(customer == null) {
      registerCall(call);
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Customer is null");
    }
    if(customer.getFirstName() == null || customer.getLastName() == null || customer.getBirthDate() == null) {
      String input = null;
      final ObjectMapper jsonMapper = new ObjectMapper();
      try {
        input = new String(jsonMapper.writeValueAsBytes(customer));
      } catch(final Exception e) {
        // Nothing to do
      }
      registerCall(call);
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Customer first_name, last_name and brith_date has to be set: " + input);
    }
    // Customer structure checks -
    // Address structure checks +
    final Address addr = customer.getAddress();
    if(addr != null
        && (addr.getLines() == null || addr.getLines().isEmpty()
        || addr.getZipCode() == null || addr.getCity() == null || addr.getCountry() == null)) {
      String input = null;
      final ObjectMapper jsonMapper = new ObjectMapper();
      try {
        input = new String(jsonMapper.writeValueAsBytes(customer));
      } catch(final Exception e) {
        // Nothing to do
      }
      registerCall(call);
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Address lines[0], zip_code, city and country has to be set: " + input);
    }
    // Address structure checks -
    CustomerBean cust = null;
    switch(mapper) {
      case DOZER:
        cust = dozer.map(customer, CustomerBean.class);
        break;
      case MAPSTRUCT:
        cust = mapstruct.customer().soapToBean(customer);
        break;
      default:
        cust = CustomerBridge.toBean(customer);
    }
    final String uuid = dao.create(cust);
    final CreateResponse res = new CreateResponse();
    res.setId(uuid);
    registerCall(call);
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
    dao.deleteAll();
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
