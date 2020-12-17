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

import com.cds.automation.selenium.balancetransfer.DealerWallet;
import com.cds.automation.selenium.common.BaseClassHelper;
import com.cds.automation.selenium.common.WebPageActionsUtil;
import com.cds.automation.selenium.exceptions.CrmUiException;
import com.cds.automation.selenium.utils.LogUtil;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.testng.Assert;

/**
 * The component define different UI functionality of CRM balance transfer page.
 * 
 * @author  Debasmita Sarkar.
 * @version 1.0
*/
@Log4j2
@Component
public class BalanceTransferPage extends BaseClassHelper {

  @FindBy(xpath = "//li[@data-label-key='Balance Details']")
  private WebElement balanceDetailsIcon;

  @FindBy(xpath = "//span[contains(text(),'Unable to Fetch Balance for this account')]")
  private WebElement balanceError;

  @FindBy(xpath = "//a[@title='BalanceTransfer']")
  private WebElement dealerWallet;

  @FindBy(xpath = "//h4[@class='fieldBlockHeader']")
  private WebElement dealerAmountBal;

  @FindBy(xpath = "//input[@id='type_account']")
  private WebElement accountRadio;

  @FindBy(xpath = "//input[@name='account_number']")
  private WebElement accountField;

  @FindBy(xpath = "//input[@name='amount']")
  private WebElement amountField;

  @FindBy(xpath = 
         "//form[@id='balanceTransfer']//a[@data-dismiss='modal'][contains(text(),'Cancel')]")
  private WebElement cancelTransfer;

  @FindBy(xpath = "//button[@type='submit']")
  private WebElement submitTransfer;

  @FindBy(xpath = "//div[@class='summaryViewHeader ']/b")
  private WebElement customerBalance;

  public void initPageFactory() {
    PageFactory.initElements(getDriver(), this);
  } // End of constructor.

  /** The method will initiate balance transfer from deal wallet. **/
  public void balanceDetailIconClick() {
    try {

      new WebDriverWait(getDriver(), 10).until(ExpectedConditions.presenceOfElementLocated(
          By.xpath("//div[@class='container-fluid main-container']//li[10]")));
      final List<WebElement> targetElement = 
            getDriver().findElements(By.xpath(
                        "//span[contains(text(),'Unable to Fetch Balance for this account')]"));
      
      WebPageActionsUtil.click(getDriver(), balanceDetailsIcon);
      Thread.sleep(5000);
      log.info("Balance Details icon clicked.");
      
      if (!targetElement.isEmpty()) {
        
        log.info("TEXT IS : {}", balanceError.getText());
        LogUtil.info("Unable to fetch balance.");
      }
    } catch (Exception e) {
      
      final StringBuilder error = 
            new StringBuilder("Error in balance details icon click.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of balanceDetailIconClick method.

  /** The method will initiate balance transfer from deal wallet. **/
  public DealerWallet dealerWalletAmountTransfer(
                                        final String transferAmount,final String accntNum) {
    try {
      
      WebPageActionsUtil.click(getDriver(), dealerWallet);
      Thread.sleep(3000);
      final String avlAmount = dealerAmountBal.getText().replaceAll("[A-Za-z ,.:]", "");
      final long amount = Long.parseLong(avlAmount);
      final long amountTobeTransfer = Long.parseLong(transferAmount);
      log.info("Amount in Wallet Before Transfer is : {}", amount);
      
      if (amount > 0 && amount > amountTobeTransfer && amountTobeTransfer > 0) {
        
        WebPageActionsUtil.click(getDriver(), accountRadio);
        Thread.sleep(2000);
        WebPageActionsUtil.type(accountField, accntNum);
        WebPageActionsUtil.type(amountField, transferAmount);
        Thread.sleep(2000);
        WebPageActionsUtil.click(getDriver(), submitTransfer);
        LogUtil.info("Dealer Submit Transfer Button clicked.");
        Thread.sleep(10000);
        
        return DealerWallet.builder().currentAmount(amount)
                                     .transferAmount(amountTobeTransfer).build();
      } else {
        
        WebPageActionsUtil.click(getDriver(), cancelTransfer);
        throw new CrmUiException("Incorrect Amount/Insufficient balance in dealer account.");
      }
    } catch (Exception e) {
      
      final StringBuilder error = 
            new StringBuilder("Error in dealer wallet click.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of dealerWalletAmountTransfer method.

  /** The method will validate deal amount before balance transfer. **/
  public void validateDealerBalAfterTransfer(final long previousAmount,final long transferAmount) {
    try {
      
      Thread.sleep(6000);
      WebPageActionsUtil.click(getDriver(), dealerWallet);
      Thread.sleep(3000);
      final String avlAmount = dealerAmountBal.getText().replaceAll("[A-Za-z ,.:]", "");
      final long dealerCurrentAmount = Long.parseLong(avlAmount);
      log.info("Available amount for dealer : {} ", avlAmount);
      log.info("Amount : {} ", previousAmount);
      log.info("T amount is {} ", transferAmount);
      final long dealerTransferAmount = previousAmount - transferAmount;
      Assert.assertEquals(dealerCurrentAmount, dealerTransferAmount);
      log.info("Dealer Balance before transfer is {}", previousAmount);
      log.info("Amount Dealer Transferred to customer is  {}", transferAmount);
      log.info("Dealer Balance After transfer is {}", dealerCurrentAmount);
      log.info("Amount in Wallet After transfer is Passed : {}", dealerCurrentAmount);
      Thread.sleep(2000);
      WebPageActionsUtil.click(getDriver(), cancelTransfer);
      Thread.sleep(2000);
      
    } catch (Exception e) {
      final StringBuilder error = 
            new StringBuilder("Error in validation after dealer balance transfer.")
                .append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of validateDealerBalAfterTransfer method.

  /** The method will provide the balance amount of given account before balance transfer. 
   * @return **/
  public double customerBalanceBeforeTransfer() {
    try {
      
      final String custBalAmount = customerBalance.getText().replaceAll("[A-Za-z ,]", "");
      final double previousBalance = Double.parseDouble(custBalAmount);
      log.info("Customer account balance before transfer : {}", previousBalance);
      Thread.sleep(4000);
      return previousBalance;
   
    } catch (Exception e) {
      final StringBuilder error = 
          new StringBuilder("Error in dealer balance transfer.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of customerBalanceBeforeTransfer method.

  /** The method validate the amount in customer account after completion of balance transfer. **/
  public void validateCustomerBalanceAfterTransfer(
                                     final double dealerCurrentAmount,final long transferAmount) {
    try {
      final String customerBalanceAmount = customerBalance.getText().replaceAll("[A-Za-z ,]", "");
      log.info("Customer balance amount = {}", customerBalanceAmount);
    
      final double balanceAfterTransfer = Double.parseDouble(customerBalanceAmount);
      final double dealerAmountAfterTransfer = dealerCurrentAmount + transferAmount;
      log.info("Customer Balance before transfer : {}", dealerCurrentAmount);
      log.info("Amount Transferred to customer :  {}", transferAmount);
      log.info("Customer Balance After transfer : {}", balanceAfterTransfer);
      Assert.assertEquals(balanceAfterTransfer, dealerAmountAfterTransfer);
    
    } catch (Exception e) {
      final StringBuilder error = 
            new StringBuilder("Error in validating balance after transfer.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of validateCustomerBalanceAfterTransfer method.
} // End of BalanceTransferPage, which perform basic actions of CRM balance transfer web page.