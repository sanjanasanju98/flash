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
import com.cds.automation.selenium.pageobjects.AccountDetailsPage;
import com.cds.automation.selenium.pageobjects.SalesOrderPage;
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
public class PrepaidSalesOrder extends BaseClassHelper {
  
  @Autowired
  private AccountDetailsPage acctDetailsPage;
  
  @Autowired
  private SalesOrderPage salesOrder;
  
  @Autowired
  private PaymentAndVerification paymentAndVerification;
  
  /** The method will validate created customer account details. **/
  public void validateAccountDetails(final Map<String,String> data) {
    try {
      
      acctDetailsPage.initPageFactory();
      final String accountStatus = acctDetailsPage.getAccountStatus();
      final String serviceType   = acctDetailsPage.getServiceType();
      final String category      = acctDetailsPage.getAccountCategory();
      final String segment       = acctDetailsPage.getSegement();
      final String customerType  = acctDetailsPage.getCustomerType();
      
      validateOutput(data.get("accountStaus"),accountStatus,
                     "Created account status and given status not matched");
      
      validateOutput(data.get("serviceType"),serviceType,
                     "Created account status and given status not matched");
      
      validateOutput(data.get("accountCategory"),category,
                     "Created account category and given category not matched");
      
      validateOutput(data.get("accountSegment"),segment,
                     "Created account segment and given segment not matched");
      
      validateOutput(data.get("customerType"),customerType,
                    "Created customer type and given customer type not matched");
     
    } catch (Exception e) {
      
      log.error("Error occurred while executing balance transfer. {}",e.getMessage());
      final StringBuilder error = 
                          new StringBuilder("Error in balance transfer").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of validateAccountDetails method.
  
  private void validateOutput(final String input,final String output,final String error) {
    
    if (!input.equalsIgnoreCase(output)) {
      
      throw new CrmUiException(error);
    }
  } // End of validateOutput method.
  
  /** The method will create sales order as part of prepaid volte onboard. **/
  public void createSalesOrder(final Map<String,String> data,final String accountNo) {
    try {
      
      salesOrder.initPageFactory();
      salesOrder.clickSalesOrderIcon();
      Thread.sleep(6000);
      salesOrder.addSalesOrder();
      salesOrder.selectPackageList();
      salesOrder.selectPrepaidPackage(data.get("packageNo"));
      salesOrder.selectRenewalPackage(data.get("renewalPackageNo"));
      salesOrder.selectSim(data.get("sim"));
      salesOrder.calculateItemTotal();
      Thread.sleep(6000);
      salesOrder.saveSalesOrder();
      final String salesOrderNo = salesOrder.getSalesOrderNo();
      log.info("Sales order number :{}", salesOrderNo);
      Thread.sleep(5000);
      
      final String orderSubType = salesOrder.getOrderSubtype();
      final String orderStatus  = salesOrder.getStatus();
      
      validateOutput(data.get("orderSubType"),orderSubType,
                     "Created order type and given order type not matched");
     
      validateOutput(data.get("orderStatus"),orderStatus,
                     "Created order status and given order status not matched");
      
      paymentAndVerification.postPayment(data, accountNo);
      paymentAndVerification.verifyAccount(data, accountNo);
     
    } catch (Exception e) {
      
      log.error("Error occurred while creating prepaid volte saled order. {}",e.getMessage());
      final StringBuilder error = 
                          new StringBuilder("Error in sales order creation").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of createSalesOrder method.
} // End of PrepaidSalesOrder.