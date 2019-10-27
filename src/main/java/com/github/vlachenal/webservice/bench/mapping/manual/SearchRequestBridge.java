/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.manual;

import java.util.Date;

import com.github.vlachenal.webservice.bench.dto.SearchRequestDTO;
import com.github.vlachenal.webservice.bench.soap.api.ListCustomersRequest;
import com.github.vlachenal.webservice.bench.thrift.api.ListAllRequest;


/**
 * Search request conversion utility class
 *
 * @author Vincent Lachenal
 */
public class SearchRequestBridge {

  // Constructors +
  /**
   * {@link SearchRequestBridge} private constructor
   */
  private SearchRequestBridge() {
    // Nothing to do
  }
  // Constructors -


  // Methods +
  /**
   * Convert SOAP request to DTO
   *
   * @param soap the SOAP request
   *
   * @return the DTO
   */
  public static SearchRequestDTO fromSoap(final ListCustomersRequest soap) {
    SearchRequestDTO dto = null;
    if(soap != null) {
      dto = new SearchRequestDTO();
      dto.setFirstName(soap.getFirstName());
      dto.setLastName(soap.getLastName());
      dto.setEmail(soap.getEmail());
      if(soap.getBirthDate() != null) {
        dto.setBirthDate(soap.getBirthDate().toGregorianCalendar().getTime());
      }
      if(soap.getBornBefore() != null) {
        dto.setBornBefore(soap.getBornBefore().toGregorianCalendar().getTime());
      }
      if(soap.getBornAfter() != null) {
        dto.setBornAfter(soap.getBornAfter().toGregorianCalendar().getTime());
      }
    }
    return dto;
  }

  /**
   * Convert Thrift request to DTO
   *
   * @param thrift the Thrift request
   *
   * @return the DTO
   */
  public static SearchRequestDTO fromThrift(final ListAllRequest thrift) {
    SearchRequestDTO dto = null;
    if(thrift != null) {
      dto = new SearchRequestDTO();
      dto.setFirstName(thrift.getFirstName());
      dto.setFirstName(thrift.getFirstName());
      dto.setLastName(thrift.getLastName());
      dto.setEmail(thrift.getEmail());
      if(thrift.getBirthDate() != 0) {
        dto.setBirthDate(new Date(thrift.getBirthDate()));
      }
      if(thrift.getBornBefore() != 0) {
        dto.setBornBefore(new Date(thrift.getBornBefore()));
      }
      if(thrift.getBornAfter() != 0) {
        dto.setBornAfter(new Date(thrift.getBornAfter()));
      }
      dto.setIds(thrift.getIds());
    }
    return dto;
  }
  // Methods -

}
