/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.thrift.api;

import java.util.ArrayList;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.bridge.CallBridge;
import com.github.vlachenal.webservice.bench.bridge.TestSuiteBridge;
import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.dao.StatisticsDAO;
import com.github.vlachenal.webservice.bench.dao.bean.CallBean;
import com.github.vlachenal.webservice.bench.dao.bean.TestSuiteBean;


/**
 * Thrift statistiscs service handler
 *
 * @author Vincent Lachenal
 */
@Component
public class StatisticsServiceHandler implements StatsService.Iface {

  // Attributes +
  /** Statistics DAO */
  @Autowired
  private StatisticsDAO dao;

  /** Statistics cache */
  @Autowired
  private StatisticsCache cache;
  // Attributes -


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
    final TestSuiteBean suite = TestSuiteBridge.toBean(test);
    final ArrayList<CallBean> calls = new ArrayList<>();
    for(final ClientCall ccall : test.getCalls()) {
      CallBean call = CallBridge.toBean(ccall);
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
