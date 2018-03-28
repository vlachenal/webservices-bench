/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.dto.CallBean;


/**
 * Statistics cache
 *
 * @author Vincent Lachenal
 */
@Component
public class StatisticsCache {

  // Attributes +
  /** Calls statistics */
  private final Map<String,CallBean> calls;
  // Attributes -


  // Constructors +
  /**
   * {@link StatisticsCache} default constructor
   */
  public StatisticsCache() {
    calls = new HashMap<>();
  }
  // Constructors -


  // Methods +
  /**
   * Register call into statistics call cache
   *
   * @param call the call to register
   */
  public synchronized void register(final CallBean call) {
    calls.put(call.getKey(), call);
  }

  /**
   * Merge call
   *
   * @param call the client call statistic
   *
   * @return the complete call if found, <code>null</code> otherwise
   */
  public CallBean mergeCall(final CallBean call) {
    final CallBean regCall = calls.get(call.getKey());
    if(regCall != null) {
      regCall.setClientStart(call.getClientStart());
      regCall.setClientEnd(call.getClientEnd());
      regCall.setOk(call.isOk());
      regCall.setErrMsg(call.getErrMsg());
    }
    return regCall;
  }

  /**
   * Clean cache
   */
  public synchronized void clean() {
    calls.clear();
  }
  // Methods -

}
