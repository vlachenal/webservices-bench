/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.rest.api.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * Customer
 *
 * @author Vincent Lachenal
 */
@Schema(description="Customer's informations")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

  // Attributes +
  /** Customer identifier */
  @Schema(description="Customer's UUID",example="cecaaf9c-0edd-4968-89ec-3e8f6017c7b9")
  private String id;

  /** Customer first name */
  @Schema(description="Customer's first name",required=true,example="Chuck")
  @JsonProperty(value="first_name")
  private String firstName;

  /* Customer last name */
  @Schema(description="Customer's last name",required=true,example="Norris")
  @JsonProperty(value="last_name")
  private String lastName;

  /** Customer brith date */
  @Schema(description="Customer's birth date",required=true,example="1940-03-10")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @JsonProperty(value="birth_date")
  private Date birthDate;

  /** Customer address */
  @Schema(description="Customer's address")
  private Address address;

  /** Customer email address */
  @Email
  @NotEmpty
  @Schema(description="Customer's email address",example="chuck.norris@yopmail.com")
  private String email;

  /** Customer phone numbers */
  @Schema(description="Customer's phone numbers")
  private List<Phone> phones;
  // Attributes -


  // Accessors +
  /**
   * Identifier getter
   *
   * @return the identifier
   */
  public String getId() {
    return id;
  }

  /**
   * Identifier setter
   *
   * @param id the identifier to set
   */
  public void setId(final String id) {
    this.id = id;
  }

  /**
   * First name getter
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * First name setter
   *
   * @param firstName the first name to set
   */
  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  /**
   * Last name getter
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Last name setter
   *
   * @param lastName the last name to set
   */
  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  /**
   * Birth date getter
   *
   * @return the birth date
   */
  public Date getBirthDate() {
    return birthDate;
  }

  /**
   * Birth date setter
   *
   * @param birthDate the birth date
   */
  public void setBirthDate(final Date birthDate) {
    this.birthDate = birthDate;
  }


  /**
   * Address getter
   *
   * @return the address
   */
  public Address getAddress() {
    return address;
  }

  /**
   * Address setter
   *
   * @param address the address to set
   */
  public void setAddress(final Address address) {
    this.address = address;
  }

  /**
   * Email address getter
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Email address setter
   *
   * @param email the email to set
   */
  public void setEmail(final String email) {
    this.email = email;
  }

  /**
   * Phone numbers getter
   *
   * @return the phone numbers
   */
  public List<Phone> getPhones() {
    return phones;
  }

  /**
   * Phone numbers setter
   *
   * @param phones the phone numbers to set
   */
  public void setPhones(final List<Phone> phones) {
    this.phones = phones;
  }
  // Accessors -

}
