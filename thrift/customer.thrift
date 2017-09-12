/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
namespace java com.github.vlachenal.webservice.bench.thrift.api

/**
 * Phone type
 */
enum PhoneType {
    /** Landline phone */
    LANDLINE = 1,

    /** Mobile phone */
    MOBILE = 2
}

/**
 * Phone
 */
struct Phone {
    /** Phone type */
    1: PhoneType type,

    /** Phone number */
    2: string number
}

/**
 * Address
 */
struct Address {
    /** Lines */
    1: list<string> lines,

    /** ZIP code */
    2: string zipCode,

    /** City */
    3: string city,

    /** Country */
    4: string country
}

/**
 * Customer
 */
struct Customer {
    /** Identifier */
    1: string id,

    /** First name */
    2: string firstName,

    /** Last name */
    3: string lastName,

    /** Brith date */
    4: i64 birthDate,

    /** Email address */
    5: string email,

    /** Address */
    6: Address address,

    /** Phones */
    7: list<Phone> phones
}

/**
 * Error code
 */
enum ErrorCode {
    /** Missing or invalid paramter */
    PARAMETER = 1,

    /** Not found */
    NOT_FOUND = 2,

    /** Customer already exists */
    ALREADY_EXISTS = 3,

    /** Unexpected error */
    SERVER = 99
}

/**
 * Customer service error
 */
exception CustomerException {
    /** Error code */
    1: ErrorCode code,

    /** Error message */
    2: string message
}

/**
 * Mapper type
 */
enum Mapper {
    /** Manual mapping */
    MANUAL = 1,

    /** Dozer mapper */
    DOZER = 2,

    /** MapStruct */
    MAPSTRUCT = 3
}

/**
 * Request header
 */
struct Header {
    /** Request sequence */
    1: i32 requestSeq,

    /** Mapper to use */
    2: Mapper mapper,
}

/**
 * Create customer request
 */
struct CreateRequest {
    /** Request header */
    1: Header header,

    /** Customer to create */
    2: Customer customer,
}

/**
 * Get details request
 */
struct GetRequest {
    /** Header */
    1: Header header,

    /** Customer to retrieve identifier */
    2: string id,
}

/**
 * List all customers request
 */
struct ListAllRequest {
    /** Request header */
    1: Header header,
}

/**
 * Customer service
 *
 * @author Vincent Lachenal
 */
service CustomerService {
    /**
     * List all customers in database
     *
     * @param request the request
     *
     * @return customers
     *
     * @throws CustomerException any error
     * @throws TException unexpected error
     */
    list<Customer> listCustomers(1: ListAllRequest request) throws (1: CustomerException error);

    /**
     * Retrieve customer details
     *
     * @param request the get details request
     *
     * @return the customer details
     *
     * @throws CustomerException any error
     * @throws TException unexpected error
     */
    Customer get(1: GetRequest request) throws (1: CustomerException error);

    /**
     * Create customer
     *
     * @param request the create request
     *
     * @return the new customer's identifier
     *
     * @throws CustomerException any error
     * @throws TException unexpected error
     */
    string create(1: CreateRequest request) throws (1: CustomerException error);

    /**
     * Delete all customers
     *
     * @throws CustomerException any error
     * @throws TException unexpected error 
     */
    void deleteAll() throws (1: CustomerException error);

}

exception StatsException {
    1: string message
}

struct ClientCall {
    /** Request sequence identifier */
    1: required i32 requestSeq,

    /** Protocol (always 'thrift') */
    2: string protocol = "thrift",

    /** The method which has been called */
    3: required string method,

    /** Client start timestamp */
    4: required i64 clientStart,

    /** Client end timestamp */
    5: required i64 clientEnd,

    /** Call status */
    6: required bool ok = false,

    /** Error message */
    7: string errMsg,
}

struct TestSuite {
    /** Test suite UUID */
    1: string id,

    /** Number of simultaneous call */
    2: required i32 nbThread,

    /** Compression type */
    3: optional string compression,

    /** Client CPU model */
    4: required string cpu,

    /** Client RAM */
    5: required string memory,

    /** Client JVM version */
    6: required string jvm,

    /** Client JVM vendor */
    7: required string vendor,

    /** Client OS family */
    8: required string osFamily,

    /** Client OS version */
    9: required string osVersion,

    /** Protocol */
    10: string protocol;

    /** Test suite comments */
    11: string comment,

    /** Client call statistics */
    12: required list<ClientCall> calls,

    /** Mapper which has been used */
    13: Mapper mapper,
}

/**
 * Statistics service
 *
 * @author Vincent Lachenal
 */
service StatsService {

    /**
     * Consolidate statistics
     *
     * @param test the test suite
     *
     * @throws StatsException any error
     * @throws TException unexpected error
     */
    void consolidate(1: TestSuite test) throws (1: StatsException error);

    /**
     * Purge server side statistics
     *
     * @throws StatsException any error
     * @throws TException unexpected error 
     */
    void purge() throws (1: StatsException error);

}
