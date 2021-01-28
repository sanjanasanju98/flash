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

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * The class will provide methods for initiating the browser and selenium web driver.
 * 
 * @author  Darshan B S.
 * @version 1.0
*/
@Log4j2
public class BaseClassHelper {

  // Declare ThreadLocal Driver
  protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
  
  /** Method to provide instance of web driver. **/
  public static WebDriver getDriver() {
    // Get Driver from threadLocalmap
    synchronized (driver) {
      return driver.get();
    }
  } // end of getDriver method.

  /** The method to launch application. **/
  public static void launchApp() throws IOException {

    final Properties prop = new Properties();
    try (InputStream ip =  Files.newInputStream(
                          Paths.get(System.getProperty("user.dir") 
                              + "//configuration//config.properties"))) {
      prop.load(ip);
    } // End of inputstream.

    WebDriverManager.chromedriver().setup();
    final String browserName = prop.getProperty("browser");
    
    if ("Chrome".equalsIgnoreCase(browserName)) {

      final ChromeOptions options = new ChromeOptions();
      final Map<String, Object> prefs = new HashMap<>();
      prefs.put("credentials_enable_service", false);
      prefs.put("profile.password_manager_enabled", false);

      options.setExperimentalOption("prefs", prefs);
      options.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, PageLoadStrategy.NONE);
      options.setExperimentalOption("useAutomationExtension", false);
      options.setExperimentalOption("excludeSwitches",
                                    Collections.singletonList("enable-automation"));
      final DesiredCapabilities caps = DesiredCapabilities.chrome();
      caps.setCapability(ChromeOptions.CAPABILITY, options);
      WebDriverManager.chromedriver().setup();
      System.setProperty("webdriver.chrome.silentOutput", "true");
      driver.set(new ChromeDriver(options));
      
    } else if ("FireFox".equalsIgnoreCase(browserName)) {
      
      WebDriverManager.firefoxdriver().setup();
      final FirefoxProfile profile = new FirefoxProfile();
      profile.setAcceptUntrustedCertificates(true);
      profile.setAssumeUntrustedCertificateIssuer(false);
      profile.setPreference("geo.enabled", false);
      profile.setPreference("geo.provider.use_corelocation", false);
      profile.setPreference("geo.prompt.testing", false);
      profile.setPreference("geo.prompt.testing.allow", false);
      
      //final DesiredCapabilities capabilities = DesiredCapabilities.firefox()
      //capabilities.setCapability(FirefoxDriver.PROFILE, profile)
      
      final FirefoxOptions firefoxOptions = new FirefoxOptions();
      firefoxOptions.setProfile(profile);
      driver.set(new FirefoxDriver(firefoxOptions));
      
    } else if ("IE".equalsIgnoreCase(browserName)) {
      WebDriverManager.iedriver().setup();
      driver.set(new InternetExplorerDriver());
    }
    getDriver().manage().window().maximize();
    ActionHelper.implicitWait(getDriver(), 30);
    ActionHelper.pageLoadTimeOut(getDriver(), 60);
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      
      log.error("Thread interrupted while launched the webpage.");
      Thread.currentThread().interrupt();
    }
    getDriver().get(prop.getProperty("url"));
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      
      log.error("Thread interrupted while launched the webpage.");
      Thread.currentThread().interrupt();
    }
  } // End of launchApp method.
  
  public void unload() {
    driver.remove();
  } // End of unload method.
} // End of BaseClass, which invoke the browser and web driver.