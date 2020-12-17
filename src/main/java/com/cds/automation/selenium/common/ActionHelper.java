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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The class will provide methods for performing different actions on webpage action elements.
 * 
 * @author  Darshan B S.
 * @version 1.0
*/
@Log4j2
public final class ActionHelper {
  
  private static final String USER_DIR = "user.dir";
  
  private ActionHelper() {
  } // End of private Constructor.
  
  /** The method provide functionality to launch the URL. **/
  public static boolean launchUrl(final WebDriver driver,final String url) {
    
    try {
      driver.navigate().to(url);
      return true;
    } catch (Exception e) {
      return false;
    }
  } // End of launchUrl method.
 
  /** The method provide functionality to verify alert. **/
  public static boolean isAlertPresent(final WebDriver driver) {
    
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  } // End of isAlertPresent method.

  public static String getTitle(final WebDriver driver) {

    return driver.getTitle();
  } // End of getTitle method.

  public static String getCurrentUrl(final WebDriver driver) {
    return driver.getCurrentUrl();
  }

  public static String getText(final WebDriver driver,final String xpath) {

    return driver.findElement(By.xpath(xpath)).getText();
  }

  /** The method provide functionality to click event. **/
  public static boolean click1(final WebElement locator) {
    try {
      locator.click();
      return true;
    } catch (Exception e) {
      return false;
    }
  } // End of click1 method.

  /** The method provide functionality to fluent wait event. **/
  public static void fluentWait(final WebDriver driver,final WebElement element,final int timeOut) {
    Wait<WebDriver> wait = null;
    try {
      wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(Duration.ofSeconds(timeOut))
          .pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class)
          .ignoring(Exception.class);
      wait.until(ExpectedConditions.visibilityOf(element));
      element.click();
      
    } catch (Exception e) {
      log.error("Error occurred during fluewnt wait {}", e.getMessage());
    }
  } // End of fluentWait method.

  
  public static void implicitWait(final WebDriver driver,final int timeOut) {
    driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
  }

  /** The method provide functionality to explicit wait. **/
  public static void explicitWait(
                     final WebDriver driver,final WebElement element,final int timeOut) {
    
    final WebDriverWait wait = new WebDriverWait(driver, timeOut);
    wait.until(ExpectedConditions.visibilityOf(element));
  } // End of explicitWait method.

  public static void pageLoadTimeOut(final WebDriver driver,final int timeOut) {
    driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
  }

  /** The method provide functionality to capture screen shot. **/
  public static String screenShot(final WebDriver driver,final String filename) {
    
    final TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
    final File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
    final String destination = "E:/SelfCare/ScreenShots/" + filename + ".png";

    try {
      FileUtils.copyFile(source, new File(destination));
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return destination;
  } // End of screenShot method.

  /** The method provide functionality to capture screen shot. **/
  public static void writeProperty(final String key,final String value) throws IOException {
    
    try (InputStream in = Files.newInputStream(
                          Paths.get(System.getProperty(USER_DIR) 
                          + "//Configuration//AccountDetails.properties"))) {
      final Properties props = new Properties();
      props.load(in);

      try (OutputStream out = Files.newOutputStream(
          Paths.get(System.getProperty(USER_DIR) 
              + "//Configuration//AccountDetails.properties"))) {
        props.setProperty(key, value);
        props.store(out, null);
      }
    }
  } // End of writeProperty method.

  /** The method provide functionality to writing inventory to properties. **/
  public static void writeInventoryProperty(
                                   final String key,final String value) throws IOException {

    try (InputStream in = Files.newInputStream(
                          Paths.get(System.getProperty(USER_DIR) 
                             + "//Configuration//Inventory.properties"))) {
      final Properties invProps = new Properties();
      invProps.load(in);

      try (OutputStream out = Files.newOutputStream(
                             Paths.get(System.getProperty(USER_DIR) 
                                 + "//Configuration//Inventory.properties"))) {
        invProps.put(key, value);
        invProps.store(out, null);
      } // end of output stream.
    } // end of input stream.
  } // End of writeInventoryProperty method.

  public static String getCurrentTime() {
    return new SimpleDateFormat("yyyy-MM-dd-hhmmss",Locale.ENGLISH).format(new Date());
  } // End of getCurrentTime method.
} // End of WebPageActionsUtil.