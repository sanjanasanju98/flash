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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * The util class provide methods for capturing webpage screens for selenium test cases.
 * 
 * @author  Darshan B S.
 * @version 1.0
*/
public final class ExtentManagerUtil {

  private ExtentManagerUtil() {
  } // End of private constructor.
 
  /** The method used to configure extent reports for selenium. **/
  public static void setExtent() {
    
    final SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss",Locale.ENGLISH);
    final String str = sdf.format(new Date());
   
    final ExtentHtmlReporter htmlReporter = 
          new ExtentHtmlReporter(System.getProperty("user.dir")
                       + "/test-output/ExtentReport/" + "CRMReport_" + str + ".html");
    htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

    final ExtentReports extent = new ExtentReports();
    extent.attachReporter(htmlReporter);

    // These should move to property file.
    extent.setSystemInfo("HostName", "MyHost");
    extent.setSystemInfo("ProjectName", "SelfCareCRM");
    extent.setSystemInfo("Tester", "Darshan");
    extent.setSystemInfo("OS", "Win10");
    extent.setSystemInfo("Browser", "Chrome");
    extent.setSystemInfo("org.freemarker.loggerLibrary", "none");
  } // End of setExtent method.
} // End of ExtentManagerUtil.