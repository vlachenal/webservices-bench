package com.github.vlachenal.webservice.bench.mapping.manual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.vlachenal.webservice.bench.dto.PhoneDTO;
import com.github.vlachenal.webservice.bench.mapping.AbstractMappingTest;


/**
 * Phone manual mapping tests
 *
 * @author Vincent Lachenal
 */
public class PhoneBridgeTest extends AbstractMappingTest {

  /** DTOs */
  private static List<PhoneDTO> dtos;

  @BeforeClass
  public static void setUpBeforeClass() {
    dtos = new ArrayList<>();
    final PhoneDTO phone = new PhoneDTO();
    phone.setNumber("+33836656565");
    phone.setType(PhoneDTO.Type.LANDLINE);
    dtos.add(phone);
  }

  @Test
  public void testToRest() {
    final List<com.github.vlachenal.webservice.bench.rest.api.dto.Phone> phones = PhoneBridge.toRest(dtos);
    assertNotNull("Converted result is null", phones);
    assertEquals(dtos.size(), phones.size());
    for(int i = 0 ; i < dtos.size() ; ++i) {
      comparePhone(dtos.get(i), phones.get(i));
    }
  }

}
