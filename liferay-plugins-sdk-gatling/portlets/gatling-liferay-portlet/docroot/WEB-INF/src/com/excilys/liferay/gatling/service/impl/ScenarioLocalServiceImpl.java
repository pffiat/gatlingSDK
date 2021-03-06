/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.excilys.liferay.gatling.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.excilys.liferay.gatling.dto.RequestDTO;
import com.excilys.liferay.gatling.dto.RequestDTO.RequestState;
import com.excilys.liferay.gatling.model.Request;
import com.excilys.liferay.gatling.model.Scenario;
import com.excilys.liferay.gatling.service.RequestLocalServiceUtil;
import com.excilys.liferay.gatling.service.base.ScenarioLocalServiceBaseImpl;
import com.excilys.liferay.gatling.util.DisplayItemDTOUtil;
import com.excilys.liferay.gatling.validator.RequestValidator;
import com.excilys.liferay.gatling.validator.ScenarioValidator;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;


/**
 * The implementation of the scenario local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.sample.service.ScenarioLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p> 
 *
 * @see com.liferay.sample.service.base.ScenarioLocalServiceBaseImpl
 * @see com.liferay.sample.service.ScenarioLocalServiceUtil
 */
public class ScenarioLocalServiceImpl extends ScenarioLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.sample.service.ScenarioLocalServiceUtil} to access the scenario local service.
	 */
	private static final Log LOG = LogFactoryUtil.getLog(ScenarioLocalServiceImpl.class);


	@Override
	public int countBySimulationId(long simulationId) throws SystemException{
		return scenarioPersistence.countBySimulationId(simulationId);
	}
	
	@Override
	public List<Scenario> findBySimulationId(long simulationId) throws SystemException {
		return scenarioPersistence.findBySimulationId(simulationId);
	}

	/**
	 * Remove all {@link Scenario} (and children) for a given simulationId 
	 */
	@Override
	public	void removeBySimulationIdCascade(long simulationId) throws SystemException, NoSuchModelException {
		final List<Scenario> listScenario = scenarioPersistence.findBySimulationId(simulationId);
		//Remove its requests
		for(Scenario scenario : listScenario) {
			RequestLocalServiceUtil.removeByScenarioId(scenario.getScenario_id());
		}
		scenarioPersistence.removeBySimulationId(simulationId);
	}

	/**
	 * Remove all {@link Request} for a scenarioId
	 */
	@Override
	public	void removeByIdCascade(long scenarioId) throws SystemException, NoSuchModelException {
		RequestLocalServiceUtil.removeByScenarioId(scenarioId);
		scenarioPersistence.remove(scenarioId);
	}

	/**
	 * Check if name is unique for {@link Scenario}
	 */
	public boolean isNameUnique(String name, long idSimulation) throws SystemException {
		final DynamicQuery dq = DynamicQueryFactoryUtil.forClass(Scenario.class)
				.add(PropertyFactoryUtil.forName("name").eq(name))
				.add(PropertyFactoryUtil.forName("simulation_id").eq(idSimulation));
		int result = (int) scenarioPersistence.countWithDynamicQuery(dq);
		return (result == 0);
	}
	
	/**
	 * Count how many {@link Scenario} have this variableName
	 */
	public int countByVariableName(String variableName, long idSimulation) throws SystemException {
		DynamicQuery dq = DynamicQueryFactoryUtil.forClass(Scenario.class)
				.add(PropertyFactoryUtil.forName("variableName").like(variableName+"%"))
				.add(PropertyFactoryUtil.forName("simulation_id").eq(idSimulation));

		return (int) scenarioPersistence.countWithDynamicQuery(dq);
	}

	/**
	 * get {@link Scenario} have this variableName
	 */
	@SuppressWarnings("unchecked")
	public List<Scenario> findByVariableName(String variableName, long idSimulation)  throws SystemException {
		DynamicQuery dq = DynamicQueryFactoryUtil.forClass(Scenario.class)
				.add(PropertyFactoryUtil.forName("variableName").like(variableName+"%"))
				.add(PropertyFactoryUtil.forName("simulation_id").eq(idSimulation));

		return scenarioPersistence.findWithDynamicQuery(dq);
	}
	
	/**
	 * Add a {@link Scenario} from an {@link ActionRequest}
	 * @param {@link ActionRequest} request
	 * @param {@link ActionResponse} response
	 * @return {@link Scenario} if added, else null
	 * @throws SystemException
	 */
	public Scenario addScenarioFromRequest(ActionRequest request) throws SystemException {
		final ThemeDisplay themeDisplay =	(ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		/*
		 * Create a scenario
		 */
		final long primaryKey = CounterLocalServiceUtil.increment(Request.class.getName());
		Scenario scenario = scenarioPersistence.create(primaryKey);
		scenario.setName(ParamUtil.getString(request, "scenarioName"));
		scenario.setSimulation_id(ParamUtil.getLong(request, "simulationId"));
		scenario.setGroup_id(ParamUtil.getLong(request, "sites"));
		
		/*
		 * Add base url
		 */
		String urlSite = GroupLocalServiceUtil.fetchGroup(ParamUtil.getLong(request, "sites")).getIconURL(themeDisplay);
		StringBuffer sb = new StringBuffer();
		sb.append(urlSite.split("/")[0]).append("//").append(urlSite.split("/")[2]).append("/web").append(GroupLocalServiceUtil.fetchGroup(ParamUtil.getLong(request, "sites")).getFriendlyURL());
		scenario.setUrl_site(sb.toString());
		// Saving ...
		final List<String> errors = ScenarioValidator.validateScenario(scenario);
		if(errors.isEmpty()) {
			scenario = scenarioPersistence.update(scenario);
			//add Requests
			final List<Layout> listLayouts = new ArrayList<Layout>(LayoutLocalServiceUtil.getLayouts(ParamUtil.getLong(request, "sites"), false, 0));
			// private page
			listLayouts.addAll(LayoutLocalServiceUtil.getLayouts(ParamUtil.getLong(request, "sites"), true, 0));
			
			List<RequestDTO> listDisplayItems = new ArrayList<RequestDTO>();
			DisplayItemDTOUtil.addLayoutToDisplayItemList(listDisplayItems, listLayouts);
			for(RequestDTO displayItem: listDisplayItems){
				RequestLocalServiceUtil.addRequestFromDisplayItem(displayItem, 0, scenario.getScenario_id());
			}
			return scenario;
		}
		else {
			for(String error : errors) {
				SessionErrors.add(request, error);
			}
		}

		return null;
	} 

	/**
	 * Edit a {@link Scenario} from an {@link ActionRequest}
	 * @param {@link ActionRequest} request
	 * @param {@link ActionResponse} response
	 * @return {@link Scenario} if added, else null
	 * @throws SystemException
	 */
	//TODO Refacto de manière propre et maintenable
	public Scenario editScenarioFromRequest(ActionRequest request) throws PortalException, SystemException  {
		final Long idScenario = ParamUtil.getLong(request, "scenarioId");

		if (idScenario == null)
			throw new NullPointerException("idScenario");

		final Map<String, String[]> parameters = request.getParameterMap();
		final Map<Long, Request> mapPublicRequestToEdit = new HashMap<Long, Request>();

		Scenario scenarioToReturn = null;

		/*
		 * Update Details
		 */
		LOG.info("editScenarioDetails");
		
		Scenario scenario = scenarioPersistence.findByPrimaryKey(idScenario);
		String scenarioName= ParamUtil.getString(request, "scenarioName");
		scenario.setName(scenarioName);
		scenario.setNumberOfUsers(ParamUtil.getLong(request, "scenarioUsers"));
		scenario.setDuration(ParamUtil.getLong(request, "scenarioDuration"));
		scenarioToReturn = scenarioPersistence.update(scenario);
		/*
		 * Then update requests
		 * 
		 */
		/*
		 * Layout
		 */
		final long groupId =ParamUtil.getLong(request, "groupId");
		
		//get public layout list
		List<Layout> listPublicLayouts = LayoutLocalServiceUtil.getLayouts(groupId, false, 0);
		
		//get private layout list
		List<Layout> listPrivateLayouts = LayoutLocalServiceUtil.getLayouts(groupId, true, 0);

		List<RequestDTO> displayItemList = new ArrayList<RequestDTO>();
		
		// Sorting layout
		DisplayItemDTOUtil.addLayoutToDisplayItemList(displayItemList, listPublicLayouts);
		DisplayItemDTOUtil.addLayoutToDisplayItemList(displayItemList, listPrivateLayouts );
		
		// Retrieve Request from DB
		final List<Request> listRequests = RequestLocalServiceUtil.findByScenarioId(idScenario);
		// Merge Layout and Request in DisplayLayout List
		displayItemList = DisplayItemDTOUtil.addRequestToDisplayItemList(displayItemList, listRequests);

		// get List request
		for(Request r : listRequests){
			mapPublicRequestToEdit.put(r.getRequest_id(),  r);
		}

		/*
		 *  update data
		 */
		int layoutId = 0;
		double weight = 0.0d;
		RequestDTO displayLayout = null;
		RequestState status = null;
		Long requestId = null;
		for (String key : parameters.keySet()){
			if ((key.contains("weight")) || (key.contains("portlet") && !key.contains("java"))) {
				layoutId =  (key.contains("weight") ? Integer.parseInt(key.replace("weight","")) : Integer.parseInt(key.replace("portlet",""))) ;
				weight = Double.parseDouble(StringUtil.merge(parameters.get(key)));
				displayLayout = displayItemList.get(layoutId);
				status = displayLayout.getState();
				requestId = displayLayout.getRequestId();
				//if not deleted page
				if(status != RequestState.OLD_REQUEST){
					
					Request updatedRequest = null;
					
					//check if the request page was added in the db 
					updatedRequest = mapPublicRequestToEdit.get(requestId);								
					//check if the weight of the request in db is changed then update data in db
					if (updatedRequest != null){
						updatedRequest.setWeight(weight);
						final List<String> errors = RequestValidator.validateRequest(updatedRequest);
						if (errors.isEmpty()) {
							RequestLocalServiceUtil.updateRequest(updatedRequest);
							LOG.debug("request updated successfully");	
						} else {
							for (String error : errors) {
								SessionErrors.add(request, error);
							}
						}
					} else {
						// else Add new page or new portlet request
						if (LOG.isInfoEnabled()){
							LOG.debug("add new request "+key+" : "+StringUtil.merge(parameters.get(key)));
						}
						RequestLocalServiceUtil.addRequestFromDisplayItem(displayLayout, weight, idScenario);
						LOG.info("request created and added succefully ");
					}	
				}
				
				// if layout doesn't exist anymore
				else {
					if (LOG.isInfoEnabled()){
						LOG.debug("delete request: "+key+" : "+StringUtil.merge(parameters.get(key)));
					}
					RequestLocalServiceUtil.deleteRequest(requestId);
				}
				
			}
		}
		return scenarioToReturn;
	}

}