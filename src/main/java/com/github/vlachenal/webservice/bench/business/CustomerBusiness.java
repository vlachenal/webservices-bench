/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.business;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.dao.CustomerDAO;
import com.github.vlachenal.webservice.bench.dto.AddressDTO;
import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.errors.InvalidParametersException;
import com.github.vlachenal.webservice.bench.errors.NotFoundException;


/**
 * Customer business component.<br>
 * Check business rules and call DAO.
 *
 * @author Vincent Lachenal
 */
@Component
public class CustomerBusiness extends AbstractBusiness {

  // Attributes +
  /** Customer DAO */
  private final CustomerDAO dao;
  // Attributes -


  // Constructors +
  /**
   * {@link CustomerBusiness} constructor
   *
   * @param dao the customer DAO to use
   */
  public CustomerBusiness(final CustomerDAO dao) {
    this.dao = dao;
  }
  // Constructors -


  // Methods +
  /**
   * List all customers
   *
   * @return the customers
   */
  public List<CustomerDTO> listAll() {
    return dao.listAll();
  }

  /**
   * Get customer's details
   *
   * @param id the customer's identifier
   *
   * @return the customer's details
   *
   * @throws InvalidParametersException invalid or missing parameter
   * @throws NotFoundException customer has not been found
   */
  public CustomerDTO getDetails(final String id) throws InvalidParametersException, NotFoundException {
    final CustomerDTO customers = dao.getDetails(toUUID(id));
    if(customers == null) {
      throw new NotFoundException("Customer " + id + " does not exist");
    }
    return customers;
  }

  /**
   * Create new customer
   *
   * @param customer the customer to create
   *
   * @return the new customer's identifier
   *
   * @throws InvalidParametersException missing or invalid parameters
   */
  public String create(final CustomerDTO customer) throws InvalidParametersException {
    // Customer structure checks +
    checkParameters("Customer is null", customer);
    checkParameters("Customer first_name, last_name and brith_date has to be set", customer.getFirstName(), customer.getLastName(), customer.getBirthDate());
    // Customer structure checks -
    // Address structure checks +
    final AddressDTO addr = customer.getAddress();
    if(addr != null) {
      checkParameters("Address lines, zip_code, city and country has to be set", addr.getLines(), addr.getZipCode(), addr.getCity(),addr.getCountry());
    }
    // Address structure checks -
    return dao.createCustomer(customer);
  }

  /**
   * Delete all customers
   */
  public void deleteAll() {
    dao.deleteAll();
  }
  // Methods -

}
