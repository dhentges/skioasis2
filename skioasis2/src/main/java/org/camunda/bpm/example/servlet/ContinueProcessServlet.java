package org.camunda.bpm.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;

public class ContinueProcessServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2266688198017649486L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException{

		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		
		String id = request.getParameter("id");
		if (null == id) {
			out.println("<h2>Error</h2>");
			out.println("<p>Parameter id missing!</p>");
		}
		else {
			try {
				runtimeService.createMessageCorrelation("continueMessage")
			      	.processInstanceId(id)
			      	.correlate();
				out.println("<h1>Message delivered to process</h1>");
				out.println("<p>ID: " + id + "</p>");
			}
			catch (MismatchingMessageCorrelationException e) {
				out.println("<h2>Error</h2>");
				out.println("<p>No correlating process instance found waiting for a message.</p>");
				out.println("<p>ID: " + id + "</p>");
			}
		}
		
		out.println("</body>");
		out.println("</html>");	
	}
}
