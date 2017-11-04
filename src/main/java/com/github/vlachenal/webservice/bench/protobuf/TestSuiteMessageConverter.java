/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.github.vlachenal.webservice.bench.protobuf.api.TestSuite;
import com.google.protobuf.util.JsonFormat;


/**
 * Protobuf test suite message converter
 *
 * @author Vincent Lachenal
 */
public class TestSuiteMessageConverter extends ProtobufMessageConverter<TestSuite> {

  // Methods +
  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.protobuf.ProtobufMessageConverter#checkClass(java.lang.Class)
   */
  @Override
  protected boolean checkClass(final Class<?> clazz) {
    return clazz.isAssignableFrom(TestSuite.class);
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.protobuf.ProtobufMessageConverter#fromProtobuf(java.io.InputStream)
   */
  @Override
  protected TestSuite fromProtobuf(final InputStream input) throws IOException {
    return TestSuite.parseFrom(input);
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.protobuf.ProtobufMessageConverter#fromJSON(java.io.InputStream)
   */
  @Override
  protected TestSuite fromJSON(final InputStream input) throws IOException {
    final TestSuite.Builder builder = TestSuite.newBuilder();
    JsonFormat.parser().merge(new InputStreamReader(input), builder);
    return builder.build();
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.protobuf.ProtobufMessageConverter#writeJSON(com.google.protobuf.GeneratedMessageV3, java.io.OutputStream)
   */
  @Override
  protected void writeJSON(final TestSuite tests, final OutputStream out) throws IOException {
    out.write(JsonFormat.printer().print(tests).getBytes());
  }
  // Methods -

}
