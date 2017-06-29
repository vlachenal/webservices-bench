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
}

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
