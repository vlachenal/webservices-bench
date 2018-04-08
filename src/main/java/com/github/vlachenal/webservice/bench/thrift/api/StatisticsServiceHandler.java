/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.thrift.api;

import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.business.StatisticsBusiness;
import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.errors.InvalidParametersException;
import com.github.vlachenal.webservice.bench.mapping.manual.TestSuiteBridge;


/**
 * Thrift statistiscs service handler
 *
 * @author Vincent Lachenal
 */
@Component
public class StatisticsServiceHandler implements StatsService.Iface {

  // Attributes +
  /** Statistics service */
  private final StatisticsBusiness business;

  /** Statistics cache */
  private final StatisticsCache cache;
  // Attributes -


  // Constructors +
  /**
   * {@link StatisticsServiceHandler} constructor
   *
   * @param dao the statistics service to use
   * @param cache the statistics cache to use
   */
  public StatisticsServiceHandler(final StatisticsBusiness business, final StatisticsCache cache) {
    this.business = business;
    this.cache = cache;
  }
  // Constructors -


  // Methods +
  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.thrift.api.StatsService.Iface#consolidate(com.github.vlachenal.webservice.bench.thrift.api.TestSuite)
   */
  @Override
  public void consolidate(final TestSuite test) throws StatsException, TException {
    try {
      business.consolidate(TestSuiteBridge.fromThrift(test));
    } catch(final InvalidParametersException e) {
      throw new StatsException(e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.thrift.api.StatsService.Iface#purge()
   */
  @Override
  public void purge() throws StatsException, TException {
    cache.clean();
  }
  // Methods -

}
