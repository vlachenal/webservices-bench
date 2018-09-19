package com.github.vlachenal.webservice.bench.mapping.manual;

import static org.junit.Assert.assertNotNull;

import javax.xml.datatype.DatatypeConfigurationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.vlachenal.webservice.bench.dto.SearchRequestDTO;
import com.github.vlachenal.webservice.bench.mapping.AbstractMappingTest;
import com.github.vlachenal.webservice.bench.soap.api.ListCustomersRequest;
import com.github.vlachenal.webservice.bench.thrift.api.ListAllRequest;


/**
 * Manual mapping unit tests for {@link SearchRequestBridge}
 *
 * @author Vincent Lachenal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SearchRequestBridgeTest extends AbstractMappingTest {

  /** {@link SearchRequestBridgeTest} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(SearchRequestBridgeTest.class);

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.mapping.manual.SearchRequestBridge#fromSoap(com.github.vlachenal.webservice.bench.soap.api.ListCustomersRequest)}.
   *
   * @throws DatatypeConfigurationException can not happened ?
   */
  @Test
  public void testFromSoap() throws DatatypeConfigurationException {
    LOG.debug("Enter in testFromSoap");
    final ListCustomersRequest soap = makeSOAPSearchRequest();
    final SearchRequestDTO dto = SearchRequestBridge.fromSoap(soap);
    assertNotNull("DTO is null", dto);
    compareSearchRequest(dto, soap);
    LOG.debug("Exit testFromSoap");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.mapping.manual.SearchRequestBridge#fromThrift(com.github.vlachenal.webservice.bench.thrift.api.ListAllRequest)}.
   */
  @Test
  public void testFromThrift() {
    LOG.debug("Enter in testThriftToDTOSearchRequest");
    final ListAllRequest thrift = makeThriftSearchRequest();
    final SearchRequestDTO dto = SearchRequestBridge.fromThrift(thrift);
    assertNotNull("DTO is null", dto);
    compareSearchRequest(dto, thrift);
    LOG.debug("Exit testThriftToDTOSearchRequest");
  }

}
