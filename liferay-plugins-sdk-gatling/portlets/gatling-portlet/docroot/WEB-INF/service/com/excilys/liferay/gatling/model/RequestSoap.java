/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.excilys.liferay.gatling.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RequestSoap implements Serializable {
	public static RequestSoap toSoapModel(Request model) {
		RequestSoap soapModel = new RequestSoap();

		soapModel.setRequestId(model.getRequestId());
		soapModel.setScenarioId(model.getScenarioId());
		soapModel.setName(model.getName());
		soapModel.setUrl(model.getUrl());
		soapModel.setWeight(model.getWeight());
		soapModel.setPrivatePage(model.getPrivatePage());
		soapModel.setParentPlId(model.getParentPlId());
		soapModel.setLayoutId(model.getLayoutId());
		soapModel.setPlId(model.getPlId());
		soapModel.setPortlet(model.getPortlet());
		soapModel.setPortetId(model.getPortetId());

		return soapModel;
	}

	public static RequestSoap[] toSoapModels(Request[] models) {
		RequestSoap[] soapModels = new RequestSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RequestSoap[][] toSoapModels(Request[][] models) {
		RequestSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RequestSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RequestSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RequestSoap[] toSoapModels(List<Request> models) {
		List<RequestSoap> soapModels = new ArrayList<RequestSoap>(models.size());

		for (Request model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RequestSoap[soapModels.size()]);
	}

	public RequestSoap() {
	}

	public long getPrimaryKey() {
		return _requestId;
	}

	public void setPrimaryKey(long pk) {
		setRequestId(pk);
	}

	public long getRequestId() {
		return _requestId;
	}

	public void setRequestId(long requestId) {
		_requestId = requestId;
	}

	public long getScenarioId() {
		return _scenarioId;
	}

	public void setScenarioId(long scenarioId) {
		_scenarioId = scenarioId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public double getWeight() {
		return _weight;
	}

	public void setWeight(double weight) {
		_weight = weight;
	}

	public boolean getPrivatePage() {
		return _privatePage;
	}

	public boolean isPrivatePage() {
		return _privatePage;
	}

	public void setPrivatePage(boolean privatePage) {
		_privatePage = privatePage;
	}

	public long getParentPlId() {
		return _parentPlId;
	}

	public void setParentPlId(long parentPlId) {
		_parentPlId = parentPlId;
	}

	public long getLayoutId() {
		return _layoutId;
	}

	public void setLayoutId(long layoutId) {
		_layoutId = layoutId;
	}

	public long getPlId() {
		return _plId;
	}

	public void setPlId(long plId) {
		_plId = plId;
	}

	public boolean getPortlet() {
		return _portlet;
	}

	public boolean isPortlet() {
		return _portlet;
	}

	public void setPortlet(boolean portlet) {
		_portlet = portlet;
	}

	public String getPortetId() {
		return _portetId;
	}

	public void setPortetId(String portetId) {
		_portetId = portetId;
	}

	private long _requestId;
	private long _scenarioId;
	private String _name;
	private String _url;
	private double _weight;
	private boolean _privatePage;
	private long _parentPlId;
	private long _layoutId;
	private long _plId;
	private boolean _portlet;
	private String _portetId;
}