package com.github.vlachenal.webservice.bench.mapping.manual;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.vlachenal.webservice.bench.dto.PhoneDTO;
import com.github.vlachenal.webservice.bench.mapping.AbstractMappingTest;


/**
 * Phone manual mapping tests
 *
 * @author Vincent Lachenal
 */
@DisplayName("Phone manual mapping test suite")
public class PhoneBridgeTest extends AbstractMappingTest {

  /** DTOs */
  private static List<PhoneDTO> dtos;

  /**
   * Initialization
   */
  @BeforeAll
  public static void setUpBeforeClass() {
    dtos = new ArrayList<>();
    final PhoneDTO phone = new PhoneDTO();
    phone.setNumber("+33836656565");
    phone.setType(PhoneDTO.Type.LANDLINE);
    dtos.add(phone);
  }

  /**
   * Test bean to REST
   */
  @DisplayName("Bean to REST customer")
  @Test
  public void testToRest() {
    final List<com.github.vlachenal.webservice.bench.rest.api.model.Phone> phones = PhoneBridge.toRest(dtos);
    assertAll("Compare phones",
              () -> assertNotNull(phones, "Converted result is null"),
              () -> assertEquals(dtos.size(), phones.size())
        );
    for(int i = 0 ; i < dtos.size() ; ++i) {
      comparePhone(dtos.get(i), phones.get(i));
    }
  }

}
