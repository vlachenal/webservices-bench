/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;

import com.github.vlachenal.webservice.bench.protobuf.api.Customer;
import com.google.protobuf.util.JsonFormat;


/**
 * Protobuf customer message converter
 *
 * @author Vincent Lachenal
 */
public class CustomerMessageConverter extends ProtobufMessageConverter<Customer> {

  // Methods +
  /**
   * {@inheritDoc}
   *
   * @see org.springframework.http.converter.HttpMessageConverter#read(java.lang.Class, org.springframework.http.HttpInputMessage)
   */
  @Override
  public Customer read(final Class<? extends Customer> clazz, final HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
    Customer customer = null;
    if(MediaType.APPLICATION_JSON_UTF8.equals(inputMessage.getHeaders().getContentType())) {
      final Customer.Builder builder = Customer.newBuilder();
      JsonFormat.parser().merge(new InputStreamReader(inputMessage.getBody()), builder);
      customer = builder.build();
    } else if(ProtobufType.PROTOBUF.equals(inputMessage.getHeaders().getContentType())) {
      customer = Customer.parseFrom(inputMessage.getBody());
    } else {
      throw new HttpMessageNotReadableException("Invalid content type: " + inputMessage.getHeaders().getContentType().toString());
    }
    return customer;
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.protobuf.ProtobufMessageConverter#checkClass(java.lang.Class)
   */
  @Override
  protected boolean checkClass(final Class<?> clazz) {
    return clazz.isAssignableFrom(Customer.class);
  }

  @Override
  protected Customer fromProtobuf(final InputStream input) throws IOException {
    return Customer.parseFrom(input);
  }

  @Override
  protected Customer fromJSON(final InputStream input) throws IOException {
    final Customer.Builder builder = Customer.newBuilder();
    JsonFormat.parser().merge(new InputStreamReader(input), builder);
    return builder.build();
  }

  @Override
  protected void writeJSON(final Customer t, final OutputStream out) {
    // TODO Auto-generated method stub
  }
  // Methods -

}
