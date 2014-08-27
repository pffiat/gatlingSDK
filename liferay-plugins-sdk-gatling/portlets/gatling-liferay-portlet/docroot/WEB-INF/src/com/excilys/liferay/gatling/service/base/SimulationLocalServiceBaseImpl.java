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

package com.excilys.liferay.gatling.service.base;

import com.excilys.liferay.gatling.model.Simulation;
import com.excilys.liferay.gatling.service.SimulationLocalService;
import com.excilys.liferay.gatling.service.persistence.LinkUsecaseRequestPersistence;
import com.excilys.liferay.gatling.service.persistence.RecordPortletPersistence;
import com.excilys.liferay.gatling.service.persistence.RequestPersistence;
import com.excilys.liferay.gatling.service.persistence.ScenarioPersistence;
import com.excilys.liferay.gatling.service.persistence.SimulationPersistence;
import com.excilys.liferay.gatling.service.persistence.UrlUsecasePersistence;
import com.excilys.liferay.gatling.service.persistence.UsecasePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the simulation local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.excilys.liferay.gatling.service.impl.SimulationLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.excilys.liferay.gatling.service.impl.SimulationLocalServiceImpl
 * @see com.excilys.liferay.gatling.service.SimulationLocalServiceUtil
 * @generated
 */
