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

import com.excilys.liferay.gatling.model.Request;
import com.excilys.liferay.gatling.service.RequestLocalServiceUtil;
import com.excilys.liferay.gatling.service.base.RequestLocalServiceBaseImpl;
import com.excilys.liferay.gatling.validator.RequestValidator;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.model.Layout;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the request local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.sample.service.RequestLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @see com.liferay.sample.service.base.RequestLocalServiceBaseImpl
 * @see com.liferay.sample.service.RequestLocalServiceUtil
 */
public class RequestLocalServiceImpl extends RequestLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.sample.service.RequestLocalServiceUtil} to access the request local service.
	 */


	@Override
	public List<Request> findByScenarioId(long scenarioId) throws SystemException{
		return requestPersistence.findByScenarioId(scenarioId);
	}

	@Override
	public void removeByScenarioId(long scenarioId) throws SystemException {
		requestPersistence.removeByScenarioId(scenarioId);
	}

	public void addRequestFromLayout(Layout layout, double weight, long idScenario, boolean checked, long userId) throws SystemException {
		//create request
		long primaryKey;
		primaryKey = CounterLocalServiceUtil.increment(Request.class.getName());
		Request newRequest = RequestLocalServiceUtil.createRequest(primaryKey);
		newRequest.setLayoutId(layout.getLayoutId());
		newRequest.setName(layout.getName(LocaleUtil.getDefault()));
		newRequest.setUrl(layout.getFriendlyURL());
		newRequest.setWeight(weight);
		newRequest.setScenario_id(idScenario);
		newRequest.setChecked(checked);
		newRequest.setPrivatePage(layout.isPrivateLayout());
		newRequest.setParentLayoutId(layout.getParentLayoutId());
		// Saving ...
		List<String> errors = new ArrayList<String>();
		if(RequestValidator.validateRequest(newRequest, errors)) {
			newRequest = RequestLocalServiceUtil.addRequest(newRequest);
		}
	} 
}