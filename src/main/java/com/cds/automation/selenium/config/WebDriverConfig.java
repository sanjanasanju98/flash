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

package com.cds.automation.selenium.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Configuration;

/**
 * The configuration class provide bean for selenium webdriver.
 * 
 * @author  Venkata Nagaraju.
 * @version 1.0.
*/
@Configuration
public class WebDriverConfig {
  
  /** Creating web driver for google chrome. **/
  //@Bean
  public WebDriver getWebDriver() {
    
    final ChromeOptions options = new ChromeOptions();
    final Map<String, Object> prefs = new HashMap<>();
    prefs.put("credentials_enable_service", false);
    prefs.put("profile.password_manager_enabled", false);

    options.setExperimentalOption("useAutomationExtension", false);
    options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
    options.setExperimentalOption("prefs", prefs);
    options.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, PageLoadStrategy.NONE);
    
    final DesiredCapabilities caps = DesiredCapabilities.chrome();
    caps.setCapability(ChromeOptions.CAPABILITY, options);
    WebDriverManager.chromedriver().setup();
    
    System.setProperty("webdriver.chrome.silentOutput", "true");
    return new ChromeDriver(options);
  } // Bean for selenium web driver.
} // End of WebBrowserUtils.