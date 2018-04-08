/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.manual;

import java.util.List;
import java.util.stream.Collectors;

import com.github.vlachenal.webservice.bench.dto.CallDTO;


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
   * Convert REST client call to DTO
   *
   * @param call the REST client call
   *
   * @return the DTO
   */
  public static CallDTO fromRest(final com.github.vlachenal.webservice.bench.rest.api.dto.ClientCall call) {
    CallDTO dto = null;
    if(call != null) {
      dto = new CallDTO();
      dto.setClientStart(call.getClientStart());
      dto.setClientEnd(call.getClientEnd());
      dto.setMethod(call.getMethod());
      dto.setProtocol(call.getProtocol());
      dto.setSeq(call.getRequestSeq());
      dto.setOk(call.isOk());
      dto.setErrMsg(call.getErrMsg());
    }
    return dto;
  }

  /**
   * Convert REST client calls list to DTO
   *
   * @param calls the REST client calls
   *
   * @return the DTOs
   */
  public static List<CallDTO> fromRest(final List<com.github.vlachenal.webservice.bench.rest.api.dto.ClientCall> calls) {
    List<CallDTO> dto = null;
    if(calls != null) {
      dto = calls.stream().map(call -> fromRest(call)).collect(Collectors.toList());
    }
    return dto;
  }

  /**
   * Convert Thrift client call to DTO
   *
   * @param call the Thrift client call
   *
   * @return the DTO
   */
  public static CallDTO fromThrift(final com.github.vlachenal.webservice.bench.thrift.api.ClientCall call) {
    CallDTO dto = null;
    if(call != null) {
      dto = new CallDTO();
      dto.setClientStart(call.getClientStart());
      dto.setClientEnd(call.getClientEnd());
      dto.setMethod(call.getMethod());
      dto.setProtocol(call.getProtocol());
      dto.setSeq(call.getRequestSeq());
      dto.setOk(call.isOk());
      dto.setErrMsg(call.getErrMsg());
    }
    return dto;
  }

  /**
   * Convert Thrift client calls list to DTO
   *
   * @param calls the Thrift client calls
   *
   * @return the DTOs
   */
  public static List<CallDTO> fromThrift(final List<com.github.vlachenal.webservice.bench.thrift.api.ClientCall> calls) {
    List<CallDTO> dto = null;
    if(calls != null) {
      dto = calls.stream().map(call -> fromThrift(call)).collect(Collectors.toList());
    }
    return dto;
  }

  /**
   * Convert SOAP client call to DTO
   *
   * @param call the SOAP client call
   *
   * @return the DTO
   */
  public static CallDTO fromSoap(final com.github.vlachenal.webservice.bench.soap.api.ClientCall call) {
    CallDTO dto = null;
    if(call != null) {
      dto = new CallDTO();
      dto.setClientStart(call.getClientStart());
      dto.setClientEnd(call.getClientEnd());
      dto.setMethod(call.getMethod());
      dto.setProtocol(call.getProtocol());
      dto.setSeq(call.getRequestSeq());
      dto.setOk(call.isOk());
      dto.setErrMsg(call.getErrMsg());
    }
    return dto;
  }

  /**
   * Convert SOAP client calls list to DTO
   *
   * @param calls the SOAP client calls
   *
   * @return the DTOs
   */
  public static List<CallDTO> fromSoap(final List<com.github.vlachenal.webservice.bench.soap.api.ClientCall> calls) {
    List<CallDTO> dto = null;
    if(calls != null) {
      dto = calls.stream().map(call -> fromSoap(call)).collect(Collectors.toList());
    }
    return dto;
  }

  /**
   * Convert Protocol buffer client call to DTO
   *
   * @param call the Protocol buffer client call
   *
   * @return the DTO
   */
  public static CallDTO fromProtobuf(final com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall call) {
    CallDTO dto = null;
    if(call != null) {
      dto = new CallDTO();
      if(call.hasField(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.getDescriptor().findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.CLIENTSTART_FIELD_NUMBER))) {
        dto.setClientStart(call.getClientStart());
      }
      if(call.hasField(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.getDescriptor().findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.CLIENTEND_FIELD_NUMBER))) {
        dto.setClientEnd(call.getClientEnd());
      }
      if(call.hasField(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.getDescriptor().findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.METHOD_FIELD_NUMBER))) {
        dto.setMethod(call.getMethod());
      }
      if(call.hasField(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.getDescriptor().findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.PROTOCOL_FIELD_NUMBER))) {
        dto.setProtocol(call.getProtocol());
      }
      if(call.hasField(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.getDescriptor().findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.REQUESTSEQ_FIELD_NUMBER))) {
        dto.setSeq(call.getRequestSeq());
      }
      if(call.hasField(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.getDescriptor().findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.OK_FIELD_NUMBER))) {
        dto.setOk(call.getOk());
      }
      if(call.hasField(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.getDescriptor().findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall.ERRMSG_FIELD_NUMBER))) {
        dto.setErrMsg(call.getErrMsg());
      }
    }
    return dto;
  }

  /**
   * Convert Protocol Buffers client calls list to DTO
   *
   * @param calls the Protocol Buffers client calls
   *
   * @return the DTOs
   */
  public static List<CallDTO> fromProtobuf(final List<com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall> calls) {
    List<CallDTO> dto = null;
    if(calls != null) {
      dto = calls.stream().map(call -> fromProtobuf(call)).collect(Collectors.toList());
    }
    return dto;
  }
  // Methods -

}
