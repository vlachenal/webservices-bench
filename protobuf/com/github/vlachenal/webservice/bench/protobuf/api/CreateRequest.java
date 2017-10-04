// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: customer.proto

package com.github.vlachenal.webservice.bench.protobuf.api;

/**
 * <pre>
 **
 * Create customer request
 * </pre>
 *
 * Protobuf type {@code webservicebench.CreateRequest}
 */
public  final class CreateRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:webservicebench.CreateRequest)
    CreateRequestOrBuilder {
  // Use CreateRequest.newBuilder() to construct.
  private CreateRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CreateRequest() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private CreateRequest(
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
          case 10: {
            com.github.vlachenal.webservice.bench.protobuf.api.Header.Builder subBuilder = null;
            if (header_ != null) {
              subBuilder = header_.toBuilder();
            }
            header_ = input.readMessage(com.github.vlachenal.webservice.bench.protobuf.api.Header.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(header_);
              header_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            com.github.vlachenal.webservice.bench.protobuf.api.Customer.Builder subBuilder = null;
            if (customer_ != null) {
              subBuilder = customer_.toBuilder();
            }
            customer_ = input.readMessage(com.github.vlachenal.webservice.bench.protobuf.api.Customer.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(customer_);
              customer_ = subBuilder.buildPartial();
            }

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
    return com.github.vlachenal.webservice.bench.protobuf.api.CustomerOuterClass.internal_static_webservicebench_CreateRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.github.vlachenal.webservice.bench.protobuf.api.CustomerOuterClass.internal_static_webservicebench_CreateRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest.class, com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest.Builder.class);
  }

  public static final int HEADER_FIELD_NUMBER = 1;
  private com.github.vlachenal.webservice.bench.protobuf.api.Header header_;
  /**
   * <pre>
   ** Request header 
   * </pre>
   *
   * <code>.webservicebench.Header header = 1;</code>
   */
  public boolean hasHeader() {
    return header_ != null;
  }
  /**
   * <pre>
   ** Request header 
   * </pre>
   *
   * <code>.webservicebench.Header header = 1;</code>
   */
  public com.github.vlachenal.webservice.bench.protobuf.api.Header getHeader() {
    return header_ == null ? com.github.vlachenal.webservice.bench.protobuf.api.Header.getDefaultInstance() : header_;
  }
  /**
   * <pre>
   ** Request header 
   * </pre>
   *
   * <code>.webservicebench.Header header = 1;</code>
   */
  public com.github.vlachenal.webservice.bench.protobuf.api.HeaderOrBuilder getHeaderOrBuilder() {
    return getHeader();
  }

  public static final int CUSTOMER_FIELD_NUMBER = 2;
  private com.github.vlachenal.webservice.bench.protobuf.api.Customer customer_;
  /**
   * <pre>
   ** Customer to create 
   * </pre>
   *
   * <code>.webservicebench.Customer customer = 2;</code>
   */
  public boolean hasCustomer() {
    return customer_ != null;
  }
  /**
   * <pre>
   ** Customer to create 
   * </pre>
   *
   * <code>.webservicebench.Customer customer = 2;</code>
   */
  public com.github.vlachenal.webservice.bench.protobuf.api.Customer getCustomer() {
    return customer_ == null ? com.github.vlachenal.webservice.bench.protobuf.api.Customer.getDefaultInstance() : customer_;
  }
  /**
   * <pre>
   ** Customer to create 
   * </pre>
   *
   * <code>.webservicebench.Customer customer = 2;</code>
   */
  public com.github.vlachenal.webservice.bench.protobuf.api.CustomerOrBuilder getCustomerOrBuilder() {
    return getCustomer();
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
    if (header_ != null) {
      output.writeMessage(1, getHeader());
    }
    if (customer_ != null) {
      output.writeMessage(2, getCustomer());
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (header_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getHeader());
    }
    if (customer_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getCustomer());
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
    if (!(obj instanceof com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest)) {
      return super.equals(obj);
    }
    com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest other = (com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest) obj;

    boolean result = true;
    result = result && (hasHeader() == other.hasHeader());
    if (hasHeader()) {
      result = result && getHeader()
          .equals(other.getHeader());
    }
    result = result && (hasCustomer() == other.hasCustomer());
    if (hasCustomer()) {
      result = result && getCustomer()
          .equals(other.getCustomer());
    }
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasHeader()) {
      hash = (37 * hash) + HEADER_FIELD_NUMBER;
      hash = (53 * hash) + getHeader().hashCode();
    }
    if (hasCustomer()) {
      hash = (37 * hash) + CUSTOMER_FIELD_NUMBER;
      hash = (53 * hash) + getCustomer().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest parseFrom(
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
  public static Builder newBuilder(com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest prototype) {
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
   * Create customer request
   * </pre>
   *
   * Protobuf type {@code webservicebench.CreateRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:webservicebench.CreateRequest)
      com.github.vlachenal.webservice.bench.protobuf.api.CreateRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.github.vlachenal.webservice.bench.protobuf.api.CustomerOuterClass.internal_static_webservicebench_CreateRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.github.vlachenal.webservice.bench.protobuf.api.CustomerOuterClass.internal_static_webservicebench_CreateRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest.class, com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest.Builder.class);
    }

    // Construct using com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest.newBuilder()
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
      if (headerBuilder_ == null) {
        header_ = null;
      } else {
        header_ = null;
        headerBuilder_ = null;
      }
      if (customerBuilder_ == null) {
        customer_ = null;
      } else {
        customer_ = null;
        customerBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.github.vlachenal.webservice.bench.protobuf.api.CustomerOuterClass.internal_static_webservicebench_CreateRequest_descriptor;
    }

    public com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest getDefaultInstanceForType() {
      return com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest.getDefaultInstance();
    }

    public com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest build() {
      com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest buildPartial() {
      com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest result = new com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest(this);
      if (headerBuilder_ == null) {
        result.header_ = header_;
      } else {
        result.header_ = headerBuilder_.build();
      }
      if (customerBuilder_ == null) {
        result.customer_ = customer_;
      } else {
        result.customer_ = customerBuilder_.build();
      }
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
      if (other instanceof com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest) {
        return mergeFrom((com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest other) {
      if (other == com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest.getDefaultInstance()) return this;
      if (other.hasHeader()) {
        mergeHeader(other.getHeader());
      }
      if (other.hasCustomer()) {
        mergeCustomer(other.getCustomer());
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
      com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.github.vlachenal.webservice.bench.protobuf.api.Header header_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.github.vlachenal.webservice.bench.protobuf.api.Header, com.github.vlachenal.webservice.bench.protobuf.api.Header.Builder, com.github.vlachenal.webservice.bench.protobuf.api.HeaderOrBuilder> headerBuilder_;
    /**
     * <pre>
     ** Request header 
     * </pre>
     *
     * <code>.webservicebench.Header header = 1;</code>
     */
    public boolean hasHeader() {
      return headerBuilder_ != null || header_ != null;
    }
    /**
     * <pre>
     ** Request header 
     * </pre>
     *
     * <code>.webservicebench.Header header = 1;</code>
     */
    public com.github.vlachenal.webservice.bench.protobuf.api.Header getHeader() {
      if (headerBuilder_ == null) {
        return header_ == null ? com.github.vlachenal.webservice.bench.protobuf.api.Header.getDefaultInstance() : header_;
      } else {
        return headerBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     ** Request header 
     * </pre>
     *
     * <code>.webservicebench.Header header = 1;</code>
     */
    public Builder setHeader(com.github.vlachenal.webservice.bench.protobuf.api.Header value) {
      if (headerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        header_ = value;
        onChanged();
      } else {
        headerBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     ** Request header 
     * </pre>
     *
     * <code>.webservicebench.Header header = 1;</code>
     */
    public Builder setHeader(
        com.github.vlachenal.webservice.bench.protobuf.api.Header.Builder builderForValue) {
      if (headerBuilder_ == null) {
        header_ = builderForValue.build();
        onChanged();
      } else {
        headerBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     ** Request header 
     * </pre>
     *
     * <code>.webservicebench.Header header = 1;</code>
     */
    public Builder mergeHeader(com.github.vlachenal.webservice.bench.protobuf.api.Header value) {
      if (headerBuilder_ == null) {
        if (header_ != null) {
          header_ =
            com.github.vlachenal.webservice.bench.protobuf.api.Header.newBuilder(header_).mergeFrom(value).buildPartial();
        } else {
          header_ = value;
        }
        onChanged();
      } else {
        headerBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     ** Request header 
     * </pre>
     *
     * <code>.webservicebench.Header header = 1;</code>
     */
    public Builder clearHeader() {
      if (headerBuilder_ == null) {
        header_ = null;
        onChanged();
      } else {
        header_ = null;
        headerBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     ** Request header 
     * </pre>
     *
     * <code>.webservicebench.Header header = 1;</code>
     */
    public com.github.vlachenal.webservice.bench.protobuf.api.Header.Builder getHeaderBuilder() {
      
      onChanged();
      return getHeaderFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     ** Request header 
     * </pre>
     *
     * <code>.webservicebench.Header header = 1;</code>
     */
    public com.github.vlachenal.webservice.bench.protobuf.api.HeaderOrBuilder getHeaderOrBuilder() {
      if (headerBuilder_ != null) {
        return headerBuilder_.getMessageOrBuilder();
      } else {
        return header_ == null ?
            com.github.vlachenal.webservice.bench.protobuf.api.Header.getDefaultInstance() : header_;
      }
    }
    /**
     * <pre>
     ** Request header 
     * </pre>
     *
     * <code>.webservicebench.Header header = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.github.vlachenal.webservice.bench.protobuf.api.Header, com.github.vlachenal.webservice.bench.protobuf.api.Header.Builder, com.github.vlachenal.webservice.bench.protobuf.api.HeaderOrBuilder> 
        getHeaderFieldBuilder() {
      if (headerBuilder_ == null) {
        headerBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.github.vlachenal.webservice.bench.protobuf.api.Header, com.github.vlachenal.webservice.bench.protobuf.api.Header.Builder, com.github.vlachenal.webservice.bench.protobuf.api.HeaderOrBuilder>(
                getHeader(),
                getParentForChildren(),
                isClean());
        header_ = null;
      }
      return headerBuilder_;
    }

    private com.github.vlachenal.webservice.bench.protobuf.api.Customer customer_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.github.vlachenal.webservice.bench.protobuf.api.Customer, com.github.vlachenal.webservice.bench.protobuf.api.Customer.Builder, com.github.vlachenal.webservice.bench.protobuf.api.CustomerOrBuilder> customerBuilder_;
    /**
     * <pre>
     ** Customer to create 
     * </pre>
     *
     * <code>.webservicebench.Customer customer = 2;</code>
     */
    public boolean hasCustomer() {
      return customerBuilder_ != null || customer_ != null;
    }
    /**
     * <pre>
     ** Customer to create 
     * </pre>
     *
     * <code>.webservicebench.Customer customer = 2;</code>
     */
    public com.github.vlachenal.webservice.bench.protobuf.api.Customer getCustomer() {
      if (customerBuilder_ == null) {
        return customer_ == null ? com.github.vlachenal.webservice.bench.protobuf.api.Customer.getDefaultInstance() : customer_;
      } else {
        return customerBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     ** Customer to create 
     * </pre>
     *
     * <code>.webservicebench.Customer customer = 2;</code>
     */
    public Builder setCustomer(com.github.vlachenal.webservice.bench.protobuf.api.Customer value) {
      if (customerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        customer_ = value;
        onChanged();
      } else {
        customerBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     ** Customer to create 
     * </pre>
     *
     * <code>.webservicebench.Customer customer = 2;</code>
     */
    public Builder setCustomer(
        com.github.vlachenal.webservice.bench.protobuf.api.Customer.Builder builderForValue) {
      if (customerBuilder_ == null) {
        customer_ = builderForValue.build();
        onChanged();
      } else {
        customerBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     ** Customer to create 
     * </pre>
     *
     * <code>.webservicebench.Customer customer = 2;</code>
     */
    public Builder mergeCustomer(com.github.vlachenal.webservice.bench.protobuf.api.Customer value) {
      if (customerBuilder_ == null) {
        if (customer_ != null) {
          customer_ =
            com.github.vlachenal.webservice.bench.protobuf.api.Customer.newBuilder(customer_).mergeFrom(value).buildPartial();
        } else {
          customer_ = value;
        }
        onChanged();
      } else {
        customerBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     ** Customer to create 
     * </pre>
     *
     * <code>.webservicebench.Customer customer = 2;</code>
     */
    public Builder clearCustomer() {
      if (customerBuilder_ == null) {
        customer_ = null;
        onChanged();
      } else {
        customer_ = null;
        customerBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     ** Customer to create 
     * </pre>
     *
     * <code>.webservicebench.Customer customer = 2;</code>
     */
    public com.github.vlachenal.webservice.bench.protobuf.api.Customer.Builder getCustomerBuilder() {
      
      onChanged();
      return getCustomerFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     ** Customer to create 
     * </pre>
     *
     * <code>.webservicebench.Customer customer = 2;</code>
     */
    public com.github.vlachenal.webservice.bench.protobuf.api.CustomerOrBuilder getCustomerOrBuilder() {
      if (customerBuilder_ != null) {
        return customerBuilder_.getMessageOrBuilder();
      } else {
        return customer_ == null ?
            com.github.vlachenal.webservice.bench.protobuf.api.Customer.getDefaultInstance() : customer_;
      }
    }
    /**
     * <pre>
     ** Customer to create 
     * </pre>
     *
     * <code>.webservicebench.Customer customer = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.github.vlachenal.webservice.bench.protobuf.api.Customer, com.github.vlachenal.webservice.bench.protobuf.api.Customer.Builder, com.github.vlachenal.webservice.bench.protobuf.api.CustomerOrBuilder> 
        getCustomerFieldBuilder() {
      if (customerBuilder_ == null) {
        customerBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.github.vlachenal.webservice.bench.protobuf.api.Customer, com.github.vlachenal.webservice.bench.protobuf.api.Customer.Builder, com.github.vlachenal.webservice.bench.protobuf.api.CustomerOrBuilder>(
                getCustomer(),
                getParentForChildren(),
                isClean());
        customer_ = null;
      }
      return customerBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:webservicebench.CreateRequest)
  }

  // @@protoc_insertion_point(class_scope:webservicebench.CreateRequest)
  private static final com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest();
  }

  public static com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CreateRequest>
      PARSER = new com.google.protobuf.AbstractParser<CreateRequest>() {
    public CreateRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new CreateRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CreateRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CreateRequest> getParserForType() {
    return PARSER;
  }

  public com.github.vlachenal.webservice.bench.protobuf.api.CreateRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

