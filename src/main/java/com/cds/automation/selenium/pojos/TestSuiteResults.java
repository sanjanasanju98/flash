/**
 * Copyright (C) 2020 Covalensedigital 
 * <br />
 * Licensed under the CDS,Version 1.0,you may not use this file except in compliance with
 * the license. You may obtain a copy of the License at 
 * <br />
 * http://www.covalensedigital.com/
 * <br />
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS,WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,either express or
 * implied.See the License for the specific language governing permissions and limitations.
*/

package com.cds.automation.selenium.pojos;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The POJO represents response of GuiTestSuite request of frontend.
 * 
 * @author  Venkata Nagaraju.
 * @version 1.0.
*/
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TestSuiteResults implements Serializable {
  
  private static final long serialVersionUID = -1349282417050505760L;

  private String testSuiteName; 
  
  private String testSuiteDesc; 
  
  private String status;
  
  private String error;
  
  private int totalTestCases;
  
  private int totalSucceeded;
  
  private int totalFailed;
  
  private int totalBlocked;
  
  private List<TestResults> testResults; 
} // End of TestSuiteResults.