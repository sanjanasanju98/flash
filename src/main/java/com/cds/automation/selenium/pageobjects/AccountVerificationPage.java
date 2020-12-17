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
import com.cds.automation.selenium.common.WebPageActionsUtil;
import com.cds.automation.selenium.exceptions.CrmUiException;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

/**
 * The component define different UI functionality of CRM account verification page.
 * 
 * @author  Darshan B S
 * @version 1.0
*/
@Log4j2
@Component
public class AccountVerificationPage extends BaseClassHelper {
  
  @FindBy(name = "account_no")
  private WebElement accountnumber;
  
  @FindBy(xpath = "//button[@class='btn btn-success btn-sm'][contains(text(),'Search')]")
  private WebElement search;
  
  @FindBy(xpath = "//*[@id='Accounts_listView_row_1']/td[2]/span/span/a")
  private WebElement accntclick;
  
  @FindBy(xpath = "//span[@class='tab-icon']//i[@class='vicon-assets']")
  private WebElement assets;
  
  @FindBy(xpath = "//span[@class='value textOverflowEllipsis']/a[contains(text(), 'Blaze CPE')]")
  private WebElement blaze;
  
  @FindBy(xpath = "//*[@id='Assets_detailView_fieldValue_inventory_id']/span/a")
  private WebElement inventory;
  
  @FindBy(xpath = "//span[@class='value textOverflowEllipsis']/a[contains(text(), 'SIM')]")
  private WebElement sim;
  
  public void initPageFactory() {
    PageFactory.initElements(getDriver(), this);
  }

  /** The method will enter account number in the verification page. **/
  public void enterAccountNo(final String accountNo) {
    try {
      
      WebPageActionsUtil.type(accountnumber, accountNo);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in entering account number.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of enterAccountNo method.
  
  /** The method will click on search button. **/
  public void clickAccountSearch() {
    try {
      
      Thread.sleep(2000);
      UiElementActions.jsClick(getDriver(), search);
      log.info("Search button clicked.");
      Thread.sleep(3000);    
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in selecting renewal package.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of clickAccountSearch method.
  
  /** The method will enter account number in the verification page. **/
  public void clickAccount() {
    try {
      
      WebPageActionsUtil.click(getDriver(), accntclick);
      log.info("Account button clicked.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in clicking account number.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of clickAccount method.
  
  /** The method will click on assets button. **/
  public void clickAssets() {
    try {
      
      UiElementActions.jsClick(getDriver(), assets);
      Thread.sleep(2000);   
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in clicking assets.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of clickAssets method.
  
  /** The method will click on blaze button. **/
  public void clickBlaze() {
    try {
      
      UiElementActions.jsClick(getDriver(), blaze);
      Thread.sleep(2000);   
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in clicking blaze.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of clickBlaze method.
  
  /** The method will get asset status from account verification page.**/
  public String getAssetStatus() {
    try {
      
      final String status = ActionHelper.getText(getDriver(),
                                "//td[@id='Assets_detailView_fieldValue_assetstatus']");
      log.info("Asset status : {}",status);   
      return status;
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching asset status.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getAssetStatus method.
  
  /** The method will click on inventory button. **/
  public void clickInventory() {
    try {
      
      UiElementActions.jsClick(getDriver(), inventory);
      Thread.sleep(2000);   
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in clicking inventory.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of clickInventory method.
  
  /** The method will get inventory status from account verification page.**/
  public String getInventoryStatus() {
    try {
      
      final String status = ActionHelper.getText(getDriver(),
                             "//*[@id='Inventory1_detailView_fieldValue_istatus']/span[1]/span");
      log.info("Inventory status : {}",status);   
      return status;
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching inventory status.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getInventoryStatus method.
  
  /** The method will click on SIM button. **/
  public void clickSimInventory() {
    try {
      
      UiElementActions.jsClick(getDriver(), sim);
      Thread.sleep(2000);   
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in clicking SIM.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of clickSimInventory method.
} // End of AccountVerificationPage.