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

import com.excilys.liferay.gatling.model.Simulation;
import com.excilys.liferay.gatling.model.SimulationModel;

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
 * The base model implementation for the Simulation service. Represents a row in the &quot;GatlingTool_Simulation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.excilys.liferay.gatling.model.SimulationModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SimulationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SimulationImpl
 * @see com.excilys.liferay.gatling.model.Simulation
 * @see com.excilys.liferay.gatling.model.SimulationModel
 * @generated
 */
public class SimulationModelImpl extends BaseModelImpl<Simulation>
	implements SimulationModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a simulation model instance should use the {@link com.excilys.liferay.gatling.model.Simulation} interface instead.
	 */
	public static final String TABLE_NAME = "GatlingTool_Simulation";
	public static final Object[][] TABLE_COLUMNS = {
			{ "simulationId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "feederContent", Types.VARCHAR },
			{ "isFeederAFile", Types.BOOLEAN }
		};
	public static final String TABLE_SQL_CREATE = "create table GatlingTool_Simulation (simulationId LONG not null primary key,name VARCHAR(75) null,feederContent VARCHAR(75) null,isFeederAFile BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table GatlingTool_Simulation";
	public static final String ORDER_BY_JPQL = " ORDER BY simulation.simulationId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY GatlingTool_Simulation.simulationId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.excilys.liferay.gatling.model.Simulation"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.excilys.liferay.gatling.model.Simulation"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.excilys.liferay.gatling.model.Simulation"));

	public SimulationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _simulationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSimulationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _simulationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Simulation.class;
	}

	@Override
	public String getModelClassName() {
		return Simulation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("simulationId", getSimulationId());
		attributes.put("name", getName());
		attributes.put("feederContent", getFeederContent());
		attributes.put("isFeederAFile", getIsFeederAFile());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long simulationId = (Long)attributes.get("simulationId");

		if (simulationId != null) {
			setSimulationId(simulationId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String feederContent = (String)attributes.get("feederContent");

		if (feederContent != null) {
			setFeederContent(feederContent);
		}

		Boolean isFeederAFile = (Boolean)attributes.get("isFeederAFile");

		if (isFeederAFile != null) {
			setIsFeederAFile(isFeederAFile);
		}
	}

	@Override
	public long getSimulationId() {
		return _simulationId;
	}

	@Override
	public void setSimulationId(long simulationId) {
		_simulationId = simulationId;
	}

	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public String getFeederContent() {
		if (_feederContent == null) {
			return StringPool.BLANK;
		}
		else {
			return _feederContent;
		}
	}

	@Override
	public void setFeederContent(String feederContent) {
		_feederContent = feederContent;
	}

	@Override
	public boolean getIsFeederAFile() {
		return _isFeederAFile;
	}

	@Override
	public boolean isIsFeederAFile() {
		return _isFeederAFile;
	}

	@Override
	public void setIsFeederAFile(boolean isFeederAFile) {
		_isFeederAFile = isFeederAFile;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Simulation.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Simulation toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Simulation)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SimulationImpl simulationImpl = new SimulationImpl();

		simulationImpl.setSimulationId(getSimulationId());
		simulationImpl.setName(getName());
		simulationImpl.setFeederContent(getFeederContent());
		simulationImpl.setIsFeederAFile(getIsFeederAFile());

		simulationImpl.resetOriginalValues();

		return simulationImpl;
	}

	@Override
	public int compareTo(Simulation simulation) {
		long primaryKey = simulation.getPrimaryKey();

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

		if (!(obj instanceof Simulation)) {
			return false;
		}

		Simulation simulation = (Simulation)obj;

		long primaryKey = simulation.getPrimaryKey();

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
	public CacheModel<Simulation> toCacheModel() {
		SimulationCacheModel simulationCacheModel = new SimulationCacheModel();

		simulationCacheModel.simulationId = getSimulationId();

		simulationCacheModel.name = getName();

		String name = simulationCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			simulationCacheModel.name = null;
		}

		simulationCacheModel.feederContent = getFeederContent();

		String feederContent = simulationCacheModel.feederContent;

		if ((feederContent != null) && (feederContent.length() == 0)) {
			simulationCacheModel.feederContent = null;
		}

		simulationCacheModel.isFeederAFile = getIsFeederAFile();

		return simulationCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{simulationId=");
		sb.append(getSimulationId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", feederContent=");
		sb.append(getFeederContent());
		sb.append(", isFeederAFile=");
		sb.append(getIsFeederAFile());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.excilys.liferay.gatling.model.Simulation");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>simulationId</column-name><column-value><![CDATA[");
		sb.append(getSimulationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>feederContent</column-name><column-value><![CDATA[");
		sb.append(getFeederContent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>isFeederAFile</column-name><column-value><![CDATA[");
		sb.append(getIsFeederAFile());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Simulation.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			Simulation.class
		};
	private long _simulationId;
	private String _name;
	private String _feederContent;
	private boolean _isFeederAFile;
	private Simulation _escapedModel;
}