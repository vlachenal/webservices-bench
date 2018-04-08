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
   * @param filedIdx the field index
   *
   * @return the value or <code>null</code> if no set
   */
  @SuppressWarnings("unchecked")
  public static <T> T getValue(final MessageOrBuilder struct, final Descriptors.Descriptor fieldDesc, final int filedIdx) {
    T val = null;
    final Descriptors.FieldDescriptor field = fieldDesc.findFieldByNumber(filedIdx);
    if(struct.hasField(field)) {
      val = (T)struct.getField(field);
    }
    return val;
  }

}
