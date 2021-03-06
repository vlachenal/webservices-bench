/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.dao;

import java.sql.Array;
import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.vlachenal.sql.Clauses;
import com.github.vlachenal.sql.SQL;
import com.github.vlachenal.sql.SQLQuery;
import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.dto.SearchRequestDTO;


/**
 * Customer DAO
 *
 * @author Vincent Lachenal
 */
@Repository
public class CustomerDAO {

  // Attributes +
  // SQL requests +
  /** Get customer details SQL request */
  private static final String REQ_GET_CUST = "SELECT id,first_name,last_name,birth_date,email FROM Customer WHERE id = ?";

  /** Delete all customer SQL request */
  private static final String REQ_DELETE_ALL = "DELETE FROM Customer";

  /** Delete customer SQL request */
  private static final String REQ_DELETE = "DELETE FROM Customer WHERE id = ?";

  /** Insert customer in database */
  private static final String REQ_ADD_CUSTOMER = "INSERT INTO Customer "
      + "(id,first_name,last_name,birth_date,email) "
      + "VALUES (?,?,?,?,?)";

  /** Customer exists */
  private static final String REQ_CUSTOMER_EXISTS = "SELECT 1 FROM Customer WHERE id = ?";

  /** Vacuum requests */
  @Value("${ds.customer.vacuum:}")
  private String vacuumReqs;
  // SQL requests -

  /** Phone DAO */
  private final PhoneDAO phoneDAO;

  /** Address DAO */
  private final AddressDAO addressDAO;

  /** JDBC template */
  private JdbcTemplate jdbc;
  // Attributes -


  // Constructors +
  /**
   * {@link CustomerDAO} constructor
   *
   * @param phoneDAO the phone DAO to use
   * @param addressDAO the address DAO to use
   */
  public CustomerDAO(final PhoneDAO phoneDAO, final AddressDAO addressDAO) {
    this.phoneDAO = phoneDAO;
    this.addressDAO = addressDAO;
  }
  // Constructors -


  // Methods +
  /**
   * Initialize JDBC template with datasource
   *
   * @param dataSource the datasource to use
   */
  @Autowired
  public void setDataSource(@Qualifier("ds.customer") final DataSource dataSource) {
    jdbc = new JdbcTemplate(dataSource);
  }

  /**
   * Check if customer exists
   *
   * @param customerId the customer's identifier
   *
   * @return {@code true} when customer exists, {@code false} otherwise
   */
  public Boolean customerExists(final UUID customerId) {
    return jdbc.query(REQ_CUSTOMER_EXISTS, res -> {
      return res.next();
    }, customerId);
  }

  /**
   * List all customers in database
   *
   * @param request the search request
   *
   * @return the customers
   */
  public List<CustomerDTO> search(final SearchRequestDTO request) {
    final Array ids = request.getIds() == null ? null : jdbc.execute((final Connection conn) -> conn.createArrayOf("uuid", request.getIds().toArray()));
    final SQLQuery query = SQL.select().field("id").field("first_name").field("last_name")
        .from("Customer")
        .where(SQL.clauses("first_name", Clauses::like, request.getFirstName())
               .and("last_name", Clauses::like, request.getLastName())
               .and("email", Clauses::equalsTo, request.getEmail())
               .and("birth_date", Clauses::equalsTo, request.getBirthDate())
               .and("birth_date", Clauses::greaterEquals, request.getBornAfter())
               .and("birth_date", Clauses::lesserEquals, request.getBornBefore())
               .and("id", Clauses::equalsAny, ids))
        .build();
    return jdbc.query(query.getQuery(), (res, rowNum) -> new CustomerDTO(res.getString(1), res.getString(2), res.getString(3)), query.values());
  }

  /**
   * Get customer details
   *
   * @param customerId the customer identifier
   *
   * @return the customer details
   */
  public CustomerDTO getDetails(final UUID customerId) {
    final CustomerDTO customer = jdbc.query(REQ_GET_CUST, res -> {
      CustomerDTO cust = null;
      if(res.next()) {
        cust = new CustomerDTO();
        cust.setId(customerId.toString());
        cust.setFirstName(res.getString(2));
        cust.setLastName(res.getString(3));
        cust.setBirthDate(res.getDate(4));
        cust.setEmail(res.getString(5));
      }
      return cust;
    }, customerId);
    if(customer != null) {
      customer.setAddress(addressDAO.getAddress(customerId));
      customer.setPhones(phoneDAO.getPhones(customerId));
    }
    return customer;
  }

  /**
   * Create customer in database
   *
   * @param customer the customer to create
   *
   * @return the customer's identifier
   */
  @Transactional
  public String createCustomer(final CustomerDTO customer) {
    final UUID customerId = UUID.randomUUID();
    jdbc.update(REQ_ADD_CUSTOMER,
                customerId,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getBirthDate(),
                customer.getEmail());
    if(customer.getAddress() != null) {
      jdbc.update(AddressDAO.REQ_ADD_ADDRESS, stmt -> addressDAO.setAddressValues(stmt, customerId, UUID.randomUUID(), customer.getAddress()));
    }
    if(customer.getPhones() != null && !customer.getPhones().isEmpty()) {
      jdbc.batchUpdate(PhoneDAO.REQ_ADD_PHONE, customer.getPhones(), customer.getPhones().size(), (stmt, phone) -> phoneDAO.setPhoneValues(stmt, customerId, phone));
    }
    return customerId.toString();
  }

  /**
   * Delete customer.<br>
   * Address and phones will be deleted throught foreign key usage (ON DELETE CASCADE).
   *
   * @param customerId the customer's identifier
   */
  public void deleteCustomer(final UUID customerId) {
    jdbc.update(REQ_DELETE, customerId);
  }

  /**
   * Delete all customers in database
   */
  public void deleteAll() {
    jdbc.update(REQ_DELETE_ALL);
    if(vacuumReqs != null && !vacuumReqs.isEmpty()) {
      jdbc.batchUpdate(vacuumReqs.split(";"));
    }
  }
  // Methods -

}
