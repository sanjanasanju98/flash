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
import com.cds.automation.selenium.utils.LogUtil;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

/**
 * The component define different UI functionality of CRM SIM change page.
 * 
 * @author  Akshata Ugrani
 * @version 1.0
*/
@Log4j2
@Component
public class SimChangePage extends BaseClassHelper {

  @FindBy(name = "account_no")
  private WebElement searchAccountNo;
  
  @FindBy(xpath = "//button[@class=\"btn btn-success btn-sm\"]")
  private WebElement searchBtn;

  @FindBy(xpath = "//li[@data-module='Assets']")
  private WebElement assetIcon;
  
  @FindBy(xpath = "//*[@id=\"listview-table\"]/thead/tr[2]/th[2]/div/input")
  private WebElement assetName;
  
  @FindBy(xpath = "//span[@class='value textOverflowEllipsis']/a[contains(text(), 'SIM')]")
  private WebElement sim;
  
  @FindBy(xpath = "//*[@id='Assets_detailView_fieldValue_inventory_id']/span/a")
  private WebElement inventory;
  
  public void initPageFactory() {
    PageFactory.initElements(getDriver(), this);
  }

  /** The method will enter given account number in SIM change page. **/
  public void enterAccountNo(final String acctNo) {

    try {
      
      Thread.sleep(4000);
      log.info("acctNo {}", acctNo);
      WebPageActionsUtil.type(searchAccountNo, acctNo);
      log.info("Account number enetered : {}", acctNo);
      
    } catch (Exception e) {
      
      final StringBuilder error = 
            new StringBuilder("Error in SIM change page while entering account.")
                .append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of enterAccountNo method.

  /** The method will search for the given accountno and select it in SIM change page. **/
  public void searchBtnClick(final String acctNo) {
    try {
      
      Thread.sleep(3000);
      UiElementActions.jsClick(getDriver(), searchBtn);
      LogUtil.info("SIM Change page search button clicked.");
      Thread.sleep(3000);
      UiElementActions.selectTableElement(acctNo, getDriver(),"//tbody[@class='overflow-y']");

      log.info("Account selected = {}", acctNo);
      Thread.sleep(5000);
      
    } catch (Exception e) {
      
      final StringBuilder error = 
            new StringBuilder("Error in SIM change search button click.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of searchBtnClick method.
  
  /** The method will select asset icon in SIM change page. **/
  public void selectAssetIcon() {
    try {
      
      new WebDriverWait(getDriver(), 20).until(
             ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@data-module='Assets']")));
      
      WebPageActionsUtil.click(getDriver(), assetIcon);
      LogUtil.info("SIM Change asset icon clicked.");
      
    } catch (Exception e) {
      
      final StringBuilder error = 
            new StringBuilder("Error in selecting asset icon of SIMChange.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of selectAssetIcon method.
  
  /** The method will enter given asset name in SIM change page. **/
  public void enterAssetName(final String asset) {

    try {
      
      Thread.sleep(4000);
      WebPageActionsUtil.type(assetName, asset);
      log.info("Asset namer enetered : {}", asset);
      
    } catch (Exception e) {
      
      final StringBuilder error = 
            new StringBuilder("Error in entering asset name of SIM change.")
                .append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of enterAssetName method.
  
  /** The method will search for the given accountno and select it in SIM change page. **/
  public void searchBtnInTable(final String sim) {
    try {
      
      Thread.sleep(3000);
      UiElementActions.jsClick(getDriver(), searchBtn);
      LogUtil.info("SIM Change page search button clicked.");
      UiElementActions.selectTableElement(sim, getDriver(),"//tbody[@class='overflow-y']");

      log.info("SIM selected = {}", sim);
      Thread.sleep(5000);
      
    } catch (Exception e) {
      
      final StringBuilder error = 
            new StringBuilder("Error in SIM change search button click.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of searchBtnInTable method.
  
  /** The method will click SIM icon in SIM change page. **/
  public void clickSim() {
    try {
      
      Thread.sleep(3000);
      UiElementActions.jsClick(getDriver(), sim);
      LogUtil.info("SIM icon clicked.");
      
    } catch (Exception e) {
      
      final StringBuilder error = 
            new StringBuilder("Error in SIM change SIM icon click.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of clickSim method.
  
  /** The method will get asset number from SIM change page.**/
  public String getAssetNumber() {
    try {
      
      final String assetNo = ActionHelper.getText(getDriver(),
                                 "//*[@id=\"Assets_detailView_fieldValue_asset_no\"]/span");
      log.info("Asset number : {}",assetNo);   
      return assetNo;
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching asset number.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getAssetNumber method.
  
  /** The method will get sales order number from SIM change page.**/
  public String getSalesOrderNumber() {
    try {
      
      final String assetNo = ActionHelper.getText(getDriver(),
                                "//*[@id=\"Assets_detailView_fieldValue_salesorder\"]/span/a");
      log.info("Sales order number : {}",assetNo);   
      return assetNo;
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching sales order number.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getSalesOrderNumber method.
  
  /** The method will get asset status from SIM change page.**/
  public String getAssetStatus() {
    try {
      
      final String status = ActionHelper.getText(getDriver(),
                               "//td[@id='Assets_detailView_fieldValue_assetstatus']");
      log.info("Asset status : {}",status);   
      return status;
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching asset status.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getAssetStatus method.
  
  /** The method will get device id from SIM change page.**/
  public String getDeviceId() {
    try {
      
      final String status = ActionHelper.getText(getDriver(),
                               "//*[@id=\"Assets_detailView_fieldValue_mdnfield\"]/span");
      log.info("Asset status : {}",status);   
      return status;
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching device id.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getDeviceId method.
  
  /** The method will search for the given accountno and select it in SIM change page. **/
  public void clickInventory() {
    try {
      
      UiElementActions.jsClick(getDriver(), inventory);
      log.info("Inventory icon clicked");
      
    } catch (Exception e) {
      
      final StringBuilder error = 
            new StringBuilder("Error in SIM change inventory click.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of clickInventory method.
  
  /** The method will get inventory number from SIM change page.**/
  public String getInventoryNumber() {
    try {
      
      final String invNo = ActionHelper.getText(getDriver(),
                             "//*[@id=\"Inventory1_detailView_fieldValue_inventoryno\"]/span");
      log.info("Inventory number : {}",invNo);   
      return invNo;
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching inventory number.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getInventoryNumber method.
  
  /** The method will get inventory status from SIM change page.**/
  public String getInventoryStatus() {
    try {
      
      final String invstatus = ActionHelper.getText(getDriver(),
                         "//*[@id='Inventory1_detailView_fieldValue_istatus']/span[1]/span");
      log.info("Inventory status : {}",invstatus);   
      return invstatus;
      
    } catch (Exception e) {
    
      final StringBuilder error = 
            new StringBuilder("Error in fetching inventory status.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of getInventoryStatus method.
} // SimChangePage.