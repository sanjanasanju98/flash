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

package com.cds.automation.selenium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * The gui-selenium-automation service provides backend functionality for GUI test automation.The
 * service will expose REST endpoints to the frontend application, for saving information regarding
 * selenium and GUI components and test data in the database which will be tested as part of 
 * information. The service provide both sequential and parallel execution of RESTful API and 
 * their test / regression suites.
 * 
 * @author  Venkata Nagaraju.
 * @version 1.0.
*/
@SpringBootApplication
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class})
@ComponentScan("com.cds.automation.selenium")
public class GuiAutomationService {
  
  public static void main(final String[] args) {

    SpringApplication.run(GuiAutomationService.class, args);
  } // End of main class for gui-automation.
} // End of GuiAutomationService, which provide test automation for GUI with Selenium.