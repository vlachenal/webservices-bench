/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.thrift.api;

import java.util.ArrayList;

import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.dao.StatisticsDAO;
import com.github.vlachenal.webservice.bench.dto.CallDTO;
import com.github.vlachenal.webservice.bench.dto.TestSuiteDTO;
import com.github.vlachenal.webservice.bench.mapping.manual.CallBridge;
import com.github.vlachenal.webservice.bench.mapping.manual.TestSuiteBridge;


/**
 * Thrift statistiscs service handler
 *
 * @author Vincent Lachenal
 */
@Component
public class StatisticsServiceHandler implements StatsService.Iface {

  // Attributes +
  /** Statistics DAO */
  private final StatisticsDAO dao;

  /** Statistics cache */
  private final StatisticsCache cache;
  // Attributes -


  // Constructors +
  /**
   * {@link StatisticsServiceHandler} constructor
   *
   * @param dao the statistics DAO to use
   * @param cache the statistics cache to use
   */
  public StatisticsServiceHandler(final StatisticsDAO dao, final StatisticsCache cache) {
    this.dao = dao;
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
    if(test == null) {
      throw new StatsException("Test suite is null");
    }
    if(test.getCpu() == null || test.getMemory() == null || test.getJvm() == null
        || test.getVendor() == null || test.getOsFamily() == null || test.getOsVersion() == null) {
      throw new StatsException("Invalid test suite information");
    }
    if(test.getCalls() == null || test.getCalls().isEmpty()) {
      throw new StatsException("No calls to consolidate");
    }
    final TestSuiteDTO suite = TestSuiteBridge.fromThrift(test);
    final ArrayList<CallDTO> calls = new ArrayList<>();
    for(final ClientCall ccall : test.getCalls()) {
      CallDTO call = CallBridge.fromThrift(ccall);
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
