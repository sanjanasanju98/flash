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

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * The class will provide methods for performing different actions on webpage UI elements.
 * 
 * @author  Darshan B S.
 * @version 1.0
*/
@Log4j2
public final class WebPageActionsUtil {
  
  private WebPageActionsUtil() {
  } // End of private Constructor.
  

  /** The method provide functionality of popup window. **/
  public static void handlePopUpWindow(final WebDriver driver) {

    final String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
    String subWindowHandler = null;

    final Set<String> handles = driver.getWindowHandles(); // get all window handles
    final Iterator<String> iterator = handles.iterator();
   
    while (iterator.hasNext()) {
      subWindowHandler = iterator.next();
      log.info("Popup window handles are - {}", subWindowHandler);
    }
    driver.switchTo().window(subWindowHandler); // switch to popup window

    // Now you are in the popup window, perform necessary actions here
    driver.switchTo().window(parentWindowHandler);
  } // End of handlePopUpWindow method.

  /** The method provide functionality to switching to old window. **/
  public static Boolean switchToOldWindow(final WebDriver driver) {
    boolean flag = false;
    try {

      final Set<String> s = driver.getWindowHandles();
      final Object[] popup = s.toArray();
      driver.switchTo().window(popup[0].toString());
      flag = true;
      return flag;
    } catch (Exception e) {
      return flag;
    }
  } // End of switchToOldWindow method.

  /** The method provide functionality to get column count. **/
  public static int getColumncount(final WebElement row) {
    
    final List<WebElement> columns = row.findElements(By.tagName("td"));
    final int a = columns.size();
    log.info(columns.size());
    for (final WebElement column : columns) {
      log.info(column.getText());
      log.info("|");
    }
    return a;
  } // End of getColumncount method.

  /** The method provide functionality to get row count. **/
  public static int getRowCount(final WebElement table) {
    
    final List<WebElement> rows = table.findElements(By.tagName("tr"));
    return rows.size() - 1;
  } // End of getRowCount method.

  /**
   * Method to verify alert present or not.
   * 
   * @return: Boolean (True: If alert preset, False: If no alert)
  */
  public static boolean alert(final WebDriver driver) {
    boolean presentFlag = false;
    Alert alert = null;

    try {
      // Check the presence of alert
      alert = driver.switchTo().alert();
      // if present consume the alert
      alert.accept();
      presentFlag = true;
      
    } catch (NoAlertPresentException e) {
      log.error("Error occurred while performing alert {}",e.getMessage());
    }
    return presentFlag;
  } // End of alert method.
  
  /** The method will provide functionality of UI element click. */
  public static void click(final WebDriver driver,final WebElement ele) {

    final Actions act = new Actions(driver);
    act.moveToElement(ele).click().build().perform();
  } // End of click method.

  /** The method will provide functionality for finding web elements. */
  public static boolean findElement(final WebElement ele) {
    
    boolean flag = false;
    try {
      ele.isDisplayed();
      flag = true;
      log.info("Element located.");
      
    } catch (Exception e) {
      log.error("Unable to find the element. {}",e.getMessage());
    }
    return flag;
  } // End of findElement method.

  /** The method will provide functionality for finding web elements. */
  public static boolean isDisplayed(final WebElement ele) {
    
    boolean flag = false;
    flag = findElement(ele);
    if (flag) {
      flag = ele.isDisplayed();
    } else {
      log.info("Element Not displayed ");
    }
    return flag;
  } // End of isDisplayed method.

  /** The method will provide functionality for element selection. */
  public static boolean isSelected(final WebElement ele) {
    
    boolean flag = false;
    flag = findElement(ele);
    if (flag) {
      flag = ele.isSelected();
    } else {
      log.info("Element Not selected.");
    }
    return flag;
  } // End of isSelected method.

  /** The method will provide functionality for verifying element enabled or not. */
  public static boolean isEnabled(final WebElement ele) {
    
    boolean flag = false;
    flag = findElement(ele);
    if (flag) {
      flag = ele.isEnabled();
    } else {
      log.info("Element not Enabled.");
    }
    return flag;
  } // End of isEnabled method.

  /** The method will provide functionality for verifying element enabled or not. */
  public static boolean type(final WebElement ele,final String text) {
    boolean flag = false;
    try {
      ele.isDisplayed();
      ele.clear();
      ele.sendKeys(text);
      flag = true;
    } catch (Exception e) {
      log.error("Location Not found");
      flag = false;
    } 
    return flag;
  } // End of method type.

  /** The method will provide functionality for entering key into an UI element. */
  public static boolean enterKey(final WebElement ele,final Keys key) {
    
    boolean flag = false;
    try {
      ele.isDisplayed();
      ele.sendKeys(key);
      flag = true;
    } catch (Exception e) {
      log.error("Location Not found");
      flag = false;
    } 
    return flag;
  } // End of enterKey method.
  
  /**
   * This method switch the to frame using frame ID.
   * 
   * @param idValue : Frame ID wish to switch
   * 
  */
  public static boolean switchToFrameById(final WebDriver driver,final String idValue) {

    try {
      driver.switchTo().frame(idValue);
      return true;
    } catch (Exception e) {
      return false;
    } 
  } // End of switchToFrameById method.

  /**
   * This method switch the to frame using frame Name.
   * 
   * @param nameValue : Frame Name wish to switch
  */
  public static boolean switchToFrameByName(final WebDriver driver,final String nameValue) {
    try {
      driver.switchTo().frame(nameValue);
      return true;
    } catch (Exception e) {

      return false;
    } 
  } // End of switchToFrameByName method.

  /** The method provide functionality to switch to default frame. **/
  public static boolean switchToDefaultFrame(final WebDriver driver) {
    try {
      driver.switchTo().defaultContent();
      return true;
    } catch (Exception e) {
      return false;
    }
  } // End of switchToDefaultFrame method.
} // End of WebPageActionsUtil.