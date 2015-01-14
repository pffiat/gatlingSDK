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

import com.excilys.liferay.gatling.NoSuchRequestException;
import com.excilys.liferay.gatling.model.Request;
import com.excilys.liferay.gatling.model.impl.RequestImpl;
import com.excilys.liferay.gatling.model.impl.RequestModelImpl;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RequestPersistence
 * @see RequestUtil
 * @generated
 */
public class RequestPersistenceImpl extends BasePersistenceImpl<Request>
	implements RequestPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RequestUtil} to access the request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RequestImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, RequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, RequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTPLID =
		new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, RequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByParentPlid",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPLID =
		new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, RequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByParentPlid",
			new String[] { Long.class.getName() },
			RequestModelImpl.PARENTPLID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARENTPLID = new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByParentPlid",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the requests where parentPlId = &#63;.
	 *
	 * @param parentPlId the parent pl ID
	 * @return the matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByParentPlid(long parentPlId)
		throws SystemException {
		return findByParentPlid(parentPlId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the requests where parentPlId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.RequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentPlId the parent pl ID
	 * @param start the lower bound of the range of requests
	 * @param end the upper bound of the range of requests (not inclusive)
	 * @return the range of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByParentPlid(long parentPlId, int start, int end)
		throws SystemException {
		return findByParentPlid(parentPlId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the requests where parentPlId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.RequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentPlId the parent pl ID
	 * @param start the lower bound of the range of requests
	 * @param end the upper bound of the range of requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByParentPlid(long parentPlId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPLID;
			finderArgs = new Object[] { parentPlId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTPLID;
			finderArgs = new Object[] { parentPlId, start, end, orderByComparator };
		}

		List<Request> list = (List<Request>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Request request : list) {
				if ((parentPlId != request.getParentPlId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_REQUEST_WHERE);

			query.append(_FINDER_COLUMN_PARENTPLID_PARENTPLID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentPlId);

				if (!pagination) {
					list = (List<Request>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Request>(list);
				}
				else {
					list = (List<Request>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first request in the ordered set where parentPlId = &#63;.
	 *
	 * @param parentPlId the parent pl ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request findByParentPlid_First(long parentPlId,
		OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = fetchByParentPlid_First(parentPlId, orderByComparator);

		if (request != null) {
			return request;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentPlId=");
		msg.append(parentPlId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRequestException(msg.toString());
	}

	/**
	 * Returns the first request in the ordered set where parentPlId = &#63;.
	 *
	 * @param parentPlId the parent pl ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching request, or <code>null</code> if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request fetchByParentPlid_First(long parentPlId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Request> list = findByParentPlid(parentPlId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last request in the ordered set where parentPlId = &#63;.
	 *
	 * @param parentPlId the parent pl ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request findByParentPlid_Last(long parentPlId,
		OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = fetchByParentPlid_Last(parentPlId, orderByComparator);

		if (request != null) {
			return request;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentPlId=");
		msg.append(parentPlId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRequestException(msg.toString());
	}

	/**
	 * Returns the last request in the ordered set where parentPlId = &#63;.
	 *
	 * @param parentPlId the parent pl ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching request, or <code>null</code> if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request fetchByParentPlid_Last(long parentPlId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByParentPlid(parentPlId);

		if (count == 0) {
			return null;
		}

		List<Request> list = findByParentPlid(parentPlId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the requests before and after the current request in the ordered set where parentPlId = &#63;.
	 *
	 * @param requestId the primary key of the current request
	 * @param parentPlId the parent pl ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request[] findByParentPlid_PrevAndNext(long requestId,
		long parentPlId, OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = findByPrimaryKey(requestId);

		Session session = null;

		try {
			session = openSession();

			Request[] array = new RequestImpl[3];

			array[0] = getByParentPlid_PrevAndNext(session, request,
					parentPlId, orderByComparator, true);

			array[1] = request;

			array[2] = getByParentPlid_PrevAndNext(session, request,
					parentPlId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Request getByParentPlid_PrevAndNext(Session session,
		Request request, long parentPlId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REQUEST_WHERE);

		query.append(_FINDER_COLUMN_PARENTPLID_PARENTPLID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(RequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentPlId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(request);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Request> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the requests where parentPlId = &#63; from the database.
	 *
	 * @param parentPlId the parent pl ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByParentPlid(long parentPlId) throws SystemException {
		for (Request request : findByParentPlid(parentPlId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(request);
		}
	}

	/**
	 * Returns the number of requests where parentPlId = &#63;.
	 *
	 * @param parentPlId the parent pl ID
	 * @return the number of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByParentPlid(long parentPlId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PARENTPLID;

		Object[] finderArgs = new Object[] { parentPlId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REQUEST_WHERE);

			query.append(_FINDER_COLUMN_PARENTPLID_PARENTPLID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentPlId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_PARENTPLID_PARENTPLID_2 = "request.parentPlId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTPLIDANDSCENARIO =
		new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, RequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByParentPlidAndScenario",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPLIDANDSCENARIO =
		new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, RequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByParentPlidAndScenario",
			new String[] { Long.class.getName(), Long.class.getName() },
			RequestModelImpl.PARENTPLID_COLUMN_BITMASK |
			RequestModelImpl.SCENARIOID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARENTPLIDANDSCENARIO = new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByParentPlidAndScenario",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the requests where parentPlId = &#63; and scenarioId = &#63;.
	 *
	 * @param parentPlId the parent pl ID
	 * @param scenarioId the scenario ID
	 * @return the matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByParentPlidAndScenario(long parentPlId,
		long scenarioId) throws SystemException {
		return findByParentPlidAndScenario(parentPlId, scenarioId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the requests where parentPlId = &#63; and scenarioId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.RequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentPlId the parent pl ID
	 * @param scenarioId the scenario ID
	 * @param start the lower bound of the range of requests
	 * @param end the upper bound of the range of requests (not inclusive)
	 * @return the range of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByParentPlidAndScenario(long parentPlId,
		long scenarioId, int start, int end) throws SystemException {
		return findByParentPlidAndScenario(parentPlId, scenarioId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the requests where parentPlId = &#63; and scenarioId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.RequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentPlId the parent pl ID
	 * @param scenarioId the scenario ID
	 * @param start the lower bound of the range of requests
	 * @param end the upper bound of the range of requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByParentPlidAndScenario(long parentPlId,
		long scenarioId, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPLIDANDSCENARIO;
			finderArgs = new Object[] { parentPlId, scenarioId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTPLIDANDSCENARIO;
			finderArgs = new Object[] {
					parentPlId, scenarioId,
					
					start, end, orderByComparator
				};
		}

		List<Request> list = (List<Request>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Request request : list) {
				if ((parentPlId != request.getParentPlId()) ||
						(scenarioId != request.getScenarioId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_REQUEST_WHERE);

			query.append(_FINDER_COLUMN_PARENTPLIDANDSCENARIO_PARENTPLID_2);

			query.append(_FINDER_COLUMN_PARENTPLIDANDSCENARIO_SCENARIOID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentPlId);

				qPos.add(scenarioId);

				if (!pagination) {
					list = (List<Request>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Request>(list);
				}
				else {
					list = (List<Request>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first request in the ordered set where parentPlId = &#63; and scenarioId = &#63;.
	 *
	 * @param parentPlId the parent pl ID
	 * @param scenarioId the scenario ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request findByParentPlidAndScenario_First(long parentPlId,
		long scenarioId, OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = fetchByParentPlidAndScenario_First(parentPlId,
				scenarioId, orderByComparator);

		if (request != null) {
			return request;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentPlId=");
		msg.append(parentPlId);

		msg.append(", scenarioId=");
		msg.append(scenarioId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRequestException(msg.toString());
	}

	/**
	 * Returns the first request in the ordered set where parentPlId = &#63; and scenarioId = &#63;.
	 *
	 * @param parentPlId the parent pl ID
	 * @param scenarioId the scenario ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching request, or <code>null</code> if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request fetchByParentPlidAndScenario_First(long parentPlId,
		long scenarioId, OrderByComparator orderByComparator)
		throws SystemException {
		List<Request> list = findByParentPlidAndScenario(parentPlId,
				scenarioId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last request in the ordered set where parentPlId = &#63; and scenarioId = &#63;.
	 *
	 * @param parentPlId the parent pl ID
	 * @param scenarioId the scenario ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request findByParentPlidAndScenario_Last(long parentPlId,
		long scenarioId, OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = fetchByParentPlidAndScenario_Last(parentPlId,
				scenarioId, orderByComparator);

		if (request != null) {
			return request;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentPlId=");
		msg.append(parentPlId);

		msg.append(", scenarioId=");
		msg.append(scenarioId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRequestException(msg.toString());
	}

	/**
	 * Returns the last request in the ordered set where parentPlId = &#63; and scenarioId = &#63;.
	 *
	 * @param parentPlId the parent pl ID
	 * @param scenarioId the scenario ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching request, or <code>null</code> if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request fetchByParentPlidAndScenario_Last(long parentPlId,
		long scenarioId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByParentPlidAndScenario(parentPlId, scenarioId);

		if (count == 0) {
			return null;
		}

		List<Request> list = findByParentPlidAndScenario(parentPlId,
				scenarioId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the requests before and after the current request in the ordered set where parentPlId = &#63; and scenarioId = &#63;.
	 *
	 * @param requestId the primary key of the current request
	 * @param parentPlId the parent pl ID
	 * @param scenarioId the scenario ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request[] findByParentPlidAndScenario_PrevAndNext(long requestId,
		long parentPlId, long scenarioId, OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = findByPrimaryKey(requestId);

		Session session = null;

		try {
			session = openSession();

			Request[] array = new RequestImpl[3];

			array[0] = getByParentPlidAndScenario_PrevAndNext(session, request,
					parentPlId, scenarioId, orderByComparator, true);

			array[1] = request;

			array[2] = getByParentPlidAndScenario_PrevAndNext(session, request,
					parentPlId, scenarioId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Request getByParentPlidAndScenario_PrevAndNext(Session session,
		Request request, long parentPlId, long scenarioId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REQUEST_WHERE);

		query.append(_FINDER_COLUMN_PARENTPLIDANDSCENARIO_PARENTPLID_2);

		query.append(_FINDER_COLUMN_PARENTPLIDANDSCENARIO_SCENARIOID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(RequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentPlId);

		qPos.add(scenarioId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(request);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Request> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the requests where parentPlId = &#63; and scenarioId = &#63; from the database.
	 *
	 * @param parentPlId the parent pl ID
	 * @param scenarioId the scenario ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByParentPlidAndScenario(long parentPlId, long scenarioId)
		throws SystemException {
		for (Request request : findByParentPlidAndScenario(parentPlId,
				scenarioId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(request);
		}
	}

	/**
	 * Returns the number of requests where parentPlId = &#63; and scenarioId = &#63;.
	 *
	 * @param parentPlId the parent pl ID
	 * @param scenarioId the scenario ID
	 * @return the number of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByParentPlidAndScenario(long parentPlId, long scenarioId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PARENTPLIDANDSCENARIO;

		Object[] finderArgs = new Object[] { parentPlId, scenarioId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REQUEST_WHERE);

			query.append(_FINDER_COLUMN_PARENTPLIDANDSCENARIO_PARENTPLID_2);

			query.append(_FINDER_COLUMN_PARENTPLIDANDSCENARIO_SCENARIOID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentPlId);

				qPos.add(scenarioId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_PARENTPLIDANDSCENARIO_PARENTPLID_2 =
		"request.parentPlId = ? AND ";
	private static final String _FINDER_COLUMN_PARENTPLIDANDSCENARIO_SCENARIOID_2 =
		"request.scenarioId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCENARIOID =
		new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, RequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByScenarioId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCENARIOID =
		new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, RequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByScenarioId",
			new String[] { Long.class.getName() },
			RequestModelImpl.SCENARIOID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCENARIOID = new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByScenarioId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the requests where scenarioId = &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @return the matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByScenarioId(long scenarioId)
		throws SystemException {
		return findByScenarioId(scenarioId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the requests where scenarioId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.RequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scenarioId the scenario ID
	 * @param start the lower bound of the range of requests
	 * @param end the upper bound of the range of requests (not inclusive)
	 * @return the range of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByScenarioId(long scenarioId, int start, int end)
		throws SystemException {
		return findByScenarioId(scenarioId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the requests where scenarioId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.RequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scenarioId the scenario ID
	 * @param start the lower bound of the range of requests
	 * @param end the upper bound of the range of requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByScenarioId(long scenarioId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCENARIOID;
			finderArgs = new Object[] { scenarioId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCENARIOID;
			finderArgs = new Object[] { scenarioId, start, end, orderByComparator };
		}

		List<Request> list = (List<Request>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Request request : list) {
				if ((scenarioId != request.getScenarioId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_REQUEST_WHERE);

			query.append(_FINDER_COLUMN_SCENARIOID_SCENARIOID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scenarioId);

				if (!pagination) {
					list = (List<Request>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Request>(list);
				}
				else {
					list = (List<Request>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first request in the ordered set where scenarioId = &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request findByScenarioId_First(long scenarioId,
		OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = fetchByScenarioId_First(scenarioId, orderByComparator);

		if (request != null) {
			return request;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scenarioId=");
		msg.append(scenarioId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRequestException(msg.toString());
	}

	/**
	 * Returns the first request in the ordered set where scenarioId = &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching request, or <code>null</code> if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request fetchByScenarioId_First(long scenarioId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Request> list = findByScenarioId(scenarioId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last request in the ordered set where scenarioId = &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request findByScenarioId_Last(long scenarioId,
		OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = fetchByScenarioId_Last(scenarioId, orderByComparator);

		if (request != null) {
			return request;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scenarioId=");
		msg.append(scenarioId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRequestException(msg.toString());
	}

	/**
	 * Returns the last request in the ordered set where scenarioId = &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching request, or <code>null</code> if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request fetchByScenarioId_Last(long scenarioId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByScenarioId(scenarioId);

		if (count == 0) {
			return null;
		}

		List<Request> list = findByScenarioId(scenarioId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the requests before and after the current request in the ordered set where scenarioId = &#63;.
	 *
	 * @param requestId the primary key of the current request
	 * @param scenarioId the scenario ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request[] findByScenarioId_PrevAndNext(long requestId,
		long scenarioId, OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = findByPrimaryKey(requestId);

		Session session = null;

		try {
			session = openSession();

			Request[] array = new RequestImpl[3];

			array[0] = getByScenarioId_PrevAndNext(session, request,
					scenarioId, orderByComparator, true);

			array[1] = request;

			array[2] = getByScenarioId_PrevAndNext(session, request,
					scenarioId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Request getByScenarioId_PrevAndNext(Session session,
		Request request, long scenarioId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REQUEST_WHERE);

		query.append(_FINDER_COLUMN_SCENARIOID_SCENARIOID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(RequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(scenarioId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(request);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Request> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the requests where scenarioId = &#63; from the database.
	 *
	 * @param scenarioId the scenario ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByScenarioId(long scenarioId) throws SystemException {
		for (Request request : findByScenarioId(scenarioId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(request);
		}
	}

	/**
	 * Returns the number of requests where scenarioId = &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @return the number of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByScenarioId(long scenarioId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SCENARIOID;

		Object[] finderArgs = new Object[] { scenarioId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REQUEST_WHERE);

			query.append(_FINDER_COLUMN_SCENARIOID_SCENARIOID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scenarioId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_SCENARIOID_SCENARIOID_2 = "request.scenarioId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCENARIOIDANDUSED =
		new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, RequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByScenarioIdAndUsed",
			new String[] {
				Long.class.getName(), Double.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_SCENARIOIDANDUSED =
		new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByScenarioIdAndUsed",
			new String[] { Long.class.getName(), Double.class.getName() });

	/**
	 * Returns all the requests where scenarioId = &#63; and weight &gt; &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param weight the weight
	 * @return the matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByScenarioIdAndUsed(long scenarioId, double weight)
		throws SystemException {
		return findByScenarioIdAndUsed(scenarioId, weight, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the requests where scenarioId = &#63; and weight &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.RequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scenarioId the scenario ID
	 * @param weight the weight
	 * @param start the lower bound of the range of requests
	 * @param end the upper bound of the range of requests (not inclusive)
	 * @return the range of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByScenarioIdAndUsed(long scenarioId,
		double weight, int start, int end) throws SystemException {
		return findByScenarioIdAndUsed(scenarioId, weight, start, end, null);
	}

	/**
	 * Returns an ordered range of all the requests where scenarioId = &#63; and weight &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.RequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scenarioId the scenario ID
	 * @param weight the weight
	 * @param start the lower bound of the range of requests
	 * @param end the upper bound of the range of requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByScenarioIdAndUsed(long scenarioId,
		double weight, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCENARIOIDANDUSED;
		finderArgs = new Object[] {
				scenarioId, weight,
				
				start, end, orderByComparator
			};

		List<Request> list = (List<Request>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Request request : list) {
				if ((scenarioId != request.getScenarioId()) ||
						(weight >= request.getWeight())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_REQUEST_WHERE);

			query.append(_FINDER_COLUMN_SCENARIOIDANDUSED_SCENARIOID_2);

			query.append(_FINDER_COLUMN_SCENARIOIDANDUSED_WEIGHT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scenarioId);

				qPos.add(weight);

				if (!pagination) {
					list = (List<Request>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Request>(list);
				}
				else {
					list = (List<Request>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first request in the ordered set where scenarioId = &#63; and weight &gt; &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param weight the weight
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request findByScenarioIdAndUsed_First(long scenarioId,
		double weight, OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = fetchByScenarioIdAndUsed_First(scenarioId, weight,
				orderByComparator);

		if (request != null) {
			return request;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scenarioId=");
		msg.append(scenarioId);

		msg.append(", weight=");
		msg.append(weight);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRequestException(msg.toString());
	}

	/**
	 * Returns the first request in the ordered set where scenarioId = &#63; and weight &gt; &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param weight the weight
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching request, or <code>null</code> if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request fetchByScenarioIdAndUsed_First(long scenarioId,
		double weight, OrderByComparator orderByComparator)
		throws SystemException {
		List<Request> list = findByScenarioIdAndUsed(scenarioId, weight, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last request in the ordered set where scenarioId = &#63; and weight &gt; &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param weight the weight
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request findByScenarioIdAndUsed_Last(long scenarioId, double weight,
		OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = fetchByScenarioIdAndUsed_Last(scenarioId, weight,
				orderByComparator);

		if (request != null) {
			return request;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scenarioId=");
		msg.append(scenarioId);

		msg.append(", weight=");
		msg.append(weight);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRequestException(msg.toString());
	}

	/**
	 * Returns the last request in the ordered set where scenarioId = &#63; and weight &gt; &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param weight the weight
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching request, or <code>null</code> if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request fetchByScenarioIdAndUsed_Last(long scenarioId,
		double weight, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByScenarioIdAndUsed(scenarioId, weight);

		if (count == 0) {
			return null;
		}

		List<Request> list = findByScenarioIdAndUsed(scenarioId, weight,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the requests before and after the current request in the ordered set where scenarioId = &#63; and weight &gt; &#63;.
	 *
	 * @param requestId the primary key of the current request
	 * @param scenarioId the scenario ID
	 * @param weight the weight
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request[] findByScenarioIdAndUsed_PrevAndNext(long requestId,
		long scenarioId, double weight, OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = findByPrimaryKey(requestId);

		Session session = null;

		try {
			session = openSession();

			Request[] array = new RequestImpl[3];

			array[0] = getByScenarioIdAndUsed_PrevAndNext(session, request,
					scenarioId, weight, orderByComparator, true);

			array[1] = request;

			array[2] = getByScenarioIdAndUsed_PrevAndNext(session, request,
					scenarioId, weight, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Request getByScenarioIdAndUsed_PrevAndNext(Session session,
		Request request, long scenarioId, double weight,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REQUEST_WHERE);

		query.append(_FINDER_COLUMN_SCENARIOIDANDUSED_SCENARIOID_2);

		query.append(_FINDER_COLUMN_SCENARIOIDANDUSED_WEIGHT_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(RequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(scenarioId);

		qPos.add(weight);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(request);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Request> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the requests where scenarioId = &#63; and weight &gt; &#63; from the database.
	 *
	 * @param scenarioId the scenario ID
	 * @param weight the weight
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByScenarioIdAndUsed(long scenarioId, double weight)
		throws SystemException {
		for (Request request : findByScenarioIdAndUsed(scenarioId, weight,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(request);
		}
	}

	/**
	 * Returns the number of requests where scenarioId = &#63; and weight &gt; &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param weight the weight
	 * @return the number of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByScenarioIdAndUsed(long scenarioId, double weight)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_SCENARIOIDANDUSED;

		Object[] finderArgs = new Object[] { scenarioId, weight };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REQUEST_WHERE);

			query.append(_FINDER_COLUMN_SCENARIOIDANDUSED_SCENARIOID_2);

			query.append(_FINDER_COLUMN_SCENARIOIDANDUSED_WEIGHT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scenarioId);

				qPos.add(weight);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_SCENARIOIDANDUSED_SCENARIOID_2 = "request.scenarioId = ? AND ";
	private static final String _FINDER_COLUMN_SCENARIOIDANDUSED_WEIGHT_2 = "request.weight > ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCENARIOIDANDISNOTPORTLET =
		new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, RequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByScenarioIdAndIsNotPortlet",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCENARIOIDANDISNOTPORTLET =
		new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, RequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByScenarioIdAndIsNotPortlet",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			RequestModelImpl.SCENARIOID_COLUMN_BITMASK |
			RequestModelImpl.PORTLET_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCENARIOIDANDISNOTPORTLET =
		new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByScenarioIdAndIsNotPortlet",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the requests where scenarioId = &#63; and portlet = &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @return the matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByScenarioIdAndIsNotPortlet(long scenarioId,
		boolean portlet) throws SystemException {
		return findByScenarioIdAndIsNotPortlet(scenarioId, portlet,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the requests where scenarioId = &#63; and portlet = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.RequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param start the lower bound of the range of requests
	 * @param end the upper bound of the range of requests (not inclusive)
	 * @return the range of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByScenarioIdAndIsNotPortlet(long scenarioId,
		boolean portlet, int start, int end) throws SystemException {
		return findByScenarioIdAndIsNotPortlet(scenarioId, portlet, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the requests where scenarioId = &#63; and portlet = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.RequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param start the lower bound of the range of requests
	 * @param end the upper bound of the range of requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByScenarioIdAndIsNotPortlet(long scenarioId,
		boolean portlet, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCENARIOIDANDISNOTPORTLET;
			finderArgs = new Object[] { scenarioId, portlet };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCENARIOIDANDISNOTPORTLET;
			finderArgs = new Object[] {
					scenarioId, portlet,
					
					start, end, orderByComparator
				};
		}

		List<Request> list = (List<Request>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Request request : list) {
				if ((scenarioId != request.getScenarioId()) ||
						(portlet != request.getPortlet())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_REQUEST_WHERE);

			query.append(_FINDER_COLUMN_SCENARIOIDANDISNOTPORTLET_SCENARIOID_2);

			query.append(_FINDER_COLUMN_SCENARIOIDANDISNOTPORTLET_PORTLET_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scenarioId);

				qPos.add(portlet);

				if (!pagination) {
					list = (List<Request>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Request>(list);
				}
				else {
					list = (List<Request>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first request in the ordered set where scenarioId = &#63; and portlet = &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request findByScenarioIdAndIsNotPortlet_First(long scenarioId,
		boolean portlet, OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = fetchByScenarioIdAndIsNotPortlet_First(scenarioId,
				portlet, orderByComparator);

		if (request != null) {
			return request;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scenarioId=");
		msg.append(scenarioId);

		msg.append(", portlet=");
		msg.append(portlet);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRequestException(msg.toString());
	}

	/**
	 * Returns the first request in the ordered set where scenarioId = &#63; and portlet = &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching request, or <code>null</code> if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request fetchByScenarioIdAndIsNotPortlet_First(long scenarioId,
		boolean portlet, OrderByComparator orderByComparator)
		throws SystemException {
		List<Request> list = findByScenarioIdAndIsNotPortlet(scenarioId,
				portlet, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last request in the ordered set where scenarioId = &#63; and portlet = &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request findByScenarioIdAndIsNotPortlet_Last(long scenarioId,
		boolean portlet, OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = fetchByScenarioIdAndIsNotPortlet_Last(scenarioId,
				portlet, orderByComparator);

		if (request != null) {
			return request;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scenarioId=");
		msg.append(scenarioId);

		msg.append(", portlet=");
		msg.append(portlet);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRequestException(msg.toString());
	}

	/**
	 * Returns the last request in the ordered set where scenarioId = &#63; and portlet = &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching request, or <code>null</code> if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request fetchByScenarioIdAndIsNotPortlet_Last(long scenarioId,
		boolean portlet, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByScenarioIdAndIsNotPortlet(scenarioId, portlet);

		if (count == 0) {
			return null;
		}

		List<Request> list = findByScenarioIdAndIsNotPortlet(scenarioId,
				portlet, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the requests before and after the current request in the ordered set where scenarioId = &#63; and portlet = &#63;.
	 *
	 * @param requestId the primary key of the current request
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request[] findByScenarioIdAndIsNotPortlet_PrevAndNext(
		long requestId, long scenarioId, boolean portlet,
		OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = findByPrimaryKey(requestId);

		Session session = null;

		try {
			session = openSession();

			Request[] array = new RequestImpl[3];

			array[0] = getByScenarioIdAndIsNotPortlet_PrevAndNext(session,
					request, scenarioId, portlet, orderByComparator, true);

			array[1] = request;

			array[2] = getByScenarioIdAndIsNotPortlet_PrevAndNext(session,
					request, scenarioId, portlet, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Request getByScenarioIdAndIsNotPortlet_PrevAndNext(
		Session session, Request request, long scenarioId, boolean portlet,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REQUEST_WHERE);

		query.append(_FINDER_COLUMN_SCENARIOIDANDISNOTPORTLET_SCENARIOID_2);

		query.append(_FINDER_COLUMN_SCENARIOIDANDISNOTPORTLET_PORTLET_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(RequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(scenarioId);

		qPos.add(portlet);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(request);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Request> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the requests where scenarioId = &#63; and portlet = &#63; from the database.
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByScenarioIdAndIsNotPortlet(long scenarioId,
		boolean portlet) throws SystemException {
		for (Request request : findByScenarioIdAndIsNotPortlet(scenarioId,
				portlet, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(request);
		}
	}

	/**
	 * Returns the number of requests where scenarioId = &#63; and portlet = &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @return the number of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByScenarioIdAndIsNotPortlet(long scenarioId, boolean portlet)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SCENARIOIDANDISNOTPORTLET;

		Object[] finderArgs = new Object[] { scenarioId, portlet };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REQUEST_WHERE);

			query.append(_FINDER_COLUMN_SCENARIOIDANDISNOTPORTLET_SCENARIOID_2);

			query.append(_FINDER_COLUMN_SCENARIOIDANDISNOTPORTLET_PORTLET_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scenarioId);

				qPos.add(portlet);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_SCENARIOIDANDISNOTPORTLET_SCENARIOID_2 =
		"request.scenarioId = ? AND ";
	private static final String _FINDER_COLUMN_SCENARIOIDANDISNOTPORTLET_PORTLET_2 =
		"request.portlet = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCENARIOIDANDUSEDANDISNOTPORTLET =
		new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, RequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByScenarioIdAndUsedAndIsNotPortlet",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Double.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_SCENARIOIDANDUSEDANDISNOTPORTLET =
		new FinderPath(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByScenarioIdAndUsedAndIsNotPortlet",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Double.class.getName()
			});

	/**
	 * Returns all the requests where scenarioId = &#63; and portlet = &#63; and weight &gt; &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param weight the weight
	 * @return the matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByScenarioIdAndUsedAndIsNotPortlet(
		long scenarioId, boolean portlet, double weight)
		throws SystemException {
		return findByScenarioIdAndUsedAndIsNotPortlet(scenarioId, portlet,
			weight, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the requests where scenarioId = &#63; and portlet = &#63; and weight &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.RequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param weight the weight
	 * @param start the lower bound of the range of requests
	 * @param end the upper bound of the range of requests (not inclusive)
	 * @return the range of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByScenarioIdAndUsedAndIsNotPortlet(
		long scenarioId, boolean portlet, double weight, int start, int end)
		throws SystemException {
		return findByScenarioIdAndUsedAndIsNotPortlet(scenarioId, portlet,
			weight, start, end, null);
	}

	/**
	 * Returns an ordered range of all the requests where scenarioId = &#63; and portlet = &#63; and weight &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.RequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param weight the weight
	 * @param start the lower bound of the range of requests
	 * @param end the upper bound of the range of requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findByScenarioIdAndUsedAndIsNotPortlet(
		long scenarioId, boolean portlet, double weight, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCENARIOIDANDUSEDANDISNOTPORTLET;
		finderArgs = new Object[] {
				scenarioId, portlet, weight,
				
				start, end, orderByComparator
			};

		List<Request> list = (List<Request>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Request request : list) {
				if ((scenarioId != request.getScenarioId()) ||
						(portlet != request.getPortlet()) ||
						(weight >= request.getWeight())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_REQUEST_WHERE);

			query.append(_FINDER_COLUMN_SCENARIOIDANDUSEDANDISNOTPORTLET_SCENARIOID_2);

			query.append(_FINDER_COLUMN_SCENARIOIDANDUSEDANDISNOTPORTLET_PORTLET_2);

			query.append(_FINDER_COLUMN_SCENARIOIDANDUSEDANDISNOTPORTLET_WEIGHT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scenarioId);

				qPos.add(portlet);

				qPos.add(weight);

				if (!pagination) {
					list = (List<Request>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Request>(list);
				}
				else {
					list = (List<Request>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first request in the ordered set where scenarioId = &#63; and portlet = &#63; and weight &gt; &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param weight the weight
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request findByScenarioIdAndUsedAndIsNotPortlet_First(
		long scenarioId, boolean portlet, double weight,
		OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = fetchByScenarioIdAndUsedAndIsNotPortlet_First(scenarioId,
				portlet, weight, orderByComparator);

		if (request != null) {
			return request;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scenarioId=");
		msg.append(scenarioId);

		msg.append(", portlet=");
		msg.append(portlet);

		msg.append(", weight=");
		msg.append(weight);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRequestException(msg.toString());
	}

	/**
	 * Returns the first request in the ordered set where scenarioId = &#63; and portlet = &#63; and weight &gt; &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param weight the weight
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching request, or <code>null</code> if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request fetchByScenarioIdAndUsedAndIsNotPortlet_First(
		long scenarioId, boolean portlet, double weight,
		OrderByComparator orderByComparator) throws SystemException {
		List<Request> list = findByScenarioIdAndUsedAndIsNotPortlet(scenarioId,
				portlet, weight, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last request in the ordered set where scenarioId = &#63; and portlet = &#63; and weight &gt; &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param weight the weight
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request findByScenarioIdAndUsedAndIsNotPortlet_Last(
		long scenarioId, boolean portlet, double weight,
		OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = fetchByScenarioIdAndUsedAndIsNotPortlet_Last(scenarioId,
				portlet, weight, orderByComparator);

		if (request != null) {
			return request;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scenarioId=");
		msg.append(scenarioId);

		msg.append(", portlet=");
		msg.append(portlet);

		msg.append(", weight=");
		msg.append(weight);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRequestException(msg.toString());
	}

	/**
	 * Returns the last request in the ordered set where scenarioId = &#63; and portlet = &#63; and weight &gt; &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param weight the weight
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching request, or <code>null</code> if a matching request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request fetchByScenarioIdAndUsedAndIsNotPortlet_Last(
		long scenarioId, boolean portlet, double weight,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByScenarioIdAndUsedAndIsNotPortlet(scenarioId,
				portlet, weight);

		if (count == 0) {
			return null;
		}

		List<Request> list = findByScenarioIdAndUsedAndIsNotPortlet(scenarioId,
				portlet, weight, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the requests before and after the current request in the ordered set where scenarioId = &#63; and portlet = &#63; and weight &gt; &#63;.
	 *
	 * @param requestId the primary key of the current request
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param weight the weight
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request[] findByScenarioIdAndUsedAndIsNotPortlet_PrevAndNext(
		long requestId, long scenarioId, boolean portlet, double weight,
		OrderByComparator orderByComparator)
		throws NoSuchRequestException, SystemException {
		Request request = findByPrimaryKey(requestId);

		Session session = null;

		try {
			session = openSession();

			Request[] array = new RequestImpl[3];

			array[0] = getByScenarioIdAndUsedAndIsNotPortlet_PrevAndNext(session,
					request, scenarioId, portlet, weight, orderByComparator,
					true);

			array[1] = request;

			array[2] = getByScenarioIdAndUsedAndIsNotPortlet_PrevAndNext(session,
					request, scenarioId, portlet, weight, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Request getByScenarioIdAndUsedAndIsNotPortlet_PrevAndNext(
		Session session, Request request, long scenarioId, boolean portlet,
		double weight, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REQUEST_WHERE);

		query.append(_FINDER_COLUMN_SCENARIOIDANDUSEDANDISNOTPORTLET_SCENARIOID_2);

		query.append(_FINDER_COLUMN_SCENARIOIDANDUSEDANDISNOTPORTLET_PORTLET_2);

		query.append(_FINDER_COLUMN_SCENARIOIDANDUSEDANDISNOTPORTLET_WEIGHT_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(RequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(scenarioId);

		qPos.add(portlet);

		qPos.add(weight);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(request);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Request> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the requests where scenarioId = &#63; and portlet = &#63; and weight &gt; &#63; from the database.
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param weight the weight
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByScenarioIdAndUsedAndIsNotPortlet(long scenarioId,
		boolean portlet, double weight) throws SystemException {
		for (Request request : findByScenarioIdAndUsedAndIsNotPortlet(
				scenarioId, portlet, weight, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(request);
		}
	}

	/**
	 * Returns the number of requests where scenarioId = &#63; and portlet = &#63; and weight &gt; &#63;.
	 *
	 * @param scenarioId the scenario ID
	 * @param portlet the portlet
	 * @param weight the weight
	 * @return the number of matching requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByScenarioIdAndUsedAndIsNotPortlet(long scenarioId,
		boolean portlet, double weight) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_SCENARIOIDANDUSEDANDISNOTPORTLET;

		Object[] finderArgs = new Object[] { scenarioId, portlet, weight };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_REQUEST_WHERE);

			query.append(_FINDER_COLUMN_SCENARIOIDANDUSEDANDISNOTPORTLET_SCENARIOID_2);

			query.append(_FINDER_COLUMN_SCENARIOIDANDUSEDANDISNOTPORTLET_PORTLET_2);

			query.append(_FINDER_COLUMN_SCENARIOIDANDUSEDANDISNOTPORTLET_WEIGHT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scenarioId);

				qPos.add(portlet);

				qPos.add(weight);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_SCENARIOIDANDUSEDANDISNOTPORTLET_SCENARIOID_2 =
		"request.scenarioId = ? AND ";
	private static final String _FINDER_COLUMN_SCENARIOIDANDUSEDANDISNOTPORTLET_PORTLET_2 =
		"request.portlet = ? AND ";
	private static final String _FINDER_COLUMN_SCENARIOIDANDUSEDANDISNOTPORTLET_WEIGHT_2 =
		"request.weight > ?";

	public RequestPersistenceImpl() {
		setModelClass(Request.class);
	}

	/**
	 * Caches the request in the entity cache if it is enabled.
	 *
	 * @param request the request
	 */
	@Override
	public void cacheResult(Request request) {
		EntityCacheUtil.putResult(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestImpl.class, request.getPrimaryKey(), request);

		request.resetOriginalValues();
	}

	/**
	 * Caches the requests in the entity cache if it is enabled.
	 *
	 * @param requests the requests
	 */
	@Override
	public void cacheResult(List<Request> requests) {
		for (Request request : requests) {
			if (EntityCacheUtil.getResult(
						RequestModelImpl.ENTITY_CACHE_ENABLED,
						RequestImpl.class, request.getPrimaryKey()) == null) {
				cacheResult(request);
			}
			else {
				request.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all requests.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(RequestImpl.class.getName());
		}

		EntityCacheUtil.clearCache(RequestImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the request.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Request request) {
		EntityCacheUtil.removeResult(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestImpl.class, request.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Request> requests) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Request request : requests) {
			EntityCacheUtil.removeResult(RequestModelImpl.ENTITY_CACHE_ENABLED,
				RequestImpl.class, request.getPrimaryKey());
		}
	}

	/**
	 * Creates a new request with the primary key. Does not add the request to the database.
	 *
	 * @param requestId the primary key for the new request
	 * @return the new request
	 */
	@Override
	public Request create(long requestId) {
		Request request = new RequestImpl();

		request.setNew(true);
		request.setPrimaryKey(requestId);

		return request;
	}

	/**
	 * Removes the request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param requestId the primary key of the request
	 * @return the request that was removed
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request remove(long requestId)
		throws NoSuchRequestException, SystemException {
		return remove((Serializable)requestId);
	}

	/**
	 * Removes the request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the request
	 * @return the request that was removed
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request remove(Serializable primaryKey)
		throws NoSuchRequestException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Request request = (Request)session.get(RequestImpl.class, primaryKey);

			if (request == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(request);
		}
		catch (NoSuchRequestException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Request removeImpl(Request request) throws SystemException {
		request = toUnwrappedModel(request);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(request)) {
				request = (Request)session.get(RequestImpl.class,
						request.getPrimaryKeyObj());
			}

			if (request != null) {
				session.delete(request);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (request != null) {
			clearCache(request);
		}

		return request;
	}

	@Override
	public Request updateImpl(com.excilys.liferay.gatling.model.Request request)
		throws SystemException {
		request = toUnwrappedModel(request);

		boolean isNew = request.isNew();

		RequestModelImpl requestModelImpl = (RequestModelImpl)request;

		Session session = null;

		try {
			session = openSession();

			if (request.isNew()) {
				session.save(request);

				request.setNew(false);
			}
			else {
				session.merge(request);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !RequestModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((requestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPLID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						requestModelImpl.getOriginalParentPlId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTPLID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPLID,
					args);

				args = new Object[] { requestModelImpl.getParentPlId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTPLID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPLID,
					args);
			}

			if ((requestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPLIDANDSCENARIO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						requestModelImpl.getOriginalParentPlId(),
						requestModelImpl.getOriginalScenarioId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTPLIDANDSCENARIO,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPLIDANDSCENARIO,
					args);

				args = new Object[] {
						requestModelImpl.getParentPlId(),
						requestModelImpl.getScenarioId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTPLIDANDSCENARIO,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPLIDANDSCENARIO,
					args);
			}

			if ((requestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCENARIOID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						requestModelImpl.getOriginalScenarioId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCENARIOID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCENARIOID,
					args);

				args = new Object[] { requestModelImpl.getScenarioId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCENARIOID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCENARIOID,
					args);
			}

			if ((requestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCENARIOIDANDISNOTPORTLET.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						requestModelImpl.getOriginalScenarioId(),
						requestModelImpl.getOriginalPortlet()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCENARIOIDANDISNOTPORTLET,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCENARIOIDANDISNOTPORTLET,
					args);

				args = new Object[] {
						requestModelImpl.getScenarioId(),
						requestModelImpl.getPortlet()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCENARIOIDANDISNOTPORTLET,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCENARIOIDANDISNOTPORTLET,
					args);
			}
		}

		EntityCacheUtil.putResult(RequestModelImpl.ENTITY_CACHE_ENABLED,
			RequestImpl.class, request.getPrimaryKey(), request);

		return request;
	}

	protected Request toUnwrappedModel(Request request) {
		if (request instanceof RequestImpl) {
			return request;
		}

		RequestImpl requestImpl = new RequestImpl();

		requestImpl.setNew(request.isNew());
		requestImpl.setPrimaryKey(request.getPrimaryKey());

		requestImpl.setRequestId(request.getRequestId());
		requestImpl.setScenarioId(request.getScenarioId());
		requestImpl.setName(request.getName());
		requestImpl.setUrl(request.getUrl());
		requestImpl.setWeight(request.getWeight());
		requestImpl.setPrivatePage(request.isPrivatePage());
		requestImpl.setParentPlId(request.getParentPlId());
		requestImpl.setLayoutId(request.getLayoutId());
		requestImpl.setPlId(request.getPlId());
		requestImpl.setPortlet(request.isPortlet());
		requestImpl.setPortetId(request.getPortetId());

		return requestImpl;
	}

	/**
	 * Returns the request with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the request
	 * @return the request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRequestException, SystemException {
		Request request = fetchByPrimaryKey(primaryKey);

		if (request == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return request;
	}

	/**
	 * Returns the request with the primary key or throws a {@link com.excilys.liferay.gatling.NoSuchRequestException} if it could not be found.
	 *
	 * @param requestId the primary key of the request
	 * @return the request
	 * @throws com.excilys.liferay.gatling.NoSuchRequestException if a request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request findByPrimaryKey(long requestId)
		throws NoSuchRequestException, SystemException {
		return findByPrimaryKey((Serializable)requestId);
	}

	/**
	 * Returns the request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the request
	 * @return the request, or <code>null</code> if a request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Request request = (Request)EntityCacheUtil.getResult(RequestModelImpl.ENTITY_CACHE_ENABLED,
				RequestImpl.class, primaryKey);

		if (request == _nullRequest) {
			return null;
		}

		if (request == null) {
			Session session = null;

			try {
				session = openSession();

				request = (Request)session.get(RequestImpl.class, primaryKey);

				if (request != null) {
					cacheResult(request);
				}
				else {
					EntityCacheUtil.putResult(RequestModelImpl.ENTITY_CACHE_ENABLED,
						RequestImpl.class, primaryKey, _nullRequest);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(RequestModelImpl.ENTITY_CACHE_ENABLED,
					RequestImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return request;
	}

	/**
	 * Returns the request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param requestId the primary key of the request
	 * @return the request, or <code>null</code> if a request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Request fetchByPrimaryKey(long requestId) throws SystemException {
		return fetchByPrimaryKey((Serializable)requestId);
	}

	/**
	 * Returns all the requests.
	 *
	 * @return the requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.RequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of requests
	 * @param end the upper bound of the range of requests (not inclusive)
	 * @return the range of requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.RequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of requests
	 * @param end the upper bound of the range of requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Request> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Request> list = (List<Request>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_REQUEST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REQUEST;

				if (pagination) {
					sql = sql.concat(RequestModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Request>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Request>(list);
				}
				else {
					list = (List<Request>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the requests from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Request request : findAll()) {
			remove(request);
		}
	}

	/**
	 * Returns the number of requests.
	 *
	 * @return the number of requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REQUEST);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the request persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.excilys.liferay.gatling.model.Request")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Request>> listenersList = new ArrayList<ModelListener<Request>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Request>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(RequestImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_REQUEST = "SELECT request FROM Request request";
	private static final String _SQL_SELECT_REQUEST_WHERE = "SELECT request FROM Request request WHERE ";
	private static final String _SQL_COUNT_REQUEST = "SELECT COUNT(request) FROM Request request";
	private static final String _SQL_COUNT_REQUEST_WHERE = "SELECT COUNT(request) FROM Request request WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "request.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Request exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Request exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(RequestPersistenceImpl.class);
	private static Request _nullRequest = new RequestImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Request> toCacheModel() {
				return _nullRequestCacheModel;
			}
		};

	private static CacheModel<Request> _nullRequestCacheModel = new CacheModel<Request>() {
			@Override
			public Request toEntityModel() {
				return _nullRequest;
			}
		};
}