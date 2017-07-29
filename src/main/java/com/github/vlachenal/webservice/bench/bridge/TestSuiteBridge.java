/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.bridge;

import com.github.vlachenal.webservice.bench.dao.bean.TestSuiteBean;


/**
 * Test suite conversion utility
 *
 * @author Vincent Lachenal
 */
public final class TestSuiteBridge {

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
  public static TestSuiteBean toBean(final com.github.vlachenal.webservice.bench.rest.api.bean.TestSuite test) {
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
  public static TestSuiteBean toBean(final com.github.vlachenal.webservice.bench.thrift.api.TestSuite test) {
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
  public static TestSuiteBean toBean(final com.github.vlachenal.webservice.bench.soap.api.TestSuite test) {
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
    }
    return bean;
  }
  // Methods -

}
