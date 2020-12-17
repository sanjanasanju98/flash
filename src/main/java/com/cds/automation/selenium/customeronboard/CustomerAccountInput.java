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

package com.cds.automation.selenium.customeronboard;

import com.cds.automation.selenium.common.BaseClassHelper;
import com.cds.automation.selenium.exceptions.CrmUiException;
import com.cds.automation.selenium.pageobjects.AccountInputPage;
import com.cds.automation.selenium.pageobjects.AccountPage;
import com.cds.automation.selenium.pageobjects.AccountSecondaryInputPage;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The component will perform the dealer balance transfer as part of test suite execution. The 
 * component will use the page objects CrmLogin, BalanceTransfer, ChangeSim, AccountPage for the
 * execution of the scenario.
 * 
 * @author  Darshan B S.
 * @version 1.0
*/
@Log4j2
@Component
public class CustomerAccountInput extends BaseClassHelper {
  
  @Autowired
  private AccountInputPage accountInput;
  
  @Autowired
  private AccountSecondaryInputPage accountSecondaryInput;
  
  @Autowired
  private AccountPage account;
  
  /** The method will enter customer account input in account UI. **/
  public void enterPrimaryInformation(final Map<String,String> data) {
    try {
      
      account.initPageFactory();
      account.addAccountClick();
      account.addAccountName(data.get("accountName"));
      account.selectCustomerType(data.get("customerType"));
      account.selectServiceType(data.get("serviceType"));
      account.selectSegment(data.get("segment"));
      account.addPrimaryContact();
      account.enterSalutation(data.get("salutation"));
      
      accountInput.initPageFactory();
      accountInput.enterFirstName(data.get("firstName"));
      accountInput.enterLastName(data.get("lastName"));
      accountInput.enterDateOfBirth(data.get("dateOfBirth"));
      accountInput.clickMiddleName();
      accountInput.enterPrimaryEmail(data.get("primaryEmail"));
      accountInput.selectGender(data.get("gender"));
      accountInput.selectMobileCode(
                   new StringBuilder("+").append(data.get("mobileCode")).toString());
      accountInput.enterPrimaryMobile(data.get("primaryMobile"));
      accountInput.enterSecondaryEmail(data.get("secondaryEmail"));
     
    } catch (Exception e) {
      
      log.error("Error occurred while entering customer input. {}",e.getMessage());
      final StringBuilder error = 
                          new StringBuilder("Error in entering input").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of enterPrimaryInformation method.
  
  /** The method will enter customer account secondary input in account UI. 
   * @return **/
  public String enterSecondaryInput(final Map<String,String> data) {
    try {
      
      accountSecondaryInput.initPageFactory();
      accountSecondaryInput.enterMotherMaidenName(data.get("maidenName"));
      Thread.sleep(8000);
      accountSecondaryInput.clickSaveContact();
      accountSecondaryInput.selectAccountClass();
      accountSecondaryInput.selectAccountCategory(data.get("accountCategory"));
      accountSecondaryInput.addBillingAddress(data.get("billingAddress"));
      accountSecondaryInput.addBillingCity(data.get("billingCity"));
      accountSecondaryInput.addBillingState(data.get("billingState"));
      
      Thread.sleep(20000);
      account.saveAccount();
      
      final String accountNumber = account.getAccountnumber();
      log.info("Account number created : {}",accountNumber);
      account.fetchAccountDetails();
      return accountNumber;
     
    } catch (Exception e) {
      
      log.error("Error occurred while entering customer input. {}",e.getMessage());
      final StringBuilder error = 
                          new StringBuilder("Error in customer input").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of enterSecondaryInput method.
} // End of CustomerAccountInput.