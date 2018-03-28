/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench;

import static org.junit.Assert.assertNotNull;

import java.util.Enumeration;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Environment information collect unit tests
 *
 * @author Vincent Lachenal
 */
public class EnvironmentTest {

  // Attributes +
  /** {@link EnvironmentTest} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(EnvironmentTest.class);
  // Attributes -


  // Tests +
  /**
   * Get environment informations
   */
  @Test
  public void testGetEnvironmentInformations() {
    for(final Map.Entry<String, String> entry : System.getenv().entrySet()) {
      LOG.info("ENV: {}={}", entry.getKey(), entry.getValue());
    }
    final Enumeration<?> keys = System.getProperties().propertyNames();
    while(keys.hasMoreElements()) {
      final String key = (String)keys.nextElement();
      LOG.info("PROP: {}={}", key, System.getProperty(key));
    }
    final String version = System.getProperty("java.version");
    assertNotNull("JAVA version is null", version);
    final String vendor = System.getProperty("java.vendor");
    assertNotNull("JAVA vendor is null", vendor);
    final String os = System.getProperty("os.name");
    assertNotNull("OS is null", os);
    final String osVersion = System.getProperty("os.version");
    assertNotNull("OS versions is null", osVersion);
    LOG.info("Summary: JRE {} by {} on {} {}", version, vendor, os, osVersion);
  }
  // Tests -

}
