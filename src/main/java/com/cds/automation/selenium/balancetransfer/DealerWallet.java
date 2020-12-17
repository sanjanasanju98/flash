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

package com.cds.automation.selenium.balancetransfer;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The POJO captures wallet information of dealer as part of balance transfer.
 * 
 * @author  Darshan B S.
 * @version 1.0.
*/
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DealerWallet implements Serializable {
  
  private static final long serialVersionUID = 3373186385519930241L;

  private long currentAmount; 
  
  private long transferAmount; 
} // End of DealerWallet.