package aem.community.examples.codes.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import aem.community.examples.codes.core.services.AdminSessionService;

@Component(immediate=true)
@Service
public class AdminSessionServiceImpl implements AdminSessionService{

	@Reference
	ResourceResolverFactory resourceResolverFactory;
	
	public Session session;
	
	@Override
	public ResourceResolver getAdministrativeResourceResolver() {
		ResourceResolver resourceResolver=null;		
		try {
			Map<String,Object> paramMap=new HashMap<String,Object>();
			paramMap.put(ResourceResolverFactory.SUBSERVICE, "Admin-Session-SubService");
			resourceResolver=resourceResolverFactory.getServiceResourceResolver(paramMap);
		} catch (LoginException e) {

		}			
		return resourceResolver;
	}
	
	@Override
	public Session getAdminSession() {
		ResourceResolver resourceResolver=null;		
		try {
			Map<String,Object> paramMap=new HashMap<String,Object>();
			paramMap.put(ResourceResolverFactory.SUBSERVICE, "Admin-Session-SubService");
			resourceResolver=resourceResolverFactory.getServiceResourceResolver(paramMap);
			session=resourceResolver.adaptTo(Session.class);
		} catch (LoginException e) {

		}			
		return session;
	}
}
