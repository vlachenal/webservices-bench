/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.soap.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.github.vlachenal.webservice.bench.business.StatisticsBusiness;
import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.errors.InvalidParametersException;
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

  /** Statistics business */
  private final StatisticsBusiness business;

  /** Statistics cache */
  private final StatisticsCache cache;
  // Attributes -


  // Constructors +
  /**
   * {@link StatisticsEndpoint} constructor
   *
   * @param dao the statistics business to use
   * @param cache the statistics cache to use
   */
  public StatisticsEndpoint(final StatisticsBusiness business, final StatisticsCache cache) {
    this.business = business;
    this.cache = cache;
  }
  // Constructors -


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
    try {
      business.consolidate(TestSuiteBridge.fromSoap(request.getTest()));
    } catch(final InvalidParametersException e) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
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
