/**
 * Copyright (C) 2019 Covalensedigital 
 * <br />
 * Licensed under the CDS,Version 1.0,you may not use this file except in compliance with the 
 * License. You may obtain a copy of the License at 
 * <br />
 * http://www.covalensedigital.com/
 * <br />
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS,WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,either express or
 * implied.See the License for the specific language governing permissions and limitations.
*/

package com.cds.automation.selenium.utils;

import org.apache.log4j.Logger;

/**
 * The util class provide log management for selenium test cases.
 * 
 * @author  Darshan B S.
 * @version 1.0
*/
public final class LogUtil {
  
  public static final Logger LOGGER = Logger.getLogger(LogUtil.class.getName());
  
  private LogUtil() {
  } // End of private constructor.

  /** Log for starting of the test case. **/
  public static void startTestCase(final String testCaseName) {
    
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("--" + testCaseName + " Test start--");
    }
  }

  /** Log for ending of the test case. **/
  public static void endTestCase(final String testCaseName) {
    
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("-" + testCaseName + " test end --");
    }
  }

  public static void info(final String message) {
    LOGGER.info(message);
  }

  public static void warn(final String message) {
    LOGGER.warn(message);
  }

  public static void error(final String message) {
    LOGGER.error(message);
  }

  public static void fatal(final String message) {
    LOGGER.fatal(message);
  }

  public static void debug(final String message) {
    LOGGER.debug(message);
  }
} // End of LogUtil.