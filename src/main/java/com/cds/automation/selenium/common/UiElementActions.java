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

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * The class will provide methods for performing different actions on webpage UI elements.
 * 
 * @author  Darshan B S
 * @version 1.0
*/
@Log4j2
public class UiElementActions extends BaseClassHelper {

  /** The method provide functionality to scroll for the element visibility. **/
  public static void scrollByVisibilityOfElement(final WebDriver driver,final WebElement ele) {
    
    final JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView();", ele);
  } // End of scrollByVisibilityOfElement method.

  /** The method provide functionality to select values from dropdown. **/
  public static boolean selectBySendkeys(final String value,final WebElement ele) {
    
    try {
      ele.sendKeys(value);
      return true;
    } catch (Exception e) {
      log.error("Unable to select value from dropdown.");
      return false;
    } 
  } // End of selectBySendkeys method.

  /**
   * select value from DropDown by using selectByIndex.
   * 
   * @param: locator     : Action to be performed on element (Get it from Object repository)
   * @param: index       : Index of value wish to select from dropdown list.
   * @param: locatorName : Meaningful name to the element (Ex:Year Dropdown, items Listbox etc..)
   * 
  */
  public static boolean selectByIndex(final WebElement element,final int index) {
    
    try {
      final Select s = new Select(element);
      s.selectByIndex(index);
      return true;
    } catch (Exception e) {
      return false;
    }
  } // End of selectByIndex method.

  /**
   * select value from DD by using value.
   * 
   * @param: locator     : Action to be performed on element (Get it from Object repository)
   * @param: value       : Value wish to select from dropdown list.
   * @param: locatorName : Meaningful name to the element (Ex:Year Dropdown, items Listbox etc..)
  */
  public static boolean selectByValue(final WebElement element,final String value) {
    
    try {
      final Select s = new Select(element);
      s.selectByValue(value);
      return true;
    } catch (Exception e) {

      return false;
    }
  } // End of selectByValue method.

  /**
   * select value from DropDown by using selectByVisibleText.
   * 
   * @param: locator     : Action to be performed on element (Get it from Object repository)
   * @param: visibletext : VisibleText wish to select from dropdown list.
   * @param: locatorName : Meaningful name to the element (Ex:Year Dropdown, items Listbox etc..)
  */
  public static boolean selectByVisibleText(final String visibletext,final WebElement ele) {
    try {
      final Select s = new Select(ele);
      s.selectByVisibleText(visibletext);
      return true;
    } catch (Exception e) {
      return false;
    }
  } // End of selectByVisibleText method.

  /** The method provide functionality to select tab elements. **/
  public static boolean selectTableElement(
                        final String visibletext,final WebDriver driver,final String path) {
    
    try {
      
      final WebElement tableContent = driver.findElement(By.xpath(path));
      final List<WebElement> tableRows = tableContent.findElements(By.tagName("tr"));
      final int rowsCount = tableRows.size();
      // Loop will execute till the last row of table.
      for (int row = 0; row < rowsCount; row++) {
        
        // To locate columns(cells) of that specific row.
        final List<WebElement> rowColumns = tableRows.get(row).findElements(By.tagName("td"));
        final int columnCount = rowColumns.size();

        // Loop will execute till the last cell of that specific row.
        for (int column = 0; column < columnCount; column++) {
          // To retrieve text from that specific cell.
          final WebElement option = rowColumns.get(column);
          if (option.getText().equalsIgnoreCase(visibletext)) {
            // Action.moveToElement(driver, option)
            option.click();
            log.info("Item matched");
            return true;
          } else {
            log.info("Item did not match");
          }
        }
      } // End of looping through table rows.
      return true;
    } catch (Exception e) {
      return false;
    } 
  } // End of selectTableElement method.

  /** The method provide functionality to hover effect of mouse. **/
  public static boolean mouseHoverByJavaScript(final WebElement ele) {
    
    try {
      final WebElement mo = ele;
      final String javaScript = "var evObj = document.createEvent('MouseEvents');"
          + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false,"
          + " false, false, false, 0, null);"
          + "arguments[0].dispatchEvent(evObj);";
      final JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript(javaScript, mo);
      return true;
    } catch (Exception e) {

      return false;
    } 
  } // End of mouseHoverByJavaScript method.

  /** The method provide functionality to click action. **/
  public static boolean jsClick(final WebDriver driver,final WebElement ele) {
   
    boolean flag = false;
    try {
      final JavascriptExecutor executor = (JavascriptExecutor) driver;
      executor.executeScript("arguments[0].click();", ele);
      flag = true;
    } catch (Exception e) {
      log.error("Error occurred while performing JS click. {}",e.getMessage());
      throw e;
    }
    return flag;
  } // End of jsClick method.
} // End of UiElementActions.