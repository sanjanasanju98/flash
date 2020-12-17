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
import com.cds.automation.selenium.common.WebPageActionsUtil;
import com.cds.automation.selenium.exceptions.CrmUiException;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

/**
 * The class will provide various methods, which perform basic operations of CRM dashboard. These
 * methods will be used by different test cases for performing operations on CRM dashboard elements.
 * 
 * @author  Darshan B S
 * @version 1.0
*/
@Log4j2
@Component
public class DashBoardPage extends BaseClassHelper {
  
  @FindBy(xpath = "//div[@id='appnavigator']//div")
  private WebElement appNavigator;

  @FindBy(xpath = "//span[contains(text(),'MARKETING')]")
  private WebElement marketing;

  @FindBy(xpath = 
          "//span[@class='module-name textOverflowEllipsis'][contains(text(),' Inventory')]")
  private WebElement subInventory;

  @FindBy(xpath = 
          " //span[@class='module-name textOverflowEllipsis'][contains(text(),' Stock Transfer')]")
  private WebElement stockTransfer;

  @FindBy(xpath = "//span[contains(text(),'INVENTORY')]")
  private WebElement inventory;

  @FindBy(xpath = 
      "//span[@class='module-name textOverflowEllipsis']"
                                  + "[contains(text(),' Dealer Purchase Order')]")
  private WebElement invDpo;

  public void initPageFactory() {
    PageFactory.initElements(getDriver(), this);
  } // End of constructor.

  /** The method will simulate the app navigation of dashboard page. **/
  public void appNavigationClick() {
    try {
      
      log.info("Dashboard navigation started.");
      new WebDriverWait(getDriver(), 60)
                    .until(ExpectedConditions.elementToBeClickable(appNavigator));
      WebPageActionsUtil.click(getDriver(), appNavigator);
      ActionHelper.fluentWait(getDriver(), appNavigator, 10);
      log.info("Navigation icon clicked");

    } catch (Exception e) {
      
      final StringBuilder error = 
            new StringBuilder("Error in dashboard app navigation.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of appNavigationClick method.

  /** The method will select the "marketing" UI element of dashboard with mouse hover. **/
  public void hoverOnMarketing() {
    try {
      
      Thread.sleep(3000);
      UiWindowActions.mouseover(getDriver(), marketing);
      log.info("Hovered on Marketing.");

    } catch (Exception e) {
      log.error("Error in marketing section of dashboard. {}",e.getMessage());
    }
  } // End of hoverOnMarketing method.

  /** The method will select the "inventory" UI element of dashboard with mouse hover. **/
  public void hoverOnInventory() {
    try {
      
      Thread.sleep(3000);
      new WebDriverWait(getDriver(), 20).until(ExpectedConditions
              .presenceOfElementLocated(By.xpath("//span[contains(text(),'INVENTORY')]")));
      UiWindowActions.mouseover(getDriver(), inventory);
      log.info("HOVER ON INVENTORY");
    
    } catch (Exception e) {
      log.error("Error in inventory section of dashboard. {}",e.getMessage());
    }
  } // End of hoverOnInventory method.

  /** The method will select the "invDpo" UI element of dashboard with mouse hover. **/
  public void hoverOnInventoryDpo() {
    try {
      
      Thread.sleep(3000);
      UiWindowActions.mouseover(getDriver(), invDpo);
      ActionHelper.fluentWait(getDriver(), appNavigator, 4);
      UiElementActions.jsClick(getDriver(), invDpo);
      log.info("INSIDE DEALER PURCHASE ORDER");
      
    } catch (Exception e) {
      log.error("Error in inventory DPO section of dashboard. {}",e.getMessage());
    }
  } // End of hoverOnInventoryDpo method.

  /** The method will select the "subInventory" UI element of dashboard with mouse hover. **/
  public void hoverOnSubInventory() {
    try {
      
      Thread.sleep(3000);
      UiWindowActions.mouseover(getDriver(), subInventory);
      ActionHelper.fluentWait(getDriver(), appNavigator, 4);
      UiElementActions.jsClick(getDriver(), subInventory);
      log.info("clicked on Sub Inventory");
      
    } catch (Exception e) {
      log.error("Error in sub inventory section of dashboard. {}",e.getMessage());
    }
  } // End of hoverOnSubInventory method.

  /** The method will select the "stockTransfer" UI element of dashboard with mouse hover. **/
  public void hoverOnStockTransfer() {
    try {
      
      Thread.sleep(3000);
      UiWindowActions.mouseover(getDriver(), stockTransfer);
      ActionHelper.fluentWait(getDriver(), appNavigator, 4);
      UiElementActions.jsClick(getDriver(), stockTransfer);
      log.info("clicked on Stock Transfer");
      
    } catch (Exception e) {
      log.error("Error in stock transfer section of dashboard. {}",e.getMessage());
    }
  } // End of hoverOnStockTransfer method.
} // End of DashBoardPage.