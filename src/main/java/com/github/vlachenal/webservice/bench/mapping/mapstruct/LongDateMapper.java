/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.mapstruct;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * Long <=> Date converter
 *
 * @author Vincent Lachenal
 */
@Component
public class LongDateMapper {

  /**
   * Convert date as long
   *
   * @param date the date
   *
   * @return the long
   */
  public Long asLong(final Date date) {
    return (date == null) ? 0 : date.getTime();
  }

  /**
   * Convert long as date
   *
   * @param date the long
   *
   * @return the date
   */
  public Date asDate(final Long date) {
    return (date == null) ? null : new Date(date);
  }

}
