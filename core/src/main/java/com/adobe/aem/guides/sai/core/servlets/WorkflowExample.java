package com.adobe.aem.guides.sai.core.servlets;

import javax.servlet.Servlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;

@Component(service=Servlet.class)
@SlingServletPaths(value= {"/bin/executionworkflow"})
public class WorkflowExample extends SlingSafeMethodsServlet {

	protected void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res, WorkflowData newWorkflowData)
	{ 
		try {
		ResourceResolver resourceResolver=req.getResourceResolver();
	
		String payload =req.getRequestParameter("page").toString();
		if(StringUtils.isNotBlank(payload))
		{
			WorkflowSession workflowSession = resourceResolver.adaptTo(WorkflowSession.class);
			WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH",payload);
			WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/saipageversioncreation");
			workflowSession.startWorkflow(workflowModel, newWorkflowData);	
		}
		}
	catch(Exception e)
	{
		e.printStackTrace();
	}


	}
}
