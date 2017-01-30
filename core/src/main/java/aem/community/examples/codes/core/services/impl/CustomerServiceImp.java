package aem.community.examples.codes.core.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.QueryManager;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aem.community.examples.codes.core.services.CustomerService;
import aem.community.y.examples.codes.core.models.Customer;

@Component
@Service
public class CustomerServiceImp implements CustomerService {
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Reference
	ResourceResolverFactory resourceResolverFactory;
	
	ResourceResolver resourceResolver;
	
	private Session session;
	

    


	@Override
	public int injestCustData(String firstName, String lastName, String phone, String desc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCustomerData(String filter) {
		
		List<Customer> custList=new ArrayList<Customer>();	
		
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put(ResourceResolverFactory.SUBSERVICE, "customerService");
		
		try {
              resourceResolver=resourceResolverFactory.getServiceResourceResolver(paramMap);
              session=resourceResolver.adaptTo(Session.class);
              
              try {
				QueryManager queryManager=session.getWorkspace().getQueryManager();
				String SqlStmt="";
				if(filter.equals("All Customers")){
					SqlStmt ="Select * From [nt:unstructured]";
					
				}
				
			} catch (RepositoryException re) {
				log.error(re.getMessage());
			}
			
		} catch (LoginException e) {
			log.error(e.getMessage());
		}
		return null;
	}

}
