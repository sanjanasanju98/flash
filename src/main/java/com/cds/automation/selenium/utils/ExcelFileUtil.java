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

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * The util class provide methods for handling excel files.
 * 
 * @author  Tanveer Khan.
 * @version 1.0
*/
@Log4j2
public class ExcelFileUtil {
  
  /** The method will add data to excel. **/
  public void addDatatoExcel(final String testcaseName,final String accountNumber,
                             final String userId,final String accountcategory,
                             final String balance,final String status) {
    try {
      final InputStream fis = Files.newInputStream(Paths.get(".//Configuration//crmtestdata.xlsx"));
      try (Workbook workbook = new XSSFWorkbook(fis)) {

        final Sheet sheet = workbook.getSheetAt(2);
        final int lastRowNumber = sheet.getLastRowNum();

        for (int j = 1; j <= lastRowNumber; j++) {
          
          final Row row = sheet.getRow(j);
          final Cell cell1 = row.getCell(0);
          final String colValue1 = cell1.getStringCellValue().trim();
          if (colValue1.equalsIgnoreCase(testcaseName)) {
            row.createCell(1).setCellValue(accountNumber);
            row.createCell(2).setCellValue(userId);
            row.createCell(3).setCellValue(accountcategory);
            row.createCell(4).setCellValue(balance);
            row.createCell(5).setCellValue(status);
            fis.close();
          }
        } // End of looping through excel rows.
      } // End of workbook try with resources.
    } catch (Exception e) {
      log.error("Error occurred while working with excel. {}",e.getMessage());
    }
  } // End of addDatatoExcel method.

  /** The method will save test data result to excel. **/
  public void saveTestDataResulttoExcel() {
    try {
      try (OutputStream outFile = 
                        Files.newOutputStream(Paths.get("./test-output/testdataresults.xlsx"))) {
        final InputStream fis = 
                          Files.newInputStream(Paths.get(".//Configuration//crmtestdata.xlsx"));
        try (Workbook workbook = new XSSFWorkbook(fis)) {
          workbook.write(outFile);
        }
      }
    } catch (Exception e) {
      log.error("Error occurred while saving data to excel. {}",e.getMessage());
    }
  } // End of saveTestDataResulttoExcel method.
} // End of ExcelFileUtil.