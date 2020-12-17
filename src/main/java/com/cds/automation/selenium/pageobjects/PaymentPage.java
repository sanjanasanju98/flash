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
 * The component define different UI functionality of CRM payment posting page.
 * 
 * @author  Darshan B S
 * @version 1.0
*/
@Log4j2
@Component
public class PaymentPage extends BaseClassHelper {
  
  @FindBy(xpath = "///span[@class='tab-icon']//img[@title='Payments']")
  private WebElement paymentsIcon;
  
  @FindBy(name = "addButton")
  private WebElement addPaymentBtn;
  
  @FindBy(id = "Payments_editView_fieldName_paymentdate")
  private WebElement paymentDate;
  
  @FindBy(id = "Payments_editView_fieldName_amount")
  private WebElement amountClick;
  
  @FindBy(name = "paymenttype")
  private WebElement paymentType;
  
  @FindBy(xpath = "//div[@class='modal myModal fade in']//tr[1]//td[4]//div[1]//input")
  private WebElement paymentAmt;
  
  @FindBy(xpath = "//div[@class='modal-footer']//button[@name='saveButton']")
  private WebElement paymentsSaveBtn;
  
  @FindBy(xpath = "//button[@id='SalesOrder_detailView_basicAction_Submit']")
  private WebElement paymentSubmitBtn;
  
  @FindBy(xpath = "//button[contains(text(),'Yes')]")
  private WebElement confirmPaymentBtn;
  
  public void initPageFactory() {
    PageFactory.initElements(getDriver(), this);
  }

  /** The method will click payment icon in payment page. **/
  public void clickPaymentIcon() {
    try {
      
      Thread.sleep(2000);
      new WebDriverWait(getDriver(), 40).until(
            ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='messageBar']/div")));
      
      UiElementActions.scrollByVisibilityOfElement(getDriver(), paymentsIcon);
      UiElementActions.jsClick(getDriver(), paymentsIcon);
      log.info("Payment icon clicked.");   
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in payment icon click.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of clickPaymentIcon method.
  
  /** The method will select prepaid package in sales order page. **/
  public void addPaymentBtnClick() {
    try {
      
      Thread.sleep(4000);
      WebPageActionsUtil.click(getDriver(), addPaymentBtn);
      log.info("Add Payment Button Clicked.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in selecting prepaid package.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of addPaymentBtnClick method.
  
  /** The method will enter payment date in the payment page. **/
  public void enterPaymentDate(final String date) {
    try {
      
      WebPageActionsUtil.type(paymentDate, date);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in entering payment date.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of enterPaymentDate method.
  
  /** The method will click amount button in payment page. **/
  public void amountBtnClick() {
    try {
      
      Thread.sleep(2000);
      WebPageActionsUtil.click(getDriver(), amountClick);
      final String amountbal = paymentAmt.getAttribute("value");
      log.info("Amount balance {}", amountbal);
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in amount button click.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of amountBtnClick method.
  
  /** The method will click amount button in payment page. **/
  public void selectPaymentType(final String payType) {
    try {
      
      UiElementActions.jsClick(getDriver(), paymentType);
      UiElementActions.selectByValue(paymentType, payType);
      log.info("Payment type selected.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in selecting payment type.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of selectPaymentType method.
  
  /** The method will click payment save button in payment page. **/
  public void savePayment() {
    try {
      
      UiElementActions.jsClick(getDriver(), paymentsSaveBtn);
      log.info("Payment save button clicked.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in saving payment.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of savePayment method.
  
  /** The method will click submit button in payment page. **/
  public void submitPaymentBtnClick() {
    try {
      
      Thread.sleep(1000);
      ActionHelper.fluentWait(getDriver(), paymentSubmitBtn, 60);
      UiElementActions.scrollByVisibilityOfElement(getDriver(), paymentSubmitBtn);
      UiElementActions.jsClick(getDriver(), paymentSubmitBtn);
      log.info("Payment submit button clicked.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in submitting payment.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of submitPaymentBtnClick method.
  
  /** The method will click payment confirm button in payment page. **/
  public void paymentConfirmationClick() {
    try {
      
      Thread.sleep(3000);
      WebPageActionsUtil.click(getDriver(), confirmPaymentBtn);
      log.info("Payment submit button clicked.");
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in confirming payment.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of paymentConfirmationClick method.
} // End of PaymentPage.