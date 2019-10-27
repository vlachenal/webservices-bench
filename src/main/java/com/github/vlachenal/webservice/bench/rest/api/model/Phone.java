/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * Phone number
 *
 * @author Vincent Lachenal
 */
@Schema(description="Customer's phone")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Phone {

  /**
   * Phone type
   *
   * @author Vincent Lachenal
   */
  public enum Type {
    LANDLINE,
    MOBILE
  }

  // Attributes +
  /** Customer identifier */
  @Schema(description="Phone's UUID",example="4064dddd-19b2-435d-97bd-6851ff728821")
  private String id;

  /** Phone type */
  @Schema(description="Phone type (LANDLINE or MOBILE)",required=true,example="LANDLINE")
  private Type type;

  /** Phone number */
  @Schema(description="Phone number",required=true,example="+33636656565")
  private String number;

  /** Customer's identifier: used for HATEOAS only */
  @JsonIgnore
  private String customerId;
  // Attributes -


  // Accessors +
  /**
   * Phone identifier getter
   *
   * @return the identifier
   */
  public final String getId() {
    return id;
  }

  /**
   * Phone identifier setter
   *
   * @param id the identifier to set
   */
  public final void setId(final String id) {
    this.id = id;
  }

  /**
   * Phone type getter
   *
   * @return the type
   */
  public Type getType() {
    return type;
  }

  /**
   * Phone type setter
   *
   * @param type the type to set
   */
  public void setType(final Type type) {
    this.type = type;
  }

  /**
   * Phone number getter
   *
   * @return the phone number
   */
  public String getNumber() {
    return number;
  }

  /**
   * Phone number setter
   *
   * @param number the phone number to set
   */
  public void setNumber(final String number) {
    this.number = number;
  }

  /**
   * Customer's identifier getter
   *
   * @return the identifier
   */
  public final String getCustomerId() {
    return customerId;
  }

  /**
   * Customer's identifier setter
   *
   * @param customerId the identifier to set
   */
  public final void setCustomerId(final String customerId) {
    this.customerId = customerId;
  }
  // Accessors -

}

