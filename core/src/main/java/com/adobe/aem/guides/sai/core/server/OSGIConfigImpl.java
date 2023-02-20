package com.adobe.aem.guides.sai.core.server;

	import org.osgi.service.component.annotations.Activate;
	import org.osgi.service.component.annotations.Component;
	import org.osgi.service.metatype.annotations.AttributeDefinition;
	import org.osgi.service.metatype.annotations.AttributeType;
	import org.osgi.service.metatype.annotations.Designate;
	import org.osgi.service.metatype.annotations.ObjectClassDefinition;
	import org.osgi.service.metatype.annotations.Option;


	@Component(service=OSGIConfig.class,immediate=true)
	@Designate(ocd=OSGIConfigImpl.ServiceConfig.class)

	public class OSGIConfigImpl implements OSGIConfig {
	
		@ObjectClassDefinition(name="Sai Teja Reddy-OSGI Configuration",
				description="OSGI Configuration demo.")
		public @interface ServiceConfig {
			
			@AttributeDefinition(
					name="Service Name",
					description="Enter service name",
					type=AttributeType.STRING)
			public String serviceName() default "mareddy project";
			
			@AttributeDefinition(name="Service Count",
					description="Add Service Count",
					type=AttributeType.INTEGER)
			public int getServiceCount() default 10;
			
			@AttributeDefinition(name="Live Data",
					description="Check this to get live  data",
					type=AttributeType.BOOLEAN)
			public boolean getLiveData() default false;
			
			@AttributeDefinition(name="Countries",
					description="Add Countries locales for which you want to run this service",
					type=AttributeType.STRING)
			public String[] getCounries() default {"in","en","usa"};
			
			@AttributeDefinition(name="Run Modes",
					description="select the Run Modes",
					type=AttributeType.STRING,
					options= { @Option(label="Author",value="author"),
							@Option(label="Publish",value="publish"),
							@Option(label="Both",value="both")
					})
			public String getRunMode() default "both";
					
		}
		private String serviceName;
		private int  serviceCount;
		private boolean  liveData;
		private String[] Countries;
		private String runModes;
		
		@Activate
		protected void activate(ServiceConfig serviceConfig)
		{
			serviceName=serviceConfig.serviceName();
			serviceCount=serviceConfig.getServiceCount();
			liveData=serviceConfig.getLiveData();
			Countries=serviceConfig.getCounries();
			runModes=serviceConfig.getRunMode();
		}
		
		@Override
		public String getServiceName() {
			return serviceName;
			
		}
		
		@Override
		public int getServiceCount() {
			return serviceCount;
			
		}
	  
	     
	     @Override
	     public String  getRunModes() {
	    	 return runModes;
	    	 
	     }

		@Override
		public boolean isLiveData() {
			return liveData;
		}

		@Override
		public String[] getCountries() {
			return Countries;
		}

		}