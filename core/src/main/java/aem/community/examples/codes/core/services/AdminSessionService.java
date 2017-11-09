package aem.community.examples.codes.core.services;

import org.apache.sling.api.resource.ResourceResolver;
import javax.jcr.Session;

public interface AdminSessionService {
	
	public abstract ResourceResolver getAdministrativeResourceResolver();

	public abstract Session getAdminSession();

}
