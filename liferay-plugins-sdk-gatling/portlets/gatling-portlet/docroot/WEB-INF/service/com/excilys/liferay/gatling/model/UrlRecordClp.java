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
import com.excilys.liferay.gatling.service.UrlRecordLocalServiceUtil;

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
public class UrlRecordClp extends BaseModelImpl<UrlRecord> implements UrlRecord {
	public UrlRecordClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return UrlRecord.class;
	}

	@Override
	public String getModelClassName() {
		return UrlRecord.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _urlRecordId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUrlRecordId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _urlRecordId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("urlRecordId", getUrlRecordId());
		attributes.put("recordId", getRecordId());
		attributes.put("url", getUrl());
		attributes.put("type", getType());
		attributes.put("order", getOrder());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long urlRecordId = (Long)attributes.get("urlRecordId");

		if (urlRecordId != null) {
			setUrlRecordId(urlRecordId);
		}

		Long recordId = (Long)attributes.get("recordId");

		if (recordId != null) {
			setRecordId(recordId);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer order = (Integer)attributes.get("order");

		if (order != null) {
			setOrder(order);
		}
	}

	@Override
	public long getUrlRecordId() {
		return _urlRecordId;
	}

	@Override
	public void setUrlRecordId(long urlRecordId) {
		_urlRecordId = urlRecordId;

		if (_urlRecordRemoteModel != null) {
			try {
				Class<?> clazz = _urlRecordRemoteModel.getClass();

				Method method = clazz.getMethod("setUrlRecordId", long.class);

				method.invoke(_urlRecordRemoteModel, urlRecordId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getRecordId() {
		return _recordId;
	}

	@Override
	public void setRecordId(long recordId) {
		_recordId = recordId;

		if (_urlRecordRemoteModel != null) {
			try {
				Class<?> clazz = _urlRecordRemoteModel.getClass();

				Method method = clazz.getMethod("setRecordId", long.class);

				method.invoke(_urlRecordRemoteModel, recordId);
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

		if (_urlRecordRemoteModel != null) {
			try {
				Class<?> clazz = _urlRecordRemoteModel.getClass();

				Method method = clazz.getMethod("setUrl", String.class);

				method.invoke(_urlRecordRemoteModel, url);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getType() {
		return _type;
	}

	@Override
	public void setType(String type) {
		_type = type;

		if (_urlRecordRemoteModel != null) {
			try {
				Class<?> clazz = _urlRecordRemoteModel.getClass();

				Method method = clazz.getMethod("setType", String.class);

				method.invoke(_urlRecordRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getOrder() {
		return _order;
	}

	@Override
	public void setOrder(int order) {
		_order = order;

		if (_urlRecordRemoteModel != null) {
			try {
				Class<?> clazz = _urlRecordRemoteModel.getClass();

				Method method = clazz.getMethod("setOrder", int.class);

				method.invoke(_urlRecordRemoteModel, order);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getUrlRecordRemoteModel() {
		return _urlRecordRemoteModel;
	}

	public void setUrlRecordRemoteModel(BaseModel<?> urlRecordRemoteModel) {
		_urlRecordRemoteModel = urlRecordRemoteModel;
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

		Class<?> remoteModelClass = _urlRecordRemoteModel.getClass();

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

		Object returnValue = method.invoke(_urlRecordRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			UrlRecordLocalServiceUtil.addUrlRecord(this);
		}
		else {
			UrlRecordLocalServiceUtil.updateUrlRecord(this);
		}
	}

	@Override
	public UrlRecord toEscapedModel() {
		return (UrlRecord)ProxyUtil.newProxyInstance(UrlRecord.class.getClassLoader(),
			new Class[] { UrlRecord.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		UrlRecordClp clone = new UrlRecordClp();

		clone.setUrlRecordId(getUrlRecordId());
		clone.setRecordId(getRecordId());
		clone.setUrl(getUrl());
		clone.setType(getType());
		clone.setOrder(getOrder());

		return clone;
	}

	@Override
	public int compareTo(UrlRecord urlRecord) {
		long primaryKey = urlRecord.getPrimaryKey();

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

		if (!(obj instanceof UrlRecordClp)) {
			return false;
		}

		UrlRecordClp urlRecord = (UrlRecordClp)obj;

		long primaryKey = urlRecord.getPrimaryKey();

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
		StringBundler sb = new StringBundler(11);

		sb.append("{urlRecordId=");
		sb.append(getUrlRecordId());
		sb.append(", recordId=");
		sb.append(getRecordId());
		sb.append(", url=");
		sb.append(getUrl());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", order=");
		sb.append(getOrder());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.excilys.liferay.gatling.model.UrlRecord");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>urlRecordId</column-name><column-value><![CDATA[");
		sb.append(getUrlRecordId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recordId</column-name><column-value><![CDATA[");
		sb.append(getRecordId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>url</column-name><column-value><![CDATA[");
		sb.append(getUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>order</column-name><column-value><![CDATA[");
		sb.append(getOrder());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _urlRecordId;
	private long _recordId;
	private String _url;
	private String _type;
	private int _order;
	private BaseModel<?> _urlRecordRemoteModel;
}