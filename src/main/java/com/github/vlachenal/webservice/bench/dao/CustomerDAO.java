/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.vlachenal.webservice.bench.dao.bean.AddressBean;
import com.github.vlachenal.webservice.bench.dao.bean.CustomerBean;
import com.github.vlachenal.webservice.bench.dao.bean.PhoneBean;


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

  /** Vacuum customer table SQL request */
  private static final String REQ_VACCUM_CUST = "VACUUM FULL ANALYZE customer";

  /** Vacuum addres table SQL request */
  private static final String REQ_VACCUM_ADDR = "VACUUM FULL ANALYZE address";

  /** Vacuum address table SQL request */
  private static final String REQ_VACCUM_PHONE = "VACUUM FULL ANALYZE phone";

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
  public void setDataSource(@Qualifier("DS.customer") final DataSource dataSource) {
    jdbc = new JdbcTemplate(dataSource);
  }

  /**
   * List all customers in database
   *
   * @return the customers
   */
  public List<CustomerBean> listAll() {
    final ArrayList<CustomerBean> customers = new ArrayList<>();
    jdbc.query(REQ_LIST_ALL, new RowCallbackHandler() {
      @Override
      public void processRow(final ResultSet rs) throws SQLException {
        final CustomerBean customer = new CustomerBean();
        final UUID uuid = rs.getObject(1, UUID.class);
        customer.setId(uuid.toString());
        customer.setFirstName(rs.getString(2));
        customer.setLastName(rs.getString(3));
        customers.add(customer);
      }
    });
    return customers;
  }

  /**
   * Get customer details
   *
   * @param id the customer identifier
   *
   * @return the customer details
   */
  public CustomerBean getDetails(final UUID id) {
    final CustomerBean customer = jdbc.query(REQ_GET_CUST, new Object[] {
      id
    }, new ResultSetExtractor<CustomerBean>() {
      @Override
      public CustomerBean extractData(final ResultSet rs) throws SQLException, DataAccessException {
        if(!rs.next()) {
          return null;
        }
        final CustomerBean cust = new CustomerBean();
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
      final AddressBean address = jdbc.query(REQ_GET_CUST_ADDR, new Object[] {
        id
      }, new ResultSetExtractor<AddressBean>() {
        private void addLine(final List<String> lines, final String line) {
          if(line != null) {
            lines.add(line);
          }
        }

        @Override
        public AddressBean extractData(final ResultSet rs) throws SQLException, DataAccessException {
          if(!rs.next()) {
            return null;
          }
          final AddressBean addr = new AddressBean();
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
      customer.setAddress(address);
      final ArrayList<PhoneBean> phones = new ArrayList<>();
      jdbc.query(REQ_GET_CUST_PHONES, new Object[] {
        id
      }, new RowCallbackHandler() {
        @Override
        public void processRow(final ResultSet rs) throws SQLException {
          final PhoneBean phone = new PhoneBean();
          phone.setType(PhoneBean.Type.fromCode(rs.getShort(1)));
          phone.setNumber(rs.getString(2));
          phones.add(phone);
        }
      });
      if(!phones.isEmpty()) {
        customer.setPhones(phones);
      }
    }
    return customer;
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
  public String create(final CustomerBean customer) {
    final UUID uuid = UUID.randomUUID();
    jdbc.update(ADD_CUSTOMER, new Object[] {
      uuid,
      customer.getFirstName(),
      customer.getLastName(),
      customer.getBirthDate(),
      customer.getEmail()
    });
    if(customer.getAddress() != null) {
      final AddressBean address = customer.getAddress();
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
      final List<PhoneBean> phones = customer.getPhones();
      jdbc.batchUpdate(ADD_PHONE, new BatchPreparedStatementSetter() {
        @Override
        public void setValues(final PreparedStatement ps, final int i) throws SQLException {
          ps.setObject(1, uuid);
          final PhoneBean phone = phones.get(i);
          ps.setShort(2, phone.getType().getCode());
          ps.setString(3, phone.getNumber());
        }

        @Override
        public int getBatchSize() {
          return phones.size();
        }
      });
    }
    return uuid.toString();
  }

  /**
   * Delete all customers in database
   */
  public void deleteAll() {
    jdbc.update(REQ_DELETE_ALL);
    jdbc.batchUpdate(REQ_VACCUM_CUST, REQ_VACCUM_ADDR, REQ_VACCUM_PHONE);
  }
  // Methods -

}
