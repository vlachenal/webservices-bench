package com.github.vlachenal.webservice.bench.business;

import com.github.vlachenal.webservice.bench.errors.InvalidParametersException;

/**
 * Abstract business class
 *
 * @author Vincent Lachenal
 */
public abstract class AbstractBusiness {

  /**
   * Check parameters
   *
   * @param errorMsg the error message
   * @param params the parameters
   *
   * @throws InvalidParametersException missing or invalid parameters
   */
  protected void checkParameters(final String errorMsg, final Object... params) throws InvalidParametersException {
    for(final Object param : params) {
      if(param == null) {
        throw new InvalidParametersException(errorMsg + ": " + params);
      }
    }
  }

}
