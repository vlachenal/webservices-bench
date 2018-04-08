package com.github.vlachenal.webservice.bench.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.cache.StatisticsCache;
import com.github.vlachenal.webservice.bench.dao.StatisticsDAO;
import com.github.vlachenal.webservice.bench.dto.TestSuiteDTO;
import com.github.vlachenal.webservice.bench.errors.InvalidParametersException;


/**
 * Statistics business implementation.<br>
 * Performs checks and call DAOs.
 *
 * @author Vincent Lachenal
 */
@Component
public class StatisticsBusiness {

  // Attributes +
  /** Server CPU */
  @Value("${cpu}")
  private String cpu;

  /** Server memory */
  @Value("${memory}")
  private String memory;

  /** Statistics DAO */
  private final StatisticsDAO dao;

  /** Statistics cache */
  private final StatisticsCache cache;
  // Attributes -


  // Constructors +
  /**
   * {@link StatisticsBusiness} constructors
   *
   * @param dao the statistics DAO to use
   */
  public StatisticsBusiness(final StatisticsDAO dao, final StatisticsCache cache) {
    this.dao = dao;
    this.cache = cache;
  }
  // Constructors -


  // Methods +
  /**
   * Consolidate test suite
   *
   * @param suite the test suite to consolidate
   *
   * @throws InvalidParametersException missing or invalid parameters
   */
  public void consolidate(final TestSuiteDTO suite) throws InvalidParametersException {
    if(suite == null) {
      throw new InvalidParametersException("Test suite is null");
    }
    if(suite.getClientCpu() == null || suite.getClientMemory() == null || suite.getClientJvmVersion() == null
        || suite.getClientJvmVendor() == null || suite.getClientOsName() == null || suite.getClientOsVersion() == null) {
      throw new InvalidParametersException("Invalid test suite information");
    }
    if(suite.getCalls() == null || suite.getCalls().isEmpty()) {
      throw new InvalidParametersException("No calls to consolidate");
    }

    suite.getCalls().stream().forEach(call -> cache.mergeCall(call));

    // Gather system informations +
    suite.setServerJvmVersion(System.getProperty("java.version"));
    suite.setServerJvmVendor(System.getProperty("java.vendor"));
    suite.setServerOsName(System.getProperty("os.name"));
    suite.setServerOsVersion(System.getProperty("os.version"));
    suite.setServerCpu(cpu);
    suite.setServerMemory(memory);
    // Gather system informations -

    dao.save(suite);
  }
  // Methods -

}
