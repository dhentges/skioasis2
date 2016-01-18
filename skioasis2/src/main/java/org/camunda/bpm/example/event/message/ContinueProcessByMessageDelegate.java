/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.example.event.message;

import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

// THIS FILE IS NOT USED IN THE EXAMPLE!!!

public class ContinueProcessByMessageDelegate implements JavaDelegate {

  public void execute(DelegateExecution execution) throws Exception {
	  
	  // create process instance by triggering message
	  RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
	  try {
	    runtimeService.createMessageCorrelation("continueMessage")
	      .processInstanceId("<id-of-the-process-instance>")
	      .correlate();
	    // message was send to the process instance!
	  }
	  catch (MismatchingMessageCorrelationException e) {
	    // process instance not found or in wrong state
	  }  
  }
}
