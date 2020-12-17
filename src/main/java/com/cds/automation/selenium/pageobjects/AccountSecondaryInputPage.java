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
 * The component define different UI input of CRM account page.
 * 
 * @author  Darshan B S
 * @version 1.0
*/
@Log4j2
@Component
public class AccountSecondaryInputPage extends BaseClassHelper {
  
  @FindBy(id = "Contacts_editView_fieldName_mothers_madein_name")
  private WebElement mothersMaidenName;
  
  @FindBy(xpath = "//button[@name='saveButton']//Strong")
  private WebElement saveContact;
  
  @FindBy(name = "bill_street")
  private WebElement billingAddress;
  
  @FindBy(id = "Accounts_editView_fieldName_bill_city")
  private WebElement billingCity;
  
  @FindBy(name = "bill_state")
  private WebElement billingState;
  
  @FindBy(name = "account_class")
  private WebElement accountClass;
  
  @FindBy(name = "account_category")
  private WebElement accountCategory;

  public void initPageFactory() {
    PageFactory.initElements(getDriver(), this);
  }
  
  /** The method will enter mother maiden name in the account page. **/
  public void enterMotherMaidenName(final String maidenName) {
    try {
      
      WebPageActionsUtil.type(mothersMaidenName, maidenName);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in entering mother maiden name.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of enterMotherMaidenName method.
  
  /** The method will click save contact in account page. **/
  public void clickSaveContact() {
    try {
      
      WebPageActionsUtil.click(getDriver(), saveContact);
      log.info("Save contact clicked.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in clicking saving contact.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of clickSaveContact method.
  
  /** The method will select account class in account page. **/
  public void selectAccountClass() {
    try {
      
      UiElementActions.selectByVisibleText("Billing", accountClass);
      log.info("Accoun class selected.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in selecting account class.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of selectAccountClass method.
  
  /** The method will select account category in account page. **/
  public void selectAccountCategory(final String accntCategry) {
    try {
      
      UiElementActions.selectByVisibleText(accntCategry, accountCategory);
      log.info("Accoun category selected.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in selecting account category.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of selectAccountCategory method.
  
  /** The method will add billing address in account page. **/
  public void addBillingAddress(final String address) {
    try {
      
      UiElementActions.scrollByVisibilityOfElement(getDriver(), billingAddress);
      WebPageActionsUtil.type(billingAddress, address);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in adding billing address.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of addBillingAddress method.
  
  /** The method will add billing city in account page. **/
  public void addBillingCity(final String city) {
    try {
      
      UiElementActions.scrollByVisibilityOfElement(getDriver(), billingAddress);
      WebPageActionsUtil.type(billingCity, city);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in adding billing city.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of addBillingCity method.
  
  /** The method will add billing state in account page. **/
  public void addBillingState(final String state) {
    try {
      
      UiElementActions.scrollByVisibilityOfElement(getDriver(), billingState);
      UiElementActions.selectByVisibleText(state, billingState);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in adding billing state.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of addBillingState method.
} // End of AccountInputPage.