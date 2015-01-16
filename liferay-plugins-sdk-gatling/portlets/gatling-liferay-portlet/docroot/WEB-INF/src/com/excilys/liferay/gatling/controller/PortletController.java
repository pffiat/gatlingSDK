package com.excilys.liferay.gatling.controller;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.Cookie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.excilys.liferay.gatling.GatlingPortlet;
import com.excilys.liferay.gatling.dto.LinkUsecaseRequestDTO;
import com.excilys.liferay.gatling.dto.PortletConfigDTO;
import com.excilys.liferay.gatling.dto.PortletConfigDTO.PortletConfigDTOBuilder;
import com.excilys.liferay.gatling.model.Record;
import com.excilys.liferay.gatling.mustache.ListScript;
import com.excilys.liferay.gatling.service.LinkUsecaseRequestLocalServiceUtil;
import com.excilys.liferay.gatling.service.RecordLocalServiceUtil;
import com.excilys.liferay.gatling.service.UrlRecordLocalServiceUtil;
import com.excilys.liferay.gatling.util.GatlingUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

@Controller(value="PortletController")
@RequestMapping("VIEW")
public class PortletController {

	private static final Log LOG = LogFactoryUtil.getLog(GatlingPortlet.class);

	@RequestMapping(params="render=renderPortlet")
	public String renderPortlet(final RenderRequest renderRequest, final RenderResponse renderResponse) throws SystemException, PortalException {

		if (ParamUtil.getString(renderRequest, "pagePortletId") == null) {
			throw new NullPointerException("portlet id is null");
		}	
		//DTO BUILDER
		final PortletConfigDTOBuilder builder = new PortletConfigDTO.PortletConfigDTOBuilder();

		final String portletId = ParamUtil.getString(renderRequest, "pagePortletId");
		final String portletName = PortletLocalServiceUtil.getPortletById(portletId).getDisplayName();
		final long groupId =  ParamUtil.getLong(renderRequest, "groupId");
		final long plId =  ParamUtil.getLong(renderRequest, "plId");
		final long requestId =  ParamUtil.getLong(renderRequest, "requestId");
		final long lineId = ParamUtil.getLong(renderRequest, "lineId");
		// Add to DTO
		builder.portletId(portletId).portletName(portletName).groupId(groupId).plId(plId).requestId(requestId).lineId(lineId);

		List<LinkUsecaseRequestDTO> arrayLinkUsecaseRequest = null;
		String listRecordsName = null;
		String[][] availableScript = null;

		List<Record> recordList = new ArrayList<Record>();
		//get record and Sample list in db if exists
		availableScript =  ListScript.getList(portletId);
		arrayLinkUsecaseRequest = GatlingUtil.fillArrayLinkUseCases(requestId);
		recordList = RecordLocalServiceUtil.findByPortletId(portletId);
		listRecordsName = GatlingUtil.createJSListOfRecordName(recordList);

		// Add to DTO
		builder.availableScript(availableScript).linkDTO(arrayLinkUsecaseRequest).listRecordNameJS(listRecordsName).listRecord(recordList);

		// Check state of recording
		final String state = renderRequest.getParameter("recordState");
		if(state != null) {
			renderRequest.setAttribute("tabs1", "record-usecase");
			if(state.equals("RECORD")) {
				builder.nextStateRecord("STOP");
			} else {
				builder.nextStateRecord("RECORD");
			}
		} else {
			builder.nextStateRecord("RECORD");
		}
		if(CookieKeys.getCookie(PortalUtil.getHttpServletRequest(renderRequest), "multipart-error") != null) {
			renderRequest.setAttribute("multipartError", true);
			final Cookie cookie = new Cookie("multipart-error", "true");
			cookie.setMaxAge(1);
			CookieKeys.addCookie(PortalUtil.getHttpServletRequest(renderRequest), PortalUtil.getHttpServletResponse(renderResponse), cookie);
			LOG.debug("Multipart form is not recorded !");
			renderRequest.setAttribute("tabs1", "record-usecase");
		}

		renderRequest.setAttribute("portletGatlingDTO", builder.build());
		return "popupPortlet/portletConfig";
	}

