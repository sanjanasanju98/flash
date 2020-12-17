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

package com.cds.automation.selenium.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspect to log execution time of methods which have annotation @ExecutionTime. The method capture
 * start of method execution in nanoseconds and capture end of execution time & find the difference
 * between both to find the time spent for execution in seconds.
 * 
 * @author  Venkata Nagaraju.
 * @version 1.0.
*/
@Aspect
@Slf4j
@Component
public class ExecutionTimeAspect {

  /** Around advice to log execution / processing time of a method. **/
  @Around("@annotation(ExecutionTime)")
  public Object logProcessingTime(final ProceedingJoinPoint joinPoint) throws Throwable {
    
    final long startTime = System.nanoTime();
    
    final Object proceed = joinPoint.proceed(); // method execution part.
    
    final long processTime = System.nanoTime() - startTime;
    log.info("Total execution time : {} seconfs ",(double) processTime / 1_000_000_000.0);
    return proceed;
  } // End of execution time logging.
} // End of ExecutionTimeAspect.