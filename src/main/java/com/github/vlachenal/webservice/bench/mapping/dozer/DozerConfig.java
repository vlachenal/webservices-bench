/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.dozer;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.vlachenal.webservice.bench.dao.bean.AddressBean;
import com.github.vlachenal.webservice.bench.dao.bean.CustomerBean;
import com.github.vlachenal.webservice.bench.dao.bean.PhoneBean;


/**
 * Dozer configuration
 *
 * @author Vincent Lachenal
 */
@Configuration
public class DozerConfig {

  // Attributes +
  /** SOAP date converter identifier */
  private static final String SOAP_DATE_CONV = "soap-date-converter";

  /** Thrift date converter identifier */
  private static final String THRIFT_DATE_CONV = "thrift-date-converter";
  // Attributes -


  // Methods +
  /**
   * Configure Dozer mapper
   *
   * @return the mapper
   */
  @Bean
  public Mapper dozer() {
    return DozerBeanMapperBuilder.create()
        .withCustomConverterWithId(SOAP_DATE_CONV, new DateXMLGregorianCalendarConverter())
        .withCustomConverterWithId(THRIFT_DATE_CONV, new DateLongConverter())
        .withMappingBuilder(soapAddress())
        .withMappingBuilder(soapPhone())
        .withMappingBuilder(soapCustomer())
        .withMappingBuilder(thriftCustomer())
        .build();
  }

  /**
   * SOAP address mapping configuration
   *
   * @return the SOAP address mapping
   */
  private BeanMappingBuilder soapAddress() {
    final BeanMappingBuilder mapBuild = new BeanMappingBuilder() {
      @Override
      protected void configure() {
        mapping(AddressBean.class, com.github.vlachenal.webservice.bench.soap.api.Address.class)
        .fields("lines", field("lines").accessible(true));
      }
    };
    return mapBuild;
  }

  /**
   * SOAP phone mapping configuration
   *
   * @return the SOAP address mapping
   */
  private BeanMappingBuilder soapPhone() {
    final BeanMappingBuilder mapBuild = new BeanMappingBuilder() {
      @Override
      protected void configure() {
        mapping(PhoneBean.class, com.github.vlachenal.webservice.bench.soap.api.Phone.class)
        .fields("type", "phoneType");
      }
    };
    return mapBuild;
  }

  /**
   * SOAP customer mapping configuration
   *
   * @return the SOAP address mapping
   */
  private BeanMappingBuilder soapCustomer() {
    final BeanMappingBuilder mapBuild = new BeanMappingBuilder() {
      @Override
      protected void configure() {
        mapping(CustomerBean.class, com.github.vlachenal.webservice.bench.soap.api.Customer.class)
        .fields("birthDate", "birthDate", FieldsMappingOptions.customConverterId(SOAP_DATE_CONV))
        .fields("phones", field("phones").accessible(true));
      }
    };
    return mapBuild;
  }

  /**
   * Thrift customer mapping configuration
   *
   * @return the SOAP address mapping
   */
  private BeanMappingBuilder thriftCustomer() {
    final BeanMappingBuilder mapBuild = new BeanMappingBuilder() {
      @Override
      protected void configure() {
        mapping(CustomerBean.class, com.github.vlachenal.webservice.bench.thrift.api.Customer.class)
        .fields("birthDate", "birthDate", FieldsMappingOptions.customConverterId(THRIFT_DATE_CONV))
        .exclude("__isset_bitfield");
      }
    };
    return mapBuild;
  }
  // Methods -


  // Classes +
  /**
   * {@link Date} <=> {@link XMLGregorianCalendar} Dozer converter
   *
   * @author Vincent Lachenal
   */
  private static class DateXMLGregorianCalendarConverter extends DozerConverter<Date,XMLGregorianCalendar> {

    /**
     * {@inheritDoc DateXMLGregorianCalendarConverter} default constructor
     */
    public DateXMLGregorianCalendarConverter() {
      super(Date.class, XMLGregorianCalendar.class);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.dozer.DozerConverter#convertTo(java.lang.Object, java.lang.Object)
     */
    @Override
    public XMLGregorianCalendar convertTo(final Date source, final XMLGregorianCalendar destination) {
      XMLGregorianCalendar dest = null;
      if(source != null) {
        final GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(source);
        try {
          dest = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch(final DatatypeConfigurationException e) {
          // Nothing to do
        }
      }
      return dest;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.dozer.DozerConverter#convertFrom(java.lang.Object, java.lang.Object)
     */
    @Override
    public Date convertFrom(final XMLGregorianCalendar source, final Date destination) {
      Date date = null;
      if(source != null) {
        date = source.toGregorianCalendar().getTime();
      }
      return date;
    }

  }

  /**
   * {@link Long} <=> {@link XMLGregorianCalendar} Dozer converter
   *
   * @author Vincent Lachenal
   */
  private static class DateLongConverter extends DozerConverter<Date,Long> {

    /**
     * {@inheritDoc DateLongConverter} default constructor
     */
    public DateLongConverter() {
      super(Date.class, Long.class);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.dozer.DozerConverter#convertTo(java.lang.Object, java.lang.Object)
     */
    @Override
    public Long convertTo(final Date source, final Long destination) {
      Long dest = null;
      if(source != null) {
        dest = source.getTime();
      }
      return dest;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.dozer.DozerConverter#convertFrom(java.lang.Object, java.lang.Object)
     */
    @Override
    public Date convertFrom(final Long source, final Date destination) {
      Date date = null;
      if(source != null) {
        date = new Date(source);
      }
      return date;
    }

  }
  // Classes -

}
