package aem.community.examples.codes.core.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;

import aem.community.examples.codes.core.services.ReadService;

public class UserMapperExamToGetResource extends WCMUsePojo{

	Logger logger = LoggerFactory.getLogger(UserMapperExamToGetResource.class);
	protected String detail="Har Har Mahadev";
	@Override
	public void activate() throws Exception {
		ReadService readService=getSlingScriptHelper().getService(ReadService.class);
		detail=readService.getUserIdSysUser();
	}

	public String getDetails(){
		return detail;
	}
}
