/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.dao;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.vlachenal.webservice.bench.dto.CallDTO;
import com.github.vlachenal.webservice.bench.dto.TestSuiteDTO;


/**
 * {@link StatisticsDAO} unit tests
 *
 * @author Vincent Lachenal
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StatisticsDAOTest {

  // Attributes +
  /** {@link CustomerDAOTest} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(StatisticsDAOTest.class);

  /** Customer DAO */
  @Autowired
  private StatisticsDAO dao;

  /** Customer datasource */
  @Qualifier("ds.customer")
  @Autowired
  private DataSource dataSource;

  /** CPU information */
  @Value("${hardware.cpu}")
  private String cpu;

  /** Memory information */
  @Value("${hardware.memory}")
  private String memory;
  // Attributes -


  // Tests +
  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.StatisticsDAO#save(com.github.vlachenal.webservice.bench.dto.TestSuiteDTO)}.
   */
  @Test
  public void testSave() {
    LOG.debug("Enter in testSave");
    final TestSuiteDTO testSuite = new TestSuiteDTO();
    testSuite.setClientCpu(cpu);
    testSuite.setClientMemory(memory);
    testSuite.setClientJvmVersion(System.getProperty("java.version"));
    testSuite.setClientJvmVendor(System.getProperty("java.vendor"));
    testSuite.setClientOsName(System.getProperty("os.name"));
    testSuite.setClientOsVersion(System.getProperty("os.version"));
    testSuite.setProtocol("rest");
    testSuite.setComment("toto");
    testSuite.setNbThreads(1000);

    // Gather system informations +
    testSuite.setServerJvmVersion(System.getProperty("java.version"));
    testSuite.setServerJvmVendor(System.getProperty("java.vendor"));
    testSuite.setServerOsName(System.getProperty("os.name"));
    testSuite.setServerOsVersion(System.getProperty("os.version"));
    testSuite.setServerCpu(cpu);
    testSuite.setServerMemory(memory);
    // Gather system informations -

    final ArrayList<CallDTO> calls = new ArrayList<>();
    CallDTO call = new CallDTO();
    call.setSeq(1);
    call.setClientStart(1);
    call.setServerStart(2);
    call.setServerEnd(3);
    call.setClientEnd(4);
    call.setOk(true);
    call.setMethod("get");
    calls.add(call);
    call = new CallDTO();
    call.setSeq(2);
    call.setClientStart(5);
    call.setServerStart(6);
    call.setServerEnd(7);
    call.setClientEnd(8);
    call.setOk(true);
    call.setMethod("get");
    calls.add(call);
    testSuite.setCalls(calls);
    testSuite.setMapper("manual");
    dao.save(testSuite);
    LOG.debug("Exit testSave");
  }
  // Tests -

}
