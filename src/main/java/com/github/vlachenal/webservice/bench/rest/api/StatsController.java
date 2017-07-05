/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.rest.api.bean.TestSuite;


/**
 * Statistics REST API entry point
 *
 * @author Vincent Lachenal
 */
@RestController
@RequestMapping(path="/rest/stats")
public class StatsController {

  // Attributes +
  /** Statistics cache */
  @Autowired
  private StatisticsCache cache;
  // Attributes -


  // Methods +
  /**
   * Consolidate REST API statistics from cache
   *
   * @param the client-side test suite to consolidate
   */
  @RequestMapping(method=RequestMethod.PUT,consumes="application/json")
  public void consolidate(final TestSuite test) {
    // TODO get statistics
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