public abstract class SimulationLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements SimulationLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.excilys.liferay.gatling.service.SimulationLocalServiceUtil} to access the simulation local service.
	 */

	/**
	 * Adds the simulation to the database. Also notifies the appropriate model listeners.
	 *
	 * @param simulation the simulation
	 * @return the simulation that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Simulation addSimulation(Simulation simulation)
		throws SystemException {
		simulation.setNew(true);

		return simulationPersistence.update(simulation);
	}

	/**
	 * Creates a new simulation with the primary key. Does not add the simulation to the database.
	 *
	 * @param simulation_id the primary key for the new simulation
	 * @return the new simulation
	 */
	@Override
	public Simulation createSimulation(long simulation_id) {
		return simulationPersistence.create(simulation_id);
	}

	/**
	 * Deletes the simulation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param simulation_id the primary key of the simulation
	 * @return the simulation that was removed
	 * @throws PortalException if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Simulation deleteSimulation(long simulation_id)
		throws PortalException, SystemException {
		return simulationPersistence.remove(simulation_id);
	}

	/**
	 * Deletes the simulation from the database. Also notifies the appropriate model listeners.
	 *
	 * @param simulation the simulation
	 * @return the simulation that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Simulation deleteSimulation(Simulation simulation)
		throws SystemException {
		return simulationPersistence.remove(simulation);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Simulation.class,
			clazz.getClassLoader());
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
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return simulationPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return simulationPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return simulationPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return simulationPersistence.countWithDynamicQuery(dynamicQuery);
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
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return simulationPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public Simulation fetchSimulation(long simulation_id)
		throws SystemException {
		return simulationPersistence.fetchByPrimaryKey(simulation_id);
	}

	/**
	 * Returns the simulation with the primary key.
	 *
	 * @param simulation_id the primary key of the simulation
	 * @return the simulation
	 * @throws PortalException if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation getSimulation(long simulation_id)
		throws PortalException, SystemException {
		return simulationPersistence.findByPrimaryKey(simulation_id);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return simulationPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the simulations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.excilys.liferay.gatling.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @return the range of simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> getSimulations(int start, int end)
		throws SystemException {
		return simulationPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of simulations.
	 *
	 * @return the number of simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getSimulationsCount() throws SystemException {
		return simulationPersistence.countAll();
	}

	/**
	 * Updates the simulation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param simulation the simulation
	 * @return the simulation that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Simulation updateSimulation(Simulation simulation)
		throws SystemException {
		return simulationPersistence.update(simulation);
	}

	/**
	 * Returns the link usecase request local service.
	 *
	 * @return the link usecase request local service
	 */
	public com.excilys.liferay.gatling.service.LinkUsecaseRequestLocalService getLinkUsecaseRequestLocalService() {
		return linkUsecaseRequestLocalService;
	}

	/**
	 * Sets the link usecase request local service.
	 *
	 * @param linkUsecaseRequestLocalService the link usecase request local service
	 */
	public void setLinkUsecaseRequestLocalService(
		com.excilys.liferay.gatling.service.LinkUsecaseRequestLocalService linkUsecaseRequestLocalService) {
		this.linkUsecaseRequestLocalService = linkUsecaseRequestLocalService;
	}

	/**
	 * Returns the link usecase request persistence.
	 *
	 * @return the link usecase request persistence
	 */
	public LinkUsecaseRequestPersistence getLinkUsecaseRequestPersistence() {
		return linkUsecaseRequestPersistence;
	}

	/**
	 * Sets the link usecase request persistence.
	 *
	 * @param linkUsecaseRequestPersistence the link usecase request persistence
	 */
	public void setLinkUsecaseRequestPersistence(
		LinkUsecaseRequestPersistence linkUsecaseRequestPersistence) {
		this.linkUsecaseRequestPersistence = linkUsecaseRequestPersistence;
	}

	/**
	 * Returns the record portlet remote service.
	 *
	 * @return the record portlet remote service
	 */
	public com.excilys.liferay.gatling.service.RecordPortletService getRecordPortletService() {
		return recordPortletService;
	}

	/**
	 * Sets the record portlet remote service.
	 *
	 * @param recordPortletService the record portlet remote service
	 */
	public void setRecordPortletService(
		com.excilys.liferay.gatling.service.RecordPortletService recordPortletService) {
		this.recordPortletService = recordPortletService;
	}

	/**
	 * Returns the record portlet persistence.
	 *
	 * @return the record portlet persistence
	 */
	public RecordPortletPersistence getRecordPortletPersistence() {
		return recordPortletPersistence;
	}

	/**
	 * Sets the record portlet persistence.
	 *
	 * @param recordPortletPersistence the record portlet persistence
	 */
	public void setRecordPortletPersistence(
		RecordPortletPersistence recordPortletPersistence) {
		this.recordPortletPersistence = recordPortletPersistence;
	}

	/**
	 * Returns the request local service.
	 *
	 * @return the request local service
	 */
	public com.excilys.liferay.gatling.service.RequestLocalService getRequestLocalService() {
		return requestLocalService;
	}

	/**
	 * Sets the request local service.
	 *
	 * @param requestLocalService the request local service
	 */
	public void setRequestLocalService(
		com.excilys.liferay.gatling.service.RequestLocalService requestLocalService) {
		this.requestLocalService = requestLocalService;
	}

	/**
	 * Returns the request persistence.
	 *
	 * @return the request persistence
	 */
	public RequestPersistence getRequestPersistence() {
		return requestPersistence;
	}

	/**
	 * Sets the request persistence.
	 *
	 * @param requestPersistence the request persistence
	 */
	public void setRequestPersistence(RequestPersistence requestPersistence) {
		this.requestPersistence = requestPersistence;
	}

	/**
	 * Returns the scenario local service.
	 *
	 * @return the scenario local service
	 */
	public com.excilys.liferay.gatling.service.ScenarioLocalService getScenarioLocalService() {
		return scenarioLocalService;
	}

	/**
	 * Sets the scenario local service.
	 *
	 * @param scenarioLocalService the scenario local service
	 */
	public void setScenarioLocalService(
		com.excilys.liferay.gatling.service.ScenarioLocalService scenarioLocalService) {
		this.scenarioLocalService = scenarioLocalService;
	}

	/**
	 * Returns the scenario persistence.
	 *
	 * @return the scenario persistence
	 */
	public ScenarioPersistence getScenarioPersistence() {
		return scenarioPersistence;
	}

	/**
	 * Sets the scenario persistence.
	 *
	 * @param scenarioPersistence the scenario persistence
	 */
	public void setScenarioPersistence(ScenarioPersistence scenarioPersistence) {
		this.scenarioPersistence = scenarioPersistence;
	}

	/**
	 * Returns the simulation local service.
	 *
	 * @return the simulation local service
	 */
	public com.excilys.liferay.gatling.service.SimulationLocalService getSimulationLocalService() {
		return simulationLocalService;
	}

	/**
	 * Sets the simulation local service.
	 *
	 * @param simulationLocalService the simulation local service
	 */
	public void setSimulationLocalService(
		com.excilys.liferay.gatling.service.SimulationLocalService simulationLocalService) {
		this.simulationLocalService = simulationLocalService;
	}

	/**
	 * Returns the simulation persistence.
	 *
	 * @return the simulation persistence
	 */
	public SimulationPersistence getSimulationPersistence() {
		return simulationPersistence;
	}

	/**
	 * Sets the simulation persistence.
	 *
	 * @param simulationPersistence the simulation persistence
	 */
	public void setSimulationPersistence(
		SimulationPersistence simulationPersistence) {
		this.simulationPersistence = simulationPersistence;
	}

	/**
	 * Returns the url usecase local service.
	 *
	 * @return the url usecase local service
	 */
	public com.excilys.liferay.gatling.service.UrlUsecaseLocalService getUrlUsecaseLocalService() {
		return urlUsecaseLocalService;
	}

	/**
	 * Sets the url usecase local service.
	 *
	 * @param urlUsecaseLocalService the url usecase local service
	 */
	public void setUrlUsecaseLocalService(
		com.excilys.liferay.gatling.service.UrlUsecaseLocalService urlUsecaseLocalService) {
		this.urlUsecaseLocalService = urlUsecaseLocalService;
	}

	/**
	 * Returns the url usecase persistence.
	 *
	 * @return the url usecase persistence
	 */
	public UrlUsecasePersistence getUrlUsecasePersistence() {
		return urlUsecasePersistence;
	}

	/**
	 * Sets the url usecase persistence.
	 *
	 * @param urlUsecasePersistence the url usecase persistence
	 */
	public void setUrlUsecasePersistence(
		UrlUsecasePersistence urlUsecasePersistence) {
		this.urlUsecasePersistence = urlUsecasePersistence;
	}

	/**
	 * Returns the usecase local service.
	 *
	 * @return the usecase local service
	 */
	public com.excilys.liferay.gatling.service.UsecaseLocalService getUsecaseLocalService() {
		return usecaseLocalService;
	}

	/**
	 * Sets the usecase local service.
	 *
	 * @param usecaseLocalService the usecase local service
	 */
	public void setUsecaseLocalService(
		com.excilys.liferay.gatling.service.UsecaseLocalService usecaseLocalService) {
		this.usecaseLocalService = usecaseLocalService;
	}

	/**
	 * Returns the usecase persistence.
	 *
	 * @return the usecase persistence
	 */
	public UsecasePersistence getUsecasePersistence() {
		return usecasePersistence;
	}

	/**
	 * Sets the usecase persistence.
	 *
	 * @param usecasePersistence the usecase persistence
	 */
	public void setUsecasePersistence(UsecasePersistence usecasePersistence) {
		this.usecasePersistence = usecasePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.excilys.liferay.gatling.model.Simulation",
			simulationLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.excilys.liferay.gatling.model.Simulation");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return Simulation.class;
	}

	protected String getModelClassName() {
		return Simulation.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = simulationPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.excilys.liferay.gatling.service.LinkUsecaseRequestLocalService.class)
	protected com.excilys.liferay.gatling.service.LinkUsecaseRequestLocalService linkUsecaseRequestLocalService;
	@BeanReference(type = LinkUsecaseRequestPersistence.class)
	protected LinkUsecaseRequestPersistence linkUsecaseRequestPersistence;
	@BeanReference(type = com.excilys.liferay.gatling.service.RecordPortletService.class)
	protected com.excilys.liferay.gatling.service.RecordPortletService recordPortletService;
	@BeanReference(type = RecordPortletPersistence.class)
	protected RecordPortletPersistence recordPortletPersistence;
	@BeanReference(type = com.excilys.liferay.gatling.service.RequestLocalService.class)
	protected com.excilys.liferay.gatling.service.RequestLocalService requestLocalService;
	@BeanReference(type = RequestPersistence.class)
	protected RequestPersistence requestPersistence;
	@BeanReference(type = com.excilys.liferay.gatling.service.ScenarioLocalService.class)
	protected com.excilys.liferay.gatling.service.ScenarioLocalService scenarioLocalService;
	@BeanReference(type = ScenarioPersistence.class)
	protected ScenarioPersistence scenarioPersistence;
	@BeanReference(type = com.excilys.liferay.gatling.service.SimulationLocalService.class)
	protected com.excilys.liferay.gatling.service.SimulationLocalService simulationLocalService;
	@BeanReference(type = SimulationPersistence.class)
	protected SimulationPersistence simulationPersistence;
	@BeanReference(type = com.excilys.liferay.gatling.service.UrlUsecaseLocalService.class)
	protected com.excilys.liferay.gatling.service.UrlUsecaseLocalService urlUsecaseLocalService;
	@BeanReference(type = UrlUsecasePersistence.class)
	protected UrlUsecasePersistence urlUsecasePersistence;
	@BeanReference(type = com.excilys.liferay.gatling.service.UsecaseLocalService.class)
	protected com.excilys.liferay.gatling.service.UsecaseLocalService usecaseLocalService;
	@BeanReference(type = UsecasePersistence.class)
	protected UsecasePersistence usecasePersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private SimulationLocalServiceClpInvoker _clpInvoker = new SimulationLocalServiceClpInvoker();
}