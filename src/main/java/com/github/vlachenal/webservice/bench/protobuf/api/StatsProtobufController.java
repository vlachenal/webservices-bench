/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.protobuf.api;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.dao.StatisticsDAO;
import com.github.vlachenal.webservice.bench.dto.CallDTO;
import com.github.vlachenal.webservice.bench.dto.TestSuiteDTO;
import com.github.vlachenal.webservice.bench.mapping.manual.CallBridge;
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
  /** Statistics DAO */
  private final StatisticsDAO dao;

  /** Statistics cache */
  private final StatisticsCache cache;
  // Attributes -


  // Constructors +
  /**
   * {@link StatsProtobufController} constructor
   *
   * @param dao the statistics DAO to use
   * @param cache the statistics cache to use
   */
  public StatsProtobufController(final StatisticsDAO dao, final StatisticsCache cache) {
    this.dao = dao;
    this.cache = cache;
  }
  // Constructors -


  // Methods +
  /**
   * Consolidate REST API statistics from cache
   *
   * @param test the client-side test suite to consolidate
   */
  @RequestMapping(method=RequestMethod.PUT,consumes={MediaType.APPLICATION_JSON_UTF8_VALUE,ProtobufType.PROTOBUF_UTF8_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  public void consolidate(@RequestBody final TestSuite test) {
    if(test == null) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test suite is null");
    }
    if(test.getCpu() == null || test.getMemory() == null || test.getJvm() == null
        || test.getVendor() == null || test.getOsFamily() == null || test.getOsVersion() == null) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid test suite information");
    }
    if(test.getCallsList() == null || test.getCallsList().isEmpty()) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "No calls to consolidate");
    }
    final TestSuiteDTO suite = TestSuiteBridge.fromProtobuf(test);
    final ArrayList<CallDTO> calls = new ArrayList<>();
    for(final TestSuite.ClientCall ccall : test.getCallsList()) {
      CallDTO call = CallBridge.fromProtobuf(ccall);
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
   * Purge statistics cache
   */
  @RequestMapping(method=RequestMethod.DELETE)
  public void purge() {
    cache.clean();
  }
  // Methods -

}
