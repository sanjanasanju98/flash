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

package com.cds.automation.selenium.controller;

import com.cds.automation.selenium.aspect.Loggable;
import com.cds.automation.selenium.common.BaseClassHelper;
import com.cds.automation.selenium.common.EvidenceDocument;
import com.cds.automation.selenium.common.TestSuiteResultCreator;
import com.cds.automation.selenium.interfaces.CrmGuiFactory;
import com.cds.automation.selenium.pojos.GuiTestSuite;
import com.cds.automation.selenium.pojos.TestCases;
import com.cds.automation.selenium.pojos.TestResults;
import com.cds.automation.selenium.pojos.TestSuiteResults;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * The processor class will invoke appropriate GUI test suite as per the page object provided in
 * the request and execute the test suite, All GUI test suites should follow the contract defined
 * by CrmGuiFactory.
 *
 * @author  Venkata Nagaraju.
 * @version 1.0
*/
@Log4j2
@Component
public class CrmGuiProcessor extends BaseClassHelper {
  
  @Autowired
  private EvidenceDocument evidence;
  
  @Autowired
  private TestSuiteResultCreator testSuiteResult;
  
  @Autowired
  private Gson gson;
  
  @Autowired
  private ApplicationContext context;
  
  /** The method will execute the CRM GUI test suites and return the suite results. **/
  @Loggable
  public TestSuiteResults executeCrmGuiTestSuite(final String payload) {
    
    // Converting given payload into GuiTestSuite POJO.
    final GuiTestSuite testSuite = gson.fromJson(payload, GuiTestSuite.class);
    try (XWPFDocument doc = new XWPFDocument()) {
      
      return executeTestCaseWithEvidence(doc,testSuite);
      
    } catch (Exception e) {
      
      log.error("Error occurred while creating evidence. {}",e.getMessage());
      return testSuiteResult.createFailureResults(testSuite, e.getMessage());
    } // End of evidence document capture.
  } // End of balanceTransferMethod method.
  
  /**
   * The method will execute all the test cases of the given test suite in the payload,capture
   * the evidences in the document and store test results in POJO and return back to the caller.
   * 
   * @param: doc       - Evidence capture document.
   * @param: testSuite - Test suite provided in the payload.
  */
  private TestSuiteResults executeTestCaseWithEvidence(
                                          final XWPFDocument doc,final GuiTestSuite testSuite) {
    try {
      
      // Adding header to the evidence document.
      evidence.createDocumentHeader(doc);
      
      final List<TestResults> testResults = new ArrayList<>();
      
      // Looping through all test cases of the test suite.
      for (final TestCases tc : testSuite.getTestCases()) {
        
        final CrmGuiFactory factory = (CrmGuiFactory) context.getBean(tc.getPageObject());
        final TestResults results = 
                          factory.executeRequest(doc,tc.getTestCaseName(), gson.toJson(tc));
        testResults.add(results);
      }
      return testSuiteResult.createSuccessResults(testSuite, testResults);
      
    } catch (Exception e) {
    
      log.error(e.getMessage());
      return testSuiteResult.createFailureResults(testSuite, e.getMessage());
      
    } finally {
      // Saving the content both text and images to the evidence document.
      evidence.saveEvidenceDocument(doc, testSuite.getTestSuiteName());
    } // End of finally.
  } // End of executeTestCaseWithEvidence method.
} // End of BalanceTransferController.