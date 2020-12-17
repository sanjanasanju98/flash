/**
 * Copyright (C) 2020 Covalensedigital 
 * <br />
 * Licensed under the CDS,Version 1.0,you may not use this file except in compliance with
 * the license. You may obtain a copy of the License at 
 * <br />
 * http://www.covalensedigital.com/
 * <br />
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS,WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,either express or
 * implied.See the License for the specific language governing permissions and limitations.
*/

package com.cds.automation.selenium.exceptions;

/**
 * The custom exception will handle CRM GUI service invocation related exceptions.
 * 
 * @author  Venkata Nagaraju.
 * @version 1.0.
*/
public class CrmUiException extends RuntimeException {
  
  private static final long serialVersionUID = -3603767312540143041L;

  public CrmUiException(final String message) {
    super(message);
  } // End of constructor.

  public CrmUiException(final String message,final Exception e) {
    super(message,e);
  } // End of constructor overloading.
} // End of CrmUiException.