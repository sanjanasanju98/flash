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

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The util class provide methods for performing different date operations.
 * 
 * @author  Venkata Nagaraju.
 * @version 1.0
*/
public class DateTimeUtil {

  /** The method will return the current date in seconds. **/
  public int currentDateInSeconds() {

    final LocalDateTime now = LocalDateTime.now();
    final LocalTime localtime = now.toLocalTime();
    return localtime.toSecondOfDay();
  } // End of currentDateInSeconds method.
} // End of DateTimeUtil.