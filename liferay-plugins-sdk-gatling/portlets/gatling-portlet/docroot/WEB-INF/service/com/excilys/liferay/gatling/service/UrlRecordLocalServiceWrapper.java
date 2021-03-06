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

package com.excilys.liferay.gatling.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UrlRecordLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UrlRecordLocalService
 * @generated
 */
public class UrlRecordLocalServiceWrapper implements UrlRecordLocalService,
	ServiceWrapper<UrlRecordLocalService> {
	public UrlRecordLocalServiceWrapper(
		UrlRecordLocalService urlRecordLocalService) {
		_urlRecordLocalService = urlRecordLocalService;
	}

	/**
	* Adds the url record to the database. Also notifies the appropriate model listeners.
	*
	* @param urlRecord the url record
	* @return the url record that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.excilys.liferay.gatling.model.UrlRecord addUrlRecord(
		com.excilys.liferay.gatling.model.UrlRecord urlRecord)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _urlRecordLocalService.addUrlRecord(urlRecord);
	}

	/**
	* Creates a new url record with the primary key. Does not add the url record to the database.
	*
	* @param urlRecordId the primary key for the new url record
	* @return the new url record
	*/
	@Override
	public com.excilys.liferay.gatling.model.UrlRecord createUrlRecord(
		long urlRecordId) {
		return _urlRecordLocalService.createUrlRecord(urlRecordId);
	}

	/**
	* Deletes the url record with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param urlRecordId the primary key of the url record
	* @return the url record that was removed
	* @throws PortalException if a url record with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.excilys.liferay.gatling.model.UrlRecord deleteUrlRecord(
		long urlRecordId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _urlRecordLocalService.deleteUrlRecord(urlRecordId);
	}

	/**
	* Deletes the url record from the database. Also notifies the appropriate model listeners.
	*
	* @param urlRecord the url record
	* @return the url record that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.excilys.liferay.gatling.model.UrlRecord deleteUrlRecord(
		com.excilys.liferay.gatling.model.UrlRecord urlRecord)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _urlRecordLocalService.deleteUrlRecord(urlRecord);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _urlRecordLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _urlRecordLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.UrlRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _urlRecordLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.UrlRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _urlRecordLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _urlRecordLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _urlRecordLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.excilys.liferay.gatling.model.UrlRecord fetchUrlRecord(
		long urlRecordId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _urlRecordLocalService.fetchUrlRecord(urlRecordId);
	}

	/**
	* Returns the url record with the primary key.
	*
	* @param urlRecordId the primary key of the url record
	* @return the url record
	* @throws PortalException if a url record with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.excilys.liferay.gatling.model.UrlRecord getUrlRecord(
		long urlRecordId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _urlRecordLocalService.getUrlRecord(urlRecordId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _urlRecordLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the url records.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.UrlRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of url records
	* @param end the upper bound of the range of url records (not inclusive)
	* @return the range of url records
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.excilys.liferay.gatling.model.UrlRecord> getUrlRecords(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _urlRecordLocalService.getUrlRecords(start, end);
	}

	/**
	* Returns the number of url records.
	*
	* @return the number of url records
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getUrlRecordsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _urlRecordLocalService.getUrlRecordsCount();
	}

	/**
	* Updates the url record in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param urlRecord the url record
	* @return the url record that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.excilys.liferay.gatling.model.UrlRecord updateUrlRecord(
		com.excilys.liferay.gatling.model.UrlRecord urlRecord)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _urlRecordLocalService.updateUrlRecord(urlRecord);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _urlRecordLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_urlRecordLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _urlRecordLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public UrlRecordLocalService getWrappedUrlRecordLocalService() {
		return _urlRecordLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedUrlRecordLocalService(
		UrlRecordLocalService urlRecordLocalService) {
		_urlRecordLocalService = urlRecordLocalService;
	}

	@Override
	public UrlRecordLocalService getWrappedService() {
		return _urlRecordLocalService;
	}

	@Override
	public void setWrappedService(UrlRecordLocalService urlRecordLocalService) {
		_urlRecordLocalService = urlRecordLocalService;
	}

	private UrlRecordLocalService _urlRecordLocalService;
}