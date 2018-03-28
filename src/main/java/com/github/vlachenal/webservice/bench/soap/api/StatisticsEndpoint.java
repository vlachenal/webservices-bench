/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.soap.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.dao.StatisticsDAO;
import com.github.vlachenal.webservice.bench.dto.CallDTO;
import com.github.vlachenal.webservice.bench.dto.TestSuiteDTO;
import com.github.vlachenal.webservice.bench.mapping.manual.CallBridge;
import com.github.vlachenal.webservice.bench.mapping.manual.TestSuiteBridge;


/**
 * Statistics SOAP service
 *
 * @author Vincent Lachenal
 */
@Endpoint
public class StatisticsEndpoint {

  // Attributes +
  /** Namespace URI */
  private static final String NAMESPACE_URI = "http://github.com/vlachenal/webservices-bench";

  /** Statistics DAO */
  @Autowired
  private StatisticsDAO dao;

  /** Statistics cache */
  @Autowired
  private StatisticsCache cache;
  // Attributes -


  // Methods +
  /**
   * Consolidate REST API statistics from cache
   *
   * @param test the client-side test suite to consolidate
   *
   * @return the (empty) response
   */
  @PayloadRoot(namespace=NAMESPACE_URI, localPart="consolidateRequest")
  @ResponsePayload
  public ConsolidateResponse consolidate(@RequestPayload final ConsolidateRequest request) {
    final TestSuite test = request.getTest();
    if(test == null) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test suite is null");
    }
    if(test.getCpu() == null || test.getMemory() == null || test.getJvm() == null
        || test.getVendor() == null || test.getOsFamily() == null || test.getOsVersion() == null) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid test suite information");
    }
    if(test.getCalls() == null || test.getCalls().isEmpty()) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "No calls to consolidate");
    }
    final TestSuiteDTO suite = TestSuiteBridge.fromSoap(test);
    final ArrayList<CallDTO> calls = new ArrayList<>();
    for(final ClientCall ccall : test.getCalls()) {
      CallDTO call = CallBridge.fromSoap(ccall);
      if(ccall == null) {
        continue;
      }
      call = cache.mergeCall(call);
      if(call != null) {
        calls.add(call);
      }
    }
    suite.setCalls(calls);
    dao.save(suite);
    return new ConsolidateResponse();
  }

  /**
   * Purge statistics cache
   *
   * @param request the (empty) request
   *
   * @return the (empty) response
   */
  @PayloadRoot(namespace=NAMESPACE_URI, localPart="purgeRequest")
  @ResponsePayload
  public PurgeResponse purge(@RequestPayload final PurgeRequest request) {
    cache.clean();
    return new PurgeResponse();
  }
  // Methods -

}
