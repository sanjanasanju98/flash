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

package com.cds.automation.selenium.customeronboard;

import com.cds.automation.selenium.common.TestSuiteResultCreator;
import com.cds.automation.selenium.constanats.UiConstantsUtil;
import com.cds.automation.selenium.interfaces.CrmGuiFactory;
import com.cds.automation.selenium.pojos.TestCases;
import com.cds.automation.selenium.pojos.TestDataResults;
import com.cds.automation.selenium.pojos.TestResults;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The component will follow the contract of CrmGuiFactory and process balance transfer scenario
 * with all the given data set,capture the evidences in the document and return back test results.
 * 
 * @author  Darshan B S.
 * @version 1.0
*/
@Component
public class PrepaidVolteOnBoard implements CrmGuiFactory {
  
  @Autowired
  private TestSuiteResultCreator testResultCreator;
  
  @Autowired
  private Gson gson;
  
  @Autowired
  private VolteOnboardProcessor volteOnboard;
  
  @Override
  public TestResults executeRequest(
              final XWPFDocument doc,final String operation,final String payload) {
    
    // Converting the part of payload into TestCases POJO.
    final TestCases request = gson.fromJson(payload, TestCases.class);
    
    final List<TestDataResults> dataResult = new ArrayList<>();
    final StringBuilder failure = new StringBuilder();
    final List<Map<String, String>> dataSet = request.getTestData();
    
    // Looping through all given data set of balance transfer test case.
    for (final Map<String, String> data : dataSet) {
      try {  
       
        volteOnboard.onboradPrepaidVolteCustomer(doc, data);
        dataResult.add(TestDataResults.builder().dataSet(data)
             .message("Prepaid volte onboard completed successfully.").result("success").build());
      } catch (Exception e) {
        
        dataResult.add(TestDataResults.builder().dataSet(data)
                              .error(e.getMessage()).result(UiConstantsUtil.FAILURE).build());
        failure.append(UiConstantsUtil.FAILURE);
      }
    }
    final TestResults results = 
                      testResultCreator.createTestDataResults(dataResult, failure, request);
    failure.setLength(0);
    return results;
  } // End of executeRequest method overriding.
} // End of PrepaidVolteOnBoard.