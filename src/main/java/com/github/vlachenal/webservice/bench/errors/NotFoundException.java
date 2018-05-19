/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;


/**
 * Data not found exception
 *
 * @author Vincent Lachenal
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
@SoapFault(faultCode=FaultCode.CUSTOM,customFaultCode="{http://github.com/vlachenal/webservices-bench}NOT_FOUND")
public class NotFoundException extends RuntimeException {

  /**
   * {@link NotFoundException} default constructor
   */
  public NotFoundException() {
    super();
  }

  /**
   * {@link NotFoundException} constructor
   *
   * @param messages the error message
   */
  public NotFoundException(final String messages) {
    super(messages);
  }

  /**
   * {@link NotFoundException} constructor
   *
   * @param cause the error cause
   */
  public NotFoundException(final Throwable cause) {
    super(cause);
  }

  /**
   * {@link NotFoundException} constructor
   *
   * @param message the error message
   * @param cause the error cause
   */
  public NotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
