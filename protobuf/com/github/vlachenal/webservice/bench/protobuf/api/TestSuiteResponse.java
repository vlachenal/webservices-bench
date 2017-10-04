// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: customer.proto

package com.github.vlachenal.webservice.bench.protobuf.api;

/**
 * Protobuf type {@code webservicebench.TestSuiteResponse}
 */
public  final class TestSuiteResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:webservicebench.TestSuiteResponse)
    TestSuiteResponseOrBuilder {
  // Use TestSuiteResponse.newBuilder() to construct.
  private TestSuiteResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TestSuiteResponse() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private TestSuiteResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
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
    return com.github.vlachenal.webservice.bench.protobuf.api.CustomerOuterClass.internal_static_webservicebench_TestSuiteResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.github.vlachenal.webservice.bench.protobuf.api.CustomerOuterClass.internal_static_webservicebench_TestSuiteResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse.class, com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse.Builder.class);
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
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse)) {
      return super.equals(obj);
    }
    com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse other = (com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse) obj;

    boolean result = true;
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse parseFrom(
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
  public static Builder newBuilder(com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse prototype) {
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
   * Protobuf type {@code webservicebench.TestSuiteResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:webservicebench.TestSuiteResponse)
      com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.github.vlachenal.webservice.bench.protobuf.api.CustomerOuterClass.internal_static_webservicebench_TestSuiteResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.github.vlachenal.webservice.bench.protobuf.api.CustomerOuterClass.internal_static_webservicebench_TestSuiteResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse.class, com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse.Builder.class);
    }

    // Construct using com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse.newBuilder()
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
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.github.vlachenal.webservice.bench.protobuf.api.CustomerOuterClass.internal_static_webservicebench_TestSuiteResponse_descriptor;
    }

    public com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse getDefaultInstanceForType() {
      return com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse.getDefaultInstance();
    }

    public com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse build() {
      com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse buildPartial() {
      com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse result = new com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse(this);
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
      if (other instanceof com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse) {
        return mergeFrom((com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse other) {
      if (other == com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse.getDefaultInstance()) return this;
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
      com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
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


    // @@protoc_insertion_point(builder_scope:webservicebench.TestSuiteResponse)
  }

  // @@protoc_insertion_point(class_scope:webservicebench.TestSuiteResponse)
  private static final com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse();
  }

  public static com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TestSuiteResponse>
      PARSER = new com.google.protobuf.AbstractParser<TestSuiteResponse>() {
    public TestSuiteResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new TestSuiteResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TestSuiteResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TestSuiteResponse> getParserForType() {
    return PARSER;
  }

  public com.github.vlachenal.webservice.bench.protobuf.api.TestSuiteResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

