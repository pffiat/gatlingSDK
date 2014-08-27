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

import com.excilys.liferay.gatling.model.UrlUsecase;
import com.excilys.liferay.gatling.service.UrlUsecaseLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the UrlUsecase service. Represents a row in the &quot;StressTool_UrlUsecase&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UrlUsecaseImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UrlUsecaseImpl
 * @see com.excilys.liferay.gatling.model.UrlUsecase
 * @generated
 */
public abstract class UrlUsecaseBaseImpl extends UrlUsecaseModelImpl
	implements UrlUsecase {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a url usecase model instance should use the {@link UrlUsecase} interface instead.
	 */
	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			UrlUsecaseLocalServiceUtil.addUrlUsecase(this);
		}
		else {
			UrlUsecaseLocalServiceUtil.updateUrlUsecase(this);
		}
	}
}