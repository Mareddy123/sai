package com.adobe.aem.guides.sai.core.servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

	@Component(service=Servlet.class,
			property= {
					"sling.servlet.paths=/sai/components/page",
					"sling.servlet.selectors=recent",
					"sling.servlet.methods=GET",
					"sling.servlet.methods=POST",
					"sling.servlet.selectors=recent1",
					"sling.servlet.extensions=txt",
					"sling.servlet.extensions=json" 
					
}
			)

public class ServletExample extends SlingAllMethodsServlet {

		protected void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res)throws IOException
		
		{
			JsonObjectBuilder obj = Json.createObjectBuilder();
			obj.add("first Name:", "Mareddy");
			obj.add("Last Name:", "Sai Teja Reddy");
			obj.add("Aim:", "Dont Belive Any One Expect u");
			PrintWriter out =res.getWriter();
			out.write(obj.build().toString());
		}
		}
		

