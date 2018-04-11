package com.github.vlachenal.webservice.bench.mapping.manual;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.mapping.AbstractMappingTest;

/**
 * Customer bridge unit tests
 *
 * @author Vincent Lachenal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerBridgeTest extends AbstractMappingTest {

  // Attributes +
  /** {@link CustomerBridgeTest} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(CustomerBridgeTest.class);
  // Attributes -


  // Tests +
  /**
   * Customer bean to SOAP conversion unit test
   */
  @Test
  public void testBeanToSOAPCustomer() {
    LOG.debug("Enter in testBeanToSOAPCustomer");
    final CustomerDTO bean = makeCustomerBean();
    final com.github.vlachenal.webservice.bench.soap.api.Customer customer = CustomerBridge.toSoap(bean);
    assertNotNull("SOAP customer is null", customer);
    compareCustomer(bean, customer);
    LOG.debug("Exit testBeanToSOAPCustomer");
  }

  /**
   * Customer bean to Thrift conversion unit test
   */
  @Test
  public void testBeanToThriftCustomer() {
    LOG.debug("Enter in testBeanToThriftCustomer");
    final CustomerDTO bean = makeCustomerBean();
    final com.github.vlachenal.webservice.bench.thrift.api.Customer customer = CustomerBridge.toThrift(bean);
    assertNotNull("Thrift customer is null", customer);
    compareCustomer(bean, customer);
    LOG.debug("Exit testBeanToThriftCustomer");
  }

  /**
   * Customer bean to REST conversion unit test
   */
  @Test
  public void testBeanToRESTCustomer() {
    LOG.debug("Enter in testBeanToRESTCustomer");
    final CustomerDTO bean = makeCustomerBean();
    final com.github.vlachenal.webservice.bench.rest.api.dto.Customer customer = CustomerBridge.toRest(bean);
    assertNotNull("REST customer is null", customer);
    compareCustomer(bean, customer);
    LOG.debug("Exit testBeanToRESTCustomer");
  }

  /**
   * Customer bean to Protocol Buffer conversion unit test
   */
  @Test
  public void testBeanToProtobufCustomer() {
    LOG.debug("Enter in testBeanToProtobufCustomer");
    final CustomerDTO bean = makeCustomerBean();
    final com.github.vlachenal.webservice.bench.protobuf.api.Customer customer = CustomerBridge.toProtobuf(bean);
    assertNotNull("Protocol Buffers customer is null", customer);
    compareCustomer(bean, customer);
    LOG.debug("Exit testBeanToProtobufCustomer");
  }
  // Tests -

}
