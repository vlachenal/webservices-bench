/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.github.vlachenal.webservice.bench.thrift.api;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum PhoneType implements org.apache.thrift.TEnum {
  LANDLINE(1),
  MOBILE(2);

  private final int value;

  private PhoneType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static PhoneType findByValue(int value) { 
    switch (value) {
      case 1:
        return LANDLINE;
      case 2:
        return MOBILE;
      default:
        return null;
    }
  }
}
