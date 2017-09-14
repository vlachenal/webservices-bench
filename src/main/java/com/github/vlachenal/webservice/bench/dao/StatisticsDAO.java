/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.vlachenal.webservice.bench.dao.bean.CallBean;
import com.github.vlachenal.webservice.bench.dao.bean.TestSuiteBean;


/**
 * Statistics DAO
 *
 * @author Vincent Lachenal
 */
@Component
public class StatisticsDAO {

  // Attributes +
  /** Insert test suite */
  private static final String INS_TEST_SUITE = "INSERT INTO TestSuite "
      + "(id, client_cpu, client_memory, client_jvm_version, client_jvm_vendor, client_os_name, "
      + "client_os_version, server_cpu, server_memory, server_jvm_version, server_jvm_vendor, "
      + "server_os_name, server_os_version, protocol, compression, nb_threads, comment, mapper) "
      + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

  /** Insert test call */
  private static final String INS_TEST_CALL = "INSERT INTO TestCall "
      + "(request_seq, test_suite_id, method, client_start, server_start, server_end, client_end, ok, error_message) "
      + "VALUES (?,?,?,?,?,?,?,?,?)";

  /** Server CPU */
  @Value("${cpu}")
  private String cpu;

  /** Server memory */
  @Value("${memory}")
  private String memory;

  /** JDBC template */
  private JdbcTemplate jdbc;
  // Attributes -


  // Methods +
  /**
   * Initialize JDBC template with datasource
   *
   * @param dataSource the datasource to use
   */
  @Autowired
  public void setDataSource(@Qualifier("DS.customer") final DataSource dataSource) {
    jdbc = new JdbcTemplate(dataSource);
  }

  /**
   * Save test suite in database
   *
   * @param testSuite the test suite to save
   */
  @Transactional
  public void save(final TestSuiteBean testSuite) {
    if(testSuite.getCalls() == null || testSuite.getCalls().isEmpty()) {
      return;
    }

    // Gather system informations +
    testSuite.setServerJvmVersion(System.getProperty("java.version"));
    testSuite.setServerJvmVendor(System.getProperty("java.vendor"));
    testSuite.setServerOsName(System.getProperty("os.name"));
    testSuite.setServerOsVersion(System.getProperty("os.version"));
    testSuite.setServerCpu(cpu);
    testSuite.setServerMemory(memory);
    // Gather system informations -

    final UUID uuid = UUID.randomUUID();

    // Insert new test suite +
    jdbc.update(INS_TEST_SUITE, new Object[] {
      uuid,
      testSuite.getClientCpu(),
      testSuite.getClientMemory(),
      testSuite.getClientJvmVersion(),
      testSuite.getClientJvmVendor(),
      testSuite.getClientOsName(),
      testSuite.getClientOsVersion(),
      testSuite.getServerCpu(),
      testSuite.getServerMemory(),
      testSuite.getServerJvmVersion(),
      testSuite.getServerJvmVendor(),
      testSuite.getServerOsName(),
      testSuite.getServerOsVersion(),
      testSuite.getProtocol(),
      testSuite.getCompression(),
      testSuite.getNbThreads(),
      testSuite.getComment(),
      testSuite.getMapper()
    });
    // Insert new test suite -

    // Insert calls +
    jdbc.batchUpdate(INS_TEST_CALL,new BatchPreparedStatementSetter() {
      @Override
      public void setValues(final PreparedStatement ps, final int i) throws SQLException {
        final CallBean call = testSuite.getCalls().get(i);
        ps.setInt(1, call.getSeq());
        ps.setObject(2, uuid);
        ps.setString(3, call.getMethod());
        ps.setLong(4, call.getClientStart());
        ps.setLong(5, call.getServerStart());
        ps.setLong(6, call.getServerEnd());
        ps.setLong(7, call.getClientEnd());
        ps.setBoolean(8, call.isOk());
        ps.setString(9, call.getErrMsg());
      }

      @Override
      public int getBatchSize() {
        return testSuite.getCalls().size();
      }
    });
    // Insert calls -
  }
  // Methods -

}
