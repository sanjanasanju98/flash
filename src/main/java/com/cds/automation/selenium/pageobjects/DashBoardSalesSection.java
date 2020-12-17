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

package com.cds.automation.selenium.pageobjects;

import com.cds.automation.selenium.common.ActionHelper;
import com.cds.automation.selenium.common.BaseClassHelper;
import com.cds.automation.selenium.common.UiElementActions;
import com.cds.automation.selenium.common.UiWindowActions;
import com.cds.automation.selenium.exceptions.CrmUiException;
import com.cds.automation.selenium.utils.LogUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

/**
 * The class will provide various methods, which perform basic operations of CRM dashboard sales
 * section. These methods will be used by different test cases for performing operations on CRM
 * dashboard sales section UI elements.
 * 
 * @author  Darshan B S
 * @version 1.0
*/
@Log4j2
@Component
public class DashBoardSalesSection extends BaseClassHelper {
  
  @FindBy(xpath = "//div[@id='appnavigator']//div")
  private WebElement appNavigator;

  @FindBy(xpath = "//span[contains(text(),'SALES')]")
  private WebElement sales;

  @FindBy(xpath = "//span[@class='module-name textOverflowEllipsis'][contains(text(),'Contacts')]")
  private WebElement salesContacts;

  @FindBy(xpath = "//span[@class='module-name textOverflowEllipsis'][contains(text(),'Accounts')]")
  private WebElement salesAccounts;

  @FindBy(xpath = 
          "//span[@class='module-name textOverflowEllipsis'][contains(text(),' Dealers')]")
  private WebElement dealers;

  @FindBy(xpath = "//span[@class='module-name textOverflowEllipsis'][contains(text(),'Vendors')]")
  private WebElement salesVendors;

  public void initPageFactory() {
    PageFactory.initElements(getDriver(), this);
  } // End of constructor.

  /** The method will select the "sales" UI element of dashboard with mouse hover. **/
  public void hoverOnSales() {
    try {
      
      Thread.sleep(3000);
      new WebDriverWait(getDriver(), 10).until(
                        ExpectedConditions.presenceOfElementLocated(
                                           By.xpath("//span[contains(text(),'SALES')]")));
      UiWindowActions.mouseover(getDriver(), sales);
      ActionHelper.fluentWait(getDriver(), appNavigator, 15);

      log.info("Hovered on sales.");
      
    } catch (Exception e) {
      
      final StringBuilder error = 
            new StringBuilder("Error in dashboard sales section.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of hoverOnSales method.

  /** The method will select the "salesContacts" UI element of dashboard with mouse hover. **/
  public void hoverOnSalesContacts() {
    try {
      
      Thread.sleep(3000);
      UiWindowActions.mouseover(getDriver(), salesContacts);
      ActionHelper.fluentWait(getDriver(), appNavigator, 4);
      UiElementActions.jsClick(getDriver(), salesContacts);
      log.info("hovered on Sales -> Contacts");
      
    } catch (Exception e) {
      log.error("Error in sales contacts section of dashboard. {}",e.getMessage());
    }
  } // End of hoverOnSalesContacts method.

  /** The method will select the "dealers" UI element of dashboard with mouse hover. **/
  public void hoverOnSalesDealers() {
    try {
      
      Thread.sleep(3000);
      UiWindowActions.mouseover(getDriver(), dealers);
      ActionHelper.fluentWait(getDriver(), appNavigator, 4);
      UiElementActions.jsClick(getDriver(), dealers);
      log.info("hovered on Sales -> Dealers");
      
    } catch (Exception e) {
      log.error("Error in dealer section of dashboard. {}",e.getMessage());
    }
  } // End of hoverOnSalesDealers method.

  /** The method will select the "salesAccounts" UI element of dashboard with mouse hover. **/
  public void hoverOnSalesAccounts() {
    try {
      
      Thread.sleep(3000);
      final String dateName = 
            new SimpleDateFormat("yyyyMMddhhmmss",Locale.ENGLISH).format(new Date());
      final String pageDate = "DashBoardPage" + "_" + dateName;
      UiWindowActions.mouseover(getDriver(), salesAccounts);
      ActionHelper.fluentWait(getDriver(), appNavigator, 4);
      ActionHelper.screenShot(getDriver(), pageDate);
      UiElementActions.jsClick(getDriver(), salesAccounts);
      LogUtil.info("hovered on and clicked Accounts.");

    } catch (Exception e) {
      
      final StringBuilder error = 
            new StringBuilder("Error in dashboard accounts section.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of hoverOnSalesAccounts method.

  /** The method will select the "salesVendors" UI element of dashboard with mouse hover. **/
  public void hoverOnSalesVendors() {
    try {
      
      Thread.sleep(3000);
      UiWindowActions.mouseover(getDriver(), salesVendors);
      ActionHelper.fluentWait(getDriver(), appNavigator, 4);
      UiElementActions.jsClick(getDriver(), salesVendors);
      log.info("hovered on and clicked Vendors.");

    } catch (Exception e) {
      log.error("Error in sales accounts section of dashboard. {}",e.getMessage());
    }
  } // End of hoverOnSalesVendors method.
} // End of DashBoardSalesSection.