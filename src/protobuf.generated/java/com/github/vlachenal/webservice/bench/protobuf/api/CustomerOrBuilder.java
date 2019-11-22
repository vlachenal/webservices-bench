// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: customer.proto

package com.github.vlachenal.webservice.bench.protobuf.api;

public interface CustomerOrBuilder extends
    // @@protoc_insertion_point(interface_extends:webservicebench.Customer)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   ** Identifier 
   * </pre>
   *
   * <code>string id = 1;</code>
   * @return The id.
   */
  java.lang.String getId();
  /**
   * <pre>
   ** Identifier 
   * </pre>
   *
   * <code>string id = 1;</code>
   * @return The bytes for id.
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <pre>
   ** First name 
   * </pre>
   *
   * <code>string firstName = 2;</code>
   * @return The firstName.
   */
  java.lang.String getFirstName();
  /**
   * <pre>
   ** First name 
   * </pre>
   *
   * <code>string firstName = 2;</code>
   * @return The bytes for firstName.
   */
  com.google.protobuf.ByteString
      getFirstNameBytes();

  /**
   * <pre>
   ** Last name 
   * </pre>
   *
   * <code>string lastName = 3;</code>
   * @return The lastName.
   */
  java.lang.String getLastName();
  /**
   * <pre>
   ** Last name 
   * </pre>
   *
   * <code>string lastName = 3;</code>
   * @return The bytes for lastName.
   */
  com.google.protobuf.ByteString
      getLastNameBytes();

  /**
   * <pre>
   ** Brith date 
   * </pre>
   *
   * <code>int64 birthDate = 4;</code>
   * @return The birthDate.
   */
  long getBirthDate();

  /**
   * <pre>
   ** Email address 
   * </pre>
   *
   * <code>string email = 5;</code>
   * @return The email.
   */
  java.lang.String getEmail();
  /**
   * <pre>
   ** Email address 
   * </pre>
   *
   * <code>string email = 5;</code>
   * @return The bytes for email.
   */
  com.google.protobuf.ByteString
      getEmailBytes();

  /**
   * <pre>
   ** Address 
   * </pre>
   *
   * <code>.webservicebench.Customer.Address address = 6;</code>
   * @return Whether the address field is set.
   */
  boolean hasAddress();
  /**
   * <pre>
   ** Address 
   * </pre>
   *
   * <code>.webservicebench.Customer.Address address = 6;</code>
   * @return The address.
   */
  com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address getAddress();
  /**
   * <pre>
   ** Address 
   * </pre>
   *
   * <code>.webservicebench.Customer.Address address = 6;</code>
   */
  com.github.vlachenal.webservice.bench.protobuf.api.Customer.AddressOrBuilder getAddressOrBuilder();

  /**
   * <pre>
   ** Phones 
   * </pre>
   *
   * <code>repeated .webservicebench.Customer.Phone phones = 7;</code>
   */
  java.util.List<com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone> 
      getPhonesList();
  /**
   * <pre>
   ** Phones 
   * </pre>
   *
   * <code>repeated .webservicebench.Customer.Phone phones = 7;</code>
   */
  com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone getPhones(int index);
  /**
   * <pre>
   ** Phones 
   * </pre>
   *
   * <code>repeated .webservicebench.Customer.Phone phones = 7;</code>
   */
  int getPhonesCount();
  /**
   * <pre>
   ** Phones 
   * </pre>
   *
   * <code>repeated .webservicebench.Customer.Phone phones = 7;</code>
   */
  java.util.List<? extends com.github.vlachenal.webservice.bench.protobuf.api.Customer.PhoneOrBuilder> 
      getPhonesOrBuilderList();
  /**
   * <pre>
   ** Phones 
   * </pre>
   *
   * <code>repeated .webservicebench.Customer.Phone phones = 7;</code>
   */
  com.github.vlachenal.webservice.bench.protobuf.api.Customer.PhoneOrBuilder getPhonesOrBuilder(
      int index);
}
