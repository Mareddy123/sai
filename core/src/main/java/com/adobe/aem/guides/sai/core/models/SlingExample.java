package com.adobe.aem.guides.sai.core.models;


import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;



@Model(adaptables= SlingHttpServletRequest.class,defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class SlingExample {
	
	@Inject
	Resource resource;
	
	@SlingObject
	ResourceResolver resourceResolver;
	
	@Self
	SlingHttpServletRequest slingHttpServletRequest;
	
	@RequestAttribute(name = "rAttribute")
	private String reqAttribute;
	
	@ResourcePath(path="/content/sai/us/en")@Via("resource")
	Resource resourcePage;
	
	@Inject
	@Via("resource")
	@Default(values="en")
	private String pagetitle;
	
	@Inject
	@Via("resource")
	@Named("jcr:lastModifiedBy")
	String modifiedBy;
	
	@Inject
	@Via("resource")
	@Default(values="Teja")
	private String firstname;
	
	@ValueMapValue
	@Default(values="sai")
	private String lastname;
	
	@Inject
	@Via("resource")
	private boolean professor;
	
	
	public String getFirstName()
	
	{
		return firstname;
	}
	
	public String getLastName()
	{
		return lastname;
	}
	
	public boolean getIsProfessor()
	
	{
		return professor;
	}

	public String getPageTitle()
	
	{
		return pagetitle;
	}
	

	public String getRequestAttribute()
	{
		return reqAttribute;
	}
	
	public String getHomePageName()
	{
		return resourcePage.getName();
	}
	
	public String getLastModifiedBy()
	{
		return modifiedBy;
	}
	
	
}