	@ActionMapping(params="action=deleteRecord")
	public void deleteRecordAction(final ActionRequest request, final ActionResponse response, final Model model) throws SystemException, PortalException {
		final long recordId = ParamUtil.getLong(request, "recordId");
		RecordLocalServiceUtil.deleteRecord(recordId);
		LinkUsecaseRequestLocalServiceUtil.removeByRecordId(recordId);
		UrlRecordLocalServiceUtil.removeByRecordId(recordId);
		//redirect
		//hack, only work this way ....
		response.setRenderParameter("p_p_state", "pop_up");
		response.setRenderParameter("tabs1", "config-record");
		PortalUtil.copyRequestParameters(request, response);
		response.setRenderParameter("render", "renderPortlet");
	}

	@ActionMapping(params="action=editRecord")
	public void editRecordAction(final ActionRequest request, final ActionResponse response, final Model model) throws SystemException, PortalException {

		final long recordId = ParamUtil.getLong(request, "recordId");
		final String name = ParamUtil.getString(request, "recordName");
		RecordLocalServiceUtil.update(recordId, name);

		//hack, only work this way ....
		response.setRenderParameter("p_p_state", "pop_up");
		PortalUtil.copyRequestParameters(request, response);
		response.setRenderParameter("render", "renderPortlet");
	}

	@ActionMapping(params="action=toggleRecord")
	public void toggleRecordAction(final ActionRequest request, final ActionResponse response, final Model model){
		final String recordState = ParamUtil.getString(request, "nextRecordState");
		response.setRenderParameter("recordState", recordState);
		//hack, only work this way ....
		response.setRenderParameter("p_p_state", "pop_up");
		PortalUtil.copyRequestParameters(request, response);
		response.setRenderParameter("render", "renderPortlet");

	}

	@ActionMapping(params="action=editPortletSample")
	public void editSampleAction(final ActionRequest request, final ActionResponse response, final Model model) throws SystemException, PortalException {
		LOG.debug("editPortletSample");
		final long requestId = ParamUtil.getLong(request, "requestId");
		final long[] linkUsecaseRequestIds = ParamUtil.getLongValues(request, "idLink");
		final double[] usecaseWeight = ParamUtil.getDoubleValues(request, "weightScenarioSample");
		final long[] recordIds = ParamUtil.getLongValues(request, "recordId");
		final boolean[] areSample = ParamUtil.getBooleanValues(request, "isSample");

		if(linkUsecaseRequestIds.length == usecaseWeight.length && usecaseWeight.length == areSample.length) {
			for(int i=0; i<linkUsecaseRequestIds.length;i++) {
				final long id = linkUsecaseRequestIds[i];
				final double weight = usecaseWeight[i];
				final long recordId = recordIds[i];
				final boolean isSample = areSample[i];
				LOG.debug(id+" "+requestId+" "+recordId+" "+weight+" "+isSample);
				LinkUsecaseRequestLocalServiceUtil.saveLinkUseCase(id, requestId, recordId, weight, isSample);
			}
		}
		//hack, only work this way ....
		response.setRenderParameter("p_p_state", "pop_up");
		PortalUtil.copyRequestParameters(request, response);
		response.setRenderParameter("render", "renderPortlet");
	}

	@ActionMapping(params="action=deleteUseCase")
	public void deleteUseCaseAction(final ActionRequest request, final ActionResponse response, final Model model) throws SystemException, PortalException{
		final long useCaseId = ParamUtil.getLong(request,"useCaseId");
		LinkUsecaseRequestLocalServiceUtil.deleteLinkUsecaseRequest(useCaseId);		

		//hack, only work this way ....
		response.setRenderParameter("p_p_state", "pop_up");
		PortalUtil.copyRequestParameters(request, response);
		//redirect
		response.setRenderParameter("render", "renderPortlet");
	}
}