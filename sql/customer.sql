--
-- Copyright © 2017 Vincent Lachenal
-- This work is free. You can redistribute it and/or modify it under the
-- terms of the Do What The Fuck You Want To Public License, Version 2,
-- as published by Sam Hocevar. See the COPYING file for more details.
--

--
-- to create PostgreSQL user: $ createuser -U postgres -P apibenchmark
-- to create PostgreSQL database: $ createdb -U postgres -O apibenchmark apibenchmark
--

CREATE TABLE Customer (
    id UUID PRIMARY KEY,
    first_name VARCHAR(256) NOT NULL DEFAULT 'John',
    last_name VARCHAR(256) NOT NULL DEFAULT 'Doe',
    birth_date DATE NOT NULL,
    email VARCHAR(512)
);

CREATE TABLE Address (
    id SERIAL PRIMARY KEY,
    customer_id UUID NOT NULL REFERENCES Customer(id) ON DELETE CASCADE,
    line1 VARCHAR(256) NOT NULL,
    line2 VARCHAR(256),
    line3 VARCHAR(256),
    line4 VARCHAR(256),
    line5 VARCHAR(256),
    line6 VARCHAR(256),
    zip_code CHAR(10) NOT NULL,
    city VARCHAR(128) NOT NULL,
    country VARCHAR(64) NOT NULL
);

CREATE TABLE Phone (
    id SERIAL PRIMARY KEY,
    customer_id UUID NOT NULL REFERENCES Customer(id) ON DELETE CASCADE,
    phone_type SMALLINT,
    number CHAR(15)
);

CREATE TABLE TestSuite (
    id UUID PRIMARY KEY,
    client_cpu VARCHAR(64) NOT NULL,
    client_memory VARCHAR(64) NOT NULL,
    client_jvm_version VARCHAR(128) NOT NULL,
    client_jvm_vendor VARCHAR(128) NOT NULL,
    client_os_name VARCHAR(128) NOT NULL,
    client_os_version VARCHAR(128) NOT NULL,
    server_cpu VARCHAR(64) NOT NULL,
    server_memory VARCHAR(64) NOT NULL,
    server_jvm_version VARCHAR(128) NOT NULL,
    server_jvm_vendor VARCHAR(128) NOT NULL,
    server_os_name VARCHAR(128) NOT NULL,
    server_os_version VARCHAR(128) NOT NULL,
    protocol VARCHAR(64) NOT NULL,
    compression CHAR(8),
    nb_threads INTEGER NOT NULL,
    comment VARCHAR(1024)
);

CREATE TABLE TestCall (
    request_seq INTEGER NOT NULL,
    test_suite_id UUID NOT NULL REFERENCES TestSuite(id) ON DELETE CASCADE,
    method VARCHAR(32) NOT NULL,
    client_start BIGINT NOT NULL,
    server_start BIGINT NOT NULL,
    server_end BIGINT NOT NULL,
    client_end BIGINT NOT NULL,
    ok BOOLEAN NOT NULL,
    error_message VARCHAR(512),
    PRIMARY KEY (request_seq, test_suite_id, method)
);
CREATE INDEX TestCall_test_suite_id_idx ON TestCall(test_suite_id);
CREATE INDEX TestCall_method_idx ON TestCall(method);
CREATE INDEX TestCall_test_suite_id_method_idx ON TestCall(test_suite_id,method);
