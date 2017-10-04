// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: customer.proto

package com.github.vlachenal.webservice.bench.protobuf.api;

/**
 * <pre>
 **
 * Request header
 * </pre>
 *
 * Protobuf type {@code webservicebench.Header}
 */
public  final class Header extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:webservicebench.Header)
    HeaderOrBuilder {
  // Use Header.newBuilder() to construct.
  private Header(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Header() {
    requestSeq_ = 0;
    mapper_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private Header(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            requestSeq_ = input.readInt32();
            break;
          }
          case 16: {
            int rawValue = input.readEnum();

            mapper_ = rawValue;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.github.vlachenal.webservice.bench.protobuf.api.CustomerOuterClass.internal_static_webservicebench_Header_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.github.vlachenal.webservice.bench.protobuf.api.CustomerOuterClass.internal_static_webservicebench_Header_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.github.vlachenal.webservice.bench.protobuf.api.Header.class, com.github.vlachenal.webservice.bench.protobuf.api.Header.Builder.class);
  }

  public static final int REQUESTSEQ_FIELD_NUMBER = 1;
  private int requestSeq_;
  /**
   * <pre>
   ** Request sequence 
   * </pre>
   *
   * <code>int32 requestSeq = 1;</code>
   */
  public int getRequestSeq() {
    return requestSeq_;
  }

  public static final int MAPPER_FIELD_NUMBER = 2;
  private int mapper_;
  /**
   * <pre>
   ** Mapper to use 
   * </pre>
   *
   * <code>.webservicebench.Mapper mapper = 2;</code>
   */
  public int getMapperValue() {
    return mapper_;
  }
  /**
   * <pre>
   ** Mapper to use 
   * </pre>
   *
   * <code>.webservicebench.Mapper mapper = 2;</code>
   */
  public com.github.vlachenal.webservice.bench.protobuf.api.Mapper getMapper() {
    com.github.vlachenal.webservice.bench.protobuf.api.Mapper result = com.github.vlachenal.webservice.bench.protobuf.api.Mapper.valueOf(mapper_);
    return result == null ? com.github.vlachenal.webservice.bench.protobuf.api.Mapper.UNRECOGNIZED : result;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (requestSeq_ != 0) {
      output.writeInt32(1, requestSeq_);
    }
    if (mapper_ != com.github.vlachenal.webservice.bench.protobuf.api.Mapper.MANUAL.getNumber()) {
      output.writeEnum(2, mapper_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (requestSeq_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, requestSeq_);
    }
    if (mapper_ != com.github.vlachenal.webservice.bench.protobuf.api.Mapper.MANUAL.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, mapper_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.github.vlachenal.webservice.bench.protobuf.api.Header)) {
      return super.equals(obj);
    }
    com.github.vlachenal.webservice.bench.protobuf.api.Header other = (com.github.vlachenal.webservice.bench.protobuf.api.Header) obj;

    boolean result = true;
    result = result && (getRequestSeq()
        == other.getRequestSeq());
    result = result && mapper_ == other.mapper_;
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + REQUESTSEQ_FIELD_NUMBER;
    hash = (53 * hash) + getRequestSeq();
    hash = (37 * hash) + MAPPER_FIELD_NUMBER;
    hash = (53 * hash) + mapper_;
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.github.vlachenal.webservice.bench.protobuf.api.Header parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.Header parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.Header parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.Header parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.Header parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.Header parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.Header parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.Header parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.Header parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.Header parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.Header parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.Header parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.github.vlachenal.webservice.bench.protobuf.api.Header prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   **
   * Request header
   * </pre>
   *
   * Protobuf type {@code webservicebench.Header}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:webservicebench.Header)
      com.github.vlachenal.webservice.bench.protobuf.api.HeaderOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.github.vlachenal.webservice.bench.protobuf.api.CustomerOuterClass.internal_static_webservicebench_Header_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.github.vlachenal.webservice.bench.protobuf.api.CustomerOuterClass.internal_static_webservicebench_Header_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.github.vlachenal.webservice.bench.protobuf.api.Header.class, com.github.vlachenal.webservice.bench.protobuf.api.Header.Builder.class);
    }

    // Construct using com.github.vlachenal.webservice.bench.protobuf.api.Header.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      requestSeq_ = 0;

      mapper_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.github.vlachenal.webservice.bench.protobuf.api.CustomerOuterClass.internal_static_webservicebench_Header_descriptor;
    }

    public com.github.vlachenal.webservice.bench.protobuf.api.Header getDefaultInstanceForType() {
      return com.github.vlachenal.webservice.bench.protobuf.api.Header.getDefaultInstance();
    }

    public com.github.vlachenal.webservice.bench.protobuf.api.Header build() {
      com.github.vlachenal.webservice.bench.protobuf.api.Header result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.github.vlachenal.webservice.bench.protobuf.api.Header buildPartial() {
      com.github.vlachenal.webservice.bench.protobuf.api.Header result = new com.github.vlachenal.webservice.bench.protobuf.api.Header(this);
      result.requestSeq_ = requestSeq_;
      result.mapper_ = mapper_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.github.vlachenal.webservice.bench.protobuf.api.Header) {
        return mergeFrom((com.github.vlachenal.webservice.bench.protobuf.api.Header)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.github.vlachenal.webservice.bench.protobuf.api.Header other) {
      if (other == com.github.vlachenal.webservice.bench.protobuf.api.Header.getDefaultInstance()) return this;
      if (other.getRequestSeq() != 0) {
        setRequestSeq(other.getRequestSeq());
      }
      if (other.mapper_ != 0) {
        setMapperValue(other.getMapperValue());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.github.vlachenal.webservice.bench.protobuf.api.Header parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.github.vlachenal.webservice.bench.protobuf.api.Header) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int requestSeq_ ;
    /**
     * <pre>
     ** Request sequence 
     * </pre>
     *
     * <code>int32 requestSeq = 1;</code>
     */
    public int getRequestSeq() {
      return requestSeq_;
    }
    /**
     * <pre>
     ** Request sequence 
     * </pre>
     *
     * <code>int32 requestSeq = 1;</code>
     */
    public Builder setRequestSeq(int value) {
      
      requestSeq_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     ** Request sequence 
     * </pre>
     *
     * <code>int32 requestSeq = 1;</code>
     */
    public Builder clearRequestSeq() {
      
      requestSeq_ = 0;
      onChanged();
      return this;
    }

    private int mapper_ = 0;
    /**
     * <pre>
     ** Mapper to use 
     * </pre>
     *
     * <code>.webservicebench.Mapper mapper = 2;</code>
     */
    public int getMapperValue() {
      return mapper_;
    }
    /**
     * <pre>
     ** Mapper to use 
     * </pre>
     *
     * <code>.webservicebench.Mapper mapper = 2;</code>
     */
    public Builder setMapperValue(int value) {
      mapper_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     ** Mapper to use 
     * </pre>
     *
     * <code>.webservicebench.Mapper mapper = 2;</code>
     */
    public com.github.vlachenal.webservice.bench.protobuf.api.Mapper getMapper() {
      com.github.vlachenal.webservice.bench.protobuf.api.Mapper result = com.github.vlachenal.webservice.bench.protobuf.api.Mapper.valueOf(mapper_);
      return result == null ? com.github.vlachenal.webservice.bench.protobuf.api.Mapper.UNRECOGNIZED : result;
    }
    /**
     * <pre>
     ** Mapper to use 
     * </pre>
     *
     * <code>.webservicebench.Mapper mapper = 2;</code>
     */
    public Builder setMapper(com.github.vlachenal.webservice.bench.protobuf.api.Mapper value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      mapper_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <pre>
     ** Mapper to use 
     * </pre>
     *
     * <code>.webservicebench.Mapper mapper = 2;</code>
     */
    public Builder clearMapper() {
      
      mapper_ = 0;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:webservicebench.Header)
  }

  // @@protoc_insertion_point(class_scope:webservicebench.Header)
  private static final com.github.vlachenal.webservice.bench.protobuf.api.Header DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.github.vlachenal.webservice.bench.protobuf.api.Header();
  }

  public static com.github.vlachenal.webservice.bench.protobuf.api.Header getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Header>
      PARSER = new com.google.protobuf.AbstractParser<Header>() {
    public Header parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new Header(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Header> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Header> getParserForType() {
    return PARSER;
  }

  public com.github.vlachenal.webservice.bench.protobuf.api.Header getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

