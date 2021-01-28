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

package com.cds.automation.selenium.balancetransfer;

import com.cds.automation.selenium.common.BaseClassHelper;
import com.cds.automation.selenium.constanats.UiConstantsUtil;
import com.cds.automation.selenium.exceptions.CrmUiException;
import com.cds.automation.selenium.pageobjects.AccountPage;
import com.cds.automation.selenium.pageobjects.BalanceTransferPage;
import com.cds.automation.selenium.pageobjects.CrmLoginPage;
import com.cds.automation.selenium.pageobjects.DashBoardPage;
import com.cds.automation.selenium.pageobjects.DashBoardSalesSection;
import com.cds.automation.selenium.pageobjects.SimChangePage;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
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
public class BalanceTransferProcessor extends BaseClassHelper {
  
  @Autowired
  private CrmLoginPage login;
  
  @Autowired
  private DashBoardPage dashboard;
  
  @Autowired
  private DashBoardSalesSection dashboardSales;
  
  @Autowired
  private SimChangePage changeSim;
  
  @Autowired
  private AccountPage account;
  
  @Autowired
  private BalanceTransferPage balance;
  
  /** The method will transfer the balance from customer account to dealer account. **/
  public void dealerBalanceTransfer(final XWPFDocument doc,final Map<String,String> data) {
    try {
      
      launchApp();  // Launching the chrome browser for CRM GUI.
      
      // Login user to the CRM application
      login.initPageFactory();
      login.login(data.get("userName"), data.get("password"),doc);
  
      // Navigating through the dashboard side menu.
      dashboard.initPageFactory();
      dashboard.appNavigationClick();

      // Navigating to sales and accounts section of the CRM GUI.
      dashboardSales.initPageFactory();
      dashboardSales.hoverOnSales();
      dashboardSales.hoverOnSalesAccounts();

      // Searching for the given account.
      changeSim.initPageFactory();
      changeSim.enterAccountNo(data.get(UiConstantsUtil.ACCOUNTNO)); 
      changeSim.searchBtnClick(data.get(UiConstantsUtil.ACCOUNTNO));

      // Fetching account details from account page.
      account.initPageFactory();
      account.fetchAccountDetails();

      // Doing the balance transfer.
      balance.initPageFactory();
      balance.balanceDetailIconClick();
      
      Thread.sleep(1000);
      final double dealerPreviousAmount = balance.customerBalanceBeforeTransfer();
      Thread.sleep(1000);
      final DealerWallet wallet = 
            balance.dealerWalletAmountTransfer(
                          data.get("amountToTransfer"), data.get(UiConstantsUtil.ACCOUNTNO),"");
      Thread.sleep(2000);
      
      balance.validateDealerBalAfterTransfer(
                            wallet.getCurrentAmount(),wallet.getTransferAmount());
      balance.balanceDetailIconClick();
      balance.validateCustomerBalanceAfterTransfer(
                              dealerPreviousAmount,wallet.getTransferAmount());
    } catch (Exception e) {
      
      log.error("Error occurred while executing balance transfer. {}",e.getMessage());
      final StringBuilder error = 
                          new StringBuilder("Error in balance transfer").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of dealerBalanceTransfer method.
} // End of BalanceTransferProcessor.