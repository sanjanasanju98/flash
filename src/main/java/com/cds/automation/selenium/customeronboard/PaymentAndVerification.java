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
import com.cds.automation.selenium.pageobjects.AccountVerificationPage;
import com.cds.automation.selenium.pageobjects.DashBoardPage;
import com.cds.automation.selenium.pageobjects.DashBoardSalesSection;
import com.cds.automation.selenium.pageobjects.PaymentPage;
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
public class PaymentAndVerification extends BaseClassHelper {
  
  @Autowired
  private AccountVerificationPage accountVerification;
  
  @Autowired
  private PaymentPage payment;
  
  @Autowired
  private DashBoardPage dashboard;
  
  @Autowired
  private DashBoardSalesSection dashboardSales;
  
  /** The method will post payment in CRM payment page. **/
  public void postPayment(final Map<String,String> data,final String accountNo) {
    try {
      
      payment.initPageFactory();
      Thread.sleep(6000);
      payment.clickPaymentIcon();
      payment.addPaymentBtnClick();
      payment.enterPaymentDate(data.get("paymentDate"));
      payment.amountBtnClick();
      payment.selectPaymentType(data.get("paymentType"));
      payment.savePayment();
      Thread.sleep(7000);
      payment.submitPaymentBtnClick();
      payment.paymentConfirmationClick();
      
      dashboard.initPageFactory();
      dashboard.appNavigationClick();
      dashboardSales.hoverOnSales();
      dashboardSales.hoverOnSalesAccounts();
      
      verifyAccount(data,accountNo);
     
    } catch (Exception e) {
      
      log.error("Error occurred while entering payment. {}",e.getMessage());
      final StringBuilder error = 
                          new StringBuilder("Error in posting payment.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of postPayment method.
  
  /** The method will enter customer account secondary input in account UI. **/
  public void verifyAccount(final Map<String,String> data,final String accountNo) {
    try {
      
      accountVerification.initPageFactory();
      accountVerification.enterAccountNo(accountNo);
      accountVerification.clickAccountSearch();
      Thread.sleep(3000);
      accountVerification.clickAccount();
      accountVerification.clickAssets();
      accountVerification.clickBlaze();
      
      final String assetStatus = accountVerification.getAssetStatus();
      if (!data.get("assetStatus").equalsIgnoreCase(assetStatus)) {
        
        throw new CrmUiException("Created asset status and expected status not matched.");
      }
      Thread.sleep(3000);
      accountVerification.clickInventory();
      
      final String inventoryStatus = accountVerification.getInventoryStatus();
      if (!data.get("inventoryStatus").equalsIgnoreCase(inventoryStatus)) {
        
        throw new CrmUiException("Created inventory status and expected status not matched.");
      }
      getDriver().navigate().back();
      accountVerification.clickSimInventory();
      accountVerification.getAssetStatus();
      accountVerification.clickInventory();
      accountVerification.getInventoryStatus();
      
    } catch (Exception e) {
      
      log.error("Error occurred while verifying the account. {}",e.getMessage());
      final StringBuilder error = 
                          new StringBuilder("Error in account creation").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of enterSecondaryInput method.
} // End of CustomerAccountInput.