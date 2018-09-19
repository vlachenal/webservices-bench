package com.github.vlachenal.webservice.bench.mapping.mapstruct;

import org.mapstruct.Mapper;

import com.github.vlachenal.webservice.bench.dto.SearchRequestDTO;
import com.github.vlachenal.webservice.bench.soap.api.ListCustomersRequest;
import com.github.vlachenal.webservice.bench.thrift.api.ListAllRequest;


/**
 * Search request mapper
 *
 * @author Vincent Lachenal
 */
@Mapper(componentModel = "spring", uses = { LongDateMapper.class })
public interface SearchRequestMapper {

  /**
   * Convert SOAP search request to DTO
   *
   * @param request the SOAP search request
   *
   * @return the DTO
   */
  SearchRequestDTO fromSoap(ListCustomersRequest request);

  /**
   * Convert Thrift search request to DTO
   *
   * @param request the Thrift search request
   *
   * @return the DTO
   */
  SearchRequestDTO fromThrift(ListAllRequest request);

}
