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
import com.cds.automation.selenium.exceptions.CrmUiException;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

/**
 * The component define different UI functionality of CRM account details page.
 * 
 * @author  Darshan B S
 * @version 1.0
*/
@Log4j2
@Component
public class AccountDetailsPage extends BaseClassHelper {
  
  public void initPageFactory() {
    PageFactory.initElements(getDriver(), this);
  }

  /** The method will get customer account status from account details page. **/
  public String getAccountStatus() {
    try {
      
      final String accntStatus = ActionHelper.getText(getDriver(), 
                                    "//*[@id='Accounts_detailView_fieldValue_accountstatus']");
      log.info("Account status : {}", accntStatus);
      return accntStatus;
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching account status.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getAccountStatus method.
  
  /** The method will get service type from account details page. **/
  public String getServiceType() {
    try {
      
      final String service = 
            ActionHelper.getText(getDriver(), 
                         "//*[@id='Accounts_detailView_fieldValue_account_servicetype']");
      log.info("Service type : {}", service);
      return service;
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching service type.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getServiceType method.
  
  /** The method will get account category from account details page. **/
  public String getAccountCategory() {
    try {
      
      final String category = 
            ActionHelper.getText(getDriver(), 
                          "//*[@id='Accounts_detailView_fieldValue_account_category']");
      log.info("Account category : {}", category);
      return category;
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching account category.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getAccountCategory method.
  
  /** The method will get account segment from account details page. **/
  public String getSegement() {
    try {
      
      final String segment = 
            ActionHelper.getText(getDriver(), 
                         "//*[@id='Accounts_detailView_fieldValue_segment']");
      log.info("Account segment : {}", segment);
      return segment;
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching account segment.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getSegement method.
  
  /** The method will get customer type from account details page. **/
  public String getCustomerType() {
    try {
      
      final String customerType = 
            ActionHelper.getText(getDriver(), 
                         "//*[@id='Accounts_detailView_fieldValue_customertype']");
      log.info("Customer type : {}", customerType);
      return customerType;
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching customer type.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getCustomerType method.
} // End of AccountDetailsPage.