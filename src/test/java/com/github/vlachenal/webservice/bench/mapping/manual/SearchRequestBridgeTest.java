package com.github.vlachenal.webservice.bench.mapping.manual;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.xml.datatype.DatatypeConfigurationException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.vlachenal.webservice.bench.dto.SearchRequestDTO;
import com.github.vlachenal.webservice.bench.mapping.AbstractMappingTest;
import com.github.vlachenal.webservice.bench.soap.api.ListCustomersRequest;
import com.github.vlachenal.webservice.bench.thrift.api.ListAllRequest;


/**
 * Manual mapping unit tests for {@link SearchRequestBridge}
 *
 * @author Vincent Lachenal
 */
@DisplayName("Search request manual mapping test suite")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SearchRequestBridgeTest extends AbstractMappingTest {

  /** {@link SearchRequestBridgeTest} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(SearchRequestBridgeTest.class);

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.mapping.manual.SearchRequestBridge#fromSoap(com.github.vlachenal.webservice.bench.soap.api.ListCustomersRequest)}.
   *
   * @throws DatatypeConfigurationException can not happened ?
   */
  @DisplayName("SOAP to bean search request")
  @Test
  public void testFromSoap() throws DatatypeConfigurationException {
    LOG.debug("Enter in testFromSoap");
    final ListCustomersRequest soap = makeSOAPSearchRequest();
    final SearchRequestDTO dto = SearchRequestBridge.fromSoap(soap);
    assertAll(() -> assertNotNull(dto, "DTO is null"),
              () -> compareSearchRequest(dto, soap)
        );
    LOG.debug("Exit testFromSoap");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.mapping.manual.SearchRequestBridge#fromThrift(com.github.vlachenal.webservice.bench.thrift.api.ListAllRequest)}.
   */
  @DisplayName("Thrift to bean search request")
  @Test
  public void testFromThrift() {
    LOG.debug("Enter in testThriftToDTOSearchRequest");
    final ListAllRequest thrift = makeThriftSearchRequest();
    final SearchRequestDTO dto = SearchRequestBridge.fromThrift(thrift);
    assertAll(() -> assertNotNull(dto, "DTO is null"),
              () -> compareSearchRequest(dto, thrift));
    LOG.debug("Exit testThriftToDTOSearchRequest");
  }

}
