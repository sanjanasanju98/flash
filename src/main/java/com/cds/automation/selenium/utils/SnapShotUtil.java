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

package com.cds.automation.selenium.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * The util class provide methods for screen capture functionality.
 * 
 * @author  Darshan B S.
 * @version 1.0
 */
public final class SnapShotUtil {
  
  private SnapShotUtil() {
  } // End of private constructor.

  /** The method will take snapshot of the page.  **/
  public static void takeSnapShot(final WebDriver webdriver,final String fileWithPath) 
                                                                  throws IOException {

    // Convert web driver object to TakeScreenshot
    final TakesScreenshot scrShot = (TakesScreenshot) webdriver;

    // Call getScreenshotAs method to create image file
    final File srcFile = scrShot.getScreenshotAs(OutputType.FILE);

    final File destFile = new File(fileWithPath);

    FileUtils.copyFile(srcFile, destFile);
  } // End of takeSanpShot method.
  
  /**
   * The method will add screenshot to the evidence document.
   * 
   * @param: is    - File Image stream
   * @param: image - Image file.
   * @param: pfRun - XWPFRun.
  */
  public static void createEvidenceDocument(
                     final InputStream is,final File image,final XWPFRun pfRun) 
                                            throws InvalidFormatException,IOException {
    final BufferedImage bimg1 = ImageIO.read(image);
    final int width1 = bimg1.getWidth();
    final int height1 = bimg1.getHeight();

    final String imgFile1 = image.getName();
    final int imgFormat1 = Document.PICTURE_TYPE_PNG;

    pfRun.addPicture(is, imgFormat1, imgFile1,Units.toEMU(width1), Units.toEMU(height1));
    pfRun.addBreak();
  }
} // End of SnapShotUtil.