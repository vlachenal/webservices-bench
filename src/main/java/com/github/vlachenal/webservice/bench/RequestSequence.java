/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench;


/**
 * Request sequence management through {@link ThreadLocal}
 *
 * @author Vincent Lachenal
 */
public final class RequestSequence {

  // Attributes +
  /** {@link RequestSequence} {@link ThreadLocal} */
  private static final ThreadLocal<Integer> REQ_SEQ_THREAD_LOCAL = new ThreadLocal<Integer>() {
    @Override
    protected Integer initialValue() {
      return -1;
    }
  };
  // Attributes -


  // Constructors +
  /**
   * {@link RequestSequence} private constructor
   */
  private RequestSequence() {
    // Nothing to do
  }
  // Constructors -


  // Methods +
  /**
   * Request sequence setter
   *
   * @param requestSeq the request sequence to set
   */
  public static void setRequestSequence(final Integer requestSeq) {
    REQ_SEQ_THREAD_LOCAL.set(requestSeq);
  }

  /**
   * Remove context from thread to avoid memory leaks
   */
  public static void unset() {
    REQ_SEQ_THREAD_LOCAL.remove();
  }

  /**
   * Request sequence getter
   *
   * @return the Request sequence
   */
  public static Integer getRequestSequence() {
    return REQ_SEQ_THREAD_LOCAL.get();
  }
  // Methods -

}
