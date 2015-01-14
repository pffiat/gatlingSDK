/**
 * Copyright 2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 */
package com.excilys.liferay.gatling;


import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ValidatorException;
import javax.servlet.http.Cookie;

import com.excilys.liferay.gatling.dto.LinkUsecaseRequestDTO;
import com.excilys.liferay.gatling.dto.PortletConfigDTO;
import com.excilys.liferay.gatling.dto.PortletConfigDTO.PortletConfigDTOBuilder;
import com.excilys.liferay.gatling.dto.RequestDTO;
import com.excilys.liferay.gatling.exception.EmptySimulation;
import com.excilys.liferay.gatling.model.Record;
import com.excilys.liferay.gatling.model.Request;
import com.excilys.liferay.gatling.model.Scenario;
import com.excilys.liferay.gatling.model.Simulation;
import com.excilys.liferay.gatling.model.UrlRecord;
import com.excilys.liferay.gatling.mustache.ListScript;
import com.excilys.liferay.gatling.mustache.ScriptGeneratorGatling;
import com.excilys.liferay.gatling.service.LinkUsecaseRequestLocalServiceUtil;
import com.excilys.liferay.gatling.service.RecordLocalServiceUtil;
import com.excilys.liferay.gatling.service.RequestLocalServiceUtil;
import com.excilys.liferay.gatling.service.ScenarioLocalServiceUtil;
import com.excilys.liferay.gatling.service.SimulationLocalServiceUtil;
import com.excilys.liferay.gatling.service.UrlRecordLocalServiceUtil;
import com.excilys.liferay.gatling.util.DisplayItemDTOUtil;
import com.excilys.liferay.gatling.util.GatlingUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;


/**
 * Portlet implementation class GatlingPortlet.
 */
public class GatlingPortlet extends MVCPortlet {

	/**
	 * logging.
	 */
	private static final Log LOG = LogFactoryUtil.getLog(GatlingPortlet.class);

	/**
	 * pages of portlet.
	 */
	protected String jspListSimulation, jspEditSimulation, jspEditScenario, jspFormFirstScenario, jspHelp, jspEditPortlet, jspEditRecord,jspWebService;

	/**
	 * get all name page
	 * @throws PortletException
	 */
	@Override
	public void init() throws PortletException {
		jspListSimulation = getInitParameter("list-simulation-jsp");
		jspEditSimulation = getInitParameter("edit-simulation-jsp");
		jspEditScenario = getInitParameter("edit-scenario-jsp");
		jspFormFirstScenario = getInitParameter("form-first-scenario-jsp");
		jspHelp = getInitParameter("help-jsp");
		jspEditPortlet = getInitParameter("portletConfig-jsp");
		jspEditRecord=getInitParameter("editRecord-jsp");
		jspWebService=getInitParameter("webService-jsp");
		super.init();
	}

