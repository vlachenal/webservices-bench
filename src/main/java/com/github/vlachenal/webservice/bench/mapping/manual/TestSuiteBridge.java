/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.manual;

import com.github.vlachenal.webservice.bench.dao.bean.TestSuiteBean;
import com.google.protobuf.Descriptors.Descriptor;


/**
 * Test suite conversion utility
 *
 * @author Vincent Lachenal
 */
public final class TestSuiteBridge {

  // Attributes +
  /** Protocol Buffer address descriptor */
  private static final Descriptor TESTSUITE_DESC = com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.getDescriptor();
  // Attributes -

  // Constructors +
  /**
   * {@link TestSuiteBridge} private constructor
   */
  private TestSuiteBridge() {
    // Nothing to do
  }
  // Constructors -


  // Methods +
  /**
   * Convert REST test suite to bean
   *
   * @param test the REST test suite
   *
   * @return the bean
   */
  public static TestSuiteBean fromRest(final com.github.vlachenal.webservice.bench.rest.api.bean.TestSuite test) {
    TestSuiteBean bean = null;
    if(test != null) {
      bean = new TestSuiteBean();
      bean.setClientCpu(test.getCpu());
      bean.setClientMemory(test.getMemory());
      bean.setClientJvmVersion(test.getJvm());
      bean.setClientJvmVendor(test.getVendor());
      bean.setClientOsName(test.getOsFamily());
      bean.setClientOsVersion(test.getOsVersion());
      bean.setNbThreads(test.getNbThread());
      bean.setCompression(test.getCompression());
      bean.setProtocol(test.getProtocol());
      bean.setComment(test.getComment());
      String mapper = "manual";
      if(test.getMapper() != null) {
        switch(test.getMapper()) {
          case DOZER:
            mapper = "dozer";
            break;
          case MAPSTRUCT:
            mapper = "mapstruct";
            break;
          default:
            // Nothing to do
        }
      }
      bean.setMapper(mapper);
    }
    return bean;
  }

  /**
   * Convert Thrift test suite to bean
   *
   * @param test the Thrift test suite
   *
   * @return the bean
   */
  public static TestSuiteBean fromThrift(final com.github.vlachenal.webservice.bench.thrift.api.TestSuite test) {
    TestSuiteBean bean = null;
    if(test != null) {
      bean = new TestSuiteBean();
      bean.setClientCpu(test.getCpu());
      bean.setClientMemory(test.getMemory());
      bean.setClientJvmVersion(test.getJvm());
      bean.setClientJvmVendor(test.getVendor());
      bean.setClientOsName(test.getOsFamily());
      bean.setClientOsVersion(test.getOsVersion());
      bean.setNbThreads(test.getNbThread());
      bean.setProtocol(test.getProtocol());
      bean.setCompression(test.getCompression());
      bean.setComment(test.getComment());
      String mapper = "manual";
      if(test.getMapper() != null) {
        switch(test.getMapper()) {
          case DOZER:
            mapper = "dozer";
            break;
          case MAPSTRUCT:
            mapper = "mapstruct";
            break;
          default:
            // Nothing to do
        }
      }
      bean.setMapper(mapper);
    }
    return bean;
  }

  /**
   * Convert SOAP test suite to bean
   *
   * @param test the SOAP test suite
   *
   * @return the bean
   */
  public static TestSuiteBean fromSoap(final com.github.vlachenal.webservice.bench.soap.api.TestSuite test) {
    TestSuiteBean bean = null;
    if(test != null) {
      bean = new TestSuiteBean();
      bean.setClientCpu(test.getCpu());
      bean.setClientMemory(test.getMemory());
      bean.setClientJvmVersion(test.getJvm());
      bean.setClientJvmVendor(test.getVendor());
      bean.setClientOsName(test.getOsFamily());
      bean.setClientOsVersion(test.getOsVersion());
      bean.setNbThreads(test.getNbThread());
      bean.setProtocol(test.getProtocol());
      bean.setCompression(test.getCompression());
      bean.setComment(test.getComment());
      String mapper = "manual";
      if(test.getMapper() != null) {
        switch(test.getMapper()) {
          case DOZER:
            mapper = "dozer";
            break;
          case MAPSTRUCT:
            mapper = "mapstruct";
            break;
          default:
            // Nothing to do
        }
      }
      bean.setMapper(mapper);
    }
    return bean;
  }

  /**
   * Convert Protocol buffer test suite to bean
   *
   * @param test the Protocol buffer test suite
   *
   * @return the bean
   */
  public static TestSuiteBean fromProtobuf(final com.github.vlachenal.webservice.bench.protobuf.api.TestSuite test) {
    TestSuiteBean bean = null;
    if(test != null) {
      bean = new TestSuiteBean();

      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.CPU_FIELD_NUMBER))) {
        bean.setClientCpu(test.getCpu());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.MEMORY_FIELD_NUMBER))) {
        bean.setClientMemory(test.getMemory());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.JVM_FIELD_NUMBER))) {
        bean.setClientJvmVersion(test.getJvm());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.VENDOR_FIELD_NUMBER))) {
        bean.setClientJvmVendor(test.getVendor());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.OSFAMILY_FIELD_NUMBER))) {
        bean.setClientOsName(test.getOsFamily());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.OSVERSION_FIELD_NUMBER))) {
        bean.setClientOsVersion(test.getOsVersion());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.NBTHREAD_FIELD_NUMBER))) {
        bean.setNbThreads(test.getNbThread());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.PROTOCOL_FIELD_NUMBER))) {
        bean.setProtocol(test.getProtocol());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.COMPRESSION_FIELD_NUMBER))) {
        bean.setCompression(test.getCompression());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.COMMENT_FIELD_NUMBER))) {
        bean.setComment(test.getComment());
      }
      String mapper = "manual";
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.MAPPER_FIELD_NUMBER))) {
        switch(test.getMapper()) {
          case DOZER:
            mapper = "dozer";
            break;
          case MAPSTRUCT:
            mapper = "mapstruct";
            break;
          default:
            // Nothing to do
        }
      }
      bean.setMapper(mapper);
    }
    return bean;
  }
  // Methods -

}
