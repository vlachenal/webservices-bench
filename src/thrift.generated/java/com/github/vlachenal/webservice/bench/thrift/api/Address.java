/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.github.vlachenal.webservice.bench.thrift.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-06-29")
public class Address implements org.apache.thrift.TBase<Address, Address._Fields>, java.io.Serializable, Cloneable, Comparable<Address> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Address");

  private static final org.apache.thrift.protocol.TField LINES_FIELD_DESC = new org.apache.thrift.protocol.TField("lines", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField ZIP_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("zipCode", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField CITY_FIELD_DESC = new org.apache.thrift.protocol.TField("city", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField COUNTRY_FIELD_DESC = new org.apache.thrift.protocol.TField("country", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new AddressStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new AddressTupleSchemeFactory();

  private java.util.List<java.lang.String> lines; // required
  private java.lang.String zipCode; // required
  private java.lang.String city; // required
  private java.lang.String country; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    LINES((short)1, "lines"),
    ZIP_CODE((short)2, "zipCode"),
    CITY((short)3, "city"),
    COUNTRY((short)4, "country");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // LINES
          return LINES;
        case 2: // ZIP_CODE
          return ZIP_CODE;
        case 3: // CITY
          return CITY;
        case 4: // COUNTRY
          return COUNTRY;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.LINES, new org.apache.thrift.meta_data.FieldMetaData("lines", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.ZIP_CODE, new org.apache.thrift.meta_data.FieldMetaData("zipCode", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CITY, new org.apache.thrift.meta_data.FieldMetaData("city", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.COUNTRY, new org.apache.thrift.meta_data.FieldMetaData("country", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Address.class, metaDataMap);
  }

  public Address() {
  }

  public Address(
    java.util.List<java.lang.String> lines,
    java.lang.String zipCode,
    java.lang.String city,
    java.lang.String country)
  {
    this();
    this.lines = lines;
    this.zipCode = zipCode;
    this.city = city;
    this.country = country;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Address(Address other) {
    if (other.isSetLines()) {
      java.util.List<java.lang.String> __this__lines = new java.util.ArrayList<java.lang.String>(other.lines);
      this.lines = __this__lines;
    }
    if (other.isSetZipCode()) {
      this.zipCode = other.zipCode;
    }
    if (other.isSetCity()) {
      this.city = other.city;
    }
    if (other.isSetCountry()) {
      this.country = other.country;
    }
  }

  public Address deepCopy() {
    return new Address(this);
  }

  @Override
  public void clear() {
    this.lines = null;
    this.zipCode = null;
    this.city = null;
    this.country = null;
  }

  public int getLinesSize() {
    return (this.lines == null) ? 0 : this.lines.size();
  }

  public java.util.Iterator<java.lang.String> getLinesIterator() {
    return (this.lines == null) ? null : this.lines.iterator();
  }

  public void addToLines(java.lang.String elem) {
    if (this.lines == null) {
      this.lines = new java.util.ArrayList<java.lang.String>();
    }
    this.lines.add(elem);
  }

  public java.util.List<java.lang.String> getLines() {
    return this.lines;
  }

  public void setLines(java.util.List<java.lang.String> lines) {
    this.lines = lines;
  }

  public void unsetLines() {
    this.lines = null;
  }

  /** Returns true if field lines is set (has been assigned a value) and false otherwise */
  public boolean isSetLines() {
    return this.lines != null;
  }

  public void setLinesIsSet(boolean value) {
    if (!value) {
      this.lines = null;
    }
  }

  public java.lang.String getZipCode() {
    return this.zipCode;
  }

  public void setZipCode(java.lang.String zipCode) {
    this.zipCode = zipCode;
  }

  public void unsetZipCode() {
    this.zipCode = null;
  }

  /** Returns true if field zipCode is set (has been assigned a value) and false otherwise */
  public boolean isSetZipCode() {
    return this.zipCode != null;
  }

  public void setZipCodeIsSet(boolean value) {
    if (!value) {
      this.zipCode = null;
    }
  }

  public java.lang.String getCity() {
    return this.city;
  }

  public void setCity(java.lang.String city) {
    this.city = city;
  }

  public void unsetCity() {
    this.city = null;
  }

  /** Returns true if field city is set (has been assigned a value) and false otherwise */
  public boolean isSetCity() {
    return this.city != null;
  }

  public void setCityIsSet(boolean value) {
    if (!value) {
      this.city = null;
    }
  }

  public java.lang.String getCountry() {
    return this.country;
  }

  public void setCountry(java.lang.String country) {
    this.country = country;
  }

  public void unsetCountry() {
    this.country = null;
  }

  /** Returns true if field country is set (has been assigned a value) and false otherwise */
  public boolean isSetCountry() {
    return this.country != null;
  }

  public void setCountryIsSet(boolean value) {
    if (!value) {
      this.country = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case LINES:
      if (value == null) {
        unsetLines();
      } else {
        setLines((java.util.List<java.lang.String>)value);
      }
      break;

    case ZIP_CODE:
      if (value == null) {
        unsetZipCode();
      } else {
        setZipCode((java.lang.String)value);
      }
      break;

    case CITY:
      if (value == null) {
        unsetCity();
      } else {
        setCity((java.lang.String)value);
      }
      break;

    case COUNTRY:
      if (value == null) {
        unsetCountry();
      } else {
        setCountry((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case LINES:
      return getLines();

    case ZIP_CODE:
      return getZipCode();

    case CITY:
      return getCity();

    case COUNTRY:
      return getCountry();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case LINES:
      return isSetLines();
    case ZIP_CODE:
      return isSetZipCode();
    case CITY:
      return isSetCity();
    case COUNTRY:
      return isSetCountry();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Address)
      return this.equals((Address)that);
    return false;
  }

  public boolean equals(Address that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_lines = true && this.isSetLines();
    boolean that_present_lines = true && that.isSetLines();
    if (this_present_lines || that_present_lines) {
      if (!(this_present_lines && that_present_lines))
        return false;
      if (!this.lines.equals(that.lines))
        return false;
    }

    boolean this_present_zipCode = true && this.isSetZipCode();
    boolean that_present_zipCode = true && that.isSetZipCode();
    if (this_present_zipCode || that_present_zipCode) {
      if (!(this_present_zipCode && that_present_zipCode))
        return false;
      if (!this.zipCode.equals(that.zipCode))
        return false;
    }

    boolean this_present_city = true && this.isSetCity();
    boolean that_present_city = true && that.isSetCity();
    if (this_present_city || that_present_city) {
      if (!(this_present_city && that_present_city))
        return false;
      if (!this.city.equals(that.city))
        return false;
    }

    boolean this_present_country = true && this.isSetCountry();
    boolean that_present_country = true && that.isSetCountry();
    if (this_present_country || that_present_country) {
      if (!(this_present_country && that_present_country))
        return false;
      if (!this.country.equals(that.country))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetLines()) ? 131071 : 524287);
    if (isSetLines())
      hashCode = hashCode * 8191 + lines.hashCode();

    hashCode = hashCode * 8191 + ((isSetZipCode()) ? 131071 : 524287);
    if (isSetZipCode())
      hashCode = hashCode * 8191 + zipCode.hashCode();

    hashCode = hashCode * 8191 + ((isSetCity()) ? 131071 : 524287);
    if (isSetCity())
      hashCode = hashCode * 8191 + city.hashCode();

    hashCode = hashCode * 8191 + ((isSetCountry()) ? 131071 : 524287);
    if (isSetCountry())
      hashCode = hashCode * 8191 + country.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Address other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetLines()).compareTo(other.isSetLines());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLines()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.lines, other.lines);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetZipCode()).compareTo(other.isSetZipCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetZipCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.zipCode, other.zipCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetCity()).compareTo(other.isSetCity());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCity()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.city, other.city);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetCountry()).compareTo(other.isSetCountry());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCountry()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.country, other.country);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Address(");
    boolean first = true;

    sb.append("lines:");
    if (this.lines == null) {
      sb.append("null");
    } else {
      sb.append(this.lines);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("zipCode:");
    if (this.zipCode == null) {
      sb.append("null");
    } else {
      sb.append(this.zipCode);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("city:");
    if (this.city == null) {
      sb.append("null");
    } else {
      sb.append(this.city);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("country:");
    if (this.country == null) {
      sb.append("null");
    } else {
      sb.append(this.country);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class AddressStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public AddressStandardScheme getScheme() {
      return new AddressStandardScheme();
    }
  }

  private static class AddressStandardScheme extends org.apache.thrift.scheme.StandardScheme<Address> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Address struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // LINES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.lines = new java.util.ArrayList<java.lang.String>(_list0.size);
                java.lang.String _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = iprot.readString();
                  struct.lines.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setLinesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ZIP_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.zipCode = iprot.readString();
              struct.setZipCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CITY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.city = iprot.readString();
              struct.setCityIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // COUNTRY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.country = iprot.readString();
              struct.setCountryIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Address struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.lines != null) {
        oprot.writeFieldBegin(LINES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.lines.size()));
          for (java.lang.String _iter3 : struct.lines)
          {
            oprot.writeString(_iter3);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.zipCode != null) {
        oprot.writeFieldBegin(ZIP_CODE_FIELD_DESC);
        oprot.writeString(struct.zipCode);
        oprot.writeFieldEnd();
      }
      if (struct.city != null) {
        oprot.writeFieldBegin(CITY_FIELD_DESC);
        oprot.writeString(struct.city);
        oprot.writeFieldEnd();
      }
      if (struct.country != null) {
        oprot.writeFieldBegin(COUNTRY_FIELD_DESC);
        oprot.writeString(struct.country);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class AddressTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public AddressTupleScheme getScheme() {
      return new AddressTupleScheme();
    }
  }

  private static class AddressTupleScheme extends org.apache.thrift.scheme.TupleScheme<Address> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Address struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetLines()) {
        optionals.set(0);
      }
      if (struct.isSetZipCode()) {
        optionals.set(1);
      }
      if (struct.isSetCity()) {
        optionals.set(2);
      }
      if (struct.isSetCountry()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetLines()) {
        {
          oprot.writeI32(struct.lines.size());
          for (java.lang.String _iter4 : struct.lines)
          {
            oprot.writeString(_iter4);
          }
        }
      }
      if (struct.isSetZipCode()) {
        oprot.writeString(struct.zipCode);
      }
      if (struct.isSetCity()) {
        oprot.writeString(struct.city);
      }
      if (struct.isSetCountry()) {
        oprot.writeString(struct.country);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Address struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.lines = new java.util.ArrayList<java.lang.String>(_list5.size);
          java.lang.String _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = iprot.readString();
            struct.lines.add(_elem6);
          }
        }
        struct.setLinesIsSet(true);
      }
      if (incoming.get(1)) {
        struct.zipCode = iprot.readString();
        struct.setZipCodeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.city = iprot.readString();
        struct.setCityIsSet(true);
      }
      if (incoming.get(3)) {
        struct.country = iprot.readString();
        struct.setCountryIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