	/**
	 * Adds a new Simulation to the database.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addSimulation(final ActionRequest request, final ActionResponse response) throws Exception {
		final Simulation simulation = SimulationLocalServiceUtil.addSimulationFromRequest(request);

		if (simulation != null) {
			response.setRenderParameter("simulationId", Long.toString(simulation.getSimulation_id()));
			// If new simulation the redirect to add First scenario page else edit simulation page
			LOG.debug("Simulation added");
			response.setRenderParameter("page", jspEditSimulation); 
		} else {
			LOG.debug("Simulation fails to add");
			response.setRenderParameter("page", jspListSimulation);
		}
	}

	/**
	 * edit simulation method
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void editSimulation(final ActionRequest request, final ActionResponse response) throws Exception {
		final long simulationId = ParamUtil.getLong(request, "simulationId");
		LOG.debug("edit Simulation with id : " + simulationId);
		final Simulation simulation = SimulationLocalServiceUtil.getSimulation(simulationId);
		simulation.setName(ParamUtil.getString(request, "simulationName"));
		SimulationLocalServiceUtil.updateSimulation(simulation);
		response.setRenderParameter("simulationId", Long.toString(simulationId));
		response.setRenderParameter("page", jspEditSimulation);
	}

	/**
	 * Edit Simulation's feeder
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void editFeeder(final ActionRequest request, final ActionResponse response) throws Exception {
		final long simulationId = ParamUtil.getLong(request, "simulationId");
		LOG.debug("Edit Feeder of simulationId "+simulationId);
		final Simulation simulation = SimulationLocalServiceUtil.getSimulation(simulationId);
		// isAFile
		final String radioOption = ParamUtil.getString(request, "option");
		final boolean isAFile = radioOption.equals("content2");
		simulation.setIsFeederAFile(isAFile);
		// content
		String content = "manualUsers";
		if(isAFile) {
			content="fileUsers";
		}
		simulation.setFeederContent(ParamUtil.getString(request,content));
		SimulationLocalServiceUtil.updateSimulation(simulation);
		response.setRenderParameter("simulationId", Long.toString(simulationId));
		response.setRenderParameter("page", jspEditSimulation);
	}

	/**
	 * remove simulation method
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void removeSimulation(final ActionRequest request, final ActionResponse response) throws Exception {
		final long simulationId = ParamUtil.getLong(request, "simulationId");
		if (LOG.isDebugEnabled()) {
			LOG.debug("remove Simulation with id : " + simulationId);
		}

		SimulationLocalServiceUtil.removeSimulationCascade(simulationId);
		response.setRenderParameter("page", jspListSimulation);
	}


	/**
	 * Add a new Scenario to the database.
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addScenario(final ActionRequest request, final ActionResponse response) throws Exception {
		final Scenario scenario = ScenarioLocalServiceUtil.addScenarioFromRequest(request);
		final String first = ParamUtil.getString(request, "first");
		if (scenario != null) {
			// redirect to editScenario
			response.setRenderParameter("scenarioId", Long.toString(scenario.getScenario_id()));
			response.setRenderParameter("page", jspEditScenario);
		} else {
			response.setRenderParameter("simulationId", ParamUtil.getString(request, "simulationId"));
			response.setRenderParameter("page", first.equals("") ? jspEditSimulation : jspFormFirstScenario);
			response.setRenderParameter("page", jspEditSimulation);
		}
	}

	/**
	 * edit scenario.
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws PortalException
	 */
	public void editScenario(final ActionRequest request, final ActionResponse response) throws SystemException, PortalException {
		LOG.debug("edit scenario controller");
		final Scenario scenario = ScenarioLocalServiceUtil.editScenarioFromRequest(request);
		response.setRenderParameter("page", scenario != null ? jspEditSimulation : jspListSimulation);
		if (scenario != null) {
			response.setRenderParameter("simulationId", Long.toString(scenario.getSimulation_id()));
		}
	}

