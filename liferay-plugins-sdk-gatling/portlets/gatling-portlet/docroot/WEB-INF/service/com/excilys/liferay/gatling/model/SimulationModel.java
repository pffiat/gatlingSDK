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
 * The base model interface for the Simulation service. Represents a row in the &quot;GatlingTool_Simulation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.excilys.liferay.gatling.model.impl.SimulationModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.excilys.liferay.gatling.model.impl.SimulationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Simulation
 * @see com.excilys.liferay.gatling.model.impl.SimulationImpl
 * @see com.excilys.liferay.gatling.model.impl.SimulationModelImpl
 * @generated
 */
public interface SimulationModel extends BaseModel<Simulation> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a simulation model instance should use the {@link Simulation} interface instead.
	 */

	/**
	 * Returns the primary key of this simulation.
	 *
	 * @return the primary key of this simulation
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this simulation.
	 *
	 * @param primaryKey the primary key of this simulation
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the simulation ID of this simulation.
	 *
	 * @return the simulation ID of this simulation
	 */
	public long getSimulationId();

	/**
	 * Sets the simulation ID of this simulation.
	 *
	 * @param simulationId the simulation ID of this simulation
	 */
	public void setSimulationId(long simulationId);

	/**
	 * Returns the name of this simulation.
	 *
	 * @return the name of this simulation
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this simulation.
	 *
	 * @param name the name of this simulation
	 */
	public void setName(String name);

	/**
	 * Returns the feeder content of this simulation.
	 *
	 * @return the feeder content of this simulation
	 */
	@AutoEscape
	public String getFeederContent();

	/**
	 * Sets the feeder content of this simulation.
	 *
	 * @param feederContent the feeder content of this simulation
	 */
	public void setFeederContent(String feederContent);

	/**
	 * Returns the is feeder a file of this simulation.
	 *
	 * @return the is feeder a file of this simulation
	 */
	public boolean getIsFeederAFile();

	/**
	 * Returns <code>true</code> if this simulation is is feeder a file.
	 *
	 * @return <code>true</code> if this simulation is is feeder a file; <code>false</code> otherwise
	 */
	public boolean isIsFeederAFile();

	/**
	 * Sets whether this simulation is is feeder a file.
	 *
	 * @param isFeederAFile the is feeder a file of this simulation
	 */
	public void setIsFeederAFile(boolean isFeederAFile);

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
	public int compareTo(Simulation simulation);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Simulation> toCacheModel();

	@Override
	public Simulation toEscapedModel();

	@Override
	public Simulation toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}