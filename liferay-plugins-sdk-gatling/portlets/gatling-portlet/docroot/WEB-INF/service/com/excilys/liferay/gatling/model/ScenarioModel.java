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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the Scenario service. Represents a row in the &quot;GatlingTool_Scenario&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.excilys.liferay.gatling.model.impl.ScenarioModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.excilys.liferay.gatling.model.impl.ScenarioImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Scenario
 * @see com.excilys.liferay.gatling.model.impl.ScenarioImpl
 * @see com.excilys.liferay.gatling.model.impl.ScenarioModelImpl
 * @generated
 */
public interface ScenarioModel extends BaseModel<Scenario> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a scenario model instance should use the {@link Scenario} interface instead.
	 */

	/**
	 * Returns the primary key of this scenario.
	 *
	 * @return the primary key of this scenario
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this scenario.
	 *
	 * @param primaryKey the primary key of this scenario
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the scenario ID of this scenario.
	 *
	 * @return the scenario ID of this scenario
	 */
	public long getScenarioId();

	/**
	 * Sets the scenario ID of this scenario.
	 *
	 * @param scenarioId the scenario ID of this scenario
	 */
	public void setScenarioId(long scenarioId);

	/**
	 * Returns the name of this scenario.
	 *
	 * @return the name of this scenario
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this scenario.
	 *
	 * @param name the name of this scenario
	 */
	public void setName(String name);

	/**
	 * Returns the url site of this scenario.
	 *
	 * @return the url site of this scenario
	 */
	@AutoEscape
	public String getUrlSite();

	/**
	 * Sets the url site of this scenario.
	 *
	 * @param urlSite the url site of this scenario
	 */
	public void setUrlSite(String urlSite);

	/**
	 * Returns the group_id of this scenario.
	 *
	 * @return the group_id of this scenario
	 */
	public long getGroup_id();

	/**
	 * Sets the group_id of this scenario.
	 *
	 * @param group_id the group_id of this scenario
	 */
	public void setGroup_id(long group_id);

	/**
	 * Returns the simulation ID of this scenario.
	 *
	 * @return the simulation ID of this scenario
	 */
	public long getSimulationId();

	/**
	 * Sets the simulation ID of this scenario.
	 *
	 * @param simulationId the simulation ID of this scenario
	 */
	public void setSimulationId(long simulationId);

	/**
	 * Returns the number of users of this scenario.
	 *
	 * @return the number of users of this scenario
	 */
	public long getNumberOfUsers();

	/**
	 * Sets the number of users of this scenario.
	 *
	 * @param numberOfUsers the number of users of this scenario
	 */
	public void setNumberOfUsers(long numberOfUsers);

	/**
	 * Returns the duration of this scenario.
	 *
	 * @return the duration of this scenario
	 */
	public long getDuration();

	/**
	 * Sets the duration of this scenario.
	 *
	 * @param duration the duration of this scenario
	 */
	public void setDuration(long duration);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(Scenario scenario);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Scenario> toCacheModel();

	@Override
	public Scenario toEscapedModel();

	@Override
	public Scenario toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}