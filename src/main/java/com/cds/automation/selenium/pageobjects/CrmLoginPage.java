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
import com.cds.automation.selenium.common.WebPageActionsUtil;
import com.cds.automation.selenium.exceptions.CrmUiException;
import com.cds.automation.selenium.pojos.DocumentContent;
import com.cds.automation.selenium.utils.SnapShotUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * The class will provide various methods, which perform basic operations of CRM Login page. These
 * methods will be used by different test cases for performing operations on CRM Login UI elements.
 * 
 * @author  Darshan B S
 * @version 1.0
*/
@Log4j2
@Component
public class CrmLoginPage extends BaseClassHelper {

  @FindBy(id = "username")
  private WebElement userName;

  @FindBy(id = "password")
  private WebElement passWord;

  @FindBy(xpath = "//div[@id='forgotPassword']//button")
  private WebElement loginSubmit;

  @FindBy(xpath = "//span[@class='fa fa-user']")
  private WebElement logoutIcon;

  @FindBy(xpath = "//a[@id='menubar_item_right_LBL_SIGN_OUT']")
  private WebElement signoutBtn;

  public void initPageFactory() {
    PageFactory.initElements(getDriver(), this);
  } // End of constructor.

  /** The method will perform CRM Login actions with selenium web driver. */
  public void login(final String usrName,final String passwd,final XWPFDocument document) {

    final List<DocumentContent> contentList = new ArrayList<>();
    try {
      
      final XWPFParagraph para = document.createParagraph();
      final XWPFRun title = para.createRun();
      title.setText("CRM Login page.");
      title.setColor("a31402");
      title.setFontSize(13);
      title.addBreak();
      title.setBold(true);
      title.addBreak();
      
      ActionHelper.fluentWait(getDriver(), userName, 120);
      WebPageActionsUtil.type(userName, usrName);
      log.info("UserName Entered = {}", userName.getAttribute("value"));
      
      ActionHelper.fluentWait(getDriver(), passWord, 120);
      WebPageActionsUtil.type(passWord, passwd);
      log.info("PassWord Entered = {}", passWord.getAttribute("value"));
      
      final WebElement loginButton = 
            getDriver().findElement(By.xpath("//*[@id=\"forgotPassword\"]/button"));
      loginButton.submit();
      
      final DocumentContent content = 
            DocumentContent.builder().contentType("string")
              .content("CRM Login page submit button clicked.").build();
      contentList.add(content);
      
      final XWPFRun subtitle = para.createRun();
      subtitle.setText("CRM Login page submit button clicked. Venkata Nagaraju.");
      log.info("Submit button of login page clicked");
      
      final Resource resource = new ClassPathResource("classpath:resources/login.png");
      SnapShotUtil.takeSnapShot(getDriver(),resource.getFilename());
      
      final String dateName = 
            new SimpleDateFormat("yyyyMMddhhmmss",Locale.ENGLISH).format(new Date());
      final StringBuilder loginScreen = 
            new StringBuilder("LoginPage").append("_").append(dateName);
      ActionHelper.screenShot(getDriver(), loginScreen.toString());
      
      final String actualUrl = getDriver().getCurrentUrl();

      if (actualUrl.endsWith("&error=login")) {
        
        final String invalid = getDriver().findElement(
                                     By.xpath("//div[@class='alert alert-danger']")).getText();
        final StringBuilder error = new StringBuilder("Invalid credentials provided.")
                                        .append(invalid);
        throw new CrmUiException(error.toString());
      }
    } catch (Exception e) {
      
      final StringBuilder error = new StringBuilder("Error during login.").append(e.getMessage());
      throw new CrmUiException(error.toString(),e);
    }
  } // End of loginToCrm method.

  /** Method to Enter PassWord in Login page. **/
  public void enterLoginPassword(final String usrPass) {
    
    WebPageActionsUtil.type(passWord, usrPass);
    log.info("PassWord Entered = {}", usrPass);
  } // End of enterLoginPassword method.

  /** Method to click login button of CRM website. **/
  public void clickCrmLogin() {
    
    try {
      Thread.sleep(2000);
      WebPageActionsUtil.click(getDriver(), loginSubmit);
      log.info("Submit button clicked");
      
    } catch (Exception e) {
      log.error("Error occurred while login. {}",e.getMessage());
    }
  } // End of clickCrmLogin method.

  /** Method to click logout button of CRM website. **/
  public void logoutClick() {
    
    try {
      Thread.sleep(2000);
      WebPageActionsUtil.click(getDriver(), logoutIcon);
      Thread.sleep(2000);
      WebPageActionsUtil.click(getDriver(), signoutBtn);
      log.info("Signout Button clicked");
      
    } catch (Exception e) {
      log.error("Error occurred while login. {}",e.getMessage());
    }
  } // End of logoutClick method.

  /** The method will handle alerts of CRM login page. **/
  public void acceptAlert() {
    
    if (ActionHelper.isAlertPresent(getDriver())) {
      try {
        WebPageActionsUtil.alert(getDriver());
      } catch (Exception e) {
        log.error("Error occurred while accepting alert of login page : {}",e.getMessage());
      }
    } // End of alert check.
  } // End of alertAccept method.
} // End of CrmLoginPage, which provide basic actions of CRM login.