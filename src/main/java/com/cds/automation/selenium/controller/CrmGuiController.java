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

import com.cds.automation.selenium.common.BaseClassHelper;
import com.google.gson.Gson;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The REST controller expose HTTP method to the frontend for executing test suites for selenium 
 * GUI services and return back test results to the frontend which display test results in graphs.
 *
 * @author  Venkata Nagaraju.
 * @version 1.0
*/
@Log4j2
@RestController
@CrossOrigin(origins = "*")
public class CrmGuiController extends BaseClassHelper {
  
  @Autowired
  private Gson gson;
  
  @Autowired
  private CrmGuiProcessor processor;
  
  /** The HTTP method will expose CRM GUI test suite execution resource. **/
  @PostMapping("/automation/gui/crm/v1")
  public String genricCrmTestSuiteMethod(@RequestBody final String payload) throws IOException {

    log.info(payload);
    log.info("CRM GUI automation test suite execution started.");
    return gson.toJson(processor.executeCrmGuiTestSuite(payload));
    
  } // End of genricCrmTestSuiteMethod method.
} // End of CrmGuiController.