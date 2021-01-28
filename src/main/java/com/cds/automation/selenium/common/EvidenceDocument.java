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

package com.cds.automation.selenium.common;

import com.cds.automation.selenium.aspect.Loggable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * The component will provide methods to create header and footer of the evidence document.
 * 
 * @author  Venkata Nagaraju.
 * @version 1.0
*/
@Log4j2
@Component
public class EvidenceDocument {
  
  @Autowired
  private ResourceLoader resourceLoader;

  /** The method will create header for the evidence document. **/
  @Loggable
  public void createDocumentHeader(final XWPFDocument doc) 
                                                throws InvalidFormatException, IOException {
    
    final XWPFParagraph header = doc.createParagraph();
    final XWPFRun headerRun = header.createRun();
    
    final Resource resource = resourceLoader.getResource("classpath:evidences/header.png");
 
    final Path imagePath = Paths.get(resource.getFile().toURI());
    try (InputStream is = Files.newInputStream(imagePath)) {
      
      headerRun.addPicture(is,Document.PICTURE_TYPE_PNG, imagePath.getFileName().toString(),
                                                         Units.toEMU(550), Units.toEMU(50));
    } // End of inputstream.
  } // End of createDocumentHeader method.
  
  /** The method will create header for the evidence document. **/
  @Loggable
  public void saveEvidenceDocument(final XWPFDocument doc,final String testSuiteName) {
    
    final StringBuilder evidenceDoc = 
          new StringBuilder("evidences\\").append(testSuiteName).append("evidence").append(".docx");
    try (OutputStream outstream = Files.newOutputStream(Paths.get(evidenceDoc.toString()))) {
      
      doc.write(outstream);
      
    } catch (IOException e) {
      log.error("Error occurred while creating evidence.");
    }
  } // End of saveEvidenceDocument method.
} // End of EvidenceDocument.