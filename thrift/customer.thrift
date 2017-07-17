/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
namespace java com.github.vlachenal.webservice.bench.thrift.api

enum PhoneType {
    LANDLINE = 1,
    MOBILE = 2
}

struct Phone {
    1: PhoneType type,
    2: string number
}

struct Address {
    1: list<string> lines,
    2: string zipCode,
    3: string city,
    4: string country
}

struct Customer {
    1: string id,
    2: string firstName,
    3: string lastName,
    4: i64 birthDate,
    5: string email,
    6: Address address,
    7: list<Phone> phones
}

enum ErrorCode {
    PARAMETER = 1,
    NOT_FOUND = 2,
    ALREADY_EXISTS = 3,
    SERVER = 99
}

exception CustomerException {
    1: ErrorCode code,
    2: string message
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
     * @return customers
     *
     * @throws CustomerException any error
     * @throws TException unexpected error
     */
    list<Customer> listCustomers() throws (1: CustomerException error);

    /**
     * Retrieve customer details
     *
     * @param id the customer identifier
     *
     * @return the customer details
     *
     * @throws CustomerException any error
     * @throws TException unexpected error
     */
    Customer get(1: string id) throws (1: CustomerException error);

    /**
     * Create customer
     *
     * @param customer the customer to create
     *
     * @return the new customer's identifier
     *
     * @throws CustomerException any error
     * @throws TException unexpected error
     */
    string create(1: Customer customer) throws (1: CustomerException error);

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
