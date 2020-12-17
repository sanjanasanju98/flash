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
 * The component define different UI input of CRM account page.
 * 
 * @author  Darshan B S
 * @version 1.0
*/
@Log4j2
@Component
public class AccountInputPage extends BaseClassHelper {
  
  @FindBy(id = "Contacts_editView_fieldName_firstname")
  private WebElement firstNameInput;
  
  @FindBy(id = "Contacts_editView_fieldName_lastname")
  private WebElement lastNameInput;
  
  @FindBy(name = "middlename")
  private WebElement middleName;
  
  @FindBy(name = "gender")
  private WebElement genderInput;
  
  @FindBy(id = "Contacts_editView_fieldName_birthday")
  private WebElement dateOfBirth;
  
  @FindBy(id = "Contacts_editView_fieldName_email")
  private WebElement primaryEmail;
  
  @FindBy(xpath = "//input[@name='mobile']/preceding-sibling::select[@name='dialcode']")
  private WebElement mobileCode;
  
  @FindBy(id = "Contacts_editView_fieldName_mobile")
  private WebElement primaryMobileNumber;
  
  @FindBy(id = "Contacts_editView_fieldName_secondaryemail")
  private WebElement secondaryEmail;

  public void initPageFactory() {
    PageFactory.initElements(getDriver(), this);
  }
  
  /** The method will enter first name of the customer in the account page. **/
  public void enterFirstName(final String firstName) {
    try {
      
      ActionHelper.fluentWait(getDriver(), firstNameInput, 120);
      WebPageActionsUtil.type(firstNameInput, firstName);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in entering first name.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of enterFirstName method.
  
  /** The method will enter first name of the customer in the account page. **/
  public void enterLastName(final String lastName) {
    try {
      
      WebPageActionsUtil.type(lastNameInput, lastName);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in entering last name.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of enterLastName method.
  
  /** The method will enter date of birth in the account page. **/
  public void enterDateOfBirth(final String dob) {
    try {
      
      WebPageActionsUtil.type(dateOfBirth, dob);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in entering date of birth.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of enterDateOfBirth method.
  
  /** The method will click middle name in account page. **/
  public void clickMiddleName() {
    try {
      
      WebPageActionsUtil.click(getDriver(), middleName);
      log.info("Middle name clicked in account page.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in clicking middle name.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of clickMiddleName method.
  
  /** The method will enter primary email in the account page. **/
  public void enterPrimaryEmail(final String email) {
    try {
      
      WebPageActionsUtil.type(primaryEmail, email);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in entering primary email.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of enterPrimaryEmail method.
  
  /** The method will enter primary mobile in the account page. **/
  public void enterPrimaryMobile(final String primaryMobile) {
    try {
      
      WebPageActionsUtil.type(primaryMobileNumber, primaryMobile);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in entering primary mobile.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of enterPrimaryMobile method.
  
  /** The method will enter secondary email in the account page. **/
  public void enterSecondaryEmail(final String secondEmail) {
    try {
      
      WebPageActionsUtil.type(secondaryEmail, secondEmail);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in entering secondary email.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of enterSecondaryEmail method.
  
  /** The method will select gender in the account page. **/
  public void selectGender(final String gender) {
    try {
      
      ActionHelper.fluentWait(getDriver(), genderInput, 10);
      UiElementActions.selectByVisibleText(gender,genderInput);
      log.info("Gender selected in account page.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in selecting gender.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of selectGender method.
  
  /** The method will select mobile code in the account page. **/
  public void selectMobileCode(final String mobile) {
    try {
      
      UiElementActions.selectByValue(mobileCode, mobile);
      log.info("Mobile code selected in account page.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in selecting mobile code.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of selectMobileCode method.
} // End of AccountInputPage.