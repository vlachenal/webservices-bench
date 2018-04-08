/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.manual;

import com.github.vlachenal.webservice.bench.dto.TestSuiteDTO;
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
   * Convert REST test suite to DTO
   *
   * @param test the REST test suite
   *
   * @return the DTO
   */
  public static TestSuiteDTO fromRest(final com.github.vlachenal.webservice.bench.rest.api.dto.TestSuite test) {
    TestSuiteDTO dto = null;
    if(test != null) {
      dto = new TestSuiteDTO();
      dto.setClientCpu(test.getCpu());
      dto.setClientMemory(test.getMemory());
      dto.setClientJvmVersion(test.getJvm());
      dto.setClientJvmVendor(test.getVendor());
      dto.setClientOsName(test.getOsFamily());
      dto.setClientOsVersion(test.getOsVersion());
      dto.setNbThreads(test.getNbThread());
      dto.setCompression(test.getCompression());
      dto.setProtocol(test.getProtocol());
      dto.setComment(test.getComment());
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
      dto.setMapper(mapper);
      dto.setCalls(CallBridge.fromRest(test.getCalls()));
    }
    return dto;
  }

  /**
   * Convert Thrift test suite to DTO
   *
   * @param test the Thrift test suite
   *
   * @return the DTO
   */
  public static TestSuiteDTO fromThrift(final com.github.vlachenal.webservice.bench.thrift.api.TestSuite test) {
    TestSuiteDTO dto = null;
    if(test != null) {
      dto = new TestSuiteDTO();
      dto.setClientCpu(test.getCpu());
      dto.setClientMemory(test.getMemory());
      dto.setClientJvmVersion(test.getJvm());
      dto.setClientJvmVendor(test.getVendor());
      dto.setClientOsName(test.getOsFamily());
      dto.setClientOsVersion(test.getOsVersion());
      dto.setNbThreads(test.getNbThread());
      dto.setProtocol(test.getProtocol());
      dto.setCompression(test.getCompression());
      dto.setComment(test.getComment());
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
      dto.setMapper(mapper);
      dto.setCalls(CallBridge.fromThrift(test.getCalls()));
    }
    return dto;
  }

  /**
   * Convert SOAP test suite to DTO
   *
   * @param test the SOAP test suite
   *
   * @return the DTO
   */
  public static TestSuiteDTO fromSoap(final com.github.vlachenal.webservice.bench.soap.api.TestSuite test) {
    TestSuiteDTO dto = null;
    if(test != null) {
      dto = new TestSuiteDTO();
      dto.setClientCpu(test.getCpu());
      dto.setClientMemory(test.getMemory());
      dto.setClientJvmVersion(test.getJvm());
      dto.setClientJvmVendor(test.getVendor());
      dto.setClientOsName(test.getOsFamily());
      dto.setClientOsVersion(test.getOsVersion());
      dto.setNbThreads(test.getNbThread());
      dto.setProtocol(test.getProtocol());
      dto.setCompression(test.getCompression());
      dto.setComment(test.getComment());
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
      dto.setMapper(mapper);
      dto.setCalls(CallBridge.fromSoap(test.getCalls()));
    }
    return dto;
  }

  /**
   * Convert Protocol buffer test suite to DTO
   *
   * @param test the Protocol buffer test suite
   *
   * @return the DTO
   */
  public static TestSuiteDTO fromProtobuf(final com.github.vlachenal.webservice.bench.protobuf.api.TestSuite test) {
    TestSuiteDTO dto = null;
    if(test != null) {
      dto = new TestSuiteDTO();

      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.CPU_FIELD_NUMBER))) {
        dto.setClientCpu(test.getCpu());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.MEMORY_FIELD_NUMBER))) {
        dto.setClientMemory(test.getMemory());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.JVM_FIELD_NUMBER))) {
        dto.setClientJvmVersion(test.getJvm());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.VENDOR_FIELD_NUMBER))) {
        dto.setClientJvmVendor(test.getVendor());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.OSFAMILY_FIELD_NUMBER))) {
        dto.setClientOsName(test.getOsFamily());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.OSVERSION_FIELD_NUMBER))) {
        dto.setClientOsVersion(test.getOsVersion());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.NBTHREAD_FIELD_NUMBER))) {
        dto.setNbThreads(test.getNbThread());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.PROTOCOL_FIELD_NUMBER))) {
        dto.setProtocol(test.getProtocol());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.COMPRESSION_FIELD_NUMBER))) {
        dto.setCompression(test.getCompression());
      }
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.COMMENT_FIELD_NUMBER))) {
        dto.setComment(test.getComment());
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
      dto.setMapper(mapper);
      if(test.hasField(TESTSUITE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.CALLS_FIELD_NUMBER))) {
        dto.setCalls(CallBridge.fromProtobuf(test.getCallsList()));
      }
    }
    return dto;
  }
  // Methods -

}
