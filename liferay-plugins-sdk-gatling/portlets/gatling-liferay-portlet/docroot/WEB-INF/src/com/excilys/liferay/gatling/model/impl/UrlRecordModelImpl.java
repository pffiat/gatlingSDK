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

package com.excilys.liferay.gatling.model.impl;

import com.excilys.liferay.gatling.model.UrlRecord;
import com.excilys.liferay.gatling.model.UrlRecordModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the UrlRecord service. Represents a row in the &quot;StressTool_UrlRecord&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.excilys.liferay.gatling.model.UrlRecordModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UrlRecordImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UrlRecordImpl
 * @see com.excilys.liferay.gatling.model.UrlRecord
 * @see com.excilys.liferay.gatling.model.UrlRecordModel
 * @generated
 */
public class UrlRecordModelImpl extends BaseModelImpl<UrlRecord>
	implements UrlRecordModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a url record model instance should use the {@link com.excilys.liferay.gatling.model.UrlRecord} interface instead.
	 */
	public static final String TABLE_NAME = "StressTool_UrlRecord";
	public static final Object[][] TABLE_COLUMNS = {
			{ "urlRecordId", Types.BIGINT },
			{ "recordId", Types.BIGINT },
			{ "url", Types.VARCHAR },
			{ "order_", Types.INTEGER }
		};
	public static final String TABLE_SQL_CREATE = "create table StressTool_UrlRecord (urlRecordId LONG not null primary key,recordId LONG,url VARCHAR(75) null,order_ INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table StressTool_UrlRecord";
	public static final String ORDER_BY_JPQL = " ORDER BY urlRecord.urlRecordId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY StressTool_UrlRecord.urlRecordId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.excilys.liferay.gatling.model.UrlRecord"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.excilys.liferay.gatling.model.UrlRecord"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.excilys.liferay.gatling.model.UrlRecord"));

	public UrlRecordModelImpl() {
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
	public Class<?> getModelClass() {
		return UrlRecord.class;
	}

	@Override
	public String getModelClassName() {
		return UrlRecord.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("urlRecordId", getUrlRecordId());
		attributes.put("recordId", getRecordId());
		attributes.put("url", getUrl());
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
	}

	@Override
	public long getRecordId() {
		return _recordId;
	}

	@Override
	public void setRecordId(long recordId) {
		_recordId = recordId;
	}

	@Override
	public String getUrl() {
		if (_url == null) {
			return StringPool.BLANK;
		}
		else {
			return _url;
		}
	}

	@Override
	public void setUrl(String url) {
		_url = url;
	}

	@Override
	public int getOrder() {
		return _order;
	}

	@Override
	public void setOrder(int order) {
		_order = order;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			UrlRecord.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UrlRecord toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (UrlRecord)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		UrlRecordImpl urlRecordImpl = new UrlRecordImpl();

		urlRecordImpl.setUrlRecordId(getUrlRecordId());
		urlRecordImpl.setRecordId(getRecordId());
		urlRecordImpl.setUrl(getUrl());
		urlRecordImpl.setOrder(getOrder());

		urlRecordImpl.resetOriginalValues();

		return urlRecordImpl;
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

		if (!(obj instanceof UrlRecord)) {
			return false;
		}

		UrlRecord urlRecord = (UrlRecord)obj;

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
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<UrlRecord> toCacheModel() {
		UrlRecordCacheModel urlRecordCacheModel = new UrlRecordCacheModel();

		urlRecordCacheModel.urlRecordId = getUrlRecordId();

		urlRecordCacheModel.recordId = getRecordId();

		urlRecordCacheModel.url = getUrl();

		String url = urlRecordCacheModel.url;

		if ((url != null) && (url.length() == 0)) {
			urlRecordCacheModel.url = null;
		}

		urlRecordCacheModel.order = getOrder();

		return urlRecordCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{urlRecordId=");
		sb.append(getUrlRecordId());
		sb.append(", recordId=");
		sb.append(getRecordId());
		sb.append(", url=");
		sb.append(getUrl());
		sb.append(", order=");
		sb.append(getOrder());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

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
			"<column><column-name>order</column-name><column-value><![CDATA[");
		sb.append(getOrder());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = UrlRecord.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			UrlRecord.class
		};
	private long _urlRecordId;
	private long _recordId;
	private String _url;
	private int _order;
	private UrlRecord _escapedModel;
}