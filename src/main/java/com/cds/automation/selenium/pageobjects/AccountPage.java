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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

/**
 * The component define different UI functionality of CRM account page.
 * 
 * @author  Darshan B S
 * @version 1.0
*/
@Log4j2
@Component
public class AccountPage extends BaseClassHelper {
  
  @FindBy(xpath = "//button[contains(.,'Add Account')]")
  private WebElement addAccount;
  
  @FindBy(id = "Accounts_editView_fieldName_accountname")
  private WebElement accountName;

  @FindBy(xpath = "//li[contains(@data-link-key,'LBL_RECORD_DETAILS')]")
  private WebElement detailsPage;
  
  @FindBy(name = "segment")
  private WebElement segmentType;
  
  @FindBy(name = "customertype")
  private WebElement customerType;
  
  @FindBy(name = "account_servicetype")
  private WebElement serviceType;
  
  @FindBy(id = "Accounts_editView_fieldName_primary_contact_create")
  private WebElement primaryContact;
  
  @FindBy(name = "salutationtype")
  private WebElement salutationType;
  
  @FindBy(xpath = "//button[@type='submit']")
  private WebElement saveAccountButton;

  public void initPageFactory() {
    PageFactory.initElements(getDriver(), this);
  }

  /** The method will fetch customer account details from account page. **/
  public void fetchAccountDetails() {
    try {
      
      WebPageActionsUtil.click(getDriver(), detailsPage);
      log.info("account details Clicked.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching account details.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of fetchAccountDetails method.
  
  /** The method will add account in the account page with button click. **/
  public void addAccountClick() {
    try {
      
      new WebDriverWait(getDriver(), 10).until(ExpectedConditions
          .presenceOfElementLocated(By.id("Accounts_listView_basicAction_LBL_ADD_RECORD")));
      WebPageActionsUtil.click(getDriver(), addAccount);
      log.info("Add account button Clicked.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in adding account.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of addAccountClick method.
  
  /** The method will enter account name in the account page. **/
  public void addAccountName(final String account) {
    try {
      
      WebPageActionsUtil.type(accountName, account);
      log.info("Account name entered.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in entering account name.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of addAccountName method.
  
  /** The method will enter customer type in the account page. **/
  public void selectCustomerType(final String custType) {
    try {
      
      UiElementActions.selectByValue(customerType, custType);
      log.info("Customer type entered.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in entering customer type.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of selectCustomerType method.
  
  /** The method will enter service type in the account page. **/
  public void selectServiceType(final String service) {
    try {
      
      UiElementActions.selectByVisibleText(service,serviceType);
      log.info("Service type selected.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in entering service type.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of selectServiceType method.
  
  /** The method will enter account segment in the account page. **/
  public void selectSegment(final String segement) {
    try {
      
      UiElementActions.selectByVisibleText(segement,segmentType);
      log.info("Service segement selected.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in entering segment.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of selectSegment method.
  
  /** The method will add primary contact to the account in account page with button click. **/
  public void addPrimaryContact() {
    try {
      
      Thread.sleep(3000);
      WebPageActionsUtil.click(getDriver(), primaryContact);
      log.info("Primary contact added to the account.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in adding primary contact.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of addPrimaryContact method.
  
  /** The method will enter salutation in the account page. **/
  public void enterSalutation(final String salutation) {
    try {
      
      ActionHelper.fluentWait(getDriver(), salutationType, 120);
      UiElementActions.selectByValue(salutationType, salutation);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in entering salutation.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of enterSalutation method.
  
  /** The method will save account input in the account page. **/
  public void saveAccount() {
    try {
      Thread.sleep(3000);
      UiElementActions.scrollByVisibilityOfElement(getDriver(), saveAccountButton);
      Thread.sleep(4000);
      WebPageActionsUtil.click(getDriver(), saveAccountButton);
      log.info("Save Account Button Clicked.");
      Thread.sleep(2000);
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in saving account.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of saveAccount method.
  
  /** The method will save account input in the account page. **/
  public String getAccountnumber() {
    try {
      
      return getDriver().findElement(By.xpath("//span[@class='account_no']")).getText();
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in saving account.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getAccountnumber method.
} // End of AccountPage.