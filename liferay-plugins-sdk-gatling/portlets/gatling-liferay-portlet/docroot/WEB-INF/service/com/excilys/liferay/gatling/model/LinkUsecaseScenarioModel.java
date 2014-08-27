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

import com.excilys.liferay.gatling.service.persistence.LinkUsecaseScenarioPK;

import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the LinkUsecaseScenario service. Represents a row in the &quot;StressTool_LinkUsecaseScenario&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.excilys.liferay.gatling.model.impl.LinkUsecaseScenarioModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.excilys.liferay.gatling.model.impl.LinkUsecaseScenarioImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LinkUsecaseScenario
 * @see com.excilys.liferay.gatling.model.impl.LinkUsecaseScenarioImpl
 * @see com.excilys.liferay.gatling.model.impl.LinkUsecaseScenarioModelImpl
 * @generated
 */
public interface LinkUsecaseScenarioModel extends BaseModel<LinkUsecaseScenario> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a link usecase scenario model instance should use the {@link LinkUsecaseScenario} interface instead.
	 */

	/**
	 * Returns the primary key of this link usecase scenario.
	 *
	 * @return the primary key of this link usecase scenario
	 */
	public LinkUsecaseScenarioPK getPrimaryKey();

	/**
	 * Sets the primary key of this link usecase scenario.
	 *
	 * @param primaryKey the primary key of this link usecase scenario
	 */
	public void setPrimaryKey(LinkUsecaseScenarioPK primaryKey);

	/**
	 * Returns the scenario_id of this link usecase scenario.
	 *
	 * @return the scenario_id of this link usecase scenario
	 */
	public long getScenario_id();

	/**
	 * Sets the scenario_id of this link usecase scenario.
	 *
	 * @param scenario_id the scenario_id of this link usecase scenario
	 */
	public void setScenario_id(long scenario_id);

	/**
	 * Returns the usecase ID of this link usecase scenario.
	 *
	 * @return the usecase ID of this link usecase scenario
	 */
	public long getUsecaseId();

	/**
	 * Sets the usecase ID of this link usecase scenario.
	 *
	 * @param usecaseId the usecase ID of this link usecase scenario
	 */
	public void setUsecaseId(long usecaseId);

	/**
	 * Returns the weight of this link usecase scenario.
	 *
	 * @return the weight of this link usecase scenario
	 */
	public double getWeight();

	/**
	 * Sets the weight of this link usecase scenario.
	 *
	 * @param weight the weight of this link usecase scenario
	 */
	public void setWeight(double weight);

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
	public int compareTo(LinkUsecaseScenario linkUsecaseScenario);

	@Override
	public int hashCode();

	@Override
	public CacheModel<LinkUsecaseScenario> toCacheModel();

	@Override
	public LinkUsecaseScenario toEscapedModel();

	@Override
	public LinkUsecaseScenario toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}