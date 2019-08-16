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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.DozerConverter;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.loader.api.FieldsMappingOptions;
import com.github.vlachenal.webservice.bench.dto.AddressDTO;
import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.dto.PhoneDTO;
import com.github.vlachenal.webservice.bench.dto.SearchRequestDTO;


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
  private static final String LONG_DATE_CONV = "long-date-converter";
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
        .withCustomConverterWithId(LONG_DATE_CONV, new DateLongConverter())
        .withMappingBuilder(soapAddress())
        .withMappingBuilder(soapPhone())
        .withMappingBuilder(soapCustomer())
        .withMappingBuilder(thriftCustomer())
        .withMappingBuilder(soapSearchRequest())
        .withMappingBuilder(thriftSearchRequest())
        .withMappingBuilder(protobufCustomer())
        .build();
  }

  /**
   * SOAP search request mapper
   *
   * @return the mapping builder
   */
  private BeanMappingBuilder soapSearchRequest() {
    return new BeanMappingBuilder() {
      @Override
      protected void configure() {
        mapping(SearchRequestDTO.class, com.github.vlachenal.webservice.bench.soap.api.ListCustomersRequest.class)
        .fields("birthDate", "birthDate", FieldsMappingOptions.customConverterId(SOAP_DATE_CONV))
        .fields("bornBefore", "bornBefore", FieldsMappingOptions.customConverterId(SOAP_DATE_CONV))
        .fields("bornAfter", "bornAfter", FieldsMappingOptions.customConverterId(SOAP_DATE_CONV));
      }
    };
  }

  /**
   * SOAP address mapping configuration
   *
   * @return the SOAP address mapping
   */
  private BeanMappingBuilder soapAddress() {
    return new BeanMappingBuilder() {
      @Override
      protected void configure() {
        mapping(AddressDTO.class, com.github.vlachenal.webservice.bench.soap.api.Address.class)
        .fields("lines", field("lines").accessible(true));
      }
    };
  }

  /**
   * SOAP phone mapping configuration
   *
   * @return the SOAP address mapping
   */
  private BeanMappingBuilder soapPhone() {
    return new BeanMappingBuilder() {
      @Override
      protected void configure() {
        mapping(PhoneDTO.class, com.github.vlachenal.webservice.bench.soap.api.Phone.class)
        .fields("type", "phoneType");
      }
    };
  }

  /**
   * SOAP customer mapping configuration
   *
   * @return the SOAP address mapping
   */
  private BeanMappingBuilder soapCustomer() {
    return new BeanMappingBuilder() {
      @Override
      protected void configure() {
        mapping(CustomerDTO.class, com.github.vlachenal.webservice.bench.soap.api.Customer.class)
        .fields("birthDate", "birthDate", FieldsMappingOptions.customConverterId(SOAP_DATE_CONV))
        .fields("phones", field("phones").accessible(true));
      }
    };
  }

  /**
   * Thrift search request mapper
   *
   * @return the mapping builder
   */
  private BeanMappingBuilder thriftSearchRequest() {
    return new BeanMappingBuilder() {
      @Override
      protected void configure() {
        mapping(SearchRequestDTO.class, com.github.vlachenal.webservice.bench.thrift.api.ListAllRequest.class)
        .fields("birthDate", "birthDate", FieldsMappingOptions.customConverterId(LONG_DATE_CONV))
        .fields("bornBefore", "bornBefore", FieldsMappingOptions.customConverterId(LONG_DATE_CONV))
        .fields("bornAfter", "bornAfter", FieldsMappingOptions.customConverterId(LONG_DATE_CONV))
        .exclude("__isset_bitfield");
      }
    };
  }

  /**
   * Thrift customer mapping configuration
   *
   * @return the SOAP address mapping
   */
  private BeanMappingBuilder thriftCustomer() {
    return new BeanMappingBuilder() {
      @Override
      protected void configure() {
        mapping(CustomerDTO.class, com.github.vlachenal.webservice.bench.thrift.api.Customer.class)
        .fields("birthDate", "birthDate", FieldsMappingOptions.customConverterId(LONG_DATE_CONV))
        .exclude("__isset_bitfield");
      }
    };
  }

  /**
   * Thrift customer mapping configuration
   *
   * @return the SOAP address mapping
   */
  private BeanMappingBuilder protobufCustomer() {
    return new BeanMappingBuilder() {
      @Override
      protected void configure() {
        mapping(AddressDTO.class, com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address.class)
        .fields("zipCode", "zipCode");
        mapping(PhoneDTO.class, com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.class);
        mapping(CustomerDTO.class, com.github.vlachenal.webservice.bench.protobuf.api.Customer.class)
        .fields("birthDate", "birthDate", FieldsMappingOptions.customConverterId(LONG_DATE_CONV))
        .fields("firstName", "firstName")
        .fields("lastName", "lastName");
      }
    };
  }
  // Methods -


  // Classes +
  /**
   * {@link Date} &lt;=&gt; {@link XMLGregorianCalendar} Dozer converter
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
     * @see com.github.dozermapper.core.DozerConverter#convertTo(java.lang.Object, java.lang.Object)
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
     * @see com.github.dozermapper.core.DozerConverter#convertFrom(java.lang.Object, java.lang.Object)
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
   * {@link Long} &lt;=&gt; {@link XMLGregorianCalendar} Dozer converter
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
     * @see com.github.dozermapper.core.DozerConverter#convertTo(java.lang.Object, java.lang.Object)
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
     * @see com.github.dozermapper.core.DozerConverter#convertFrom(java.lang.Object, java.lang.Object)
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
