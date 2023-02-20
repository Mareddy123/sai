package com.adobe.aem.guides.sai.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;


@Component(service=Servlet.class,immediate=true)
@SlingServletResourceTypes(resourceTypes="sai/components/page",
							methods= {HttpConstants.METHOD_GET,HttpConstants.METHOD_POST},
							selectors="recent",
							extensions="json")
public class DServlet extends SlingAllMethodsServlet {
	@Reference
	QueryBuilder queryBuilder;

	protected void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res)
			throws IOException{
		Map<String,String> predicate = new HashMap<String,String>();
		predicate.put("type", "cq:page");
		predicate.put("path", "/content/sai/us/en");
		predicate.put("orderby", "@jcr:content/cq:lastModified");
		predicate.put("orderby.sort", "desc");
		predicate.put("p.limit", "-1");
		
		
		Query createQuery= queryBuilder.createQuery(PredicateGroup.create(predicate),req.getResourceResolver().adaptTo(Session.class));
		SearchResult result = createQuery.getResult();
		List <Hit> hits =  result.getHits();
		JsonArrayBuilder createArrayBuilder = Json.createArrayBuilder();
		
		for(Hit hit:hits)
		{
			JsonObjectBuilder createObjectBuilder = Json.createObjectBuilder();
			try {
				
				Resource resource = hit.getResource();
				Resource content = resource.getResourceResolver().getResource(resource.getPath()+"/jcr:content");
				createObjectBuilder.add("Title",content.getValueMap().get("jcr:title",String.class));
				createObjectBuilder.add("Path",content.getPath());
				
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
			PrintWriter out=res.getWriter();
			out.write(createArrayBuilder.build().toString());
		}
	
	}
	protected void doPost(SlingHttpServletRequest req,SlingHttpServletResponse res)
			throws IOException{
		Map<String,String> predicate = new HashMap<String,String>();
		predicate.put("type", "cq:page");
		predicate.put("path", "/content/sai/us/en");
		predicate.put("orderby", "@jcr:content/cq:lastModified");
		predicate.put("orderby.sort", "desc");
		predicate.put("p.limit", "-1");
		
		
		Query createQuery= queryBuilder.createQuery(PredicateGroup.create(predicate),req.getResourceResolver().adaptTo(Session.class));
		SearchResult result = createQuery.getResult();
		List <Hit> hits =  result.getHits();
		JsonArrayBuilder createArrayBuilder = Json.createArrayBuilder();
		
		for(Hit hit:hits)
		{
			JsonObjectBuilder createObjectBuilder = Json.createObjectBuilder();
			try {
				
				Resource resource = hit.getResource();
				Resource content = resource.getResourceResolver().getResource(resource.getPath()+"/jcr:content");
				createObjectBuilder.add("Title",content.getValueMap().get("jcr:title",String.class));
				createObjectBuilder.add("Path",content.getPath());
				
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
			PrintWriter out=res.getWriter();
			out.write(createArrayBuilder.build().toString());
		}
	
	}
}



	
	
