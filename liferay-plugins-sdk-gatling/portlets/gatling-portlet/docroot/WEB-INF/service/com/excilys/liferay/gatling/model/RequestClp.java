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

import com.excilys.liferay.gatling.service.ClpSerializer;
import com.excilys.liferay.gatling.service.RequestLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class RequestClp extends BaseModelImpl<Request> implements Request {
	public RequestClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Request.class;
	}

	@Override
	public String getModelClassName() {
		return Request.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _requestId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRequestId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _requestId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("requestId", getRequestId());
		attributes.put("scenarioId", getScenarioId());
		attributes.put("name", getName());
		attributes.put("url", getUrl());
		attributes.put("weight", getWeight());
		attributes.put("privatePage", getPrivatePage());
		attributes.put("parentPlId", getParentPlId());
		attributes.put("layoutId", getLayoutId());
		attributes.put("plId", getPlId());
		attributes.put("portlet", getPortlet());
		attributes.put("portetId", getPortetId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long requestId = (Long)attributes.get("requestId");

		if (requestId != null) {
			setRequestId(requestId);
		}

		Long scenarioId = (Long)attributes.get("scenarioId");

		if (scenarioId != null) {
			setScenarioId(scenarioId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		Double weight = (Double)attributes.get("weight");

		if (weight != null) {
			setWeight(weight);
		}

		Boolean privatePage = (Boolean)attributes.get("privatePage");

		if (privatePage != null) {
			setPrivatePage(privatePage);
		}

		Long parentPlId = (Long)attributes.get("parentPlId");

		if (parentPlId != null) {
			setParentPlId(parentPlId);
		}

		Long layoutId = (Long)attributes.get("layoutId");

		if (layoutId != null) {
			setLayoutId(layoutId);
		}

		Long plId = (Long)attributes.get("plId");

		if (plId != null) {
			setPlId(plId);
		}

		Boolean portlet = (Boolean)attributes.get("portlet");

		if (portlet != null) {
			setPortlet(portlet);
		}

		String portetId = (String)attributes.get("portetId");

		if (portetId != null) {
			setPortetId(portetId);
		}
	}

	@Override
	public long getRequestId() {
		return _requestId;
	}

	@Override
	public void setRequestId(long requestId) {
		_requestId = requestId;

		if (_requestRemoteModel != null) {
			try {
				Class<?> clazz = _requestRemoteModel.getClass();

				Method method = clazz.getMethod("setRequestId", long.class);

				method.invoke(_requestRemoteModel, requestId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getScenarioId() {
		return _scenarioId;
	}

	@Override
	public void setScenarioId(long scenarioId) {
		_scenarioId = scenarioId;

		if (_requestRemoteModel != null) {
			try {
				Class<?> clazz = _requestRemoteModel.getClass();

				Method method = clazz.getMethod("setScenarioId", long.class);

				method.invoke(_requestRemoteModel, scenarioId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;

		if (_requestRemoteModel != null) {
			try {
				Class<?> clazz = _requestRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_requestRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUrl() {
		return _url;
	}

	@Override
	public void setUrl(String url) {
		_url = url;

		if (_requestRemoteModel != null) {
			try {
				Class<?> clazz = _requestRemoteModel.getClass();

				Method method = clazz.getMethod("setUrl", String.class);

				method.invoke(_requestRemoteModel, url);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public double getWeight() {
		return _weight;
	}

	@Override
	public void setWeight(double weight) {
		_weight = weight;

		if (_requestRemoteModel != null) {
			try {
				Class<?> clazz = _requestRemoteModel.getClass();

				Method method = clazz.getMethod("setWeight", double.class);

				method.invoke(_requestRemoteModel, weight);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getPrivatePage() {
		return _privatePage;
	}

	@Override
	public boolean isPrivatePage() {
		return _privatePage;
	}

	@Override
	public void setPrivatePage(boolean privatePage) {
		_privatePage = privatePage;

		if (_requestRemoteModel != null) {
			try {
				Class<?> clazz = _requestRemoteModel.getClass();

				Method method = clazz.getMethod("setPrivatePage", boolean.class);

				method.invoke(_requestRemoteModel, privatePage);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getParentPlId() {
		return _parentPlId;
	}

	@Override
	public void setParentPlId(long parentPlId) {
		_parentPlId = parentPlId;

		if (_requestRemoteModel != null) {
			try {
				Class<?> clazz = _requestRemoteModel.getClass();

				Method method = clazz.getMethod("setParentPlId", long.class);

				method.invoke(_requestRemoteModel, parentPlId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getLayoutId() {
		return _layoutId;
	}

	@Override
	public void setLayoutId(long layoutId) {
		_layoutId = layoutId;

		if (_requestRemoteModel != null) {
			try {
				Class<?> clazz = _requestRemoteModel.getClass();

				Method method = clazz.getMethod("setLayoutId", long.class);

				method.invoke(_requestRemoteModel, layoutId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getPlId() {
		return _plId;
	}

	@Override
	public void setPlId(long plId) {
		_plId = plId;

		if (_requestRemoteModel != null) {
			try {
				Class<?> clazz = _requestRemoteModel.getClass();

				Method method = clazz.getMethod("setPlId", long.class);

				method.invoke(_requestRemoteModel, plId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getPortlet() {
		return _portlet;
	}

	@Override
	public boolean isPortlet() {
		return _portlet;
	}

	@Override
	public void setPortlet(boolean portlet) {
		_portlet = portlet;

		if (_requestRemoteModel != null) {
			try {
				Class<?> clazz = _requestRemoteModel.getClass();

				Method method = clazz.getMethod("setPortlet", boolean.class);

				method.invoke(_requestRemoteModel, portlet);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPortetId() {
		return _portetId;
	}

	@Override
	public void setPortetId(String portetId) {
		_portetId = portetId;

		if (_requestRemoteModel != null) {
			try {
				Class<?> clazz = _requestRemoteModel.getClass();

				Method method = clazz.getMethod("setPortetId", String.class);

				method.invoke(_requestRemoteModel, portetId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getRequestRemoteModel() {
		return _requestRemoteModel;
	}

	public void setRequestRemoteModel(BaseModel<?> requestRemoteModel) {
		_requestRemoteModel = requestRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _requestRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_requestRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			RequestLocalServiceUtil.addRequest(this);
		}
		else {
			RequestLocalServiceUtil.updateRequest(this);
		}
	}

	@Override
	public Request toEscapedModel() {
		return (Request)ProxyUtil.newProxyInstance(Request.class.getClassLoader(),
			new Class[] { Request.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		RequestClp clone = new RequestClp();

		clone.setRequestId(getRequestId());
		clone.setScenarioId(getScenarioId());
		clone.setName(getName());
		clone.setUrl(getUrl());
		clone.setWeight(getWeight());
		clone.setPrivatePage(getPrivatePage());
		clone.setParentPlId(getParentPlId());
		clone.setLayoutId(getLayoutId());
		clone.setPlId(getPlId());
		clone.setPortlet(getPortlet());
		clone.setPortetId(getPortetId());

		return clone;
	}

	@Override
	public int compareTo(Request request) {
		long primaryKey = request.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RequestClp)) {
			return false;
		}

		RequestClp request = (RequestClp)obj;

		long primaryKey = request.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{requestId=");
		sb.append(getRequestId());
		sb.append(", scenarioId=");
		sb.append(getScenarioId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", url=");
		sb.append(getUrl());
		sb.append(", weight=");
		sb.append(getWeight());
		sb.append(", privatePage=");
		sb.append(getPrivatePage());
		sb.append(", parentPlId=");
		sb.append(getParentPlId());
		sb.append(", layoutId=");
		sb.append(getLayoutId());
		sb.append(", plId=");
		sb.append(getPlId());
		sb.append(", portlet=");
		sb.append(getPortlet());
		sb.append(", portetId=");
		sb.append(getPortetId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.excilys.liferay.gatling.model.Request");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>requestId</column-name><column-value><![CDATA[");
		sb.append(getRequestId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scenarioId</column-name><column-value><![CDATA[");
		sb.append(getScenarioId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>url</column-name><column-value><![CDATA[");
		sb.append(getUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>weight</column-name><column-value><![CDATA[");
		sb.append(getWeight());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>privatePage</column-name><column-value><![CDATA[");
		sb.append(getPrivatePage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentPlId</column-name><column-value><![CDATA[");
		sb.append(getParentPlId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>layoutId</column-name><column-value><![CDATA[");
		sb.append(getLayoutId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>plId</column-name><column-value><![CDATA[");
		sb.append(getPlId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portlet</column-name><column-value><![CDATA[");
		sb.append(getPortlet());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portetId</column-name><column-value><![CDATA[");
		sb.append(getPortetId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
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
	private BaseModel<?> _requestRemoteModel;
}