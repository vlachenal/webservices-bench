/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.protobuf.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.vlachenal.webservice.bench.business.StatisticsBusiness;
import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.mapping.manual.TestSuiteBridge;
import com.github.vlachenal.webservice.bench.protobuf.ProtobufType;


/**
 * Statistics REST API entry point
 *
 * @author Vincent Lachenal
 */
@RestController
@RequestMapping(path="/protobuf/stats")
public class StatsProtobufController {

  // Attributes +
  /** Statistics business */
  private final StatisticsBusiness business;

  /** Statistics cache */
  private final StatisticsCache cache;
  // Attributes -


  // Constructors +
  /**
   * {@link StatsProtobufController} constructor
   *
   * @param business the statistics business to use
   * @param cache the statistics cache to use
   */
  public StatsProtobufController(final StatisticsBusiness business, final StatisticsCache cache) {
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
  @RequestMapping(method=RequestMethod.PUT,consumes={ProtobufType.PROTOBUF_UTF8_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  public void consolidate(@RequestBody final TestSuite test) {
    business.consolidate(TestSuiteBridge.fromProtobuf(test));
  }

  /**
   * Purge statistics cache
   */
  @RequestMapping(method=RequestMethod.DELETE)
  public void purge() {
    cache.clean();
  }
  // Methods -

}
