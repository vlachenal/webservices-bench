/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.protobuf.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.github.vlachenal.webservice.bench.dao.bean.CallBean;
import com.github.vlachenal.webservice.bench.dao.bean.TestSuiteBean;
import com.github.vlachenal.webservice.bench.mapping.manual.CallBridge;
import com.github.vlachenal.webservice.bench.mapping.manual.TestSuiteBridge;
import com.github.vlachenal.webservice.bench.protobuf.ProtobufType;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


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
  @Autowired
  private StatisticsDAO dao;

  /** Statistics cache */
  @Autowired
  private StatisticsCache cache;
  // Attributes -


  // Methods +
  /**
   * Consolidate REST API statistics from cache
   *
   * @param test the client-side test suite to consolidate
   */
  @RequestMapping(method=RequestMethod.PUT,consumes={MediaType.APPLICATION_JSON_UTF8_VALUE,ProtobufType.PROTOBUF_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation("Consolidate client/server statistics")
  @ApiResponses(value= {
    @ApiResponse(code=201,message="Customer has been successfully created"),
    @ApiResponse(code=400,message="Missing or invalid field")
  })
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
    final TestSuiteBean suite = TestSuiteBridge.toBean(test);
    final ArrayList<CallBean> calls = new ArrayList<>();
    for(final TestSuite.ClientCall ccall : test.getCallsList()) {
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
   * Purge statistics cache
   */
  @RequestMapping(method=RequestMethod.DELETE)
  @ApiOperation("Purge gathered statistcs")
  public void purge() {
    cache.clean();
  }
  // Methods -

}
