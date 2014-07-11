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

package com.liferay.sample.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the Request service. Represents a row in the &quot;StressTool_Request&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.sample.model.impl.RequestModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.sample.model.impl.RequestImpl}.
 * </p>
 *
 * @author sana
 * @see Request
 * @see com.liferay.sample.model.impl.RequestImpl
 * @see com.liferay.sample.model.impl.RequestModelImpl
 * @generated
 */
public interface RequestModel extends BaseModel<Request> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a request model instance should use the {@link Request} interface instead.
	 */

	/**
	 * Returns the primary key of this request.
	 *
	 * @return the primary key of this request
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this request.
	 *
	 * @param primaryKey the primary key of this request
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the request_id of this request.
	 *
	 * @return the request_id of this request
	 */
	public long getRequest_id();

	/**
	 * Sets the request_id of this request.
	 *
	 * @param request_id the request_id of this request
	 */
	public void setRequest_id(long request_id);

	/**
	 * Returns the scenario_id of this request.
	 *
	 * @return the scenario_id of this request
	 */
	public long getScenario_id();

	/**
	 * Sets the scenario_id of this request.
	 *
	 * @param scenario_id the scenario_id of this request
	 */
	public void setScenario_id(long scenario_id);

	/**
	 * Returns the url of this request.
	 *
	 * @return the url of this request
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this request.
	 *
	 * @param url the url of this request
	 */
	public void setUrl(String url);

	/**
	 * Returns the rate of this request.
	 *
	 * @return the rate of this request
	 */
	public int getRate();

	/**
	 * Sets the rate of this request.
	 *
	 * @param rate the rate of this request
	 */
	public void setRate(int rate);

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
	public int compareTo(Request request);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Request> toCacheModel();

	@Override
	public Request toEscapedModel();

	@Override
	public Request toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}