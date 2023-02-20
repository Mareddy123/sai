package com.adobe.aem.guides.sai.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service=Servlet.class,immediate=true)
@SlingServletResourceTypes(resourceTypes="sai/components/page",
							methods= {HttpConstants.METHOD_GET,HttpConstants.METHOD_POST},
							selectors="recent",
							extensions="json")

public class DSServletExample extends SlingAllMethodsServlet{

	protected void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res)
	throws IOException
	{
		String articlePaths = req.getParameter("rootpage");
		if(articlePaths==null)
		{
			articlePaths="/content/sai/us/en";
		}
			
			
				
				PageManager pm=req.getResourceResolver().adaptTo(PageManager.class);
				Page page =pm.getPage(articlePaths);
				JsonArrayBuilder jab =Json.createArrayBuilder();
				Iterator<Page> listChildren = page.listChildren();
				while(listChildren.hasNext())
				{
					Page next =listChildren.next();
					JsonObjectBuilder job = Json.createObjectBuilder();
					job.add("Path",next.getPath());
					job.add("Title",next.getTitle());
					jab.add(job);
				}
				
			
			PrintWriter out = res.getWriter();
			out.write(jab.build().toString());
	}

	protected void doPost(SlingHttpServletRequest req,SlingHttpServletResponse res) 
	{
		
	}
	
	}
