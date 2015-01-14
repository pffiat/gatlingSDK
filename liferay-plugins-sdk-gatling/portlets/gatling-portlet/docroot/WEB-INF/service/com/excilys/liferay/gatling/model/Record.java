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

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the Record service. Represents a row in the &quot;GatlingTool_Record&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see RecordModel
 * @see com.excilys.liferay.gatling.model.impl.RecordImpl
 * @see com.excilys.liferay.gatling.model.impl.RecordModelImpl
 * @generated
 */
public interface Record extends RecordModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.excilys.liferay.gatling.model.impl.RecordImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
}