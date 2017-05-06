
package aem.community.y.examples.codes.core.listeners;

import java.util.HashMap;
import java.util.Map;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.ReplicationAction;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(immediate=true,metatype=true)
@Service(value=EventHandler.class)
@Property(name="event.topics",value=ReplicationAction.EVENT_TOPIC)
public class ReplicationEventListner implements EventHandler{
	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	@Reference
	ResourceResolverFactory resourceResolverFactory;

	@Override
	public void handleEvent(Event event) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put(ResourceResolverFactory.SUBSERVICE,"dataWrite");
		ReplicationAction action=ReplicationAction.fromEvent(event);
		if(action.getType().equals(ReplicationActionType.ACTIVATE)){
		ResourceResolver resourceResolver=null;
		try {
			resourceResolver=resourceResolverFactory
					.getServiceResourceResolver(paramMap);
			PageManager pageManager=resourceResolver.adaptTo(PageManager.class);
			Page pg=pageManager.getContainingPage(action.getPath());
			if(pg!=null){
				logger.info("*******Activation of page{}",pg.getTitle());
			}
			
		} catch (LoginException e) {
			logger.debug("Exception inside HandleEvent");
		}
		finally{
			if(resourceResolver!=null&&resourceResolver.isLive()){
				resourceResolver.close();
			}
		}
	}

	}


}
