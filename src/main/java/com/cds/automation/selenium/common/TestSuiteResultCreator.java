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

package com.cds.automation.selenium.common;

import com.cds.automation.selenium.aspect.Loggable;
import com.cds.automation.selenium.constanats.UiConstantsUtil;
import com.cds.automation.selenium.pojos.GuiTestSuite;
import com.cds.automation.selenium.pojos.TestCases;
import com.cds.automation.selenium.pojos.TestDataResults;
import com.cds.automation.selenium.pojos.TestResults;
import com.cds.automation.selenium.pojos.TestSuiteResults;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * The component will provide methods to create test success and failure results.
 * 
 * @author  Venkata Nagaraju.
 * @version 1.0
*/
@Component
public class TestSuiteResultCreator {

  /** The method will create success result of test suite. **/
  @Loggable
  public TestSuiteResults createSuccessResults(
                           final GuiTestSuite testSuite,final List<TestResults> testResults,
                           final int totalTestCases,final int success,final int failed) {
    
    return TestSuiteResults.builder().testResults(testResults)
                           .testSuiteName(testSuite.getTestSuiteName())
                           .testSuiteDesc(testSuite.getTestSuiteDesc())
                           .totalTestCases(totalTestCases).totalSucceeded(success)
                           .totalFailed(failed).build();
  } // End of createDocumentHeader method.
  
  /** The method will create failure result of test suite. **/
  @Loggable
  public TestSuiteResults createFailureResults(
                                final GuiTestSuite testSuite,final int total,
                                final int success,final int failed,final String error) {
    
    return TestSuiteResults.builder().status(UiConstantsUtil.FAILURE).error(error)
                           .testSuiteName(testSuite.getTestSuiteName())
                           .totalTestCases(total).totalSucceeded(success).totalFailed(failed)
                           .totalBlocked(total - success - failed)
                           .testSuiteDesc(testSuite.getTestSuiteDesc()).build();
  } // End of createFailureResults method.
  
  /** The method will create success / failure result of test data. **/
  @Loggable
  public TestResults createTestDataResults(
                                   final List<TestDataResults> dataResult,
                                   final StringBuilder failure,final TestCases request) {
    
    // Creating test results with the given data set.
    final TestResults results = 
          TestResults.builder().dataResult(dataResult).testCaseName(request.getTestCaseName())
                               .testCaseDesc(request.getTestCaseDesc()).build();
    if (failure.length() > 0) {
      results.setStatus(UiConstantsUtil.FAILURE);
    } else {
      results.setStatus("success");
    } // End of specifying the test case status.
    return results;
  } // End of createFailureResults method.
} // End of TestSuiteResultCreator.