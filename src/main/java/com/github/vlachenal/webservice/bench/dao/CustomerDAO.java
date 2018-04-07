/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.vlachenal.webservice.bench.dto.AddressDTO;
import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.dto.PhoneDTO;


/**
 * Customer DAO
 *
 * @author Vincent Lachenal
 */
@Repository
public class CustomerDAO {

  // Attributes +
  // SQL request +
  /** List all customer SQL request */
  private static final String REQ_LIST_ALL = "SELECT id,first_name,last_name FROM Customer";

  /** Get customer details SQL request */
  private static final String REQ_GET_CUST = "SELECT id,first_name,last_name,birth_date,email FROM Customer WHERE id = ?";

  /** Get customer address SQL request */
  private static final String REQ_GET_CUST_ADDR = "SELECT line1,line2,line3,line4,line5,line6,zip_code,city,country FROM address WHERE customer_id = ?";

  /** Get customer phones SQL request */
  private static final String REQ_GET_CUST_PHONES = "SELECT phone_type,number FROM phone WHERE customer_id = ?";

  /** Delete all customer SQL request */
  private static final String REQ_DELETE_ALL = "DELETE FROM Customer";

  /** Insert customer in database */
  private static final String ADD_CUSTOMER = "INSERT INTO customer "
      + "(id,first_name,last_name,birth_date,email) "
      + "VALUES (?,?,?,?,?)";

  /** Insert phone in database */
  private static final String ADD_ADDRESS = "INSERT INTO address "
      + "(customer_id,line1,line2,line3,line4,line5,line6,zip_code,city,country) "
      + "VALUES (?,?,?,?,?,?,?,?,?,?)";

  /** Insert phone in database */
  private static final String ADD_PHONE = "INSERT INTO phone "
      + "(customer_id,phone_type,number) "
      + "VALUES (?,?,?)";

  /** Vacuum requests */
  @Value("${ds.customer.vacuum}")
  private String vacuumReqs;
  // SQL request -

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
   * List all customers in database
   *
   * @return the customers
   */
  public List<CustomerDTO> listAll() {
    final ArrayList<CustomerDTO> customers = new ArrayList<>();
    jdbc.query(REQ_LIST_ALL, (rs, rowNum) -> new CustomerDTO(rs.getString(1), rs.getString(2), rs.getString(3))).forEach(cust -> customers.add(cust));
    return customers;
  }

  /**
   * List all customers in database
   *
   * @return the customers' stream
   */
  public Stream<CustomerDTO> stream() {
    return jdbc.query(REQ_LIST_ALL, (rs, rowNum) -> new CustomerDTO(rs.getString(1), rs.getString(2), rs.getString(3))).stream();
  }

  /**
   * Get customer details
   *
   * @param id the customer identifier
   *
   * @return the customer details
   */
  public CustomerDTO getDetails(final UUID id) {
    final CustomerDTO customer = jdbc.query(REQ_GET_CUST, new Object[] {
      id
    }, new ResultSetExtractor<CustomerDTO>() {
      @Override
      public CustomerDTO extractData(final ResultSet rs) throws SQLException, DataAccessException {
        if(!rs.next()) {
          return null;
        }
        final CustomerDTO cust = new CustomerDTO();
        cust.setId(id.toString());
        cust.setId(rs.getString(1));
        cust.setFirstName(rs.getString(2));
        cust.setLastName(rs.getString(3));
        cust.setBirthDate(rs.getDate(4));
        cust.setEmail(rs.getString(5));
        return cust;
      }
    });
    if(customer != null) {
      customer.setAddress(getAddress(id));
      final List<PhoneDTO> phones = getPhones(id);
      if(!phones.isEmpty()) {
        customer.setPhones(phones);
      }
    }
    return customer;
  }

  /**
   * Get customer's address
   *
   * @param id the customer identifier
   *
   * @return the address
   */
  public AddressDTO getAddress(final UUID id) {
    final AddressDTO address = jdbc.query(REQ_GET_CUST_ADDR, new Object[] {
      id
    }, new ResultSetExtractor<AddressDTO>() {
      private void addLine(final List<String> lines, final String line) {
        if(line != null) {
          lines.add(line);
        }
      }

      @Override
      public AddressDTO extractData(final ResultSet rs) throws SQLException, DataAccessException {
        if(!rs.next()) {
          return null;
        }
        final AddressDTO addr = new AddressDTO();
        final ArrayList<String> lines = new ArrayList<>();
        addLine(lines, rs.getString(1));
        addLine(lines, rs.getString(2));
        addLine(lines, rs.getString(3));
        addLine(lines, rs.getString(4));
        addLine(lines, rs.getString(5));
        addLine(lines, rs.getString(6));
        if(!lines.isEmpty()) {
          addr.setLines(lines);
        }
        addr.setZipCode(rs.getString(7).trim());
        addr.setCity(rs.getString(8));
        addr.setCountry(rs.getString(9));
        return addr;
      }
    });
    return address;
  }

  /**
   * Get customer's phones
   *
   * @param id the customer's identifier
   *
   * @return the customer's phones
   */
  public List<PhoneDTO> getPhones(final UUID id) {
    final ArrayList<PhoneDTO> phones = new ArrayList<>();
    jdbc.query(REQ_GET_CUST_PHONES, new Object[] {
      id
    }, new RowCallbackHandler() {
      @Override
      public void processRow(final ResultSet rs) throws SQLException {
        final PhoneDTO phone = new PhoneDTO();
        phone.setType(PhoneDTO.Type.fromCode(rs.getShort(1)));
        phone.setNumber(rs.getString(2));
        phones.add(phone);
      }
    });
    return phones;
  }

  /**
   * Get address line value to insert
   *
   * @param lines the address lines
   * @param idx the line index
   *
   * @return <code>true</code> if line exists, <code>false</code> otherwise
   */
  private String getLine(final List<String> lines, final int idx) {
    String line = null;
    if(lines != null && lines.size() > idx) {
      line = lines.get(idx);
    }
    return line;
  }

  /**
   * Create customer in database
   *
   * @param customer the customer to create
   *
   * @return the customer identifier
   */
  @Transactional
  public String create(final CustomerDTO customer) {
    final UUID uuid = UUID.randomUUID();
    jdbc.update(ADD_CUSTOMER, new Object[] {
      uuid,
      customer.getFirstName(),
      customer.getLastName(),
      customer.getBirthDate(),
      customer.getEmail()
    });
    if(customer.getAddress() != null) {
      final AddressDTO address = customer.getAddress();
      final List<String> lines = address.getLines();
      jdbc.update(ADD_ADDRESS, new Object[] {
        uuid,
        getLine(lines,0),
        getLine(lines,1),
        getLine(lines,2),
        getLine(lines,3),
        getLine(lines,4),
        getLine(lines,5),
        address.getZipCode(),
        address.getCity(),
        address.getCountry()
      });
    }
    if(customer.getPhones() != null && !customer.getPhones().isEmpty()) {
      jdbc.batchUpdate(ADD_PHONE, customer.getPhones(), customer.getPhones().size(), (ps, phone) -> {
        ps.setObject(1, uuid);
        ps.setShort(2, phone.getType().getCode());
        ps.setString(3, phone.getNumber());
      });
    }
    return uuid.toString();
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
