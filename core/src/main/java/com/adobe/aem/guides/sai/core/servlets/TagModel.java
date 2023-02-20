package com.adobe.aem.guides.sai.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;


@Component(service=Servlet.class)
@SlingServletResourceTypes(resourceTypes="cq:Page",
							extensions="json")
public class TagModel extends SlingAllMethodsServlet {

	  JSONObject obj=new JSONObject();
      JSONArray array=new JSONArray();
      
	protected void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res)
	throws IOException{
		 ResourceResolver rr=req.getResourceResolver();
		
	     Tag resolve = rr.adaptTo(TagManager.class).resolve("/content/cq:tags/saitags/subtag");
	     Iterator<Tag> listChildren=resolve.listChildren();
	     while(listChildren.hasNext())
	    	 
	     {
	    	Tag next= listChildren.next();
	    	try {
	    		obj.put(next.getTitle(),next.getPath());
	    	}
	    	catch (JSONException e) {
	    		e.printStackTrace();
	    	}
	     }
		 array.put(obj);
		 PrintWriter out=res.getWriter();
		 out.print(array);
		
	}
}
	


