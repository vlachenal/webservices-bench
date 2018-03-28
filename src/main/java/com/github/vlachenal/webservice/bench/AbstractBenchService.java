/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.dto.CallDTO;

/**
 * Abstract bench service
 *
 * @author Vincent Lachenal
 */
public abstract class AbstractBenchService {

  // Attributes +
  /** Statistics cache */
  @Autowired
  private StatisticsCache stats;
  // Attributes -


  // Methods +
  /**
   * Protocol getter
   *
   * @return the protocol
   */
  public abstract String getProtocol();

  /**
   * Initialize call
   *
   * @param requestSeq the request sequence
   * @param method the method
   *
   * @return the call if requestSeq is not equal to <code>-1</code>, <code>null</code> otherwise
   */
  protected CallDTO initializeCall(final int requestSeq, final String method) {
    CallDTO call = null;
    if(requestSeq != -1) {
      final long start = System.nanoTime();
      call = new CallDTO();
      call.setSeq(requestSeq);
      call.setServerStart(start);
      call.setProtocol(getProtocol());
      call.setMethod(method);
    }
    return call;
  }

  /**
   * Register call if not null in cache
   *
   * @param call the call to register
   */
  protected void registerCall(final CallDTO call) {
    if(call != null) {
      call.setServerEnd(System.nanoTime());
      stats.register(call);
    }
  }
  // Methods -

}
