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

package com.excilys.liferay.gatling.service.persistence;

import com.excilys.liferay.gatling.model.LinkUsecaseRequest;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the link usecase request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LinkUsecaseRequestPersistenceImpl
 * @see LinkUsecaseRequestUtil
 * @generated
 */
public interface LinkUsecaseRequestPersistence extends BasePersistence<LinkUsecaseRequest> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LinkUsecaseRequestUtil} to access the link usecase request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the link usecase requests where requestId = &#63; and weight &gt; &#63;.
	*
	* @param requestId the request ID
	* @param weight the weight
	* @return the matching link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.excilys.liferay.gatling.model.LinkUsecaseRequest> findByRequestIdAndUsed(
		long requestId, double weight)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the link usecase requests where requestId = &#63; and weight &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.LinkUsecaseRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param requestId the request ID
	* @param weight the weight
	* @param start the lower bound of the range of link usecase requests
	* @param end the upper bound of the range of link usecase requests (not inclusive)
	* @return the range of matching link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.excilys.liferay.gatling.model.LinkUsecaseRequest> findByRequestIdAndUsed(
		long requestId, double weight, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the link usecase requests where requestId = &#63; and weight &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.LinkUsecaseRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param requestId the request ID
	* @param weight the weight
	* @param start the lower bound of the range of link usecase requests
	* @param end the upper bound of the range of link usecase requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.excilys.liferay.gatling.model.LinkUsecaseRequest> findByRequestIdAndUsed(
		long requestId, double weight, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first link usecase request in the ordered set where requestId = &#63; and weight &gt; &#63;.
	*
	* @param requestId the request ID
	* @param weight the weight
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching link usecase request
	* @throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException if a matching link usecase request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest findByRequestIdAndUsed_First(
		long requestId, double weight,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first link usecase request in the ordered set where requestId = &#63; and weight &gt; &#63;.
	*
	* @param requestId the request ID
	* @param weight the weight
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching link usecase request, or <code>null</code> if a matching link usecase request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest fetchByRequestIdAndUsed_First(
		long requestId, double weight,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last link usecase request in the ordered set where requestId = &#63; and weight &gt; &#63;.
	*
	* @param requestId the request ID
	* @param weight the weight
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching link usecase request
	* @throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException if a matching link usecase request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest findByRequestIdAndUsed_Last(
		long requestId, double weight,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last link usecase request in the ordered set where requestId = &#63; and weight &gt; &#63;.
	*
	* @param requestId the request ID
	* @param weight the weight
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching link usecase request, or <code>null</code> if a matching link usecase request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest fetchByRequestIdAndUsed_Last(
		long requestId, double weight,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the link usecase requests before and after the current link usecase request in the ordered set where requestId = &#63; and weight &gt; &#63;.
	*
	* @param linkUsecaseRequestId the primary key of the current link usecase request
	* @param requestId the request ID
	* @param weight the weight
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next link usecase request
	* @throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException if a link usecase request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest[] findByRequestIdAndUsed_PrevAndNext(
		long linkUsecaseRequestId, long requestId, double weight,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the link usecase requests where requestId = &#63; and weight &gt; &#63; from the database.
	*
	* @param requestId the request ID
	* @param weight the weight
	* @throws SystemException if a system exception occurred
	*/
	public void removeByRequestIdAndUsed(long requestId, double weight)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of link usecase requests where requestId = &#63; and weight &gt; &#63;.
	*
	* @param requestId the request ID
	* @param weight the weight
	* @return the number of matching link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public int countByRequestIdAndUsed(long requestId, double weight)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the link usecase requests where requestId = &#63; and recordId = &#63;.
	*
	* @param requestId the request ID
	* @param recordId the record ID
	* @return the matching link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.excilys.liferay.gatling.model.LinkUsecaseRequest> findByRequestIdAndrecordId(
		long requestId, long recordId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the link usecase requests where requestId = &#63; and recordId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.LinkUsecaseRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param requestId the request ID
	* @param recordId the record ID
	* @param start the lower bound of the range of link usecase requests
	* @param end the upper bound of the range of link usecase requests (not inclusive)
	* @return the range of matching link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.excilys.liferay.gatling.model.LinkUsecaseRequest> findByRequestIdAndrecordId(
		long requestId, long recordId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the link usecase requests where requestId = &#63; and recordId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.LinkUsecaseRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param requestId the request ID
	* @param recordId the record ID
	* @param start the lower bound of the range of link usecase requests
	* @param end the upper bound of the range of link usecase requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.excilys.liferay.gatling.model.LinkUsecaseRequest> findByRequestIdAndrecordId(
		long requestId, long recordId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first link usecase request in the ordered set where requestId = &#63; and recordId = &#63;.
	*
	* @param requestId the request ID
	* @param recordId the record ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching link usecase request
	* @throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException if a matching link usecase request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest findByRequestIdAndrecordId_First(
		long requestId, long recordId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first link usecase request in the ordered set where requestId = &#63; and recordId = &#63;.
	*
	* @param requestId the request ID
	* @param recordId the record ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching link usecase request, or <code>null</code> if a matching link usecase request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest fetchByRequestIdAndrecordId_First(
		long requestId, long recordId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last link usecase request in the ordered set where requestId = &#63; and recordId = &#63;.
	*
	* @param requestId the request ID
	* @param recordId the record ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching link usecase request
	* @throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException if a matching link usecase request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest findByRequestIdAndrecordId_Last(
		long requestId, long recordId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last link usecase request in the ordered set where requestId = &#63; and recordId = &#63;.
	*
	* @param requestId the request ID
	* @param recordId the record ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching link usecase request, or <code>null</code> if a matching link usecase request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest fetchByRequestIdAndrecordId_Last(
		long requestId, long recordId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the link usecase requests before and after the current link usecase request in the ordered set where requestId = &#63; and recordId = &#63;.
	*
	* @param linkUsecaseRequestId the primary key of the current link usecase request
	* @param requestId the request ID
	* @param recordId the record ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next link usecase request
	* @throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException if a link usecase request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest[] findByRequestIdAndrecordId_PrevAndNext(
		long linkUsecaseRequestId, long requestId, long recordId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the link usecase requests where requestId = &#63; and recordId = &#63; from the database.
	*
	* @param requestId the request ID
	* @param recordId the record ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByRequestIdAndrecordId(long requestId, long recordId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of link usecase requests where requestId = &#63; and recordId = &#63;.
	*
	* @param requestId the request ID
	* @param recordId the record ID
	* @return the number of matching link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public int countByRequestIdAndrecordId(long requestId, long recordId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the link usecase requests where requestId = &#63;.
	*
	* @param requestId the request ID
	* @return the matching link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.excilys.liferay.gatling.model.LinkUsecaseRequest> findByRequestId(
		long requestId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the link usecase requests where requestId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.LinkUsecaseRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param requestId the request ID
	* @param start the lower bound of the range of link usecase requests
	* @param end the upper bound of the range of link usecase requests (not inclusive)
	* @return the range of matching link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.excilys.liferay.gatling.model.LinkUsecaseRequest> findByRequestId(
		long requestId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the link usecase requests where requestId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.LinkUsecaseRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param requestId the request ID
	* @param start the lower bound of the range of link usecase requests
	* @param end the upper bound of the range of link usecase requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.excilys.liferay.gatling.model.LinkUsecaseRequest> findByRequestId(
		long requestId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first link usecase request in the ordered set where requestId = &#63;.
	*
	* @param requestId the request ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching link usecase request
	* @throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException if a matching link usecase request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest findByRequestId_First(
		long requestId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first link usecase request in the ordered set where requestId = &#63;.
	*
	* @param requestId the request ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching link usecase request, or <code>null</code> if a matching link usecase request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest fetchByRequestId_First(
		long requestId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last link usecase request in the ordered set where requestId = &#63;.
	*
	* @param requestId the request ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching link usecase request
	* @throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException if a matching link usecase request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest findByRequestId_Last(
		long requestId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last link usecase request in the ordered set where requestId = &#63;.
	*
	* @param requestId the request ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching link usecase request, or <code>null</code> if a matching link usecase request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest fetchByRequestId_Last(
		long requestId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the link usecase requests before and after the current link usecase request in the ordered set where requestId = &#63;.
	*
	* @param linkUsecaseRequestId the primary key of the current link usecase request
	* @param requestId the request ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next link usecase request
	* @throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException if a link usecase request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest[] findByRequestId_PrevAndNext(
		long linkUsecaseRequestId, long requestId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the link usecase requests where requestId = &#63; from the database.
	*
	* @param requestId the request ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByRequestId(long requestId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of link usecase requests where requestId = &#63;.
	*
	* @param requestId the request ID
	* @return the number of matching link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public int countByRequestId(long requestId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the link usecase requests where recordId = &#63;.
	*
	* @param recordId the record ID
	* @return the matching link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.excilys.liferay.gatling.model.LinkUsecaseRequest> findByRecordId(
		long recordId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the link usecase requests where recordId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.LinkUsecaseRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param recordId the record ID
	* @param start the lower bound of the range of link usecase requests
	* @param end the upper bound of the range of link usecase requests (not inclusive)
	* @return the range of matching link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.excilys.liferay.gatling.model.LinkUsecaseRequest> findByRecordId(
		long recordId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the link usecase requests where recordId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.LinkUsecaseRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param recordId the record ID
	* @param start the lower bound of the range of link usecase requests
	* @param end the upper bound of the range of link usecase requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.excilys.liferay.gatling.model.LinkUsecaseRequest> findByRecordId(
		long recordId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first link usecase request in the ordered set where recordId = &#63;.
	*
	* @param recordId the record ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching link usecase request
	* @throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException if a matching link usecase request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest findByRecordId_First(
		long recordId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first link usecase request in the ordered set where recordId = &#63;.
	*
	* @param recordId the record ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching link usecase request, or <code>null</code> if a matching link usecase request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest fetchByRecordId_First(
		long recordId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last link usecase request in the ordered set where recordId = &#63;.
	*
	* @param recordId the record ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching link usecase request
	* @throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException if a matching link usecase request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest findByRecordId_Last(
		long recordId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last link usecase request in the ordered set where recordId = &#63;.
	*
	* @param recordId the record ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching link usecase request, or <code>null</code> if a matching link usecase request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest fetchByRecordId_Last(
		long recordId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the link usecase requests before and after the current link usecase request in the ordered set where recordId = &#63;.
	*
	* @param linkUsecaseRequestId the primary key of the current link usecase request
	* @param recordId the record ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next link usecase request
	* @throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException if a link usecase request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest[] findByRecordId_PrevAndNext(
		long linkUsecaseRequestId, long recordId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the link usecase requests where recordId = &#63; from the database.
	*
	* @param recordId the record ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByRecordId(long recordId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of link usecase requests where recordId = &#63;.
	*
	* @param recordId the record ID
	* @return the number of matching link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public int countByRecordId(long recordId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the link usecase request in the entity cache if it is enabled.
	*
	* @param linkUsecaseRequest the link usecase request
	*/
	public void cacheResult(
		com.excilys.liferay.gatling.model.LinkUsecaseRequest linkUsecaseRequest);

	/**
	* Caches the link usecase requests in the entity cache if it is enabled.
	*
	* @param linkUsecaseRequests the link usecase requests
	*/
	public void cacheResult(
		java.util.List<com.excilys.liferay.gatling.model.LinkUsecaseRequest> linkUsecaseRequests);

	/**
	* Creates a new link usecase request with the primary key. Does not add the link usecase request to the database.
	*
	* @param linkUsecaseRequestId the primary key for the new link usecase request
	* @return the new link usecase request
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest create(
		long linkUsecaseRequestId);

	/**
	* Removes the link usecase request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param linkUsecaseRequestId the primary key of the link usecase request
	* @return the link usecase request that was removed
	* @throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException if a link usecase request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest remove(
		long linkUsecaseRequestId)
		throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.excilys.liferay.gatling.model.LinkUsecaseRequest updateImpl(
		com.excilys.liferay.gatling.model.LinkUsecaseRequest linkUsecaseRequest)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the link usecase request with the primary key or throws a {@link com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException} if it could not be found.
	*
	* @param linkUsecaseRequestId the primary key of the link usecase request
	* @return the link usecase request
	* @throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException if a link usecase request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest findByPrimaryKey(
		long linkUsecaseRequestId)
		throws com.excilys.liferay.gatling.NoSuchLinkUsecaseRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the link usecase request with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param linkUsecaseRequestId the primary key of the link usecase request
	* @return the link usecase request, or <code>null</code> if a link usecase request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.excilys.liferay.gatling.model.LinkUsecaseRequest fetchByPrimaryKey(
		long linkUsecaseRequestId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the link usecase requests.
	*
	* @return the link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.excilys.liferay.gatling.model.LinkUsecaseRequest> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the link usecase requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.LinkUsecaseRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of link usecase requests
	* @param end the upper bound of the range of link usecase requests (not inclusive)
	* @return the range of link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.excilys.liferay.gatling.model.LinkUsecaseRequest> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the link usecase requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.LinkUsecaseRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of link usecase requests
	* @param end the upper bound of the range of link usecase requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.excilys.liferay.gatling.model.LinkUsecaseRequest> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the link usecase requests from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of link usecase requests.
	*
	* @return the number of link usecase requests
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}