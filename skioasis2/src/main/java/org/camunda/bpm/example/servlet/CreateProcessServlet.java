package org.camunda.bpm.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class CreateProcessServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2266688198017649486L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException{
		
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		PrintWriter out = response.getWriter();
		
		String info = request.getParameter("info");
		ProcessInstance processInstance;
		if (null == info) { 
			processInstance = runtimeService.startProcessInstanceByMessage("instantiationMessage");
		}
		else {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("info", info);
			processInstance = runtimeService.startProcessInstanceByMessage("instantiationMessage", map);
		}
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Process Instance Started</h1>");
		out.println("<p>ID: " + processInstance.getId() + "</p>");
		out.println("<p>Info: " + info + "</p>");
		out.println("</body>");
		out.println("</html>");	
	}
}
