/*
 * Copyright 2015 Jiesoft Consulting.
 * All Rights Reserved.
 *
 * All information contained herein is, and remains the property
 * of Jiesoft Consulting. The intellectual and technical concepts 
 * contained herein are proprietary to Jiesoft and are protected by 
 * trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless 
 * prior written permission is obtained from Jiesoft Consulting.
 *
 *      http://www.jiesoft.com
 */

package com.jiesoft.mitrac.dao.impl;

import java.util.List;

import com.jiesoft.mitrac.dao.AbstractDao;
import com.jiesoft.mitrac.dao.DeviceGroupDao;
import com.jiesoft.mitrac.domain.bo.DeviceGroup;

/**
 * @author Ray Shi
 */

public class DeviceGroupDaoImpl extends AbstractDao implements DeviceGroupDao {

	public DeviceGroupDaoImpl() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeviceGroup> findDeviceGroupsByAccountName(final String accountName) {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM DeviceGroup WHERE id.accountId = :accountId")
				.setParameter("accountId", accountName)
				.list();
	}

}
