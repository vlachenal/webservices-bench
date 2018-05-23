/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.manual;

import com.google.protobuf.Descriptors;
import com.google.protobuf.MessageOrBuilder;


/**
 * Protocol Buffers manual mapping utilities class
 *
 * @author Vincent Lachenal
 */
public class ProtobufBridgeUtils {

  /**
   * Get Protocol Buffers structure value ... sic
   *
   * @param struct the structure
   * @param fieldDesc the field descriptor
   * @param fieldIdx the field index
   *
   * @return the value or {@code null} if no set
   */
  @SuppressWarnings("unchecked")
  public static <T> T getValue(final MessageOrBuilder struct, final Descriptors.Descriptor fieldDesc, final int fieldIdx) {
    T val = null;
    final Descriptors.FieldDescriptor field = fieldDesc.findFieldByNumber(fieldIdx);
    if(struct.hasField(field)) {
      val = (T)struct.getField(field);
    }
    return val;
  }

}
