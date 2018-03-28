/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.manual;

import com.github.vlachenal.webservice.bench.dao.bean.CallBean;


/**
 * Test call conversion utility class
 *
 * @author Vincent Lachenal
 */
public final class CallBridge {

  // Constructors +
  /**
   * {@link CallBridge} private construtor
   */
  private CallBridge() {
    // Nothing to do
  }
  // Constructors -


  // Methods +
  /**
   * Convert REST client call to bean
   *
   * @param call the REST client call
   *
   * @return the bean
   */
  public static CallBean fromRest(final com.github.vlachenal.webservice.bench.rest.api.bean.ClientCall call) {
    CallBean bean = null;
    if(call != null) {
      bean = new CallBean();
      bean.setClientStart(call.getClientStart());
      bean.setClientEnd(call.getClientEnd());
      bean.setMethod(call.getMethod());
      bean.setProtocol(call.getProtocol());
      bean.setSeq(call.getRequestSeq());
      bean.setOk(call.isOk());
      bean.setErrMsg(call.getErrMsg());
    }
    return bean;
  }

  /**
   * Convert Thrift client call to bean
   *
   * @param call the Thrift client call
   *
   * @return the bean
   */
  public static CallBean fromThrift(final com.github.vlachenal.webservice.bench.thrift.api.ClientCall call) {
    CallBean bean = null;
    if(call != null) {
      bean = new CallBean();
      bean.setClientStart(call.getClientStart());
      bean.setClientEnd(call.getClientEnd());
      bean.setMethod(call.getMethod());
      bean.setProtocol(call.getProtocol());
      bean.setSeq(call.getRequestSeq());
      bean.setOk(call.isOk());
      bean.setErrMsg(call.getErrMsg());
    }
    return bean;
  }

  /**
   * Convert SOAP client call to bean
   *
   * @param call the SOAP client call
   *
   * @return the bean
   */
  public static CallBean fromSoap(final com.github.vlachenal.webservice.bench.soap.api.ClientCall call) {
    CallBean bean = null;
    if(call != null) {
      bean = new CallBean();
      bean.setClientStart(call.getClientStart());
      bean.setClientEnd(call.getClientEnd());
      bean.setMethod(call.getMethod());
      bean.setProtocol(call.getProtocol());
      bean.setSeq(call.getRequestSeq());
      bean.setOk(call.isOk());
      bean.setErrMsg(call.getErrMsg());
    }
    return bean;
  }

  /**
   * Convert Protocol buffer client call to bean
   *
   * @param call the Protocol buffer client call
   *
   * @return the bean
   */
  public static CallBean fromProtobuf(final com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall call) {
    CallBean bean = null;
    if(call != null) {
      bean = new CallBean();
      if(call.hasField(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.getDescriptor().findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.CLIENTSTART_FIELD_NUMBER))) {
        bean.setClientStart(call.getClientStart());
      }
      if(call.hasField(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.getDescriptor().findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.CLIENTEND_FIELD_NUMBER))) {
        bean.setClientEnd(call.getClientEnd());
      }
      if(call.hasField(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.getDescriptor().findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.METHOD_FIELD_NUMBER))) {
        bean.setMethod(call.getMethod());
      }
      if(call.hasField(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.getDescriptor().findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.PROTOCOL_FIELD_NUMBER))) {
        bean.setProtocol(call.getProtocol());
      }
      if(call.hasField(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.getDescriptor().findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.REQUESTSEQ_FIELD_NUMBER))) {
        bean.setSeq(call.getRequestSeq());
      }
      if(call.hasField(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.getDescriptor().findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.OK_FIELD_NUMBER))) {
        bean.setOk(call.getOk());
      }
      if(call.hasField(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.getDescriptor().findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.ERRMSG_FIELD_NUMBER))) {
        bean.setErrMsg(call.getErrMsg());
      }
    }
    return bean;
  }
  // Methods -

}
