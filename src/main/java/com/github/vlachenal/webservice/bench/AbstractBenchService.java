/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.dto.CallDTO;


/**
 * Abstract bench service
 *
 * @author Vincent Lachenal
 */
public abstract class AbstractBenchService {

  // Attributes +
  /** Statistics cache */
  private final StatisticsCache stats;
  // Attributes -


  // Constructors +
  /**
   * {@link AbstractBenchService} constructor
   *
   * @param stats the statistics cache to use
   */
  protected AbstractBenchService(final StatisticsCache stats) {
    this.stats = stats;
  }
  // Constructors -


  // Methods +
  /**
   * Protocol getter
   *
   * @return the protocol
   */
  public abstract String getProtocol();

  /**
   * Initialize call
   *
   * @param requestSeq the request sequence
   * @param method the method
   *
   * @return the call if requestSeq is not equal to {@code -1}, {@code null} otherwise
   */
  protected CallDTO initializeCall(final int requestSeq, final String method) {
    CallDTO call = null;
    if(requestSeq != -1) {
      final long start = System.nanoTime();
      call = new CallDTO();
      call.setSeq(requestSeq);
      call.setServerStart(start);
      call.setProtocol(getProtocol());
      call.setMethod(method);
    }
    return call;
  }

  /**
   * Register call if not null in cache
   *
   * @param call the call to register
   */
  protected void registerCall(final CallDTO call) {
    if(call != null) {
      call.setServerEnd(System.nanoTime());
      stats.register(call);
    }
  }

  /**
   * Map source to destination
   *
   * @param <S> the source type
   * @param <D> the destination type
   * @param <M> the mapper to use
   *
   * @param source the object to map
   * @param mapper the mapper
   * @param func the mapping function
   *
   * @return the mapped object
   */
  protected <D,S,M> List<D> map(final Collection<S> source, final M mapper, final FuncMapper<D,S,M> func) {
    return source.stream().map(src -> map(src, mapper, func)).collect(Collectors.toList());
  }

  /**
   * Map source to destination
   *
   * @param <S> the source type
   * @param <D> the destination type
   * @param <M> the mapper to use
   *
   * @param source the object to map
   * @param mapper the mapper
   * @param func the mapping function
   *
   * @return the mapped object
   */
  protected <D,S,M> D map(final S source, final M mapper, final FuncMapper<D,S,M> func) {
    return func.map(source, mapper);
  }
  // Methods -


  // Functions +
  /**
   * @param <D> destination type
   * @param <S> source type
   * @param <M> mapper
   *
   * @author Vincent Lachenal
   */
  @FunctionalInterface
  protected interface FuncMapper<D,S,M> {
    /**
     * Map object
     *
     * @param source source
     * @param mapper mapper as {@link String}
     *
     * @return the mapped object
     */
    D map(S source, M mapper);
  }
  // Functions -

}
