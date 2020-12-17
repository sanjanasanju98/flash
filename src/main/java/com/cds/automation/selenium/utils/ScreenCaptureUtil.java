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

import com.cds.automation.selenium.common.BaseClassHelper;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * The util class provide methods for capturing webpage screens for selenium test cases.
 * 
 * @author  Darshan B S.
 * @version 1.0
*/
@Log4j2
public class ScreenCaptureUtil extends BaseClassHelper {

  /** The method will capture screen shot in full window mode. */
  public void fullPageScreenShot(final String filename) {

    final Screenshot screenshot = 
                     new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500))
                                .takeScreenshot(getDriver());
    try {
      final StringBuilder destination = 
                          new StringBuilder("E:/SelfCare/ScreenShots/").append(filename);
      ImageIO.write(screenshot.getImage(), "PNG", new File(destination.toString()));
    } catch (IOException e) {
      log.error("Error occurred while cpaturing screenshot. {}",e.getMessage());
    }
  } // End of fullPageScreenShot method.

  /** The method will provide screen capture functionality to the selenium test cases. */
  public void captureScreenShot(final String filename,final WebElement element) {
    
    final Screenshot screenshot = 
                     new AShot().coordsProvider(new WebDriverCoordsProvider())
                                .takeScreenshot(getDriver(), element);
    try {
      final StringBuilder destination = 
                          new StringBuilder("E:/SelfCare/ScreenShots/").append(filename);
      ImageIO.write(screenshot.getImage(), "PNG", new File(destination.toString()));
      
    } catch (IOException e) {
      log.error("Error occurred while cpaturing screenshot. {}",e.getMessage());
    }
  } // End of captureScreenShot method.
} // End of ScreenCaptureUtil, which capture screens in full window mode.