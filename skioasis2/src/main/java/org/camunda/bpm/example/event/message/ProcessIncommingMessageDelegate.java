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

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.HttpClients;

public class ProcessIncommingMessageDelegate implements JavaDelegate {

  public void execute(DelegateExecution execution) throws Exception {
	  
	  // build HTTP request with all variables as parameters
	  HttpClient client = HttpClients.createDefault();
	  RequestBuilder requestBuilder = RequestBuilder.get()
			  .setUri("http://requestb.in/1m6qkpg1")
			  .addParameter("id", execution.getProcessInstanceId());
	  for (String variable : execution.getVariableNames()) {
		  requestBuilder.addParameter(variable, String.valueOf(execution.getVariable(variable)));
	  }
	  
	  // execute request
	  HttpUriRequest request = requestBuilder.build();
	  HttpResponse response = client.execute(request);
	  
	  // log debug information
	  System.out.println(request.getURI());
	  System.out.println(response.getStatusLine());
	  
  }
}
