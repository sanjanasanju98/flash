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

package com.cds.automation.selenium.interfaces;

import com.cds.automation.selenium.pojos.TestResults;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * The factory class will define contract for executing CRM GUI test suites.
 * 
 * @author  Venkata Nagaraju.
 * @version 1.0.
*/
public interface CrmGuiFactory {
  
  /** Method to execute CRM GUI test suites. **/
  TestResults executeRequest(XWPFDocument doc,String operation,String payload);
} // End of CrmGuiFactory.