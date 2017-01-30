package aem.community.examples.codes.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

import aem.community.examples.codes.core.services.ReadService;

@Component(immediate=true)
@Service
public class ReadServiceImpl implements ReadService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Reference
	private ResourceResolverFactory resourceResolverFactory;


	@Override
	public String getUserIdSysUser() {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put(ResourceResolverFactory.SUBSERVICE,"readService");
		ResourceResolver resourceResolver=null;
		String message=null;
		try {
			resourceResolver=resourceResolverFactory.getServiceResourceResolver(paramMap);
			log.info("UserId:-"+resourceResolver.getUserID());
			//Resource res=resourceResolver.getResource("/content/geometrixx");
            Session session=resourceResolver.adaptTo(Session.class);
            try {
				Node rootNode=session.getRootNode();
				Node adobe=rootNode.addNode("Adobe");
				Node day=adobe.addNode("day");
				day.setProperty("message","Adobe Experience Manager ,Setting property using JCR Node api");
				session.save();	
				
				Node node=session.getNode("/Adobe/cq");
				message=node.getProperty("message").toString();
				log.info("Message content is :-"+message);
				session.logout();
				
			} catch (RepositoryException e) {
				 log.error(e.getMessage());
			}
		} catch (LoginException e) {
			 log.error(e.getMessage());
		}
		return message;	

	}

}
