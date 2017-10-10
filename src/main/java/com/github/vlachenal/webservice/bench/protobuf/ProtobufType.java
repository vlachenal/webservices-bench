/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.protobuf;

import org.springframework.http.MediaType;


/**
 * Protocol buffer media type
 *
 * @author Vincent Lachenal
 */
public final class ProtobufType {

  // Attributes +
  /** Protocol buffer media type defintion */
  public static final MediaType PROTOBUF = new MediaType("application", "x-protobuf");

  /** Protocol buffer media type string */
  public static final String PROTOBUF_VALUE = "application/x-protobuf";
  // Attributes -


  // Constructors +
  /**
   * {@link ProtobufType} private constructor
   */
  private ProtobufType() {
    // Nothing to do
  }
  // Constructors -

}