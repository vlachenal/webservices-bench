/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.business;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.dao.CustomerDAO;
import com.github.vlachenal.webservice.bench.dao.PhoneDAO;
import com.github.vlachenal.webservice.bench.dto.PhoneDTO;
import com.github.vlachenal.webservice.bench.errors.InvalidParametersException;
import com.github.vlachenal.webservice.bench.errors.NotFoundException;


/**
 * Phone business
 *
 * @author Vincent Lachenal
 */
@Component
public class PhoneBusiness extends AbstractBusiness {

  // Attributes +
  /** Phone DAO */
  private final PhoneDAO phoneDAO;

  /** Customer DAO */
  private final CustomerDAO custumerDAO;
  // Attributes -


  // Constructors +
  /**
   * {@link PhoneBusiness} constructor
   *
   * @param phoneDAO the phone DAO to use
   * @param custumerDAO the customer DAO to use
   */
  public PhoneBusiness(final PhoneDAO phoneDAO, final CustomerDAO custumerDAO) {
    this.phoneDAO = phoneDAO;
    this.custumerDAO = custumerDAO;
  }
  // Constructors -


  // Methods +
  /**
   * List customer's phones
   *
   * @param customerId the customer identifier
   *
   * @return the phones
   *
   * @throws InvalidParametersException customer idenfier is not an UUID
   */
  public List<PhoneDTO> listAll(final String customerId) throws InvalidParametersException {
    return phoneDAO.getPhones(toUUID(customerId));
  }

  /**
   * Get phone
   *
   * @param customerId the customer identifier
   * @param phoneId the phone identifier
   *
   * @return the phone
   *
   * @throws InvalidParametersException customer and/or phone idenfier is not an UUID
   * @throws NotFoundException phone has not been found
   */
  public PhoneDTO getPhone(final String customerId, final String phoneId) throws InvalidParametersException, NotFoundException {
    final PhoneDTO phone = phoneDAO.getPhone(toUUID(phoneId), toUUID(customerId));
    if(phone == null) {
      throw new NotFoundException("Phone " + phoneId + " has not been found");
    }
    return phone;
  }

  /**
   * Register phone
   *
   * @param customerId the customer identifier
   * @param phone the phone to register
   *
   * @return the new phone's identifier
   *
   * @throws InvalidParametersException customer idenfier is not an UUID
   * @throws NotFoundException customer has not been found
   */
  public String registerPhone(final String customerId, final PhoneDTO phone) throws InvalidParametersException, NotFoundException {
    final UUID custId = toUUID(customerId);
    if(!custumerDAO.customerExists(custId)) {
      throw new NotFoundException("Customer " + customerId + " does not exist");
    }
    return phoneDAO.addPhone(custId, phone);
  }

  /**
   * Delete phone
   *
   * @param customerId the customer identifier
   * @param phoneId the phone identifier
   *
   * @throws InvalidParametersException customer and/or phone idenfier is not an UUID
   * @throws NotFoundException phone has not been found
   */
  public void deletePhone(final String customerId, final String phoneId) throws InvalidParametersException, NotFoundException {
    final UUID custId = toUUID(customerId);
    final UUID id = toUUID(phoneId);
    if(phoneDAO.getPhone(id, custId) == null) {
      throw new NotFoundException("Phone " + phoneId + " has not been found");
    }
    phoneDAO.deletePhone(id, custId);
  }
  // Methods -

}
