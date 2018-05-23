/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

  /**
   * Optional unit tests
   */
  @Test
  public void testOptional() {
    final List<String> emptyList = new ArrayList<>();
    final List<Integer> emptyInt = Optional.ofNullable(emptyList).map(l -> l.stream().map(str -> Integer.parseInt(str)).collect(Collectors.toList())).orElse(null);
    assertNotNull(emptyInt);
    assertTrue(emptyInt.isEmpty());
    final List<String> nullList = null;
    final List<Integer> nullInt = Optional.ofNullable(nullList).map(l -> l.stream().map(str -> Integer.parseInt(str)).collect(Collectors.toList())).orElse(null);
    assertNull(nullInt);
    final List<String> list = new ArrayList<>();
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    final List<Integer> intList = Optional.ofNullable(list).map(l -> l.stream().map(str -> Integer.parseInt(str)).collect(Collectors.toList())).orElse(null);
    assertNotNull(intList);
    assertFalse(intList.isEmpty());
    assertEquals(4, intList.size());
    LOG.info("{}", intList);
  }

  /**
   * Print item
   *
   * @param uuid the UUID
   * @param i the integer
   */
  private void printItem(final UUID uuid, final int i) {
    LOG.info("{}: {}", i, uuid);
  }

  /**
   * Lambda 'binding' unit test
   */
  @Test
  public void testLambdaBinding() {
    LOG.debug("Enter in testLambdaBinding");
    final List<Integer> list = Arrays.asList(1,2,3,4,5,6);
    list.forEach(i -> printItem(UUID.randomUUID(), i));
    LOG.debug("Exit testLambdaBinding");
  }
  // Tests -

}
