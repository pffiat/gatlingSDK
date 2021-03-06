package com.excilys.liferay.gatling.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@Controller(value = "PortletsController")  
@RequestMapping("VIEW")
public class PortletsController {
	
	private static final Log LOG = LogFactoryUtil.getLog(PortletsController.class);
	
	@RenderMapping(params="tabs1=portlets")
	public String handleRenderRequest(RenderRequest request,RenderResponse response,Model model){ 
		LOG.info("Portlets");
		return "library";  
	}  
}
