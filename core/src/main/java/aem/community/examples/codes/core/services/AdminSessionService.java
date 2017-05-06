package aem.community.examples.codes.core.services;

import org.apache.sling.api.resource.ResourceResolver;

public interface AdminSessionService {
	
	public abstract ResourceResolver getAdministrativeResourceResolver();

}
