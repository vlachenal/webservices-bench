/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.bridge;

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
  public static CallBean toBean(final com.github.vlachenal.webservice.bench.rest.api.bean.ClientCall call) {
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
  public static CallBean toBean(final com.github.vlachenal.webservice.bench.thrift.api.ClientCall call) {
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
  // Methods -

}
