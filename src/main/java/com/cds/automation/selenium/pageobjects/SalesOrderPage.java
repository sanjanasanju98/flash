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
 * The component define different UI functionality of CRM sales order page.
 * 
 * @author  Darshan B S
 * @version 1.0
*/
@Log4j2
@Component
public class SalesOrderPage extends BaseClassHelper {
  
  @FindBy(xpath = "//body/div[@id='page']//ul/li[6]/a[1]")
  private WebElement salesOrderIcon;
  
  @FindBy(name = "addButton")
  private WebElement addSalesOrderBtn;
  
  @FindBy(id = "packagelist_display")
  private WebElement packageList;
  
  @FindBy(xpath = "//input[@name='packagelist_display']")
  private WebElement prepaidPackageList;
  
  @FindBy(xpath = "//input[@name='packagelist_no']")
  private WebElement packageListNo;
  
  @FindBy(xpath = "//button[starts-with(text(),'Search')]")
  private WebElement packageListSearchBtn;
  
  @FindBy(id = "finishSelection")
  private WebElement finishSelectionBtn;
  
  @FindBy(xpath = "//input[@id='renewablepackagelist_display']")
  private WebElement renewablePackageList;
  
  @FindBy(xpath = "//*[@id='row7']/td[1]/div[1]/div[1]/span[2]/i")
  private WebElement sim;
  
  @FindBy(name = "update_button")
  private WebElement saveSalesOrderBtn;
  
  @FindBy(xpath = "//select[@name='istatus']")
  private WebElement inventoryStatus;
  
  @FindBy(xpath = "//button[@class='btn btn-success']")
  private WebElement searchBtn;
  
  private static final String TABLE = 
         "//table[@class='listview-table table-bordered listViewEntriesTable']//tbody";
  
  public void initPageFactory() {
    PageFactory.initElements(getDriver(), this);
  }

  /** The method will click sales order icon in sales order page. **/
  public void clickSalesOrderIcon() {
    try {
      
      new WebDriverWait(getDriver(), 40).until(ExpectedConditions
                   .invisibilityOfElementLocated(By.xpath("//*[@id='messageBar']/div")));
      Thread.sleep(2000);
      UiElementActions.jsClick(getDriver(), salesOrderIcon);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in sales order icon click.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of clickSalesOrderIcon method.
  
  /** The method will click add sales order in sales order page. **/
  public void addSalesOrder() {
    try {
      
      Thread.sleep(2000); 
      WebPageActionsUtil.click(getDriver(), addSalesOrderBtn);
      log.info("Add sales order clicked in sales order page.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in adding sales order button.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of addSalesOrder method.
  
  /** The method will click save sales order in sales order page. **/
  public void saveSalesOrder() {
    try {
      
      Thread.sleep(2000);
      UiElementActions.scrollByVisibilityOfElement(getDriver(), saveSalesOrderBtn);
      UiElementActions.jsClick(getDriver(), saveSalesOrderBtn);
      Thread.sleep(8000);
      log.info("Save Sales Order Button Clicked.");   
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in saving sales order button.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of saveSalesOrder method.
  
  /** The method will get order sub type from sales order page.**/
  public String getOrderSubtype() {
    try {
      
      final String ordertype = ActionHelper.getText(getDriver(),
                 "//*[@id='SalesOrder_detailView_fieldValue_sosubstatus']/span/span");
      log.info("order subtype : {}",ordertype);   
      return ordertype;
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching sales order subtype.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of saveSalesOrder method.
  
  /** The method will get order sub type from sales order page.**/
  public String getStatus() {
    try {
      
      final String status = ActionHelper.getText(getDriver(),
                             "//*[@id='SalesOrder_detailView_fieldValue_sostatus']/span/span");
      log.info("order status : {}",status);   
      return status;
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching sales order status.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getStatus method.
  
  /** The method will select package list in sales order page. **/
  public void selectPackageList() {
    try {
      
      UiElementActions.scrollByVisibilityOfElement(getDriver(), packageList);
      Thread.sleep(2000);
      UiElementActions.jsClick(getDriver(), packageList);
      log.info("Package list selected in sales order page.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in selecting package list.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of selectPackageList method.
  
  /** The method will select prepaid package in sales order page. **/
  public void selectPrepaidPackage(final String packageNo) {
    try {
      
      WebPageActionsUtil.click(getDriver(), prepaidPackageList);
      WebPageActionsUtil.type(packageListNo, packageNo);
      UiElementActions.jsClick(getDriver(), packageListSearchBtn);
      Thread.sleep(2000);
      UiElementActions.selectTableElement(packageNo, getDriver(),TABLE);
      UiElementActions.jsClick(getDriver(), finishSelectionBtn);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in selecting prepaid package.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of selectPrepaidPackage method.
  
  /** The method will select renewal package in sales order page. **/
  public void selectRenewalPackage(final String packageNo) {
    try {
      
      Thread.sleep(2000);
      
      UiElementActions.jsClick(getDriver(), renewablePackageList);
      WebPageActionsUtil.type(packageListNo, packageNo);
      Thread.sleep(2000);
      UiElementActions.selectTableElement(packageNo, getDriver(),TABLE);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in selecting renewal package.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of selectRenewalPackage method.
  
  /** The method will select SIM in sales order page. **/
  public void selectSim(final String inventorySim) {
    try {
      
      UiWindowActions.moveToElement(getDriver(), sim);
      UiElementActions.jsClick(getDriver(), sim);
      log.info("Blaze CPE Clicked.");
      UiElementActions.selectByValue(inventoryStatus, inventorySim);
      UiElementActions.jsClick(getDriver(), searchBtn);
      Thread.sleep(2000);
      UiElementActions.selectTableElement(inventorySim, getDriver(),TABLE);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in selecting SIM.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of selectSim method.
  
  /** The method will select SIM in sales order page. **/
  public void calculateItemTotal() {
    try {
      
      log.info("Calculating item total");   
      
      final String itemsTotalAmt = ActionHelper.getText(getDriver(), "//*[@id='netTotal']");
      final String discountAmt   = 
                   ActionHelper.getText(getDriver(), "//*[@id='discountTotal_final']");
      final String grandTotalAmt = ActionHelper.getText(getDriver(), "//*[@id='grandTotal']");
      final String taxAmt = ActionHelper.getText(getDriver(), "//span[@id='tax_final']");
      
      log.info(" Total amount : {} ", itemsTotalAmt);
      log.info("discount : {}", discountAmt);
      log.info("Grand total : {}", grandTotalAmt);

      final Double d = Double.parseDouble(itemsTotalAmt);
      final Double d1 = Double.parseDouble(discountAmt);
     
      final Double amount = d - d1;
      log.info("amount : {}", amount);
      final Double tax = Double.parseDouble(taxAmt);
      log.info("tax {}",tax);
      final Double total = tax + amount;
      log.info("total after tax : {}", total);
      final String totalAmount = String.format("%.2f", total);
             
      log.info("Total Amount : {}", totalAmount);
      ActionHelper.writeProperty("totalAmount", totalAmount);
      log.info("The total amount is: {}", totalAmount);   
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in selecting SIM.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of selectSim method.
  
  /** The method will save account input in the account page. **/
  public String getSalesOrderNo() {
    try {
      
      return ActionHelper.getText(getDriver(),"//span[@class='salesorder_no']");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching sales order number.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getSalesOrderNo method.
} // End of AccountDetailsPage.