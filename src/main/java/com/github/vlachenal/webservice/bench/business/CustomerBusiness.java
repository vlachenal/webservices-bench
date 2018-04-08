package com.github.vlachenal.webservice.bench.business;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class CustomerBusiness {

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
    UUID custId = null;
    try {
      custId = UUID.fromString(id);
    } catch(final IllegalArgumentException e) {
      throw new InvalidParametersException(id + " is not an UUID");
    }
    final CustomerDTO customers = dao.getDetails(custId);
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
    // TODO reduce complexity
    // Customer structure checks +
    if(customer == null) {
      throw new InvalidParametersException("Customer is null");
    }
    if(customer.getFirstName() == null || customer.getLastName() == null || customer.getBirthDate() == null) {
      String input = null;
      final ObjectMapper jsonMapper = new ObjectMapper();
      try {
        input = new String(jsonMapper.writeValueAsBytes(customer));
      } catch(final Exception e) {
        // Nothing to do
      }
      throw new InvalidParametersException("Customer first_name, last_name and brith_date has to be set: " + input);
    }
    // Customer structure checks -
    // Address structure checks +
    final AddressDTO addr = customer.getAddress();
    if(addr != null
        && (addr.getLines() == null || addr.getLines().isEmpty()
        || addr.getZipCode() == null || addr.getCity() == null || addr.getCountry() == null)) {
      String input = null;
      final ObjectMapper jsonMapper = new ObjectMapper();
      try {
        input = new String(jsonMapper.writeValueAsBytes(customer));
      } catch(final Exception e) {
        // Nothing to do
      }
      throw new InvalidParametersException("Address lines[0], zip_code, city and country has to be set: " + input);
    }
    // Address structure checks -
    return dao.create(customer);
  }

  /**
   * Delete all customers
   */
  public void deleteAll() {
    dao.deleteAll();
  }
  // Methods -

}
