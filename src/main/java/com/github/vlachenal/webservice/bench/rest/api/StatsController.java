/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.rest.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.vlachenal.webservice.bench.business.StatisticsBusiness;
import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.mapping.manual.TestSuiteBridge;
import com.github.vlachenal.webservice.bench.rest.api.model.TestSuite;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


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
  @PutMapping(consumes={MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(description="Consolidate client/server statistics")
  @ApiResponse(responseCode="201",description="Customer has been successfully created")
  @ApiResponse(responseCode="400",description="Missing or invalid field")
  public void consolidate(@RequestBody final TestSuite test) {
    business.consolidate(TestSuiteBridge.fromRest(test));
  }

  /**
   * Purge statistics cache
   */
  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(description="Purge gathered statistcs")
  public void purge() {
    cache.clean();
  }
  // Methods -

}
