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

import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The class will provide methods for performing different actions on webpage window elements..
 * 
 * @author  Darshan B S
 * @version 1.0
*/
@Log4j2
public class UiWindowActions extends BaseClassHelper {

  /** The method provide functionality to mouse over event. **/
  public static void mouseOverElement(final WebDriver driver,final WebElement element) {
    try {
      new Actions(driver).moveToElement(element).build().perform();
    } catch (Exception e) {
      log.error("Error occurred for mouse over event {}",e.getMessage());
    } 
  } // End of mouseOverElement method.

  /** The method provide functionality to mouse over event. **/
  public static boolean moveToElement(final WebDriver driver,final WebElement ele) {
    boolean flag = false;
    try {
      
      final JavascriptExecutor executor = (JavascriptExecutor) driver;
      executor.executeScript("arguments[0].scrollIntoView(true);", ele);
      final Actions actions = new Actions(driver);
      actions.moveToElement(ele).build().perform();
      flag = true;
      
    } catch (Exception e) {
      log.error("Error occurred for moving cursur to elements {}",e.getMessage());
    }
    return flag;
  } // End of moveToElement method.

  /** The method provide functionality to mouse over. **/
  public static boolean mouseover(final WebDriver driver,final WebElement ele) {
    try {
      
      new Actions(driver).moveToElement(ele).build().perform();
      return true;
    } catch (Exception e) {
      return false;
    }
  } // End of mouseover method.

  /** The method provide functionality to dragging. **/
  public static boolean draggable(
                        final WebDriver driver,final WebElement source,final int x,final int y) {
    try {
      new Actions(driver).dragAndDropBy(source, x, y).build().perform();
      Thread.sleep(5000);
      return true;

    } catch (Exception e) {
      return false;
    } 
  } // End of draggable method.

  /** The method provide functionality to drag and drop. **/
  public static boolean draganddrop(
                        final WebDriver driver,final WebElement source,final WebElement target) {
    try {
      new Actions(driver).dragAndDrop(source, target).perform();
      return true;
    } catch (Exception e) {

      return false;
    } 
  } // End of draganddrop method.

  /** The method provide functionality to slider. **/
  public static boolean slider(
                        final WebDriver driver,final WebElement ele,final int x,final int y) {
    try {
      new Actions(driver).dragAndDropBy(ele, x, y).build().perform();// 150,0
      Thread.sleep(5000);
      return true;
      
    } catch (Exception e) {
      return false;
    }
  } // End of slider method.

  /** The method provide functionality to right click event. **/
  public static boolean rightclick(final WebDriver driver,final WebElement ele) {
    try {
      
      final Actions clicker = new Actions(driver);
      clicker.contextClick(ele).perform();
      return true;
    } catch (Exception e) {

      return false;
    }
  } // End of rightclick method.

  /** The method provide functionality to switching windows. **/
  public static Boolean switchWindowByTitle(
                        final WebDriver driver,final String windowTitle,final int count) {
    
    boolean flag = false;
    try {
      final Set<String> windowList = driver.getWindowHandles();

      final String[] array = windowList.toArray(new String[0]);

      driver.switchTo().window(array[count - 1]);

      if (driver.getTitle().contains(windowTitle)) {
        flag = true;
      } else {
        flag = false;
      }
      return flag;
    } catch (Exception e) {
      return false;
    }
  } // End of switchWindowByTitle method.

  /** The method provide functionality to switching to new window. **/
  public static Boolean switchToNewWindow(final WebDriver driver) {
    boolean flag = false;
    try {

      final Set<String> s = driver.getWindowHandles();
      final Object[] popup = s.toArray();
      driver.switchTo().window(popup[1].toString());
      flag = true;
      return flag;
    } catch (Exception e) {
      return flag;
    }
  } // End of switchToNewWindow method.
  
  /** The method provide functionality to switch frame windows. **/
  public static boolean switchToFrameByIndex(final WebDriver driver,final int index) {
    
    try {
      new WebDriverWait(driver, 15)
          .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
      driver.switchTo().frame(index);
      return true;
    } catch (Exception e) {

      return false;
    } 
  } // End of switchToFrameByIndex method.
} // End of UiElementActions.