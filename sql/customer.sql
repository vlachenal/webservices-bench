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
