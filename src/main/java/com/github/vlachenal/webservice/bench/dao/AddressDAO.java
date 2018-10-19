/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.vlachenal.webservice.bench.dto.AddressDTO;


/**
 * Address DAO
 *
 * @author Vincent Lachenal
 */
@Repository
public class AddressDAO {

  // Attributes +
  // SQL requests +
  /** Insert address in database */
  public static final String REQ_ADD_ADDRESS = "INSERT INTO Address "
      + "(line1,line2,line3,line4,line5,line6,zip_code,city,country,customer_id,id) "
      + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

  /** Get customer address SQL request */
  private static final String REQ_GET_ADDRESS = "SELECT line1,line2,line3,line4,line5,line6,zip_code,city,country FROM Address WHERE customer_id = ?";

  /** Delete customer address SQL request */
  private static final String REQ_DELETE_ADDRESS = "DELETE FROM Address WHERE customer_id = ?";
  // SQL requests -

  /** JDBC template */
  private JdbcTemplate jdbc;
  // Attributes -


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
   * Get address line value to insert
   *
   * @param lines the address lines
   * @param idx the line index
   *
   * @return {@code true} if line exists, {@code false} otherwise
   */
  private String getLine(final List<String> lines, final int idx) {
    String line = null;
    if(lines != null && lines.size() > idx) {
      line = lines.get(idx);
    }
    return line;
  }

  /**
   * Set address' values in {@link PreparedStatement} for insertion
   *
   * @param stmt the {@link PreparedStatement}
   * @param customerId the customer identifier
   * @param addressId the address identifier
   * @param address the address
   *
   * @throws SQLException any error
   */
  public void setAddressValues(final PreparedStatement stmt, final UUID customerId, final UUID addressId, final AddressDTO address) throws SQLException {
    stmt.setString(1, getLine(address.getLines(), 0));
    stmt.setString(2, getLine(address.getLines(), 1));
    stmt.setString(3, getLine(address.getLines(), 2));
    stmt.setString(4, getLine(address.getLines(), 3));
    stmt.setString(5, getLine(address.getLines(), 4));
    stmt.setString(6, getLine(address.getLines(), 5));
    stmt.setString(7, address.getZipCode());
    stmt.setString(8, address.getCity());
    stmt.setString(9, address.getCountry());
    stmt.setObject(10, customerId);
    stmt.setObject(11, addressId);
  }

  /**
   * Add address to customer
   *
   * @param customerId the customer identifier
   * @param address the customer's address to add
   *
   * @return the new address identifier
   */
  public String registerAddress(final UUID customerId, final AddressDTO address) {
    final UUID addressId = UUID.randomUUID();
    jdbc.update(REQ_ADD_ADDRESS, stmt -> setAddressValues(stmt, customerId, addressId, address));
    return addressId.toString();
  }

  /**
   * Get customer's address
   *
   * @param customerId the customer identifier
   *
   * @return the address
   */
  public AddressDTO getAddress(final UUID customerId) {
    return jdbc.query(REQ_GET_ADDRESS,
                      res -> res.next() ? new AddressDTO(res.getString(7).trim(),
                                                         res.getString(8),
                                                         res.getString(9),
                                                         res.getString(1),
                                                         res.getString(2),
                                                         res.getString(3),
                                                         res.getString(4),
                                                         res.getString(5),
                                                         res.getString(6))
                                        : null, customerId);
  }

  /**
   * Delete address
   *
   * @param customerId the customer's identifier
   */
  public void deleteAddress(final UUID customerId) {
    jdbc.update(REQ_DELETE_ADDRESS, customerId);
  }
  // Methods -

}
