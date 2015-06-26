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

import java.util.ArrayList;
import java.util.List;

import com.jiesoft.mitrac.dao.AbstractDao;
import com.jiesoft.mitrac.dao.DeviceDao;
import com.jiesoft.mitrac.domain.bo.Device;

/**
 * @author Ray Shi
 */

public class DeviceDaoImpl extends AbstractDao implements DeviceDao {

	public DeviceDaoImpl() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> findDevicesByGroupIds(final List<String> deviceGroupIds, final String accountId) {
		final List<String> deviceIds = sessionFactory.getCurrentSession()
				.createQuery("SELECT id.deviceId FROM DeviceList WHERE id.groupId in (:deviceGroupIds) AND id.accountId = :accountId")
				.setParameterList("deviceGroupIds", deviceGroupIds)
				.setParameter("accountId", accountId)
				.list();
		
		if (deviceIds.isEmpty()) {
			return new ArrayList<Device>();
		}
		
		return sessionFactory.getCurrentSession()
				.createQuery("FROM Device WHERE id.deviceId IN (:deviceIds) And id.accountId = :accountId")
				.setParameterList("deviceIds", deviceIds)
				.setParameter("accountId", accountId)
				.list();
	}

}