	/**
	 * Remove scenario from database.
	 * @param request
	 * @param response
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void removeScenario(final ActionRequest request, final ActionResponse response)
			throws PortalException, SystemException {
		final long scenarioId = ParamUtil.getLong(request, "scenarioId");
		final long simulationId = ParamUtil.getLong(request, "simulationId");
		if (LOG.isDebugEnabled()){
			LOG.debug("remove Scenario with id : " + scenarioId);
		}
		// cascade delete
		ScenarioLocalServiceUtil.removeByIdCascade(scenarioId);
		response.setRenderParameter("simulationId", Long.toString(simulationId));
		response.setRenderParameter("page", jspEditSimulation);
	}


	/**
	 * Remove request from database.
	 * @param request
	 * @param response
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void removeRequest(final ActionRequest request, final ActionResponse response) throws PortalException, SystemException {
		final long requestId = Long.parseLong(request.getParameter("requestId"));
		RequestLocalServiceUtil.deleteRequest(requestId);
		LOG.debug("request deleted successfully ");
		response.setRenderParameter("scenarioId", request.getParameter("scenarioId"));
		response.setRenderParameter("page", jspEditScenario);
	}

	/**
	 * edit portlet sample.
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws PortalException
	 */
	public void editPortletSample(final ActionRequest request, final ActionResponse response) throws SystemException, PortalException {
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

		response.setRenderParameter("page", jspEditPortlet);
		//hack, only work this way ....
		response.setRenderParameter("p_p_state", "pop_up");
		PortalUtil.copyRequestParameters(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws PortalException
	 */
	public void editRecord(final ActionRequest request, final ActionResponse response) throws SystemException, PortalException {

		final long recordId = ParamUtil.getLong(request, "recordId");
		final String name = ParamUtil.getString(request, "recordName");
		RecordLocalServiceUtil.update(recordId, name);

		response.setRenderParameter("page", jspEditRecord);
		//hack, only work this way ....
		response.setRenderParameter("p_p_state", "pop_up");
		PortalUtil.copyRequestParameters(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws PortalException
	 */
	public void removeLinkUseCase(final ActionRequest request, final ActionResponse response) throws SystemException, PortalException{
		final long useCaseId = ParamUtil.getLong(request,"useCaseId");
		LinkUsecaseRequestLocalServiceUtil.deleteLinkUsecaseRequest(useCaseId);		

		//redirect
		response.setRenderParameter("page", jspEditPortlet);
		//hack, only work this way ....
		response.setRenderParameter("p_p_state", "pop_up");
		PortalUtil.copyRequestParameters(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws PortalException
	 */
	public void removeRecord(final ActionRequest request, final ActionResponse response) throws SystemException, PortalException{

		final long recordId = ParamUtil.getLong(request, "recordId");
		RecordLocalServiceUtil.deleteRecord(recordId);
		LinkUsecaseRequestLocalServiceUtil.removeByRecordId(recordId);
		UrlRecordLocalServiceUtil.removeByRecordId(recordId);
		//redirect
		response.setRenderParameter("page", jspEditPortlet);
		//hack, only work this way ....
		response.setRenderParameter("p_p_state", "pop_up");
		response.setRenderParameter("tabs1", "config-record");
		PortalUtil.copyRequestParameters(request, response);
	}


	/**
	 * 
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws PortalException
	 */
	public void toggleRecord(final ActionRequest request, final ActionResponse response) throws SystemException, PortalException {
		final String recordState = ParamUtil.getString(request, "nextRecordState");
		response.setRenderParameter("recordState", recordState);
		//hack, only work this way ....
		response.setRenderParameter("p_p_state", "pop_up");
		PortalUtil.copyRequestParameters(request, response);
	}

	/**
	 * get the scenario state, if completed or not yet.
	 * @param scenario
	 * @return
	 */
	private int scenarioState(final Scenario scenario) {
		try {
			final int count = RequestLocalServiceUtil.countByScenarioIdAndUsedAndIsNotPortlet(scenario.getScenario_id());
			if (count != 0 && scenario.isComplete()) {
				// completed scenario = case if all minimal information are
				// completed
				return 2;
			} else if (count != 0 && !scenario.isComplete()) {
				// incomplete scenario = case if one or more information detail of
				// scenario are not completed but there is request selected
				return 1;
			}
		} catch (final SystemException e) {
			if (LOG.isErrorEnabled()){
				LOG.error("enable to determine Scenario state " + e.getMessage());
			}
		}
		// case if not request selected to that scenario = empty scenario
		return 0;
	}

	/**
	 * get the simulation state, if all scenario are completed or not yet.
	 * @param simulation
	 * @return
	 */
	public int simulationState(final Simulation simulation) {
		try {
			final List<Scenario> scenariosList = ScenarioLocalServiceUtil.findBySimulationId(simulation.getSimulation_id());
			int checkNumberCompleted = 0;
			for (final Scenario scenario : scenariosList) {
				if (scenarioState(scenario) == 2) {
					checkNumberCompleted++;
				}
			}
			if (checkNumberCompleted == 0 || scenariosList.size() == 0) {
				//if no one scenario is completed = simulation empty
				return 0;
			} else if (checkNumberCompleted == scenariosList.size() && simulation.isComplete()) {
				//if all scenario completed = simulation completed
				return 2;
			} else {
				//other case = simulation uncompleted
				return 1;
			}
		} catch (final SystemException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("enable to determine Simulation state " + e.getMessage());
			}
			return 0;
		}
	}

	/**
	 * View method : redirect to requested page and send necessary parameters.
	 */

	@Override
	public void doView(final RenderRequest renderRequest, final RenderResponse renderResponse) throws IOException, PortletException {
		/* get the path  for next jsp or by  default jspListSimulation */
		String page = ParamUtil.get(renderRequest, "page", jspListSimulation);

		if (page.equals(jspListSimulation)) {
			/*
			 * 
			 * 
			 * ===== view.jsp => list of the simulations
			 * 
			 * 
			 */
			LOG.debug("DoView : List Simulation");
			List<Simulation> simulationList = new ArrayList<Simulation>();
			final Map<Simulation, Integer[]> simulationMap = new HashMap<Simulation, Integer[]>();
			try {
				simulationList = SimulationLocalServiceUtil.getSimulations(0, SimulationLocalServiceUtil.getSimulationsCount());
				for (final Simulation simulation : simulationList) {
					final Integer[] simulationInfos = new Integer[3];
					simulationInfos[0] = ScenarioLocalServiceUtil.countBySimulationId(simulation.getSimulation_id());
					simulationInfos[1] = simulationState(simulation);
					simulationInfos[2] = SimulationLocalServiceUtil.containsPrivatePage(simulation.getSimulation_id()) ? 1 : 0;
					simulationMap.put(simulation, simulationInfos);
				}
			} catch (final SystemException e) {
				throw new RuntimeException("error with getSimulation with localServiceUtil " + e.getMessage());
			}
			final String JSListName = GatlingUtil.createJSListOfSimulationName(simulationList);
			final javax.portlet.PortletPreferences prefs = renderRequest.getPreferences();
			String gatlingVersionString;
			gatlingVersionString = prefs.getValue("gatlingVersion", null);

			renderRequest.setAttribute("gatlingVersion", gatlingVersionString);
			renderRequest.setAttribute("listOfSimulationName", JSListName);
			renderRequest.setAttribute("listSimulation", simulationList);
			renderRequest.setAttribute("MapSimulation", simulationMap);
		} else if (page.equals(jspEditSimulation) || page.equals(jspFormFirstScenario)) {
			/*
			 * 
			 * 
			 * ===== Edit simulation, get and send scenarios list to jsp page
			 * 
			 * 
			 */
			LOG.debug("DoView : Edit Simulation");

			final Long id = ParamUtil.getLong(renderRequest, "simulationId");
			if(id==null) {
				throw new NullPointerException("simulation id is null");
			}

			Simulation simulation;		

			try {
				simulation = SimulationLocalServiceUtil.getSimulation(id);
				final int scenarioListSize = ScenarioLocalServiceUtil.countBySimulationId(simulation.getSimulation_id());
				renderRequest.setAttribute("simulation", simulation);
				// If empty go to form first scenario
				if(scenarioListSize == 0) {
					throw new EmptySimulation();
				}
				// List of Scenario
				final List<Scenario> scenarioList = ScenarioLocalServiceUtil.findBySimulationId(simulation.getSimulation_id());

				// map <scenario, scenarioInfo>
				final Map<Scenario, Number[]> scenariosMap = new HashMap<Scenario, Number[]>();
				for (final Scenario scenario : scenarioList) {
					final Number[] info = new Number[3];
					info[0] = RequestLocalServiceUtil.countByScenarioIdAndUsedAndIsNotPortlet(scenario.getScenario_id());
					info[1] = RequestLocalServiceUtil.countByScenarioIdAndIsNotPortlet(scenario.getScenario_id());
					info[2] = scenarioState(scenario);
					scenariosMap.put(scenario, info);
				}
				final String JSListName = GatlingUtil.createJSListOfScenarioName(scenarioList);
				renderRequest.setAttribute("listOfScenarioName", JSListName);
				/*
				 * get list of simulation (for edit name)
				 */
				final List<Simulation> listSimulations = SimulationLocalServiceUtil.getSimulations(0,
						SimulationLocalServiceUtil.getSimulationsCount());
				final String JSListSimName = GatlingUtil.createJSListOfSimulationName(listSimulations);

				renderRequest.setAttribute("listOfSimulationName", JSListSimName);
				renderRequest.setAttribute("listScenario", scenarioList);
				renderRequest.setAttribute("MapScenario", scenariosMap);
			} catch (final SystemException e) {
				throw new RuntimeException("error with get scenario list with localServiceUtil " + e.getMessage());
			} catch (final PortalException e) {
				throw new RuntimeException("error with get scenario list with localServiceUtil " + e.getMessage());
			} catch (final EmptySimulation e) {
				LOG.info("Empty simulation ... redirect");
				page = jspFormFirstScenario;
			}
			// List of Sites
			final List<Group> listGroups = GatlingUtil.getListOfSites();
			renderRequest.setAttribute("listGroup", listGroups);
		} else if (page.equals(jspEditScenario)) {
			/*
			 * 
			 * 
			 * ===== Edit scenario -> request list send to jsp page
			 * 
			 * 
			 */
			LOG.debug("DoView : Edit Scenario");
			if (ParamUtil.getLong(renderRequest, "scenarioId") == 0) {
				throw new NullPointerException("scenario id is null");
			}
			try {
				// get scenario
				final Scenario scenario = ScenarioLocalServiceUtil.getScenario(ParamUtil.getLong(renderRequest, "scenarioId"));
				final Simulation simulation = SimulationLocalServiceUtil.getSimulation(scenario.getSimulation_id());

				//update friendlyUrl of site if changed
				final String oldFriendlyURL = scenario.getUrl_site();
				final ThemeDisplay themeDisplay =	(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				String currentFriendlyURL = GroupLocalServiceUtil.fetchGroup(scenario.getGroup_id()).getIconURL(themeDisplay);
				final StringBuilder sb = new StringBuilder(currentFriendlyURL.split("/")[0]);
				sb.append("//").append(currentFriendlyURL.split("/")[2]).append("/web").append(GroupLocalServiceUtil.fetchGroup(scenario.getGroup_id()).getFriendlyURL());
				currentFriendlyURL = sb.toString();

				if (! oldFriendlyURL.equals(currentFriendlyURL)) {
					//update site url 
					scenario.setUrl_site(currentFriendlyURL);
					ScenarioLocalServiceUtil.updateScenario(scenario);
				}

				//get public layout list
				final long groupId = scenario.getGroup_id();
				final List<Layout> listPublicLayouts = LayoutLocalServiceUtil.getLayouts(groupId, false, 0);

				//get private layout list
				final List<Layout> listPrivateLayouts = LayoutLocalServiceUtil.getLayouts(groupId, true, 0);

				//get site name
				final String siteName = GroupLocalServiceUtil.getGroup(groupId).getDescriptiveName();

				//create DisplayLayoutList with actuel layout of the site and old layout added from requests
				List<RequestDTO> displayItemList = new ArrayList<RequestDTO>();
				DisplayItemDTOUtil.addLayoutToDisplayItemList(displayItemList, listPublicLayouts);
				DisplayItemDTOUtil.addLayoutToDisplayItemList(displayItemList, listPrivateLayouts);

				//get list of request to add the old page to DisplayLayout
				final List<Request> listRequests = RequestLocalServiceUtil.findByScenarioId(scenario.getScenario_id());
				//Merge Layout and Request in DisplayLayout List
				displayItemList = DisplayItemDTOUtil.addRequestToDisplayItemList(displayItemList, listRequests);

				// Get list of used names
				final List<Scenario> scenariolist = ScenarioLocalServiceUtil.getScenarios(0, ScenarioLocalServiceUtil.getScenariosCount());
				final String JSListName = GatlingUtil.createJSListOfScenarioName(scenariolist);

				//add private and public url of site
				final String publicURL = scenario.getUrl_site();
				final String privateURL = scenario.getUrl_site().replace("web", "group");
				/*
				 * create header list
				 */
				final String[] headerList = new String[] {simulation.getName(), scenario.getName(), siteName};
				renderRequest.setAttribute("headerList", headerList);
				//add request parameters
				renderRequest.setAttribute("scenario", scenario);
				renderRequest.setAttribute("listPages", displayItemList);
				renderRequest.setAttribute("siteName", siteName);
				renderRequest.setAttribute("publicURL", publicURL);
				renderRequest.setAttribute("privateURL", privateURL);
				renderRequest.setAttribute("listOfScenarioName", JSListName);
			} catch (final SystemException e) {
				throw new RuntimeException("cannot get layout list: " + e.getMessage());
			} catch (final PortalException e) {
				throw new RuntimeException("cannot get layout list: " + e.getMessage());
			} 

		} else if (page.equals(jspEditPortlet)) {
			/*
			 * 
			 * 
			 * ===== PORTLET POPUP
			 * 
			 * 
			 */
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
			try {
				availableScript =  ListScript.getList(portletId);
			} catch (final SystemException e) {
				if(LOG.isErrorEnabled()){
					LOG.error("error getting list of script: "+e.getMessage());
				}
			}
			try {
				arrayLinkUsecaseRequest = GatlingUtil.fillArrayLinkUseCases(requestId);

			} catch (final SystemException e) {
				if(LOG.isErrorEnabled()){
					LOG.error("error when search for Record list: "+e.getMessage());
				}
			} catch (final PortalException e) {
				if(LOG.isErrorEnabled()){
					LOG.error("error when search for Record list: "+e.getMessage());
				}
			}
			try {
				recordList = RecordLocalServiceUtil.findByPortletId(portletId);
				listRecordsName = GatlingUtil.createJSListOfRecordName(recordList);
			} catch (final SystemException e) {
				if(LOG.isErrorEnabled()){
					LOG.error("error findByPortletId : "+e.getMessage());
				}
			} 

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
		}

		else if (page.equals(jspEditRecord)) {

			//Get the list of UrlRecord that corresponds to the recordId, if requested
			final long  recordId = ParamUtil.getLong(renderRequest, "recordId");
			final String recordName = ParamUtil.getString(renderRequest, "recordname");
			List<UrlRecord> listRecordUrl = new ArrayList<UrlRecord>();

			if(recordId != 0){
				try {
					listRecordUrl = UrlRecordLocalServiceUtil.findByRecordId(recordId);
				} catch (final SystemException e) {
					if(LOG.isErrorEnabled()){
						LOG.error("error when search for UrlRecord list: "+e.getMessage());
					}
				}
			}
			else{
				throw new NullPointerException("RecordId is null");
			}

			LOG.info("portlet id "+ParamUtil.getString(renderRequest, "portletId"));
			renderRequest.setAttribute("listRecordUrl", listRecordUrl);
			renderRequest.setAttribute("recordName", recordName);
			renderRequest.setAttribute("recordId", recordId);
			renderRequest.setAttribute("portletId", ParamUtil.getString(renderRequest, "portletId"));
			renderRequest.setAttribute("requestId", ParamUtil.getString(renderRequest, "requestId"));

		}
		/* redirect to jsp page */
		include(page, renderRequest, renderResponse);
	}

	/**
	 * ServeResource is used to download gatling's generated files
	 * 
	 */
	@Override
	public void serveResource(final ResourceRequest request, final ResourceResponse response) {
		/*
		 * Get template from version
		 */
		final int gatlingVersion = ParamUtil.getInteger(request, "gatlingVersion");

		//add user preference
		final PortletPreferences prefs = request.getPreferences();
		try {
			prefs.setValue("gatlingVersion", Integer.toString(gatlingVersion));
			prefs.store();
		} catch (final ReadOnlyException e) {
			throw new RuntimeException("cannot add user preferences for gatling version " + e.getMessage());
		} catch (final ValidatorException e) {
			throw new RuntimeException("cannot add user preferences for gatling version " + e.getMessage());
		} catch (final IOException e) {
			throw new RuntimeException("cannot add user preferences for gatling version " + e.getMessage());
		} 
		//scripting Gatling
		String template;
		switch (gatlingVersion) {
		case 1:
			template = "/templateGatling1.5.mustache";
			break;
		case 2:
		default:
			template = "/templateGatling2.0.X.mustache";
			break;
		}

		/*
		 * Get simulations ids
		 */
		final long[] simulationsIds = ParamUtil.getLongValues(request, "export");
		Simulation simulation;
		final Date date = new Date();
		if (simulationsIds.length > 1) {
			response.setContentType("application/zip");
			response.addProperty("Content-Disposition", "attachment; filename = GatlingSimulations" + date.getTime() + ".zip");

			try {
				final ZipOutputStream zipOutputStream = new ZipOutputStream(response.getPortletOutputStream());
				for (final long id : simulationsIds) {
					if (id  > 0) {
						simulation = SimulationLocalServiceUtil.getSimulation(id);
						zipOutputStream.putNextEntry(new ZipEntry("Simulation" + simulation.getName() + date.getTime() + ".scala"));
						final String currentPath = request.getPortletSession().getPortletContext().getRealPath("/WEB-INF/src/resources") + template;
						final String tmp = Mustache.compiler().compile(new FileReader(currentPath)).execute(new ScriptGeneratorGatling(id,PortalUtil.getPortalURL(request)));
						zipOutputStream.write(tmp.getBytes());
						zipOutputStream.closeEntry();
					}
				}
				zipOutputStream.close();
			} catch (final Exception e) {
				throw new RuntimeException("cannot export zip for scenario(s) " + e.getMessage());
			}

		} else if (simulationsIds.length == 1 && simulationsIds[0] > 0) {
			//create and export only one file with scenario script for this simulation id
			response.setContentType("application/x-wais-source");
			try {
				simulation = SimulationLocalServiceUtil.getSimulation(simulationsIds[0]);
				response.addProperty("Content-Disposition", "attachment; filename=Simulation"  + simulation.getName()  + date.getTime() + ".scala");
				final OutputStream out = response.getPortletOutputStream();
				final String currentPath = request.getPortletSession().getPortletContext().getRealPath("/WEB-INF/src/resources") + template;
				final Template tmpl = Mustache.compiler().compile(new FileReader(currentPath));
				final String script = tmpl.execute(new ScriptGeneratorGatling(simulationsIds[0],PortalUtil.getPortalURL(request)));
				out.write(script.getBytes());
				out.flush();
				out.close();
			} catch (final Exception e) {
				throw new RuntimeException("cannot export script file " + e.getMessage());
			}
		} else {
			throw new NullPointerException("nothing to export");
		}
		response.addProperty(HttpHeaders.CACHE_CONTROL, "max-age=3600, must-revalidate");
	}
}


