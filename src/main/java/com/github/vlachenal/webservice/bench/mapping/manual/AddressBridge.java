/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.manual;

import com.github.vlachenal.webservice.bench.dto.AddressDTO;
import com.google.protobuf.Descriptors.Descriptor;


/**
 * Address conversion utility class
 *
 * @author Vincent Lachenal
 */
public final class AddressBridge {

  // Attributes +
  /** Protocol Buffer address descriptor */
  private static final Descriptor ADDRESS_DESC = com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address.getDescriptor();
  // Attributes -


  // Constructors +
  /**
   * {@link AddressBridge} private construtor
   */
  private AddressBridge() {
    // Nothing to do
  }
  // Constructors -


  // Methods +
  /**
   * Convert addres DTO to REST/JSON structure
   *
   * @param dto the DTO to convert
   *
   * @return the JSON structure
   */
  public static com.github.vlachenal.webservice.bench.rest.api.dto.Address toRest(final AddressDTO dto) {
    com.github.vlachenal.webservice.bench.rest.api.dto.Address address = null;
    if(dto != null) {
      address = new com.github.vlachenal.webservice.bench.rest.api.dto.Address();
      address.setLines(dto.getLines());
      address.setZipCode(dto.getZipCode());
      address.setCity(dto.getCity());
      address.setCountry(dto.getCountry());
    }
    return address;
  }

  /**
   * Convert REST address to DTI
   *
   * @param address the REST address
   *
   * @return the DTI
   */
  public static AddressDTO fromRest(final com.github.vlachenal.webservice.bench.rest.api.dto.Address address) {
    AddressDTO dto = null;
    if(address != null) {
      dto = new AddressDTO();
      dto.setLines(address.getLines());
      dto.setZipCode(address.getZipCode());
      dto.setCity(address.getCity());
      dto.setCountry(address.getCountry());
    }
    return dto;
  }

  /**
   * Convert address DTO to Thift structure
   *
   * @param dto the DTO to convert
   *
   * @return the Thrift structure
   */
  public static com.github.vlachenal.webservice.bench.thrift.api.Address toThrift(final AddressDTO dto) {
    com.github.vlachenal.webservice.bench.thrift.api.Address address = null;
    if(dto != null) {
      address = new com.github.vlachenal.webservice.bench.thrift.api.Address();
      address.setLines(dto.getLines());
      address.setZipCode(dto.getZipCode());
      address.setCity(dto.getCity());
      address.setCountry(dto.getCountry());
    }
    return address;
  }

  /**
   * Convert Thrift address to DTO
   *
   * @param address the Thrift address
   *
   * @return the DTO
   */
  public static AddressDTO fromThrift(final com.github.vlachenal.webservice.bench.thrift.api.Address address) {
    AddressDTO dto = null;
    if(address != null) {
      dto = new AddressDTO();
      dto.setLines(address.getLines());
      dto.setZipCode(address.getZipCode());
      dto.setCity(address.getCity());
      dto.setCountry(address.getCountry());
    }
    return dto;
  }

  /**
   * Convert addres DTO to SOAP structure
   *
   * @param dto the DTO to convert
   *
   * @return the SOAP structure
   */
  public static com.github.vlachenal.webservice.bench.soap.api.Address toSoap(final AddressDTO dto) {
    com.github.vlachenal.webservice.bench.soap.api.Address address = null;
    if(dto != null) {
      address = new com.github.vlachenal.webservice.bench.soap.api.Address();
      if(dto.getLines() != null) {
        address.getLines().addAll(dto.getLines());
      }
      address.setZipCode(dto.getZipCode());
      address.setCity(dto.getCity());
      address.setCountry(dto.getCountry());
    }
    return address;
  }

  /**
   * Convert SOAP address to DTO
   *
   * @param address the SOAP address
   *
   * @return the DTO
   */
  public static AddressDTO fromSoap(final com.github.vlachenal.webservice.bench.soap.api.Address address) {
    AddressDTO dto = null;
    if(address != null) {
      dto = new AddressDTO();
      dto.setLines(address.getLines());
      dto.setZipCode(address.getZipCode());
      dto.setCity(address.getCity());
      dto.setCountry(address.getCountry());
    }
    return dto;
  }

  /**
   * Convert address DTO to Protocol buffer structure
   *
   * @param dto the DTO to convert
   *
   * @return the Protocol buffer structure
   */
  public static com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address toProtobuf(final AddressDTO dto) {
    com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address address = null;
    if(dto != null) {
      final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address.Builder builder = com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address.newBuilder();
      if(dto.getLines() != null) {
        builder.addAllLines(dto.getLines());
      }
      if(dto.getZipCode() != null) {
        builder.setZipCode(dto.getZipCode());
      }
      if(dto.getCity() != null) {
        builder.setCity(dto.getCity());
      }
      if(dto.getCountry() != null) {
        builder.setCountry(dto.getCountry());
      }
      address = builder.build();
    }
    return address;
  }

  /**
   * Convert Protocol buffer address to DTO
   *
   * @param address the Protocol buffer address
   *
   * @return the DTO
   */
  public static AddressDTO fromProtobuf(final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address address) {
    AddressDTO dto = null;
    if(address != null) {
      dto = new AddressDTO();
      if(address.getRepeatedFieldCount(ADDRESS_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address.LINES_FIELD_NUMBER)) > 0) {
        dto.setLines(address.getLinesList());
      }
      if(address.hasField(ADDRESS_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address.ZIPCODE_FIELD_NUMBER))) {
        dto.setZipCode(address.getZipCode());
      }
      if(address.hasField(ADDRESS_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address.CITY_FIELD_NUMBER))) {
        dto.setCity(address.getCity());
      }
      if(address.hasField(ADDRESS_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address.COUNTRY_FIELD_NUMBER))) {
        dto.setCountry(address.getCountry());
      }
    }
    return dto;
  }
  // Methods -

}
