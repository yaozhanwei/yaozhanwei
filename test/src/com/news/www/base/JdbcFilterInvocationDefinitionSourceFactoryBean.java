package com.news.www.base;

import java.sql.ResultSet; 
import java.sql.SQLException; 

import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Map; 

import javax.sql.DataSource; 

import org.springframework.beans.factory.FactoryBean; 

import org.springframework.jdbc.core.support.JdbcDaoSupport; 
import org.springframework.jdbc.object.MappingSqlQuery; 

import org.springframework.security.ConfigAttributeDefinition; 
import org.springframework.security.ConfigAttributeEditor; 
import org.springframework.security.intercept.web.DefaultFilterInvocationDefinitionSource; 
import org.springframework.security.intercept.web.FilterInvocationDefinitionSource; 
import org.springframework.security.intercept.web.RequestKey; 
import org.springframework.security.util.AntUrlPathMatcher; 
import org.springframework.security.util.UrlMatcher; 

public class JdbcFilterInvocationDefinitionSourceFactoryBean extends JdbcDaoSupport implements FactoryBean{

	private String resourceQuery;
	public Object getObject() throws Exception {
		 return new DefaultFilterInvocationDefinitionSource(this .getUrlMatcher(), this.buildRequestMap()); 
    }

	public Class getObjectType() {
		return FilterInvocationDefinitionSource.class ;
	}

	public boolean isSingleton() {
		return true;
	}
	
	protected List<Resource> findResources(){
		ResourceMapping resourceMapping = new ResourceMapping(getDataSource(),resourceQuery);
		return resourceMapping.execute();
	}
	
	protected LinkedHashMap<RequestKey,ConfigAttributeDefinition> buildRequestMap(){
		LinkedHashMap<RequestKey,ConfigAttributeDefinition> requestMap = null;
		requestMap = new LinkedHashMap<RequestKey,ConfigAttributeDefinition>();
		ConfigAttributeEditor editor = new ConfigAttributeEditor();
		List<Resource> resourceList = this.findResources();
		for(Resource resource:resourceList){
			RequestKey key = new RequestKey(resource.getUrl(),null);
			editor.setAsText(resource.getRole());
			requestMap.put(key, (ConfigAttributeDefinition)editor.getValue());
		}
		return requestMap;
	}
	
	protected UrlMatcher getUrlMatcher(){
		return new AntUrlPathMatcher();
	}
	
	public void setResourceQuery(String resourceQuery){
		this.resourceQuery = resourceQuery;
	}
	
	private class Resource{
		private String url;
		private String role;
		public Resource(String url,String role){
			this.url = url;
			this.role = role;
		}
	    public String getUrl(){
	    	return url;
	    }
	    public String getRole(){
	    	return role;
	    }
	}
	
	private class ResourceMapping extends MappingSqlQuery{
		protected ResourceMapping(DataSource dataSource,String resourceQuery){
			super(dataSource,resourceQuery);
			compile();
		}
		@Override
		protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
			String url = rs.getString(1);
			String role = rs.getString(2);
			Resource resource = new Resource(url,role);
			return resource;
		}
		
	}
	
}
