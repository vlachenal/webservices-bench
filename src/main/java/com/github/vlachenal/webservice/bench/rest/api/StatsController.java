/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.rest.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.github.vlachenal.webservice.bench.business.StatisticsBusiness;
import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.errors.InvalidParametersException;
import com.github.vlachenal.webservice.bench.mapping.manual.TestSuiteBridge;
import com.github.vlachenal.webservice.bench.rest.api.dto.TestSuite;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * Statistics REST API entry point
 *
 * @author Vincent Lachenal
 */
@RestController
@RequestMapping(path="/rest/stats")
public class StatsController {

  // Attributes +
  /** Statistics business */
  private final StatisticsBusiness business;

  /** Statistics cache */
  private final StatisticsCache cache;
  // Attributes -


  // Constructors +
  /**
   * {@link StatsController} constructor
   *
   * @param dao the statistics business to use
   * @param cache the statistics cache
   */
  public StatsController(final StatisticsBusiness business, final StatisticsCache cache) {
    this.business = business;
    this.cache = cache;
  }
  // Constructors -


  // Methods +
  /**
   * Consolidate REST API statistics from cache
   *
   * @param test the client-side test suite to consolidate
   */
  @RequestMapping(method=RequestMethod.PUT,consumes={MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation("Consolidate client/server statistics")
  @ApiResponses(value= {
    @ApiResponse(code=201,message="Customer has been successfully created"),
    @ApiResponse(code=400,message="Missing or invalid field")
  })
  public void consolidate(@RequestBody final TestSuite test) {
    try {
      business.consolidate(TestSuiteBridge.fromRest(test));
    } catch(final InvalidParametersException e) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  /**
   * Purge statistics cache
   */
  @RequestMapping(method=RequestMethod.DELETE)
  @ApiOperation("Purge gathered statistcs")
  public void purge() {
    cache.clean();
  }
  // Methods -

}
