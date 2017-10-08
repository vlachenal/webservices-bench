/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.mapstruct;

import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.dao.bean.PhoneBean;
import com.github.vlachenal.webservice.bench.protobuf.api.Customer;


/**
 * MapStruct customer mapper
 *
 * @author Vincent Lachenal
 */
@Component
public class MapStructCustomMapper {

  /**
   * Ptotocol buffer phone type to bean converter
   *
   * @param type the Ptotocol buffer phone type
   *
   * @return the bean
   */
  public PhoneBean.Type toBean(final Customer.Phone.PhoneType type) {
    PhoneBean.Type bean = null;
    if(type != null) {
      switch(type) {
        case LANDLINE:
          bean = PhoneBean.Type.LANDLINE;
          break;
        case MOBILE:
          bean = PhoneBean.Type.MOBILE;
          break;
        default:
          // Nothing to do
      }
    }
    return bean;
  }

}
