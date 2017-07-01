/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.thrift.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;

import com.github.vlachenal.webservice.bench.RequestSequence;


/**
 * Thrift servlet which will retrieve request sequence from header
 *
 * @author Vincent Lachenal
 */
@SuppressWarnings("serial")
public class TRequestSeqServlet extends TServlet {

  // Constructors +
  /**
   * {@link TRequestSeqServlet} constructor
   *
   * @param processor the Thrift processor to use
   * @param protocolFactory the Thrift protocol factory to use
   */
  public TRequestSeqServlet(final TProcessor processor, final TProtocolFactory protocolFactory) {
    super(processor, protocolFactory);
  }
  // Constructors -


  // Methods +
  /**
   * Retrieve request sequence from HTTP headers.<br>
   * {@inheritDoc}
   *
   * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    final String seq = req.getHeader("request_seq");
    int requestSeq = -1;
    if(seq != null) {
      try {
        requestSeq = Integer.parseInt(seq);
      } catch(final Exception e) {
        // Nothing to do
      }
    }
    RequestSequence.setRequestSequence(requestSeq);
    try {
      super.service(req, resp);
    } finally {
      RequestSequence.unset();
    }
  }
  // Methods -

}
