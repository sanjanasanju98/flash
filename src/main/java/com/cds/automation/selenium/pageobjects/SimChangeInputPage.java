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
import com.cds.automation.selenium.utils.LogUtil;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

/**
 * The component define different UI functionality of CRM SIM change page.
 * 
 * @author  Akshata Ugrani
 * @version 1.0
*/
@Log4j2
@Component
public class SimChangeInputPage extends BaseClassHelper {
  
  @FindBy(xpath = "//*[@id=\"listview-table\"]/tbody/tr/td[10]/div/button")
  private WebElement selectActionType;
  
  @FindBy(xpath = "//*[@id=\"listview-table\"]/tbody/tr/td[10]/div/ul/li[1]/a")
  private WebElement changeSimOpt;
  
  @FindBy(xpath = "//button[contains(text(),'Yes')]")
  private WebElement confirmBtn;
  
  @FindBy(xpath = "//*[@id=\"row1\"]/td[1]/div[1]/div/div/div[1]/span[2]/i")
  private WebElement simIcon;
  
  @FindBy(xpath = "//select[@name='istatus']")
  private WebElement invStatus;
  
  @FindBy(xpath = "//*[@id=\"mCSB_6_container\"]/div/table/tbody/tr[1]/td[1]/button")
  private WebElement searchBtton;
  
  @FindBy(xpath = "//*[@name = \"update_button\"]")
  private WebElement saveSalesOrderBtn;
  
  @FindBy(xpath = "//button[@id='SalesOrder_detailView_basicAction_Submit']")
  private WebElement submitBtn;
  
  @FindBy(xpath = "//*[@id=\"page\"]/div[4]/div/div[2]/div/div[2]/div[1]/ul/li[2]/a/span/strong")
  private WebElement salesDetail;

  public void initPageFactory() {
    PageFactory.initElements(getDriver(), this);
  }

  /** The method will select action icon in SIM change page. **/
  public void selectAction(final String changeSim) {
    try {
      
      Thread.sleep(3000);
      WebPageActionsUtil.click(getDriver(), selectActionType);
      LogUtil.info("SIM Change asset icon clicked.");
      UiElementActions.selectTableElement(changeSim, getDriver(),"//tbody[@class='overflow-y']");
      UiElementActions.jsClick(getDriver(), changeSimOpt);
      
    } catch (Exception e) {
      
      final StringBuilder error = 
            new StringBuilder("Error in selecting action icon.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of selectAction method.
  
  /** The method will click confirm button in SIM change page. **/
  public void clickConfirmButton() {
    try {
      
      Thread.sleep(3000);
      WebPageActionsUtil.click(getDriver(), confirmBtn);
      
    } catch (Exception e) {
      
      final StringBuilder error = 
            new StringBuilder("Error in confirm button click of SIMChange.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of clickConfirmButton method.
  
  /** The method will select SIM in SIM change page. **/
  public void selectSim(final String inventorySim) {
    try {
      
      final List<WebElement> wb = getDriver()
                 .findElements(By.xpath("//*[@id=\"row1\"]/td[1]/div[1]/div/div/div[1]/span[2]/i"));
      log.info("Web element size : {}", wb.size());
      Thread.sleep(10000);
      UiWindowActions.moveToElement(getDriver(), simIcon);
      
      Thread.sleep(10000);
      UiElementActions.jsClick(getDriver(), simIcon);
      
      Thread.sleep(10000);
      UiElementActions.selectByValue(invStatus, inventorySim);
      UiElementActions.jsClick(getDriver(), searchBtton);

      Thread.sleep(10000);
      UiElementActions.selectTableElement(inventorySim, getDriver(),
          "//table[@class='listview-table table-bordered listViewEntriesTable']//tbody");
      log.info("New SIM selected.");
      
    } catch (Exception e) {
      
      final StringBuilder error = 
            new StringBuilder("Error in select SIM of SIMChange.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of selectSim method.
  
  /** The method will click save sales order in SIM change page. **/
  public void saveSalesOrder() {
    try {
      
      Thread.sleep(3000);
      UiElementActions.scrollByVisibilityOfElement(getDriver(), saveSalesOrderBtn);
      UiElementActions.jsClick(getDriver(), saveSalesOrderBtn);
      
      Thread.sleep(8000);
      log.info("Save Sales Order Button Clicked in SIM change.");   
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in saving sales order button.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of saveSalesOrder method.
  
  /** The method will click save sales order in SIM change page. **/
  public void clickSubmitButton() {
    try {
      
      if (WebPageActionsUtil.isEnabled(submitBtn)) {
        
        ActionHelper.fluentWait(getDriver(), submitBtn, 60);
        UiElementActions.scrollByVisibilityOfElement(getDriver(), submitBtn);
        WebPageActionsUtil.click(getDriver(), submitBtn);
      
        Thread.sleep(8000);
        log.info("Save Sales Order submit Clicked in SIM change."); 
      }
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in submit button of SIM change.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of clickSubmitButton method.
  
  /** The method will click sales order details in SIM change page. **/
  public void salesOrderDetails() {
    try {
      
      Thread.sleep(3000);
      UiElementActions.jsClick(getDriver(), salesDetail);
      log.info("Sales order details selected in SIM change."); 
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in sales order of SIM change.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of salesOrderDetails method.
  
  /** The method will fetch status of sales order from SIM change page. **/
  public String getSalesOrderStatus() {
    try {
      
      return ActionHelper.getText(getDriver(),
                 "//*[@id=\"SalesOrder_detailView_fieldValue_sostatus\"]/span/span");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching sales order of SIMChange.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getSalesOrderStatus method.
} // SimChangePage.